/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//@this file contains code to organize various file types into their respective folders
//@such as Music, Videos and Documents.
package sources;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*import sources.FileUtil;
import static sources.FileUtil.ff;
import static sources.FileUtil.fflist;
import static sources.FileUtil.fss;
import static sources.FileUtil.sts;*/
/**
 *
 * @author Abummoja
 */
public class FileOrganizer {

    static String sts;
    public static String files[] = {""};
    static List<String> fileList = new ArrayList<>(Arrays.asList(files));

    public static void iterateAndFilter(String mpath, String ext) throws IOException {
        Path dir = Paths.get(mpath);
        PathMatcher imageFileMatcher
                = FileSystems.getDefault().getPathMatcher(
                        ext);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir,
                entry -> imageFileMatcher.matches(entry.getFileName()))) {
            for (Path path : stream) {
                System.out.println(path.getFileName());
                System.out.println(path.toString());
                fileList.add(path.toString());
                // sts = "Moved "+ext+" files from "+mpath;
                //sts = "\nFound: "+fflist.size()+" files of type "+ext;
            }
        }
        files = fileList.toArray(new String[fileList.size()]);
        System.out.println(files.length);
        //fss should be a variable to receive the directory path due to current file extension
        //fss = "C:\\Users\\Abummoja\\Music";
        // moveF(fss);
    }

    public void listFiles(String type, String dir) throws IOException {
        iterateAndFilter(dir, type);
    }
    
    public void clearList(){
        fileList.clear();
        files = fileList.toArray(new String[fileList.size()]);
    }
    
    public static void moveFiles(String morig, String mdest) throws IOException {

        File orig = new File(morig);
        File dest = new File(mdest);
        String orr = orig.getAbsolutePath();
        String nf = dest.getAbsolutePath();
        String nm = orig.getName();
        //nf is dir and nm is original file name the\\ take the file into the dir not at dir level
        File fin = new File(nf+"\\"+nm);
        System.out.println("\nnf is "+nf+" nm is "+nm);
        System.out.println("\n==> "+fin.getAbsolutePath());
        if(dest.isDirectory()){
        orig.renameTo(fin);
        sts = fin.getAbsolutePath();
        }else{
            System.err.println("not a directory"+dest.getAbsolutePath());
        }
    }
    
    public void moveFi(String destina, String[] ff) throws IOException{
        int i = 0;
        for (String t : ff) {
            i+=1;
            System.out.println("\nmoving from "+t+" to "+destina);
            moveFiles(t, destina);
            sts += "\nmoved "+i+" files.";
        }
    }
}
