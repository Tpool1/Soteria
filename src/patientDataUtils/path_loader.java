package patientDataUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class path_loader {
   public path_loader(String load_path) {
       String[] filesArr = getFiles(load_path);
       System.out.println(Arrays.toString(filesArr));
   }

   public static String[] getFiles(String directory) {
       File dir = new File(directory);

       Collection<String> files = new ArrayList<String>();
       if (dir.isDirectory()) {
           File[] listFiles = dir.listFiles();

           for (File file : listFiles) {
               if (file.isFile()) {
                   files.add(file.getName());
               }
           }
       }

       return files.toArray(new String[]{});
   }
}
