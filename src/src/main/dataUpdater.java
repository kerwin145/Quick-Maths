package src.main;
// 
// Decompiled by Procyon v0.5.36
// 

public class dataUpdater
{
    UserData uData;
    private String[] difficultyText;
    private String[] typeText;
    
    public dataUpdater(final UserData uData) {
        this.difficultyText = new String[] { "Easy", "Medium", "Hard", "Insane" };
        this.typeText = new String[] { "Addition", "Subtraction", "Multiplication", "Division" };
        this.uData = uData;
    }
    
    public void updateQuestionComplete(final int type, final int difficulty) {
        ++this.uData.questionsCompleted[type][difficulty];
    }
    
    public void updateQuestionCorrect(final int type, final int difficulty) {
        ++this.uData.questionsCorrect[type][difficulty];
    }
    
    public void resetData() {
        final int[][] questionsCompleted = { new int[4], new int[4], new int[4], new int[4] };
        final int[][] questionsCorrect = { new int[4], new int[4], new int[4], new int[4] };
        final int[][] vanillaAchLevel = { new int[4], new int[4], new int[4], new int[4] };
        final long[][] timeAverageSum = { new long[4], new long[4], new long[4], new long[4] };
        final int[][] timeAverageCount = { new int[4], new int[4], new int[4], new int[4] };
        final long[][] recordTimeIndividual = { new long[4], new long[4], new long[4], new long[4] };
        this.uData.questionsCompleted = questionsCompleted;
        this.uData.questionsCorrect = questionsCorrect;
        this.uData.vanillaAchLevel = vanillaAchLevel;
        this.uData.timeAverageSum = timeAverageSum;
        this.uData.timeAverageCount = timeAverageCount;
        this.uData.recordTimeIndividual = recordTimeIndividual;
    }
    
    public void updateTime(final int type, final int difficulty, final long timeMillis) {
        final long[] array = this.uData.tempTimeAverageSum[type];
        array[difficulty] += timeMillis;
        final int[] array2 = this.uData.tempTimeAverageCount[type];
        ++array2[difficulty];
        final long[] array3 = this.uData.timeAverageSum[type];
        array3[difficulty] += timeMillis;
        final int[] array4 = this.uData.timeAverageCount[type];
        ++array4[difficulty];
        if (this.uData.recordTimeIndividual[type][difficulty] == 0L || timeMillis < this.uData.recordTimeIndividual[type][difficulty]) {
            this.uData.recordTimeIndividual[type][difficulty] = timeMillis;
        }
    }
    
    public void deleteRoundTimeData() {
        for (int i = 0; i < this.uData.timeAverageSum.length; ++i) {
            for (int j = 0; j < this.uData.timeAverageSum[i].length; ++j) {
                final long[] array = this.uData.timeAverageSum[i];
                final int n = j;
                array[n] -= this.uData.tempTimeAverageSum[i][j];
                final int[] array2 = this.uData.timeAverageCount[i];
                final int n2 = j;
                array2[n2] -= this.uData.tempTimeAverageCount[i][j];
            }
        }
    }
}
