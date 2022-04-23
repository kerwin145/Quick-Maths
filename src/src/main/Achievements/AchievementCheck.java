package src.main.Achievements;

import src.main.GUI;
import src.main.QuestionPageNumber;
import src.main.TitlePage;
import src.main.UserData;

public class AchievementCheck {
	
	QuestionPageNumber qPage;
	UserData uData;
	GUI gui;
	TitlePage titlePage;
	AllAchievements allAch;
	

	public double[] vanillaSetScoreReq = {0.7, 0.75, .8, .85, .9};
	public int[] vanillaSetLengthReq = {10, 15, 20, 25, 30};
	//public int[] vanillaSetLengthReq = {1,2,3,4,5};
	
	
    
		
	//for achievements that rely on the results/conditions of the recently completed problem set
	//called on the results page initialize
	public AchievementCheck(GUI gui) {
		this.gui = gui;
		titlePage = gui.getTitlePage();
		this.uData = gui.getUdata();
		
	}

	private void updateVanillaAch() {
		qPage = gui.getQuestionPage();
		allAch = gui.getAchMenu().getAllAchievements();

		Achievement tempAch;
		/*if one is question type is selected.
		* loop through the array of vanilla operations:
		* Get achievement list from achievement pages and set a temp achievement to i index.
		* If question set operation equals the operation, difficulty equals the type, score of the question set is greater equal to the vanillaSetScoreReq[achievement.stage], 
		* and set length greater than or equal to vanillaSetLengthReq[achievement.stage] then grant the achievement
		* If the achievement.currentStage is less than stages, then level the stage up and update the explanation text. 
		*/
		
		if(qPage.getQuestionTypes().size() == 1 && titlePage.questionPage == titlePage.questionPage.Normal) {
			for(int type = 0; type < 4; type++) {
				for(int operation = 0; operation < 4; operation++) {
					tempAch = allAch.vanillaAchievements.get(type).get(operation);
					//if the operation and type match the achievement, and question set and results satisfy requirements
					if(qPage.getQuestionTypes().contains(operation) && qPage.getDifficulty() == type
							&& tempAch.stage < tempAch.numStages 
							&& qPage.getAccuracy() >= vanillaSetScoreReq[tempAch.getStage()] 
							//note current question-1 gives the number of completed questions
							&& qPage.quesionsCompleted >= vanillaSetLengthReq[tempAch.getStage()]) {

						
						//update vanilla achievement list and data
						if(tempAch.stageUp()) {
							gui.getNotificationManager().addNotification("Vanilla Achievement: " + AllAchievements.vanillaAchievements.get(type).get(operation).getFullName() + " completed!");
							AllAchievements.numAchVanilla++;
							AllAchievements.numAchTotal++;
							uData.vanillaAchLevel[type][operation] = tempAch.stage;
							uData.numAchTotal++;
							uData.numAchVanilla++;
						}
						

					}//end check req
				}//end inner for
			}//end outer for
		}//end if 
		
		
	}
	
	public void updateAchievements() {
		updateVanillaAch();
	}

}