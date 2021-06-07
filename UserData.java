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

	public double[][] timeAverageSum = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
	public int[][] timeAverageCount = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
	public double[][] tempTimeAverageSum = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
	public int[][] tempTimeAverageCount = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
	
	public double[][] recordTimeIndividual = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
	
	public int[][] vanillaAchLevel = {{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0}};

	public String[] difficultyText = {"Easy","Med","Hard","Insane"};
	public String[] typeText = {"Addition", "Subtraction", "Multiplication", "Division"}; 
					
}
