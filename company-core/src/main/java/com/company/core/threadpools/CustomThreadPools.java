package com.company.core.threadpools;

import com.company.core.util.ApplicationPropertyEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author mukulbansal
 */
@Configuration
public class CustomThreadPools {

    @Bean(name = "generalUseThreadPool")
    public TaskExecutor generalUseThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Integer.parseInt(ApplicationPropertyEnum.GENERAL_USE_THREAD_POOL_MIN_THREADS.getValue()));
        executor.setMaxPoolSize(Integer.parseInt(ApplicationPropertyEnum.GENERAL_USE_THREAD_POOL_MAX_THREADS.getValue()));
        executor.initialize();
        return executor;
    }
}
