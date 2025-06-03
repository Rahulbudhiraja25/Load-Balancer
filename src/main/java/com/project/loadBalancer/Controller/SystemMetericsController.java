package com.project.loadBalancer.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

@RestController
public class SystemMetericsController {

    private final SystemInfo systemInfo;
    private final CentralProcessor processor;

    public SystemMetericsController() {
        this.systemInfo = new SystemInfo();
        this.processor = systemInfo.getHardware().getProcessor();
    }

    @GetMapping("/metrics/cpu")
    public String getCpuLoad() {
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long[] ticks = processor.getSystemCpuLoadTicks();
       double load= processor.getSystemCpuLoadBetweenTicks(prevTicks);
       return (String.format("%.2f", load*100))+"%";
    }

    @GetMapping("/metrics/memory")
    public String getMemoryUsage() {
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long total = memory.getTotal();
        long available = memory.getAvailable();
        return String.format("Memory Used: %.2f%%", ((double)(total - available) / total) * 100);
    }
}
