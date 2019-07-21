package com.up.platform.config;

import com.up.platform.manager.UpdateDBQuartTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail UpdateDBJob() {
        return JobBuilder.newJob(UpdateDBQuartTask.class).withIdentity("UpdateDBJob").storeDurably().build();
    }

    @Bean
    public Trigger UpdateDBTrigger() {
        return TriggerBuilder.newTrigger().forJob(UpdateDBJob())
                .withIdentity("UpdateDBJob")
                .withSchedule(CronScheduleBuilder.cronSchedule(("*/5 * * * * ?")))
                .build();
    }

}
