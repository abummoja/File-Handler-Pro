/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import java.io.File;

/**
 *
 * @author Abummoja
 */
public class FileRenamer {
      public String renameBulk (String folderName, String ReplaceFrom, String ReplaceWith)
    {
        try
        {
        // This method will rename all files in a folder by chaning ReplaceFrom string with ReplaceWith string
        File folder=new File(folderName);
        File[] filesList=folder.listFiles();
        for (int i=0; i< filesList.length; i++)
        {
            String newName= (filesList[i].toString().replaceAll(ReplaceFrom, ReplaceWith)).trim();
            
            filesList[i].renameTo(new File(newName));
            
            
        }
        return "Successfully renamed "+filesList.length+" files.";
        }
        catch (Exception e)
        {
            return (e.getMessage());
            
        }
    }
     //TODO: - to rename selected files in list - list shall have model(name, path) to handle specific selection
    //public String renameSelection(){}
}
