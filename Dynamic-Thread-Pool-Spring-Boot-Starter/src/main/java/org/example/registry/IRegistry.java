package org.example.registry;

import org.example.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

/**
 * 数据上报注册中心
 */
public interface IRegistry {

    // 线程池上报
    void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolConfigEntities);

    // 线程池参数上报
    void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity);

}
