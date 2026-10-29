package com.dev.imran.domain;

import com.dev.imran.domain.task.FileReaderTask;
import com.dev.imran.service.FileMergeService;
import com.dev.imran.service.FileReaderService;
import com.dev.imran.service.FileWriterService;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class TaskManager {

    private final FileReaderService reader = new FileReaderService();
    private final FileWriterService writer = new FileWriterService();
    private final FileMergeService merger = new FileMergeService();

    public void process(Path file1, Path file2, Path outputFile) throws InterruptedException {

        Map<String, List<String>> fileDataStorage = new ConcurrentHashMap<>();

        Runnable barrierAction = () -> {
            try {
                List<String> fileContent1 = fileDataStorage.get(file1);
                List<String> fileContent2 = fileDataStorage.get(file2);

                List<String> mergedContent = merger.mergeFileContents((fileDataStorage));
                writer.writeFile(outputFile, mergedContent);

                System.out.println("Merged and written to " + outputFile);
            } catch (Exception e) {
                System.err.println("Barrier action failed");
                e.printStackTrace();
            }
        };
        CyclicBarrier barrier = new CyclicBarrier(2, barrierAction);

        Thread t1 = new Thread(new FileReaderTask(file1, reader, fileDataStorage, barrier), "Reader-1");
        Thread t2 = new Thread(new FileReaderTask(file2, reader, fileDataStorage, barrier), "Reader-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}

