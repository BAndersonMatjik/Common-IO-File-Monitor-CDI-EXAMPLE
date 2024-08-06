package com.pli.indonesia.athena.demo1;

import jakarta.enterprise.inject.Produces;
import org.apache.commons.io.monitor.FileAlterationMonitor;


public class FileMonitorFactory {

    @Produces
    public FileAlterationMonitor producesFileAlterationMonitor(){
        return new FileAlterationMonitor(1000);
    }
}
