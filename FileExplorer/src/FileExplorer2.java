import java.io.FileNotFoundException;
import java.io.File;
import java.util.Date;

public class FileExplorer {
	private File file;
	private String filename, path, filetype, contentList, permissions;
	private File[] contents;
	private long size;
	private int total;
	Date lastModified;

	public FileExplorer(String filename) throws FileNotFoundException {
		this.filename = filename;
		this.file = new File(filename);
		
		path = file.getAbsolutePath();
		
		if (file.isDirectory()) {
			contents = file.listFiles();
			StringBuilder contentsList = new StringBuilder();
            String item;
			for (int i = 0; i < contents.length; i++) { //Adds all contents to an array
                item = contents[i].getName();
                
                contentsList.append(item + "\n               "); 
                
                if (i == contents.length - 1) {
                    contentsList.append(item);
                }
            }
            
            contentList = contentsList.toString();
			total = contents.length;
			
			if (file.isHidden()) {
				filetype = "hidden directory";
			} else {
				filetype = "directory";
			}
		}
			
		else if (file.isFile()) {
			size = file.length();
			
			if (file.isHidden()) {
				filetype = "hidden file";
			} else {
				filetype = "normal file";
			}
		}
		
		else {
			throw new FileNotFoundException();
		}
		
		StringBuilder filePermissions = new StringBuilder();
		if (file.canRead()) {
			filePermissions.append("r");
		} else {
			filePermissions.append("-");
		}
		
		if (file.canWrite()) {
			filePermissions.append("w");
		} else {
			filePermissions.append("-");
		}
		
		if (file.canExecute()) {
			filePermissions.append("x");
		} else {
			filePermissions.append("-");
		}
		
		permissions = filePermissions.toString();
		lastModified = new Date(file.lastModified());
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("Full path:     " + path + "\n");
		output.append("File type:     " + filetype + "\n");
		output.append("Permissions:   " + permissions + "\n");
		output.append("Last modified: " + lastModified + "\n");
		if (file.isDirectory()) {
			output.append("Contents:      " + contents + "\n");
			output.append("Total:         " + total + "\n");
		}
		else if (file.isFile()) {
			output.append("Size:          " + size + " bytes" + "\n");
		}
		return output.toString();
	}
	
	public static void main(String[] args) {
		FileExplorer file = null;
		if (args.length == 1) {
			try {
				file = new FileExplorer(args[0]);
			} catch (FileNotFoundException fnfe) {
				System.err.println("Error: File '" + args[0] + "' does not exist.");
				System.exit(1);
			}
		} else {
			try {
				file = new FileExplorer(".");
			} catch (FileNotFoundException fnfe) {
				System.err.println("Error: File '" + args[0] + "' does not exist.");
				System.exit(1);
			}
		}
		System.out.println(file.toString());
	}
}
