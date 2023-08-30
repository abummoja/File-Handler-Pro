/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abummoja
 */
public class FileUtil {

    static File mFile;
    static String fss;
    public static String sts;
    static String ff[] = {""};
    public static String audlst[] = {""}, vidlst[]= {""}, piclst[]= {""}, doclst[]= {""}, archlst[]= {""};
    public static List<String> auds = new ArrayList<>(Arrays.asList(audlst));
    public static List<String> vids = new ArrayList<>(Arrays.asList(vidlst));
    public static List<String> pics = new ArrayList<>(Arrays.asList(piclst));
    public static List<String> docs = new ArrayList<>(Arrays.asList(doclst));
    public static List<String> archs = new ArrayList<>(Arrays.asList(archlst));
    static List<String> fflist = new ArrayList<>(Arrays.asList(ff));
    static String audex = "regex:.*(?i:mp3|ogg|avi|wav|flacc|aud|flp)";
    static String videx = "regex:.*(?i:mp4|mkv|webm|ts|wmp|mov)";
    static String picex = "regex:.*(?i:jpg|jpeg|png|gif|bmp|jpe|jfif)";
    static String docex = "regex:.*(?i:pdf|doc|txt|pptx|xls|mhtml|html|ppt|mdb|accdb|docx)";
    static String archex = "regex:.*(?i:zip|rar|7z|aar|jar|gz|tar)";
    static String appex = "regex:.*(?i:exe|com|apk|bat|msi|iso|app|sh)";

    //get file name
    public static String getFileName(String path) {
        mFile = new File(path);
        return mFile.getName();
    }

    public static String getFileSize(String path) {
        Path p = Paths.get(path);
        String si = "0 MB";
        try {
            // size of a file (in bytes)
            long bytes = Files.size(p);
            si = String.format("%,d MB", (bytes / 1024) / 1024);
        } catch (IOException e) {

        }
        return si;
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

    public static void copyFile(InputStream source, OutputStream destination) throws IOException {
        /*try {
            int c;
            while ((c = source.read()) != -1) {
                destination.write(c);
            }
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }*/

    }
    
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
                fflist.add(path.toString());
               // sts = "Moved "+ext+" files from "+mpath;
               sts = "\nFound: "+fflist.size()+" files of type "+ext;
            }
        }
        ff = fflist.toArray(new String[fflist.size()]);
        //fss should be a variable to receive the directory path due to current file extension
        fss = "C:\\Users\\Abummoja\\Music";
        moveF(fss);
    }
    
    private static void moveF(String desty) throws IOException{
        int i = 0;
        for (String t : ff) {
            i+=1;
            System.out.println("\nmoving from "+t+" to "+desty);
            moveFiles(t, desty);
            sts += "\nmoved "+i+" files.";
        }
    }
    //read zip files
    public static void readZip() {
        /*Path pathToZip = Paths.get("path/to/file.zip");
        try (FileSystem zipFs = FileSystems.newFileSystem(pathToZip, null)) {
            Path root = zipFs.getPath("/");
            ... //access the content of the zip file same as ordinary files
        } catch (IOException ex) {
            
        }*/
    }

    //create new zip and write to it
    public static void createZip() {
        /* Map<String, String> env = new HashMap<>();
        env.put("create", "true"); //required for creating a new zip file
        env.put("encoding", "UTF-8"); //optional: default is UTF-8
        URI uri = URI.create("jar:file:/path/to/file.zip");
        try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
            Path newFile = zipFs.getPath("/newFile.txt");
            //writing to file
            Files.write(newFile, "Hello world".getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/

    }
}
