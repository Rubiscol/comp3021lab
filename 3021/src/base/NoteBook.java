package base;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import com.sun.tools.javac.util.List;


public class NoteBook {
	
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName,String title) {
		TextNote note=new TextNote(title);
		return insertNote(folderName,note);
	
	}
	public boolean createImageNote(String folderName,String title) {
		ImageNote note=new ImageNote(title);
		return insertNote(folderName,note);
	
	}
	public ArrayList<Folder>getFolders(){
		return folders;
	}
	
	public void sortFolders() {
		for (Folder f:folders) {
			f.sortNotes();
			}
		Collections.sort(folders);
		}
	
	public boolean insertNote(String foldername,Note note) {
		
		Folder folder=null;
		for (Folder f:folders) {
			if (f.getName().equals(foldername)) {
				folder=f;
			}
		}
		if (folder==null) {
			folder= new Folder(foldername);
			folders.add(folder);
		}
		
		for (Note n : folder.getNotes()) {
			if (n.equals(note)) {
			System.out.println("Creating note "+note.getTitle()+" under folder "+foldername+" failed");
			return false;
			}
			}
		folder.addNote(note);
		return true;
			
		}
	

	
	public List<Note> searchNotes(String keywords){
		ArrayList<Note> note = new ArrayList<Note>(); 
		for (Folder f:folders) {
			if (!(f.searchNotes(keywords).isEmpty())) {
				note.addAll(f.searchNotes(keywords));
			}
		}
		return note;
		
	}
	
	public boolean createTextNote(String folderName,String title,String content) {
		TextNote note=new TextNote(title,content);
		return insertNote(folderName,note);
		
	}

	}
	


