package com.dev.imran.domain.task;

import com.dev.imran.service.FileReaderService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class FileReaderTask implements Runnable {

    private final Path path;
    private final Map<String, List<String>> fileStorage;
    private FileReaderService fileReaderService;
    private CyclicBarrier cyclicBarrier;

    public FileReaderTask(Path path, FileReaderService fileReaderService, Map<String, List<String>> fileStorage, CyclicBarrier cyclicBarrier) {
        this.path = path;
        this.fileReaderService = fileReaderService;
        this.fileStorage = fileStorage;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            fileReaderService.readFile(path);
            System.out.println(Thread.currentThread().getName() + " completed reading content " + path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try {
            System.out.println(Thread.currentThread().getName() + " now calling await()!");
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
