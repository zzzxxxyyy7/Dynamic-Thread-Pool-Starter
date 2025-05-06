package org.example.domain;

import org.example.domain.model.entity.ThreadPoolConfigEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class DynamicThreadPoolServiceImpl implements IDynamicThreadPoolService {

    private final Logger logger = LoggerFactory.getLogger(DynamicThreadPoolServiceImpl.class);

    private final String applicationName;

    private final Map<String, ThreadPoolExecutor> threadPoolExecutorMap;

    public DynamicThreadPoolServiceImpl(String applicationName, Map<String, ThreadPoolExecutor> threadPoolExecutorMap) {
        this.applicationName = applicationName;
        this.threadPoolExecutorMap = threadPoolExecutorMap;
    }

    @Override
    public List<ThreadPoolConfigEntity> queryThreadPoolList() {
        Set<String> threadPoolKeys = threadPoolExecutorMap.keySet();
        List<ThreadPoolConfigEntity> threadPoolConfigEntities = new ArrayList<>(threadPoolKeys.size());
        threadPoolKeys.forEach(threadPoolName -> {
            ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(threadPoolName);
            ThreadPoolConfigEntity threadPoolConfigEntity = createThreadPoolConfigEntity(threadPoolExecutor, threadPoolName);
            threadPoolConfigEntities.add(threadPoolConfigEntity);
        });
        return threadPoolConfigEntities;
    }

    @Override
    public ThreadPoolConfigEntity queryThreadPoolConfigByName(String threadPoolName) {
        ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(threadPoolName);
        if (threadPoolExecutor == null) return new ThreadPoolConfigEntity(applicationName, threadPoolName);
        ThreadPoolConfigEntity threadPoolConfigEntity = createThreadPoolConfigEntity(threadPoolExecutor, threadPoolName);

        if (logger.isDebugEnabled()) {
            logger.debug("动态线程池，查询配置 应用名：{} 线程名: {} 池化配置: {}", applicationName, threadPoolName, threadPoolConfigEntity);
        }

        return threadPoolConfigEntity;
    }

    @Override
    public void updateThreadPoolConfig(ThreadPoolConfigEntity threadPoolConfigEntity) {
        if (null == threadPoolConfigEntity || !applicationName.equals(threadPoolConfigEntity.getAppName())) return;
        ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(threadPoolConfigEntity.getThreadPoolName());
        if (null == threadPoolExecutor) return;

        // 设置参数「核心线程数、最大线程数」
        threadPoolExecutor.setCorePoolSize(threadPoolConfigEntity.getCorePoolSize());
        threadPoolExecutor.setMaximumPoolSize(threadPoolConfigEntity.getMaximumPoolSize());
    }

    private ThreadPoolConfigEntity createThreadPoolConfigEntity(ThreadPoolExecutor threadPoolExecutor, String threadPoolName) {
        ThreadPoolConfigEntity threadPoolConfigEntity = new ThreadPoolConfigEntity(applicationName, threadPoolName);
        threadPoolConfigEntity.setCorePoolSize(threadPoolExecutor.getCorePoolSize());
        threadPoolConfigEntity.setMaximumPoolSize(threadPoolExecutor.getMaximumPoolSize());
        threadPoolConfigEntity.setActiveCount(threadPoolExecutor.getActiveCount());
        threadPoolConfigEntity.setPoolSize(threadPoolExecutor.getPoolSize());
        BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
        threadPoolConfigEntity.setQueueType(queue.getClass().getName());
        threadPoolConfigEntity.setQueueSize(queue.size());
        threadPoolConfigEntity.setRemainingCapacity(queue.remainingCapacity());
        return threadPoolConfigEntity;
    }

}
