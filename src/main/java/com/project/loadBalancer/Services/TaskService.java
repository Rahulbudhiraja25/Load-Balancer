package com.project.loadBalancer.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TaskService {

    @Autowired
    private MetericsSchedulerService metricsSchedulerService;

    private static final double CPU_THRESHOLD = 80.0;
    private static final double MEMORY_USED_THRESHOLD = 80.0;
    private static final double TOTAL_MEMORY_GB = 12.0; // adjust if needed

    public void executeTaskBasedOnLoad() {
        double cpuLoad = metricsSchedulerService.getLatestCpuLoad();
        double memFree = metricsSchedulerService.getLatestMemoryAvailable();
        double memUsedPercent = ((TOTAL_MEMORY_GB - memFree) / TOTAL_MEMORY_GB) * 100;

        if (cpuLoad >= CPU_THRESHOLD || memUsedPercent >= MEMORY_USED_THRESHOLD) {
            runAsyncTask(); // high load, run asynchronously
        } else {
            runTask(); // normal load, run synchronously
        }
    }

    @Async
    public void runAsyncTask() {
        System.out.println("🔁 Running task asynchronously due to high load");
        doHeavyProcessing("Async");
    }

    public void runTask() {
        System.out.println("✅ Running task synchronously due to normal load");
        doHeavyProcessing("Sync");
    }

    private void doHeavyProcessing(String mode) {
        // Example: simulate a long-running computation
        for (int i = 1; i <= 5; i++) {
            System.out.println("[" + mode + "] Processing step " + i);
            try {
                Thread.sleep(1000); // simulate time delay for processing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("[" + mode + "] Task interrupted!");
            }
        }
        System.out.println("[" + mode + "] Task completed.");
    }
}
