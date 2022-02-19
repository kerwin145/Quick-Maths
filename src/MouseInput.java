import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import k_Methods.MoColors;
import k_Methods.Rectangle_;
import k_Methods.Rectangle_.gradientFormat;

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
				}
				else if(titlePage.questionPage == titlePage.questionPage.SpecialYN && !qPageYN.askForConfirm) {
					qPageYN.timerUnPause();
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

			if(clickInBounds(levSelectPage.easyDif)) {levSelectPage.setDifficulty(0);}
			if(clickInBounds(levSelectPage.medDif)) {levSelectPage.setDifficulty(1);}
			if(clickInBounds(levSelectPage.hardDif)) {levSelectPage.setDifficulty(2);}
			if(clickInBounds(levSelectPage.insaneDif)) {levSelectPage.setDifficulty(3);}
			
			//if(clickInBounds(levSelectPage.challenge0)) {levSelectPage.setChallengeLvl(0);};
			if(clickInBounds(levSelectPage.challenge1)) {levSelectPage.setChallengeLvl(1);};
			if(clickInBounds(levSelectPage.challenge2)) {levSelectPage.setChallengeLvl(2);};
			if(clickInBounds(levSelectPage.challenge3)) {levSelectPage.setChallengeLvl(3);};

			if(clickInBounds(levSelectPage.Endless)){levSelectPage.endlessQuestions  = !levSelectPage.endlessQuestions;}

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
				levSelectPageSpecial.setQuestionType(0);
			}
			else if(clickInBounds(levSelectPageSpecial.multBy3)){
				levSelectPageSpecial.setQuestionType(1);
			}
			else if(clickInBounds(levSelectPageSpecial.multBy4)){
				levSelectPageSpecial.setQuestionType(2);
			}
			else if(clickInBounds(levSelectPageSpecial.multBy5)){
				levSelectPageSpecial.setQuestionType(3);
			}
			
			else if(clickInBounds(levSelectPageSpecial.divBy2)){
				levSelectPageSpecial.setQuestionType(4);
			}
			else if(clickInBounds(levSelectPageSpecial.divBy3)){
				levSelectPageSpecial.setQuestionType(5);
			}
			else if(clickInBounds(levSelectPageSpecial.divBy4)){
				levSelectPageSpecial.setQuestionType(6);
			}
			else if(clickInBounds(levSelectPageSpecial.divBy5)){
				levSelectPageSpecial.setQuestionType(7);
			}
			
			else if (clickInBounds(levSelectPageSpecial.isItDivisible)){
				levSelectPageSpecial.setQuestionType(8);
			}
			else if (clickInBounds(levSelectPageSpecial.squaringNumberThatEndIn5)){
				levSelectPageSpecial.setQuestionType(9);
			}
			else if (clickInBounds(levSelectPageSpecial.multBy11)){
				levSelectPageSpecial.setQuestionType(10);
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

			if(clickInBounds(levSelectPageSpecial.Endless)){levSelectPageSpecial.endlessQuestions  = !levSelectPageSpecial.endlessQuestions;}

			if(clickInBounds(levSelectPageSpecial.HomePage))gui.State = gui.State.TITLE;
				
		}
		
		else if (gui.State == gui.State.QUESTIONROUNDNUMBER) {
			
			qPage.inputTextAnswer.attemptFocus(mx, my);
			
			if(clickInBounds(qPage.HomePage)) {
				gui.State = gui.State.TITLE;
				if((titlePage.questionPage == titlePage.questionPage.Special || titlePage.questionPage == titlePage.questionPage.Normal) && !qPage.askForConfirm) {
					qPage.timerPause();
					qPage.timeQuestionStartMillis += qPage.deltaMillisFix;
				}
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
			
			if(clickInBounds(qPageYN.yes)) qPageYN.yesNo = 1;
			if(clickInBounds(qPageYN.no)) qPageYN.yesNo = 0;
	
			if(clickInBounds(qPageYN.HomePage)) {
				gui.State = gui.State.TITLE;
				if(titlePage.questionPage == titlePage.questionPage.SpecialYN && !qPageYN.askForConfirm) {
					qPageYN.timerPause();
					qPageYN.timeQuestionStartMillis += qPageYN.deltaMillisFix;
				}			
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
			if(clickInBounds(results.newRound)) {
				if(titlePage.questionPage == titlePage.questionPage.Normal)
					gui.State = gui.State.LEVELSELECT;
				else if(titlePage.questionPage == titlePage.questionPage.Special)
					gui.State = gui.State.LEVELSELECTSPECIAL;
				else if(titlePage.questionPage ==titlePage.questionPage.SpecialYN)
					gui.State = gui.State.LEVELSELECTSPECIAL;
			}
			if(clickInBounds(results.rollBack)){
				if(!results.rolledBack && qPage.askToRollBack){
					results.rollBack();
					results.rolledBack = true;
				}
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
