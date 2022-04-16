package src.main;
import java.io.Serializable;



public class UserData implements Serializable
{
    private static final long serialVersionUID = 4346584688278976605L;
    public int[][] questionsCompleted;
    public int[][] questionsCorrect;
    public int[] operationsScoreEquivalent;
    
    public long[][] timeAverageSum;
    public int[][] timeAverageCount;
    public long[][] tempTimeAverageSum;
    public int[][] tempTimeAverageCount;
    
    public long[][] recordTimeIndividual;
    public int[][] vanillaAchLevel;
   
    
    
    public String[] difficultyText = new String[] { "Easy", "Med", "Hard", "Insane" };
    public String[] typeText = new String[] { "Addition", "Subtraction", "Multiplication", "Division" };
    
    public UserData() {
        questionsCompleted = new int[][] { new int[4], new int[4], new int[4], new int[4] };
        questionsCorrect = new int[][] { new int[4], new int[4], new int[4], new int[4] };
        operationsScoreEquivalent = new int[4];
        timeAverageSum = new long[][] { new long[4], new long[4], new long[4], new long[4] };
        timeAverageCount = new int[][] { new int[4], new int[4], new int[4], new int[4] };
        tempTimeAverageSum = new long[][] { new long[4], new long[4], new long[4], new long[4] };
        tempTimeAverageCount = new int[][] { new int[4], new int[4], new int[4], new int[4] };
        recordTimeIndividual = new long[][] { new long[4], new long[4], new long[4], new long[4] };
        vanillaAchLevel = new int[][] { new int[4], new int[4], new int[4], new int[4] };
      
    }
}
