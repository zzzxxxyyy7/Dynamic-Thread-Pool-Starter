package org.example.config;

import com.alibaba.fastjson.JSON;
import jodd.util.StringUtil;
import org.example.domain.DynamicThreadPoolServiceImpl;
import org.example.domain.IDynamicThreadPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 动态配置入口
 */
@Configuration
public class DynamicThreadPoolAutoConfig {

    private final Logger logger = LoggerFactory.getLogger(DynamicThreadPoolAutoConfig.class);

    @Bean("dynamicThreadPollService")
    public DynamicThreadPoolServiceImpl DynamicThreadPollService(ApplicationContext applicationContext, Map<String, ThreadPoolExecutor> threadPoolExecutorMap) {
        String applicationName = applicationContext.getEnvironment().getProperty("spring.application.name");

        if (StringUtil.isBlank(applicationName)) {
            applicationName = "缺省的";
            logger.warn("动态线程池，启动提示。SpringBoot 应用未配置 spring.application.name 无法获取到应用名称！");
        }

        logger.info("线程池信息：{}", JSON.toJSONString(threadPoolExecutorMap.keySet()));

        return new DynamicThreadPoolServiceImpl(applicationName, threadPoolExecutorMap);
    }

}
