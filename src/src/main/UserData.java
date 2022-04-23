package src.main;
import java.io.Serializable;

import src.main.Achievements.AllAchievements;



public class UserData implements Serializable
{
    private static final long serialVersionUID = 4346584688278976605L;
    public int[][] questionsCompleted  = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    public int[][] questionsCorrect = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    public int[] operationsScoreEquivalent = {0,0,0,0};
    
    public long[][] timeAverageSum  = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    public int[][] timeAverageCount  = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    public long[][] tempTimeAverageSum  = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    public int[][] tempTimeAverageCount  = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};;
    
    public long[][] recordTimeIndividual  = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    public int[][] vanillaAchLevel = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    
	public int numAchTotal = 0;
	public int numAchVanilla = 0;
   
    
    
    public String[] difficultyText = new String[] { "Easy", "Med", "Hard", "Insane" };
    public String[] typeText = new String[] { "Addition", "Subtraction", "Multiplication", "Division" };
    
    public UserData() {
    
      
    }
    
 
}
