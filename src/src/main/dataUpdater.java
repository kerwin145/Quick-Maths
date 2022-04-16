package src.main;
// 
// Decompiled by Procyon v0.5.36
// 

public class dataUpdater
{
    UserData uData;

    private int[] pointEquivalencies = {1,5,20,60};
    //weights: easy is 1 point, medium is 5 points, hard is 20 points, insane is 60
    
    public dataUpdater( UserData uData) {

        this.uData = uData;
    }
    
    public void updateQuestionComplete(int type, int difficulty) {
        ++uData.questionsCompleted[type][difficulty];
    }
    
    public void updateQuestionCorrect(int type, int difficulty) {
        ++uData.questionsCorrect[type][difficulty];
        uData.operationsScoreEquivalent[type] =  uData.operationsScoreEquivalent[type] + pointEquivalencies[difficulty]; //apparently += doesn't work
    }
    
    public void resetData() {
        int[][] questionsCompleted = { new int[4], new int[4], new int[4], new int[4] };
        int[][] questionsCorrect = { new int[4], new int[4], new int[4], new int[4] };
        int[] operationsScoreEquivalent = new int[4];
        int[][] vanillaAchLevel = { new int[4], new int[4], new int[4], new int[4] };
        long[][] timeAverageSum = { new long[4], new long[4], new long[4], new long[4] };
        int[][] timeAverageCount = { new int[4], new int[4], new int[4], new int[4] };
        long[][] recordTimeIndividual = { new long[4], new long[4], new long[4], new long[4] };
        uData.questionsCompleted = questionsCompleted;
        uData.questionsCorrect = questionsCorrect;
        uData.operationsScoreEquivalent = operationsScoreEquivalent;
        uData.vanillaAchLevel = vanillaAchLevel;
        uData.timeAverageSum = timeAverageSum;
        uData.timeAverageCount = timeAverageCount;
        uData.recordTimeIndividual = recordTimeIndividual;
    }
    
    public void updateTime( int type,  int difficulty,  long timeMillis) {
        long[] array = uData.tempTimeAverageSum[type];
        array[difficulty] += timeMillis;
        int[] array2 = uData.tempTimeAverageCount[type];
        ++array2[difficulty];
        long[] array3 = uData.timeAverageSum[type];
        array3[difficulty] += timeMillis;
        int[] array4 = uData.timeAverageCount[type];
        ++array4[difficulty];
        if (uData.recordTimeIndividual[type][difficulty] == 0L || timeMillis < uData.recordTimeIndividual[type][difficulty]) {
            uData.recordTimeIndividual[type][difficulty] = timeMillis;
        }
    }
    
    public void deleteRoundTimeData() {
        for (int i = 0; i < uData.timeAverageSum.length; ++i) {
            for (int j = 0; j < uData.timeAverageSum[i].length; ++j) {
                 long[] array = uData.timeAverageSum[i];
                 int n = j;
                array[n] -= uData.tempTimeAverageSum[i][j];
                 int[] array2 = uData.timeAverageCount[i];
                 int n2 = j;
                array2[n2] -= uData.tempTimeAverageCount[i][j];
            }
        }
    }
}
