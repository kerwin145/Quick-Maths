import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.36
// 

public class UserData implements Serializable
{
    private static final long serialVersionUID = 4346584688278976605L;
    public int[][] questionsCompleted;
    public int[][] questionsCorrect;
    public long[][] timeAverageSum;
    public int[][] timeAverageCount;
    public long[][] tempTimeAverageSum;
    public int[][] tempTimeAverageCount;
    public long[][] recordTimeIndividual;
    public int[][] vanillaAchLevel;
    public String[] difficultyText;
    public String[] typeText;
    
    public UserData() {
        this.questionsCompleted = new int[][] { new int[4], new int[4], new int[4], new int[4] };
        this.questionsCorrect = new int[][] { new int[4], new int[4], new int[4], new int[4] };
        this.timeAverageSum = new long[][] { new long[4], new long[4], new long[4], new long[4] };
        this.timeAverageCount = new int[][] { new int[4], new int[4], new int[4], new int[4] };
        this.tempTimeAverageSum = new long[][] { new long[4], new long[4], new long[4], new long[4] };
        this.tempTimeAverageCount = new int[][] { new int[4], new int[4], new int[4], new int[4] };
        this.recordTimeIndividual = new long[][] { new long[4], new long[4], new long[4], new long[4] };
        this.vanillaAchLevel = new int[][] { new int[4], new int[4], new int[4], new int[4] };
        this.difficultyText = new String[] { "Easy", "Med", "Hard", "Insane" };
        this.typeText = new String[] { "Addition", "Subtraction", "Multiplication", "Division" };
    }
}
