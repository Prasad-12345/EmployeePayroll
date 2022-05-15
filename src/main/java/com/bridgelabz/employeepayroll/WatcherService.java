package com.bridgelabz.employeepayroll;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.IntStream;

/*
 *Author: Prasad
 * Create a Watch Service to watch particular directory along with all Files and Sub Directories
 */
public class WatcherService {
    private  final WatchService watchService;
    static Path path;

    //constructor
    public WatcherService() throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.path = Paths.get("C:\\Users\\Prasad\\TempPlayGround");
    }

    //register methods
    public void registerWithWatcher() throws IOException {
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_CREATE);
    }

    //this method takes key signal and gives events
    public void watchEvent() throws InterruptedException {
        WatchKey key;
        while ((key = watchService.take()) != null){
            for (WatchEvent<?> event : key.pollEvents()){
                System.out.println("Event type" + event.kind() + "File affected" + event.context());
            }
        }
        key.reset();
    }

    //to create files
    public void createFiles(){
        String HOME = System.getProperty("user.home");
        String PLAY_WITH_NIO = "TempPlayGround";
        Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
        IntStream.range(1, 10).forEach(counter -> {
            Path tempFile = Paths.get(playPath + "/temp" + counter);
            //  Assert.assertTrue(Files.notExists(tempFile));
            try {
                Files.createFile(tempFile);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            //  Assert.assertTrue(Files.exists(tempFile));
        });
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //object
        WatcherService watcherService = new WatcherService();
        watcherService.registerWithWatcher();
        watcherService.createFiles();
        watcherService.watchEvent();
        Long count =  Files.list(path).filter(Files::isRegularFile).count();
        System.out.println("number of entries  are :" + count);
    }
}
