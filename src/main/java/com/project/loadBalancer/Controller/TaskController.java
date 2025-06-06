package com.project.loadBalancer.Controller;

import com.project.loadBalancer.Services.MetericsSchedulerService;
import com.project.loadBalancer.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private MetericsSchedulerService metricsSchedulerService;

    @PostMapping("/run")
    public ResponseEntity<String> runBasedOnLoad(){
        double load = metricsSchedulerService.getLatestCpuLoad();
        double usage= metricsSchedulerService.getLatestMemoryAvailable();

        if(load>=80 || usage>=80 ){
            taskService.runAsyncTask();
            return ResponseEntity.ok("Task ran synchronously.");
        }
        else {
            taskService.runTask();
            return ResponseEntity.ok("Task ran asynchronously.");
        }
    }
}
