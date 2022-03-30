package base;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TableNote extends Note {
	private static final long serialVersionUID = 1L;

	ArrayList<String[]> Table;
	
	public TableNote(String title) {
		super(title);
	}

	public TableNote(File f) {
		super(f.getName());
		try {
			this.Table = getTextFromFile(f.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private ArrayList<String[]> getTextFromFile(String absolutePath) throws IOException, FileNotFoundException {
		
		BufferedReader buf = null;

		try {
			File file = new File(absolutePath);
			buf = new BufferedReader(new FileReader(file));
			ArrayList<String[]> newTable=new ArrayList<String[]>();
			while ((buf.readLine()) != null) {
				String[] newLine=buf.readLine().split(" ");
				newTable.add(newLine);
			}
			buf.close();
			return newTable;
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file from path '" + absolutePath + "'");
		} catch (IOException e) {
			System.out.println("Error reading file '" + absolutePath + "'");
		}
		return null;
		
	}

	public void exportTextToFile(String pathFolder) {
		try {
			if (pathFolder=="") {
				pathFolder=".";
			}
			File file = new File(pathFolder + File.separator + this.getTitle().replaceAll(" ","_") + ".txt");
			BufferedWriter buf = new BufferedWriter(new FileWriter(file));
			for(int i=0; i<Table.size()-1;i++) {
				String newLine=new String();
				for(int j=0;j<Table.get(i).length-1;j++) {
					newLine=newLine+Table.get(i)[j];
				}
				
				buf.write(newLine);
			}
			
			buf.close();

		} catch (IOException e) {
			System.out.println("Error writing file '" + pathFolder + "'");
		}
	}
}