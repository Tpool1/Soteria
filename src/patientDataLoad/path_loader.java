package patientDataLoad;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import patientDataUtils.image;

public class path_loader {
   
   public image[] imgArr; 

   public path_loader(String load_path) {
       String[] filesArr = getFiles(load_path);
       image[] imgArr = loadFiles(filesArr);
   }

   public static String[] getFiles(String directory) {
       File dir = new File(directory);

       String[] files = dir.list();

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
