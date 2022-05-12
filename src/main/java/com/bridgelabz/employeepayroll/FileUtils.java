package com.bridgelabz.employeepayroll;

import java.io.File;
/*
 *Author: Prasad
 */
public class FileUtils {
    /*
     *Method to get list of all files and deleting it
     */
    public static boolean deleteFolder(File file){
        for(File subFile : file.listFiles()){
            if(subFile.isDirectory()){
                deleteFolder(subFile);
            }
            else {
                subFile.delete();
            }
        }
        return file.delete();
    }
}
