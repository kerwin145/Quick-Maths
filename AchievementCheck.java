
public class AchievementCheck {
	
	QuestionPageNumber qPage;
	UserData uData;
	ResultsPage resultsPage;
	GUI gui;
	
	public double[] vanillaSetScoreReq = {0.7, 0.8, 0.95};
	public int[] vanillaSetLengthReq = {10, 15, 20};
	
	Achievement tempAch;
	
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
		/*if one is question type is selected.
		* loop through the array of vainlla operations:
		* Get achievement list from achievement pages and set a temp achievement to i index.
		* If question set operation equals the operation, difficulty equals the type, score of the question set is greater equal to the vanillaSetScoreReq[achievement.stage], 
		* and set length greater than or equal to vanillaSetLengthReq[achievement.stage] then grant the achievement
		* If the achievement.currentStage is less than stages, then level the stage up and update the explanation text. 
		*/
		
		if(qPage.getQuestionTypes().size() == 1) {
			for(int type = 0; type < 4; type++) {
				for(int operation = 0; operation < 4; operation++) {
					tempAch = gui.getAchPage().getVanillaAchievementList().get(type).get(operation);
					//if the operation and type match the achievement, and question set and results satisfy requirements
					if(qPage.getQuestionTypes().contains(operation) && qPage.getDifficulty() == type
							&& resultsPage.getPercentCorrect() >= vanillaSetScoreReq[tempAch.getCurrentStage()] 
							&& qPage.getNumQuestions() >= vanillaSetLengthReq[tempAch.getCurrentStage()]) {
						
						uData.vanillaAchLevel[type][operation] = uData.vanillaAchLevel[type][operation] + 1;
						if(tempAch.getCurrentStage() < tempAch.getStages()) {
							tempAch.updateCurrentStage();
						}	
					}//end check req
				}//end inner for
			}//end outer for
		}//end if 
		
		
	}

}
