package base;

public class TextNote extends Note{
	String content;
	public TextNote(String title){
		super(title);
	}
	public String getContent() {
		return content;
	}
	public TextNote(String title, String content) {
		super(title);
		this.content=content;
	}

}
