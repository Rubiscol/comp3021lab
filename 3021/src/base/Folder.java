package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.List;

public class Folder implements Comparable<Folder>,java.io.Serializable {
	private ArrayList<Note> notes;
	private String name;
	private static final long serialVersionUID = 1L;
	
	
	public Folder(String name) {
		this.name = new String(name);
		notes = new ArrayList<Note>();
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for (Note note : notes) {
			if (note instanceof TextNote) {
				nText++;
			} else {
				nImage++;
			}
		}

		return name + ":" + nText + ":" + nImage;
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

	public List<Note> searchNotes(String keywords) {
		ArrayList<Note> note = new ArrayList<Note>();
		keywords = keywords.toLowerCase();
		for (Note n : notes) {
			String keyward = new String(keywords);
			String[] wordlists = keyward.split(" ");
			boolean findOr = true;
			// First we check all the or condition. If one of the or condition fail in n,
			// then we continue to check next n
			// If all the or conditions pass, all the or and its related elements will be transformed into blank space
			for (int i = 0; i < wordlists.length; i++) {

				if (wordlists[i].equals("or")) {
					findOr = false;
					String name = n.getTitle().toLowerCase();
					if (n instanceof ImageNote) {
						if (((name.contains(wordlists[i - 1])) || (name.contains(wordlists[i + 1])))) {
							wordlists[i - 1] = " ";
							wordlists[i] = " ";
							wordlists[i + 1] = " ";
							findOr = true;
						}
					}
					if (n instanceof TextNote) {
						String content = n.getContent().toLowerCase();
						if ((((name.contains(wordlists[i - 1])) || (name.contains(wordlists[i + 1]))))
								|| (((content.contains(wordlists[i - 1])) || (content.contains(wordlists[i + 1])))))

						{
							wordlists[i - 1] = " ";
							wordlists[i] = " ";
							wordlists[i + 1] = " ";
							findOr = true;
						}

					}

					if (findOr == false) {
						break;
					}
				}
			}
			if (findOr == false) {
				continue;
			}
			// Check for the non-blank space element
			boolean pass = true;
			for (int x = 0; x < wordlists.length; x++) {

				boolean notcontain1 = true;
				boolean notcontain2 = true;
				if (!wordlists[x].equals(" ")) {

					String name = n.getTitle().toLowerCase();
					if (n instanceof ImageNote) {
						if (name.contains(wordlists[x])) {
							notcontain1 = false;
						}
					}
					if (n instanceof TextNote) {
						String content = n.getContent().toLowerCase();
						if (name.contains(wordlists[x]) || content.contains(wordlists[x])) {
							notcontain2 = false;
						}

					}
					if (notcontain1 && notcontain2) {
						pass = false;
						break;
					}

				}

			}
			if (pass == false) {
				continue;
			}

			note.add(n);

		}
		return note;

//		int i = 0;
//		for (i = 0; i < wordlists.length - 1; i++) {
//
//			if (wordlists[i].equals("or")) {
//				boolean findOr = false;
//				for (Note n : notes) {
//					String name = n.getTitle().toLowerCase();
//					if (n instanceof ImageNote) {
//						if (((name.contains(wordlists[i - 1])) || (name.contains(wordlists[i + 1])))) {
//							wordlists[i-1]=" ";
//							wordlists[i]=" ";
//							wordlists[i+1]=" ";
//							findOr = true;
//							
//						}
//					}
//					if (n instanceof TextNote) {
//						String content = n.getContent().toLowerCase();
//						if ((((name.contains(wordlists[i - 1])) || (name.contains(wordlists[i + 1]))))
//								|| (((content.contains(wordlists[i - 1])) || (content.contains(wordlists[i + 1])))))
//
//						{
//							wordlists[i-1]=" ";
//							wordlists[i]=" ";
//							wordlists[i+1]=" ";
//							findOr = true;
//						
//						}
//
//					}
//				}
//				if (findOr==false) {
//					return null;
//				}
//			}
//		}
//		for (i = 0; i < wordlists.length - 1; i++) {
//			if (!wordlists[i].equals(" ")) {
//				for (Note n : notes) {
//					String name = n.getTitle().toLowerCase();
//					if (n instanceof ImageNote) {
//						if (name.contains(wordlists[i])) {
//							findOr = true;
//							
//						}
//					}
//					if (n instanceof TextNote) {
//						String content = n.getContent().toLowerCase();
//						if ((((name.contains(wordlists[i - 1])) || (name.contains(wordlists[i + 1]))))
//								|| (((content.contains(wordlists[i - 1])) || (content.contains(wordlists[i + 1])))))
//
//						{
//							wordlists[i-1]=" ";
//							wordlists[i]=" ";
//							wordlists[i+1]=" ";
//							findOr = true;
//						
//						}
//
//					}
//				}
//				
//				
//				
//				
//			}
//			
//		}

//		ArrayList<Note> note = new ArrayList<Note>(); 
//		keywords = keywords.toLowerCase();
//		keywords=keywords.replace(" or "," ");
//		String[] wordlists=keywords.split(" ");
//		String first=wordlists[0];
//		String second=wordlists[1];
//		String third=wordlists[2];	
//		String fourth=wordlists[3];
//		for (Note n : notes) {
//			String name=n.getTitle().toLowerCase();
//			if(n instanceof ImageNote) {
//				if (((name.contains(first))||(name.contains(second)))&&((name.contains(third))||(name.contains(fourth)))) {
//					note.add(n);
//				}
//			}
//			else {
//				String content=n.getContent().toLowerCase();
//				if (
//				(((name.contains(first))||(name.contains(second)))&&((name.contains(third))||(name.contains(fourth))))||
//				(((content.contains(first))||(content.contains(second)))&&((content.contains(third))||(content.contains(fourth))))
//				)
//				{
//					note.add(n);
//				}
//				
//			}
//		
//		}
//		return note;

	}
}
