package org.example.domain.model.entity;

import lombok.Getter;
import lombok.Setter;


/**
 * 线程池配置信息实体对象
 */
@Getter
public class ThreadPoolConfigEntity {

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 线程池名称
     */
    private String threadPoolName;

    /**
     * 核心线程数
     */
    @Setter
    private int corePoolSize;

    /**
     * 最大线程数
     */
    @Setter
    private int maximumPoolSize;

    /**
     * 当前活跃线程数
     */
    @Setter
    private int activeCount;

    /**
     * 当前池中线程数
     */
    @Setter
    private int poolSize;

    /**
     * 队列类型
     */
    @Setter
    private String queueType;

    /**
     * 当前队列任务数
     */
    @Setter
    private int queueSize;

    /**
     * 队列剩余任务数
     */
    @Setter
    private int remainingCapacity;

    public ThreadPoolConfigEntity() {
    }

    public ThreadPoolConfigEntity(String appName, String threadPoolName) {
        this.appName = appName;
        this.threadPoolName = threadPoolName;
    }

}
