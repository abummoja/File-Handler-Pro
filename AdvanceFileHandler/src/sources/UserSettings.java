/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

//import SettingsMgr;
import java.io.File;
import java.util.prefs.Preferences;
import javax.swing.JTabbedPane;

/**
 *
 * @author Abummoja
 */
public class UserSettings {
    static String unm = System.getProperty("user.name");

     final static Preferences pref = Preferences.userNodeForPackage(UserSettings.class);
    public static String mp3dir = pref.get("mp3dir", "C:\\Users\\" + unm + "\\Music");
    public static String mp4dir = pref.get("mp3dir", "C:\\Users\\" + unm + "\\Videos");
    public static String picdir = pref.get("mp3dir", "C:\\Users\\" + unm + "\\Pictures");
    public static String archdir = pref.get("mp3dir", "C:\\Users\\" + unm + "\\Archives");
    public static String appsdir = pref.get("mp3dir", "C:\\Users\\" + unm + "\\Apps");
    public static String docsdir = pref.get("mp3dir", "C:\\Users\\" + unm + "\\Documents");
    public static boolean isDark = pref.getBoolean("lightTheme", true);
    public static int tabSide = pref.getInt("tabSide", JTabbedPane.TOP);
    
    public String[] dirs = {mp3dir, mp4dir, picdir, archdir, appsdir, docsdir};
    public UserSettings(){
        //constructor
    }
    
    public String getDir(String typee){
        switch(typee){
            case "mp3":
                return mp3dir;
            case "mp4":
                return mp4dir;
            case "pic":
                return picdir;
            case "aar":
                return archdir;
            case "app":
                return appsdir;
            case "doc":
                return docsdir;
            default:
                break;
        }
        return null;
    }
    
    public void setTheme(boolean lightOrNot){
        pref.putBoolean("lightTheme", lightOrNot);
    }
    
    public void createDir(String[] paths){
        for(String p:paths){
            File f = new File(p);
            if(!f.exists()){
                f.mkdir();
            }else{
                System.out.println("Directory exists!");
            }
        }
    }
   
}
