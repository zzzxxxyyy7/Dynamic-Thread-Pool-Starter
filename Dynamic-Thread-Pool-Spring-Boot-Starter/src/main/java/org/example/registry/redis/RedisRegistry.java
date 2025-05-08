package org.example.registry.redis;

import org.example.domain.model.entity.ThreadPoolConfigEntity;
import org.example.domain.model.valobj.RegistryEnumVO;
import org.example.registry.IRegistry;
import org.redisson.api.RBucket;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;

import java.time.Duration;
import java.util.List;


public class RedisRegistry implements IRegistry {

    private final RedissonClient redissonClient;

    public RedisRegistry(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    // 池化配置列表
    @Override
    public void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolConfigEntities) {
        RSet<ThreadPoolConfigEntity> set = redissonClient.getSet(RegistryEnumVO.THREAD_POOL_CONFIG_LIST_KEY.getKey());
        set.delete();
        set.addAll(threadPoolConfigEntities);
    }

    // 监听线程池配置参数
    @Override
    public void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity) {
        String cacheKey = RegistryEnumVO.THREAD_POOL_CONFIG_PARAMETER_LIST_KEY.getKey() + "_" + threadPoolConfigEntity.getAppName() + "_" + threadPoolConfigEntity.getThreadPoolName();
        RBucket<ThreadPoolConfigEntity> bucket = redissonClient.getBucket(cacheKey);
        bucket.set(threadPoolConfigEntity, Duration.ofDays(7));
    }

}
