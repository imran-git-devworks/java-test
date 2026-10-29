package com.dev.imran.service;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class FileMergeService {

    public List<String> mergeFileContents(Map<String, List<String>> fileStorage) throws RuntimeException {

        String file1Path = "src/main/java/resources/file1.txt";
        String file2Path = "src/main/java/resources/file2.txt";
        List<String> file1Content = fileStorage.get(file1Path);
        List<String> file2Content = fileStorage.get(file2Path);
//        if (file1Content.isEmpty() || file2Content.isEmpty()) {
//            throw new RuntimeException("File Contents not Found to merge!!");
//        }
        int size = Math.min(file1Content.size(), file2Content.size());
        List<String> mergedList = IntStream.range(0, size).mapToObj(i -> file1Content.get(i) + file2Content.get(i)).toList();
        return mergedList;
    }
}
