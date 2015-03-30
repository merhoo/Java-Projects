import java.io.FileNotFoundException;
import java.io.File; //Imports the File from input
import java.util.Date; //Imports the Date class to be used for printing

public class FileExplorer {
    private File file;
    private String filename, path, type, permissions, contentList;
    private File[] contents;
    private int numContents;
    private long size;
    Date lastModified;
    
    public FileExplorer (String filename) throws FileNotFoundException {
        this.filename = filename;
        this.file = new File(filename);
        
        path = file.getAbsolutePath(); //Finds the File's path
        
        if (file.isDirectory()) { //Checks if the file is a directory
            contents = file.listFiles(); //Stores all of the directory's contents
            
            StringBuilder contentsList = new StringBuilder();
            String item; //Content item in a directory
            for (int i = 0; i < contents.length; i++) { //Adds all contents to an array
                item = contents[i].getName();
                
                contentsList.append(item + "\n               "); 
                
                if (i == contents.length - 1) {
                    contentsList.append(item);
                }
            }
            
            contentList = contentsList.toString(); //Formats the contents for printing
            
            numContents = contents.length; //Finds total number of contents
            
            if (file.isHidden()) { //Checks if directory is hidden
                type = "hidden directory";
            } else {
                type = "directory";
            }
            
        } else if (file.isFile()) { //Checks if it is a file
            size = file.length(); //Sets the size of the file in bytes
            if (file.isHidden()) { // Checks if the file is hidden
                type = "hidden file";
            } else {
                type = "normal file";
            }
        } else { //Exception thrown if a file is not found
            throw new FileNotFoundException();
        }
        
        StringBuilder filePermissions = new StringBuilder();
        if (file.canRead()) { //Checks if readable
            filePermissions.append("r");
        } else {
            filePermissions.append("-");
        } 
        if (file.canWrite()) { //Checks if writable
            filePermissions.append("w");
        } else {
            filePermissions.append("-");
        }
        if (file.canExecute()) { //Checks if executable
            filePermissions.append("x");
        } else {
            filePermissions.append("-");
        }
        permissions = filePermissions.toString();
        lastModified = new Date(file.lastModified());
    }
    
    public String toString() {
        StringBuilder properties = new StringBuilder(); //Creates a string for the properties of the item searched
        
        properties.append("Full path:     " + path + "\n" +
                      "File type:     " + type + "\n" +
                      "Permissions:   " + permissions + "\n" +
                      "Last Modified: " + lastModified + "\n"); //Adds some file data
        
        if (file.isDirectory()) { //Checks if the item is a Directory
            properties.append("Contents:      " + contentList + "\n" +
                          "Total:         " + numContents);
        } else if (file.isFile()) { //Checks if the item is a File
            properties.append("Size:          " + size + " bytes");
        }
        
        return properties.toString(); //Formats the properties to be printed
    }
    
    public static void main (String[] args) {
        FileExplorer FE = null; //Creates an instance of the class; Initializes to null in the case that a File is not found
        if (args.length == 0) {
            try {
                FE = new FileExplorer("."); //Goes to the current directory, if nothing is entered
            } catch (FileNotFoundException fnfe) {
                System.err.println("Error: File '" + args[0] + "' does not exist.");
                System.exit(1);
            }
        }
        else {
            try {
                FE = new FileExplorer(args[0]); //Goes to the location entered
            } catch (FileNotFoundException fnfe) {
                System.err.println("Error: File '" + args[0] + "' does not exist.");
                System.exit(1);
            }
        }
        System.out.println(FE.toString());
    }
}

