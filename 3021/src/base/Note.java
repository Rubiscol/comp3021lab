package base;

import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note>,java.io.Serializable{
	private Date date;
	private String title;
	private static final long serialVersionUID = 1L;
	
	public Note(String title) {
		this.title=new String(title);
		date =new Date(System.currentTimeMillis());
	}

	public String getTitle() {

		return title;
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}
	
	public boolean equals(Note note) {

		return (title.equals(note.getTitle()));
	}
	
	public int compareTo(Note o) {
		if (this.date.before(o.getDate())) {
			return 1;
		}
		else if(this.date.equals(o.getDate())) {
			return 0;
		}
		else {
			return -1;
		}
		
	}
	
	public String getContent() {
		return null;
	}
	
	public String toString() {
		return date.toString()+"\t"+title;
	}
	
	public Date getDate() {
		return date;
	}

}
