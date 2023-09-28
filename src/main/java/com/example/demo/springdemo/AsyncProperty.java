package com.example.demo.springdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hypc.service.async-task-processing-thread-pool-executor")
@Component
public class AsyncProperty {
    private int corePoolSize;
    private int maximumPoolSize;
    private int keepAliveTimeInSeconds;
    private int queueCapacity;

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public int getKeepAliveTimeInSeconds() {
        return keepAliveTimeInSeconds;
    }

    public void setKeepAliveTimeInSeconds(int keepAliveTimeInSeconds) {
        this.keepAliveTimeInSeconds = keepAliveTimeInSeconds;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}
