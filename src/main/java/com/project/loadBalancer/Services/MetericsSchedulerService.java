package com.project.loadBalancer.Services;

import com.project.loadBalancer.Controller.SystemMetericsController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MetericsSchedulerService {

    private final SystemMetericsController systemMetericsController;

    // Cached latest metrics
    private volatile double latestCpuLoad = 0.0;
    private volatile double latestMemoryAvailable = 0.0;

    public MetericsSchedulerService(SystemMetericsController systemMetericsController) {
        this.systemMetericsController = systemMetericsController;
    }

    @Scheduled(fixedRate = 5000)
    public void fetchMetricsPeriodically() {
        latestCpuLoad = systemMetericsController.getCpuLoad();
        latestMemoryAvailable = systemMetericsController.getMemoryUsage();

        System.out.printf("Scheduled Metrics - CPU Load: %.2f%%, Memory Available: %.2f bytes%n",
                latestCpuLoad * 100, latestMemoryAvailable);
    }

    public double getLatestCpuLoad() {
        return latestCpuLoad;
    }

    public double getLatestMemoryAvailable() {
        return latestMemoryAvailable;
    }
}
