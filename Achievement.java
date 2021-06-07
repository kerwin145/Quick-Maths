import java.io.Serializable;
import java.util.ArrayList;

public class Achievement {
	
	private static final long serialVersionUID = 1L;
	String displayText;
	ArrayList<String> explanation = new ArrayList<String>();
	int stages, currentStage = 0; //0 means you haven't unlocked it. 
	
	String[] romanNumerals = {"I", "II", "III", "IV", "V"};//annoying because index starts at 0

	public Achievement(String displayText, String explanation) {
		this.displayText = displayText;
		this.explanation.add(explanation);
		stages = 0;
	}
	//detection will be done outside of this object. I won't be making an abstract method.

	public Achievement(String displayText, ArrayList<String> explanation, int stages) {
		this.displayText = displayText;
		this.explanation = explanation;
		this.stages = stages; //account for array index starting at 0
	}


	public int getCurrentStage() {
		return currentStage;
	}
	
	public void setCurrentStage(int cStage) {
		currentStage = cStage;
	}

	public void updateCurrentStage() {
		currentStage++;
	}

	public int getStages() {
		return stages;
	}
	public String getDisplayText() {
		if(currentStage < stages)
			return displayText + " " + romanNumerals[currentStage];
		else return "**" + displayText + "**";
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public void setExplanation(ArrayList<String> explanation) {
		this.explanation = explanation;
	}

	public String getExplanation(){
		if(currentStage < stages)
			return explanation.get(currentStage);
		else return "COMPLETE!";
	}
	
	public void setExplanation(String explanation) {
		this.explanation.set(0, explanation);
	}


}
