package base;

import java.util.Date;
import java.util.Objects;

public class Note {
	private Date date;
	private String title;
	
	
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

}
