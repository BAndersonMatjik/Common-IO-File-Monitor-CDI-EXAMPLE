package com.pli.indonesia.athena.demo2;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.commons.io.monitor.FileAlterationMonitor;


public class FileMonitorFactory {

    @Produces
    public FileAlterationMonitor producesFileAlterationMonitor(){
        return new FileAlterationMonitor(1000);
    }
}
