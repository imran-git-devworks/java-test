package com.dev.imran.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileWriterService {

    public void writeFile(Path path, List<String> fileContent) throws IOException {

//        if(null == path || fileContent.isEmpty()){
//            throw new IOException("Either Path is null or No content found to write!!");
//        }
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for(String line : fileContent){
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        }
    }

}
