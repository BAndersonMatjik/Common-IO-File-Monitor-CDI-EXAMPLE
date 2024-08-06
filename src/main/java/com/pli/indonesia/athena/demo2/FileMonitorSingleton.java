package com.pli.indonesia.athena.demo2;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.io.monitor.FileAlterationMonitor;

@ApplicationScoped
public class FileMonitorSingleton {

    @Inject
    private FileAlterationMonitor fileAlterationMonitor;

    public FileAlterationMonitor getFileAlterationMonitor() {
        return fileAlterationMonitor;
    }

    @PostConstruct
    void init() {
        try {
            System.out.println("START MONITOR");
//            fileAlterationMonitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    void destroy() {
        try {
            System.out.println("STOP MONITOR");
            fileAlterationMonitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
