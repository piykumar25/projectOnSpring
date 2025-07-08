package com.codeWithPiyush.SpringBatchApp.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;
    @GetMapping("/customers")
    public void loadCsvToDatabase() throws Exception {
        long startAt = System.currentTimeMillis();
        JobParameters jobParameters = new JobParametersBuilder().addLong("Start-At", startAt).toJobParameters();
        jobLauncher.run(job, jobParameters);
    }
}
