package com.project.loadBalancer.Controller;

import com.project.loadBalancer.Services.MetericsSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/metrics")
public class CachedMetricsController {

    private final MetericsSchedulerService metericsSchedulerService;


    public CachedMetricsController(MetericsSchedulerService service) {
        this.metericsSchedulerService = service;
    }

    @GetMapping("/latest")
    public Map<String, Object> getLatestMetrics() {

        return Map.of(
                "cpuLoad", metericsSchedulerService.getLatestCpuLoad(),
                "memoryAvailable", metericsSchedulerService.getLatestMemoryAvailable()
        );
    }
}
