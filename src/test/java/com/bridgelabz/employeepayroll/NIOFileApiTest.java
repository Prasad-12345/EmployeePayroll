package com.bridgelabz.employeepayroll;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/*
 *Author: Prasad
 *Write a program to demonstrate File Operations
 */
public class NIOFileApiTest {
    private static String HOME = System.getProperty("user.home");
    private static String PLAY_WITH_NIO = "TempPlayGround";

    @Test
    public void getPath_WhenChecked_ThenConfirm() throws IOException {
        //check file exists
        Path homePath = Paths.get(HOME);
        Assert.assertTrue(Files.exists(homePath));

        //delete files and check file not exists
        Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
        if (Files.exists(playPath)){
            FileUtils.deleteFolder(playPath.toFile());
        }
        Assert.assertTrue(Files.notExists(playPath));

        //create directory and check it exists
        Files.createDirectories(playPath);
        Assert.assertTrue(Files.exists(playPath));

        //creating file
        IntStream.range(1, 10).forEach(counter -> {
            Path tempFile = Paths.get(playPath + "/temp" + counter);
            Assert.assertTrue(Files.notExists(tempFile));
            try {
                Files.createFile(tempFile);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            Assert.assertTrue(Files.exists(tempFile));
        });


        // List Files, Directories as well as Files with Extension
        Files.list(playPath).filter(Files::isRegularFile).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("-------------------------------------------");

        Files.newDirectoryStream(playPath).forEach(System.out::println);
        System.out.println("-------------------------------------------");

        Files.newDirectoryStream(playPath, path -> path.toFile().isFile() && path.toString().startsWith("temp")).forEach(System.out::println);
        System.out.println("-------------------------------------------");
    }
}
