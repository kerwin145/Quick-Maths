import java.io.Serializable;

public class Achievement {
	
	private static final long serialVersionUID = 1L;
	String displayText;
	String explanation;
	boolean unlocked;
	
	public Achievement(String displayText, String explanation, boolean unlocked) {
		this.displayText = displayText;
		this.explanation= explanation;
		this.unlocked = unlocked;
	}
	//detection will be done outside of this object. I won't be making an abstract method.

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public boolean isUnlocked() {
		return unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}

}
