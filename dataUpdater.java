
public class dataUpdater{
	
	UserData uData;
	
	public dataUpdater(UserData uData){
		this.uData = uData;
	}
	
	private String[] difficultyText = {"Easy","Medium","Hard","Insane"};
	private String[] typeText = {"Addition", "Subtraction", "Multiplication", "Division"}; 

	
	public void updateQuestionComplete(int type, int difficulty){
		uData.questionsCompleted[type][difficulty] = uData.questionsCompleted[type][difficulty] + 1;
	}
	public void updateQuestionCorrect(int type, int difficulty){
		uData.questionsCorrect[type][difficulty] = uData.questionsCorrect[type][difficulty] + 1;
	}
	
	public void resetData() {
		int[][] questionsCompleted = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}; //groups are the question types, inner brackets are the question difficulties
		int[][] questionsCorrect = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}; //groups are the question types, inner brackets are the question difficulties
		int[][] vanillaAchLevel = {{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0}};
		double[][] timeAverageSum = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
		int[][] timeAverageCount = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
		double[][] recordTimeIndividual = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
		uData.questionsCompleted = questionsCompleted;
		uData.questionsCorrect = questionsCorrect;
		uData.vanillaAchLevel = vanillaAchLevel;
		uData.timeAverageSum = timeAverageSum;
		uData.timeAverageCount = timeAverageCount;
		uData.recordTimeIndividual = recordTimeIndividual;
		
	}
							
	public void updateTime(int type, int difficulty, double timeMillis){
		uData.tempTimeAverageSum[type][difficulty] += timeMillis;
		uData.tempTimeAverageCount[type][difficulty]++;
		
		uData.timeAverageSum[type][difficulty] += timeMillis;
		uData.timeAverageCount[type][difficulty]++;
		
		if(uData.recordTimeIndividual[type][difficulty] == 0 || timeMillis < uData.recordTimeIndividual[type][difficulty]) {
			uData.recordTimeIndividual[type][difficulty] = timeMillis;
		}
	}
	
	public void deleteRoundTimeData(){
		for(int i = 0; i < uData.timeAverageSum.length; i++){
			for(int j = 0; j< uData.timeAverageSum[i].length; j++){
				uData.timeAverageSum[i][j] -= uData.tempTimeAverageSum[i][j];
				uData.timeAverageCount[i][j] -= uData.tempTimeAverageCount[i][j];				
			}
		}
	}
	
}
