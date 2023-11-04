package com.aquariux.crypto.scheduling;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@ComponentScan("com.aquariux.crypto.scheduling")
@EnableScheduling
public class PriceAggregationScheduling implements SchedulingConfigurer {

    @Autowired
    private TestService testService;

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addTriggerTask(
            () -> testService.tick(),
            context -> {
                Optional<Date> lastCompletionTime = Optional.ofNullable(context.lastCompletionTime());

                Instant nextExecutionTime = lastCompletionTime.orElseGet(Date::new).toInstant().plusMillis(testService.getDelay());
                return Date.from(nextExecutionTime);
            }
        );
    }
}