import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class UserData implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4346584688278976605L;
	public int[][] questionsCompleted = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}; //groups are the question types, inner brackets are the question difficulties
	public int[][] questionsCorrect = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}; //groups are the question types, inner brackets are the question difficulties
	public String[] difficultyText = {"Easy","Med","Hard","Insane"};
	public String[] typeText = {"Addition", "Subtraction", "Multiplication", "Division"}; 
	public int[][] vanillaAchLevel = {{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0}};
	
	public void resetData() {
		int[][] questionsCompleted = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}; //groups are the question types, inner brackets are the question difficulties
		int[][] questionsCorrect = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}; //groups are the question types, inner brackets are the question difficulties
		int[][] vanillaAchLevel = {{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0}};
		this.questionsCompleted = questionsCompleted;
		this.questionsCorrect = questionsCorrect;
		this.vanillaAchLevel = vanillaAchLevel;
		
	}
											
}
