package org.example.domain;

import org.example.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

/**
 * 动态线程池服务
 */
public interface IDynamicThreadPoolService {

    // 查询线程池列表
    List<ThreadPoolConfigEntity> queryThreadPoolList();

    // 根据名称查询线程池配置
    ThreadPoolConfigEntity queryThreadPoolConfigByName(String threadPoolName);

    // 更新线程池配置
    void updateThreadPoolConfig(ThreadPoolConfigEntity threadPoolConfigEntity);

}
