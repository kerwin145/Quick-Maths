import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class UserData implements Serializable{	

	public int[][] questionsCompleted = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}; //groups are the question types, inner brackets are the question difficulties
	public int[][] questionsCorrect = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}; //groups are the question types, inner brackets are the question difficulties
	public String[] difficultyText = {"Easy","Med","Hard","Insane"};
	public String[] typeText = {"Addition", "Subtraction", "Multiplication", "Division"}; 

	public boolean beginnerAdd = false, beginnerSub = false, beginnerMult = false, beginnerDiv = false,
			       intermediateAdd = false, intermediateSub = false, intermediateMult = false, intermediateDiv = false,
			       profAdd = false, profSub = false, profMult = false, profDiv = false,
			       challAdd = false, challSub = false, challMult = false, challDiv = false;
	
	public boolean[][] vanillaAchObtained = {{beginnerAdd, beginnerSub, beginnerMult, beginnerDiv},
											{intermediateAdd, intermediateSub, intermediateMult, intermediateDiv},
											{profAdd, profSub, profMult, profDiv},
											{challAdd, challSub, challMult, challDiv}};
	
}
