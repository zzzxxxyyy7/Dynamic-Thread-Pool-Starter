package org.example.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ThreadPoolConfigEntity that = (ThreadPoolConfigEntity) obj;
        return Objects.equals(threadPoolName, that.threadPoolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(threadPoolName);
    }

}
