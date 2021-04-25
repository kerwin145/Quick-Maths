
public class AchievementCheck {
	
	QuestionPage qPage;
	UserData uData;
	ResultsPage resultsPage;
	GUI gui;
	
	//for achievements that rely on the results/conditions of the recently completed problem set
	//called on the results page initialize
	public AchievementCheck(ResultsPage resultsPage, GUI gui) {
		this.resultsPage = resultsPage;
		this.gui = gui;
		qPage = gui.getQuestionPage();
		this.uData = gui.getUdata();
		
	}

	//for achievements that rely on ur data. This will be checked after every question.
	public AchievementCheck(UserData uData) {
		this.uData = uData;
	}

	public void checkVanillaAch() {
		if((qPage.getQuestionTypes().size() == 1) && (qPage.getNumQuestions() >= 10) && (resultsPage.getPercentCorrect() >= 0.6)) { //only contains one question type
			for(int type = 0; type < uData.vanillaAchObtained.length; type++) {
				for(int operation = 0; operation < uData.vanillaAchObtained.length; operation++) {
					if((qPage.getDifficulty() == type) && qPage.getQuestionTypes().contains(operation)) {
						uData.vanillaAchObtained[type][operation] = true;
						System.out.println("Achievement conidition at " + type + ", " + operation+ ": " + uData.vanillaAchObtained[type][operation]);
					}
					
				}
			}
			if(qPage.getDifficulty() == 0) {
				if(qPage.getDifficultyList().contains("0")) uData.vanillaAchObtained[0][0] = true;
				
			}
		}
		
		
	}

}
