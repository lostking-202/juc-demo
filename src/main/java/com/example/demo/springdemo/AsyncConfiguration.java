package com.example.demo.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@ComponentScan
public class AsyncConfiguration {
    @Bean("applicationTaskExecutor")
    public TaskExecutor asyncTaskProcessingThreadPoolExecutor(AsyncProperty property) {

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(property.getCorePoolSize());
        taskExecutor.setMaxPoolSize(property.getMaximumPoolSize());
        taskExecutor.setQueueCapacity(property.getQueueCapacity());
        taskExecutor.setKeepAliveSeconds(property.getKeepAliveTimeInSeconds());
        taskExecutor.setAllowCoreThreadTimeOut(true);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(15 * 60); // wait 15 minutes
        taskExecutor.setThreadNamePrefix("asyncTask-");
        taskExecutor.setTaskDecorator(runnable -> runnable);

        return taskExecutor;
    }
}
