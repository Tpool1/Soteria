package patientDataLoad;

import java.io.File;
import patientDataUtils.image;
import java.util.StringJoiner;

public class path_loader {
   
   public image[] imgArr; 

   public path_loader(String load_path) {
       String[] filesArr = getFiles(load_path);
       imgArr = loadFiles(filesArr);
   }

   public static String[] getFiles(String directory) {
       File dir = new File(directory);

       String[] files;
       files = dir.list();

       // get full path for all files
       for (int i = 0; i < files.length; i++) {
        StringJoiner joiner = new StringJoiner(File.separator);
        joiner.add(directory).add(files[i]);
        String fullPath = joiner.toString();
        files[i] = fullPath;
        }

       return files;
   }

   public static image[] loadFiles(String[] fileArr) {
       // make array for image objs with same length as fileArr
       image[] imgArr = new image[fileArr.length];

       for (int i = 0; i < fileArr.length; i++) {
           String path = fileArr[i];
           image path_img = new image(path);
           imgArr[i] = path_img;
       }

       return imgArr;
   }
}
