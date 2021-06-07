import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
	
	GUI gui;
	LevelSelect levSelectPage;
	LevelSelectSpecial levSelectPageSpecial;
	TitlePage titlePage;
	QuestionPageNumber qPage;
	QuestionPageYesNo qPageYN;
	ResultsPage results;
	AchievementPages achPage;
	int mx = -1, my = -1;
		
	public MouseInput(GUI gui) {
		this.gui = gui;
		levSelectPage = gui.getLevSelect();
		levSelectPageSpecial = gui.getLevelSelectSpecial();
		titlePage = gui.getTitlePage();
		qPage = gui.getQuestionPage();
		qPageYN = gui.getQuestionPageYesNo();
		results = gui.getResultsPage();
		achPage = gui.getAchPage();
	}
	
	public void mouseClicked(MouseEvent e) {
		mx = e.getX(); 
		my = e.getY();
		
		if (gui.State == gui.State.TITLE) {
			
			if(clickInBounds(titlePage.playButton)) { gui.State = gui.State.LEVELSELECT;}
			if(clickInBounds(titlePage.playSpecialButton)) { gui.State = gui.State.LEVELSELECTSPECIAL;}
			if(clickInBounds(titlePage.resumeButton)){
				//System.out.println("Set Finished " + titlePage.setFinished + "Set Special Finished " + titlePage.setSpecialFinished);
				if(titlePage.setFinished && !titlePage.setSpecialFinished){ //if you have a special round going on
					if(titlePage.questionPage == titlePage.questionPage.Special){
						gui.State = gui.State.QUESTIONROUNDNUMBER;
					}
					else if(titlePage.questionPage == titlePage.questionPage.SpecialYN){
						gui.State = gui.State.QUESTIONROUNDYESNO;
					}
				}
				else if(!titlePage.setFinished && titlePage.setSpecialFinished){//if you have a normal round going on 
					gui.State = gui.State.QUESTIONROUNDNUMBER;
				}
				if((titlePage.questionPage == titlePage.questionPage.Special || titlePage.questionPage == titlePage.questionPage.Normal) && !qPage.askForConfirm) {
					qPage.timerUnPause();
					qPage.timeQuestionStartMillis += qPage.deltaMillisFix;
				}
				else if(titlePage.questionPage == titlePage.questionPage.SpecialYN && !qPageYN.askForConfirm) {
					qPageYN.timerUnPause();
					qPageYN.timeQuestionStartMillis += qPageYN.deltaMillisFix;
				}

			}
				
			if(clickInBounds(titlePage.achievementsButton)) {gui.State = gui.State.ACHIEVEMENTS;}
			if(clickInBounds(titlePage.quitButton))	{System.exit(1);}
		
		}//title
		
		else if (gui.State == gui.State.LEVELSELECT) {
			
			levSelectPage.numQuestionsInput.attemptFocus(mx, my);
			
			if(clickInBounds(levSelectPage.HomePage)) {
				gui.State = gui.State.TITLE;
			}
			if(clickInBounds(levSelectPage.AddChoose)) {levSelectPage.AddChosen();}
			if(clickInBounds(levSelectPage.SubChoose)) {levSelectPage.SubChosen();}
			if(clickInBounds(levSelectPage.MultChoose)) {levSelectPage.MultChosen();}
			if(clickInBounds(levSelectPage.DivChoose)) {levSelectPage.DivChosen();}

			if(levSelectPage.getQChosen()[1])
				if(clickInBounds(levSelectPage.OnlyPositive)){levSelectPage.onlyPositive = !levSelectPage.onlyPositive;}
			if (levSelectPage.getQChosen()[3])
				if(clickInBounds(levSelectPage.PerfectDivisors)){levSelectPage.perfectDivisors = !levSelectPage.perfectDivisors;}
						
			if(clickInBounds(levSelectPage.easyDif)) {levSelectPage.setDifficulty(0);}
			if(clickInBounds(levSelectPage.medDif)) {levSelectPage.setDifficulty(1);}
			if(clickInBounds(levSelectPage.hardDif)) {levSelectPage.setDifficulty(2);}
			if(clickInBounds(levSelectPage.insaneDif)) {levSelectPage.setDifficulty(3);}
	
			if(clickInBounds(levSelectPage.GenerateSet) && levSelectPage.isSetReady()) {
				qPage.initializeRound();
				gui.State = gui.State.QUESTIONROUNDNUMBER;
			}
			
			if(clickInBounds(levSelectPage.InfoBox)) {
				levSelectPage.renderHelp = !levSelectPage.renderHelp;
			}
			
			//System.out.println("Only Positive" + levSelectPage.onlyPositive + ". Perect Divisor " + levSelectPage.perfectDivisors);

		}//levelselect
		
		else if (gui.State == gui.State.LEVELSELECTSPECIAL) {
			levSelectPageSpecial.numQuestionsInput.attemptFocus(mx, my);

			//i should probably put these into a list. 
			if(clickInBounds(levSelectPageSpecial.multBy2)){
				levSelectPageSpecial.specialQuestionNum2 = 2;
				levSelectPageSpecial.specialQTypeChosen = 2;//2 for multiplication
				levSelectPageSpecial.specialQChosenIndex = 0;
			}
			else if(clickInBounds(levSelectPageSpecial.multBy3)){
				levSelectPageSpecial.specialQuestionNum2 = 3;
				levSelectPageSpecial.specialQTypeChosen = 2;//2 for multiplication
				levSelectPageSpecial.specialQChosenIndex = 1;
			}
			else if(clickInBounds(levSelectPageSpecial.multBy4)){
				levSelectPageSpecial.specialQuestionNum2 = 4;
				levSelectPageSpecial.specialQTypeChosen = 2;//2 for multiplication
				levSelectPageSpecial.specialQChosenIndex = 2;
			}
			else if(clickInBounds(levSelectPageSpecial.multBy5)){
				levSelectPageSpecial.specialQuestionNum2 = 5;
				levSelectPageSpecial.specialQTypeChosen = 2;//2 for multiplication
				levSelectPageSpecial.specialQChosenIndex = 3;
			}
			else if(clickInBounds(levSelectPageSpecial.divBy2)){
				levSelectPageSpecial.specialQuestionNum2 = 2;
				levSelectPageSpecial.specialQTypeChosen = 3;//3 for division
				levSelectPageSpecial.specialQChosenIndex = 4;
			}
			else if(clickInBounds(levSelectPageSpecial.divBy3)){
				levSelectPageSpecial.specialQuestionNum2 = 3;
				levSelectPageSpecial.specialQTypeChosen = 3;//3 for division
				levSelectPageSpecial.specialQChosenIndex = 5;
			}
			else if(clickInBounds(levSelectPageSpecial.divBy4)){
				levSelectPageSpecial.specialQuestionNum2 = 4;
				levSelectPageSpecial.specialQTypeChosen = 3;//3 for division
				levSelectPageSpecial.specialQChosenIndex = 6;
			}
			else if(clickInBounds(levSelectPageSpecial.divBy5)){
				levSelectPageSpecial.specialQuestionNum2 = 5;
				levSelectPageSpecial.specialQTypeChosen = 3;//3 for division
				levSelectPageSpecial.specialQChosenIndex = 7;
			}
			
			else if (clickInBounds(levSelectPageSpecial.isItDivisible)){
				levSelectPageSpecial.specialQChosenIndex = 8;
			}
			else if (clickInBounds(levSelectPageSpecial.squaringNumberThatEndIn5)){
				levSelectPageSpecial.specialQChosenIndex = 9;
			}
			else if (clickInBounds(levSelectPageSpecial.multBy11)){
				levSelectPageSpecial.specialQChosenIndex = 10;
			}
				
			else if(clickInBounds(levSelectPageSpecial.GenerateSet) && levSelectPageSpecial.isSetReady()) {
				if(levSelectPageSpecial.specialQChosenIndex <= 7){
					qPage.initializeRoundSpecial();
					gui.State = gui.State.QUESTIONROUNDNUMBER;
				}
				if(levSelectPageSpecial.specialQChosenIndex == 8){
					qPageYN.initializeRound();
					gui.State = gui.State.QUESTIONROUNDYESNO;
				}
			}
			
			else if(clickInBounds(levSelectPageSpecial.easyDif)) levSelectPageSpecial.setDifficulty(0);
			else if(clickInBounds(levSelectPageSpecial.medDif)) levSelectPageSpecial.setDifficulty(1);
			else if(clickInBounds(levSelectPageSpecial.hardDif)) levSelectPageSpecial.setDifficulty(2);
			else if(clickInBounds(levSelectPageSpecial.insaneDif)) levSelectPageSpecial.setDifficulty(3);
			
			if(clickInBounds(levSelectPageSpecial.HomePage))gui.State = gui.State.TITLE;
				
		}
		
		else if (gui.State == gui.State.QUESTIONROUNDNUMBER) {
			
			qPage.inputTextAnswer.attemptFocus(mx, my);
			
			if(clickInBounds(qPage.HomePage)) {
				gui.State = gui.State.TITLE;
				qPage.timerPause();
			}
			if(clickInBounds(qPage.submitAnswer)) {qPage.submitAnswer();}
			if(clickInBounds(qPage.InfoBox)){qPage.renderHelp = !qPage.renderHelp;}
			
			if(clickInBounds(qPage.finishSet)){		
				qPage.finishClicked = true; 
				qPage.genSkipSetPassword();
			}
			else{qPage.finishClicked = false;}
			
			int x1 = (int)(gui.WIDTH * gui.SCALE - qPage.HomePage.width*2.2);
			int y1 = (int)(qPage.HomePage.y + qPage.HomePage.height * 3);
			if(clickInBounds(new Rectangle(x1, y1 , qPage.timerTextLength, qPage.timerTextHeight))){
				qPage.timerHidden = !qPage.timerHidden;
				//System.out.println("@MouseInput timerHidden number clicked!");
			}
		
		}//question round number
		
		else if (gui.State == gui.State.QUESTIONROUNDYESNO) {
			
			if(clickInBounds(qPageYN.Yes)) qPageYN.yesNo = 1;
			if(clickInBounds(qPageYN.No)) qPageYN.yesNo = 0;
	
			if(clickInBounds(qPageYN.HomePage)) {
				gui.State = gui.State.TITLE;
				qPage.timerPause();
			}
			if(clickInBounds(qPageYN.submitAnswer)) {qPageYN.submitAnswer();}
			if(clickInBounds(qPageYN.InfoBox)){qPageYN.renderHelp = !qPageYN.renderHelp;}
			if(clickInBounds(qPageYN.AutoConfirm)){qPageYN.autoConfirm = !qPageYN.autoConfirm;}
			
			int x1 = (int)(gui.WIDTH * gui.SCALE - qPage.HomePage.width*2.2);
			int y1 = (int)(qPage.HomePage.y + qPage.HomePage.height * 3);
			if(clickInBounds(new Rectangle(x1, y1 , qPageYN.timerTextLength, qPageYN.timerTextHeight)))
				qPageYN.timerHidden = !qPageYN.timerHidden;
			
			if(clickInBounds(qPageYN.finishSet)){		
				qPageYN.finishClicked = true; 
				qPageYN.genSkipSetPassword();
			}
			else{qPageYN.finishClicked = false;}
			
			
		
		}//question round yn
		
		else if(gui.State == gui.State.RESULTS){
			
			if(clickInBounds(results.HomePage)) {gui.State = gui.State.TITLE;}
			if(clickInBounds(results.playButton)) {
				if(titlePage.questionPage == titlePage.questionPage.Normal)
					gui.State = gui.State.LEVELSELECT;
				else if(titlePage.questionPage == titlePage.questionPage.Special)
					gui.State = gui.State.LEVELSELECTSPECIAL;
				else if(titlePage.questionPage ==titlePage.questionPage.SpecialYN)
					gui.State = gui.State.LEVELSELECTSPECIAL;
			}
			
		}//results
		
		else if (gui.State == gui.State.ACHIEVEMENTS){
						
			if(achPage.getPageIndex() == 0){
				for(int type = 0; type < achPage.achievementButtons.size(); type++) {
					for(int operation = 0; operation < achPage.achievementButtons.get(type).size(); operation++) {
						if(clickInBounds(achPage.achievementButtons.get(type).get(operation))){
							achPage.setSelectedAchievement(achPage.getVanillaAchievementList().get(type).get(operation));
						}	
					}
				}				
			}
			
			if(clickInBounds(achPage.HomePage)) {gui.State = gui.State.TITLE;};
			if(clickInBounds(achPage.nextSlide) && achPage.pageIndex < achPage.numPages-1) {achPage.setPageIndex(achPage.getPageIndex() + 1);}
			if(clickInBounds(achPage.prevSlide) && achPage.pageIndex > 0) {achPage.setPageIndex(achPage.getPageIndex() - 1);}

		}//achievements

	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public boolean clickInBounds(Rectangle rectangle) {
		if((mx >= rectangle.x && mx <= rectangle.x + rectangle.width) && (my >= rectangle.y && my <= rectangle.y + rectangle.height)){
			return true;
		}
		else return false;
	}
	
}
