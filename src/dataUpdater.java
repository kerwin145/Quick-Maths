
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

}
