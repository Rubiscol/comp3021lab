package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.List;
public class Folder implements Comparable<Folder>{
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name=new String(name);
		notes=new ArrayList<Note>();
	}
	public void addNote(Note note) {
		notes.add(note);
	}
	public String getName() {	
		return name;
	}
	public ArrayList<Note> getNotes(){	
		return notes;
	}
	
	@Override
	public String toString() {
		int nText=0;
		int nImage=0;
		for (Note note: notes) {
			if(note instanceof TextNote) {
				nText++;
			}
			else {
				nImage++;
			}
		}

		return name+":"+nText+":"+nImage;
	}
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
	
	public int compareTo(Folder o) {
		return name.compareTo(o.name);
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	public List<Note> searchNotes (String keywords){
	
		ArrayList<Note> note = new ArrayList<Note>(); 
		keywords = keywords.toLowerCase();
		keywords=keywords.replace(" or "," ");
		String[] wordlists=keywords.split(" ");
		String first=wordlists[0];
		String second=wordlists[1];
		String third=wordlists[2];	
		String fourth=wordlists[3];
		for (Note n : notes) {
			String name=n.getTitle().toLowerCase();
			if(n instanceof ImageNote) {
				if (((name.contains(first))||(name.contains(second)))&&((name.contains(third))||(name.contains(fourth)))) {
					note.add(n);
				}
			}
			else {
				String content=n.getContent().toLowerCase();
				if (
				(((name.contains(first))||(name.contains(second)))&&((name.contains(third))||(name.contains(fourth))))||
				(((content.contains(first))||(content.contains(second)))&&((content.contains(third))||(content.contains(fourth))))
				)
				{
					note.add(n);
				}
				
			}
		
		}
		return note;
	}
}
