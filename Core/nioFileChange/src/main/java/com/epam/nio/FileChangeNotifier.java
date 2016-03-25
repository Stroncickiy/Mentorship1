package com.epam.nio;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileChangeNotifier
{
   public static void main(String args[]) throws IOException, InterruptedException
   {
      watchDir("D:\\MyFolder");		// Monitor changes to the files in E:\MyFolder
   }
   public static void watchDir(String dir) throws IOException, InterruptedException
   {
       WatchService service = FileSystems.getDefault().newWatchService();	// Create a WatchService
       Path path = Paths.get(dir);	// Get the directory to be monitored
       path.register(service,
            StandardWatchEventKinds.ENTRY_CREATE,
            StandardWatchEventKinds.ENTRY_MODIFY,
            StandardWatchEventKinds.ENTRY_DELETE);	// Register the directory
       while(true)
       {
          WatchKey key = service.take();	// retrieve the watchkey
          for (WatchEvent event : key.pollEvents())
          {
             System.out.println(event.kind() + ": "+ event.context());	// Display event and file name
          }
          boolean valid = key.reset();
          if (!valid)
          {
             break;	// Exit if directory is deleted
          }
       }
   }
}