package com.dev.imran;

import com.dev.imran.domain.TaskManager;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(java.nio.file.Paths.get("file1.txt").toAbsolutePath());
        TaskManager manager = new TaskManager();
        manager.process(

                Path.of("file1.txt"),
                Path.of("file2.txt"),
                Path.of("output.txt")
        );
    }
}