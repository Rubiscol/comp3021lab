package base;

import java.io.*;

public class TextNote extends Note {
	private static final long serialVersionUID = 1L;
	String content;

	public TextNote(String title) {
		super(title);
	}

	public TextNote(File f) {
		super(f.getName());
		try {
			this.content = getTextFromFile(f.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getContent() {
		return content;
	}

	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}

	private String getTextFromFile(String absolutePath) throws IOException, FileNotFoundException {
		String result = "";
		BufferedReader buf = null;

		try {
			File file = new File(absolutePath);
			buf = new BufferedReader(new FileReader(file));
			String line;
			while ((line = buf.readLine()) != null) {
				result = result + line;
			}
			buf.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file from path '" + absolutePath + "'");
		} catch (IOException e) {
			System.out.println("Error reading file '" + absolutePath + "'");
		}
		return result;
	}

	public void exportTextToFile(String pathFolder) {
		try {
			if (pathFolder=="") {
				pathFolder=".";
			}
			File file = new File(pathFolder + File.separator + this.getTitle().replaceAll(" ","_") + ".txt");
			BufferedWriter buf = new BufferedWriter(new FileWriter(file));
			buf.write(content);
			buf.close();

		} catch (IOException e) {
			System.out.println("Error writing file '" + pathFolder + "'");
		}
	}
}