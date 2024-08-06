package com.pli.indonesia.athena.demo2;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

@Path("/hello-world")
@RequestScoped
public class HelloResource {
    AtomicBoolean isFound = new AtomicBoolean(false);
    FileAlterationObserver observer = new FileAlterationObserver("/Users/billyandersonmatjik/IdeaProjects/demo2/test");
    FileAlterationListener listener = new FileAlterationListenerAdaptor() {
        @Override
        public void onFileCreate(File file) {
            if (file.getName().equals("test.txt")) {
                isFound.set(true);
                System.out.println("found created " + file.getName());
            }
        }

        @Override
        public void onFileDelete(File file) {
            if (file.getName().equals("test.txt")) {
                isFound.set(true);
                System.out.println("found delete " + file.getName());
            }
        }

        @Override
        public void onFileChange(File file) {
            // code for processing change event
            if (file.getName().equals("test.txt")) {
                isFound.set(true);
                System.out.println("found change " + file.getName());
            }
        }

        @Override
        public void onStart(FileAlterationObserver observer) {
            System.out.println("started");
        }

        @Override
        public void onStop(FileAlterationObserver observer) {
            System.out.println("stop");
        }
    };

    @PreDestroy
    public void destroyAfter() {
        System.out.println("Destroy request");
        try {
            observer.removeListener(listener);
            monitor.getFileAlterationMonitor().removeObserver(observer);
            observer.destroy();
            monitor.getFileAlterationMonitor().stop(1000);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Inject
    private FileMonitorSingleton monitor;

    @GET
    @Produces("text/plain")
    public String hello() {
        observer.addListener(listener);
        monitor.getFileAlterationMonitor().addObserver(observer);
        try {
            monitor.getFileAlterationMonitor().start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        while (!isFound.get()) {
        }
        if (isFound.get()) {
            System.out.println("FOUNDED");
        }
        return "Hello, World!";
    }
}