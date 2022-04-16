package src.main;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import k_Methods.Rectangle_;
import src.main.GUI.STATE;
import src.main.Achievements.Achievement;
import src.main.Achievements.AchievementMenu.AchPage;
import src.main.Achievements.AllAchievements;

public class MouseInput implements MouseListener, MouseMotionListener{
	
	GUI gui;
	LevelSelect levSelectPage;
	LevelSelectSpecial levSelectPageSpecial;
	TitlePage titlePage;
	QuestionPageNumber qPage;
	QuestionPageYesNo qPageYN;
	ResultsPage results;
	Rectangle_ highlighted = null; //gets to null whenever you click a button. A convneinet solution to highlighted rectangle persisting through page changes

	int mx = -1, my = -1;
	
		
	public MouseInput(GUI gui) {
		this.gui = gui;
		levSelectPage = gui.getLevSelect();
		levSelectPageSpecial = gui.getLevelSelectSpecial();
		titlePage = gui.getTitlePage();
		qPage = gui.getQuestionPage();
		qPageYN = gui.getQuestionPageYesNo();
		results = gui.getResultsPage();
	}
	
	public void mouseClicked(MouseEvent e) {		
		if (gui.State == gui.State.TITLE) {
			if(highlighted != null) {
			highlighted.setHighlighted(false);
			highlighted = null;
			}
			
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
				
			if(clickInBounds(titlePage.achievementsButton)) {gui.State = gui.State.ACHIEVEMENTMENU;}
			if(clickInBounds(titlePage.quitButton))	{System.exit(1);}
		
		}//title
		
		else if (gui.State == gui.State.LEVELSELECT) {
			
			levSelectPage.numQuestionsInput.attemptFocus(mx, my);
			
			if(clickInBounds(levSelectPage.Home)) {
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
			
			if(clickInBounds(levSelectPage.vanishChallenge)) {levSelectPage.cycleVanishLevel();}
			if(clickInBounds(levSelectPage.strikeOutChallenge)) {levSelectPage.cycleStrikeOutLevel();}
			
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
			else if (clickInBounds(levSelectPageSpecial.squaringNumberThatEndsIn5)){
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

			if(clickInBounds(levSelectPageSpecial.Home))gui.State = gui.State.TITLE;
				
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
		
		else if (gui.State == gui.State.ACHIEVEMENTMENU){
			if(gui.getAchMenu().achPage == AchPage.home) {
				if(clickInBounds(gui.getAchMenu().getHomePage().getBack()))
					gui.State = gui.State.TITLE;
				if(clickInBounds(gui.getAchMenu().getHomePage().vanillaBtn)) {
					gui.getAchMenu().achPage = AchPage.vanilla;
					gui.getAchMenu().getVanillaPage().reInit();

				}
			}
			else if(gui.getAchMenu().achPage == AchPage.vanilla) {
				if(clickInBounds(gui.getAchMenu().getHomePage().getBack()))
					gui.getAchMenu().achPage = AchPage.home;
				for(ArrayList<Achievement> row: AllAchievements.vanillaAchievements) {
					for(Achievement ach: row)
						if(clickInBounds(ach.getBox())) gui.getAchMenu().getVanillaPage().setInfoBoxText(ach.getExplanation());
				}

			}


		
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

	
	public void mouseDragged(MouseEvent e) {
		
	}
/*  the frame work for highlight checking
 	if(highlighted == null) {	
		//if statements here
		 
		try{highlighted.setHighlighted(true);}catch(Exception ex){}

	}else if(!clickInBounds(highlighted)) {
		highlighted.setHighlighted(false);	
		highlighted = null;
	}
 */
	public void mouseMoved(MouseEvent e) {
		mx = e.getX(); 
		my = e.getY();
		
		
		if(GUI.State == STATE.TITLE) {
			if(highlighted == null) {	
				if (clickInBounds(gui.getTitlePage().playButton))
					highlighted = gui.getTitlePage().playButton;
				else if (clickInBounds(gui.getTitlePage().playSpecialButton))
					highlighted = gui.getTitlePage().playSpecialButton;
				else if (clickInBounds(gui.getTitlePage().resumeButton))
					highlighted = gui.getTitlePage().resumeButton;
				else if (clickInBounds(gui.getTitlePage().achievementsButton))
					highlighted = gui.getTitlePage().achievementsButton;
				else if (clickInBounds(gui.getTitlePage().quitButton))
					highlighted = gui.getTitlePage().quitButton;
				try{highlighted.setHighlighted(true);}catch(Exception ex){}

			}else if(!clickInBounds(highlighted)) {
				highlighted.setHighlighted(false);	
				highlighted = null;
			}
		}
		else if(GUI.State == STATE.LEVELSELECT) {
		 	if(highlighted == null) {	
		 		if (clickInBounds(gui.getLevSelect().AddChoose))
					highlighted = gui.getLevSelect().AddChoose;
		 		else if (clickInBounds(gui.getLevSelect().SubChoose))
		 			highlighted = gui.getLevSelect().SubChoose;
		 		else if (clickInBounds(gui.getLevSelect().MultChoose))
		 			highlighted = gui.getLevSelect().MultChoose;
		 		else if (clickInBounds(gui.getLevSelect().DivChoose))
		 			highlighted = gui.getLevSelect().DivChoose;
		 		else if (clickInBounds(gui.getLevSelect().easyDif))
					highlighted = gui.getLevSelect().easyDif;
				else if (clickInBounds(gui.getLevSelect().medDif))
					highlighted = gui.getLevSelect().medDif;
				else if (clickInBounds(gui.getLevSelect().hardDif))
					highlighted = gui.getLevSelect().hardDif;
				else if (clickInBounds(gui.getLevSelect().insaneDif))
					highlighted = gui.getLevSelect().insaneDif;
				else if (clickInBounds(gui.getLevSelect().vanishChallenge)) 
					highlighted = gui.getLevSelect().vanishChallenge;
				else if (clickInBounds(gui.getLevSelect().Endless)) 
					highlighted = gui.getLevSelect().Endless;
				else if (clickInBounds(gui.getLevSelect().GenerateSet)) 
					highlighted = gui.getLevSelect().GenerateSet;
				else if(clickInBounds(gui.getLevSelect().Home))
					highlighted = gui.getLevSelect().Home;

				try{highlighted.setHighlighted(true);}catch(Exception ex){}

			}else if(!clickInBounds(highlighted)) {
				highlighted.setHighlighted(false);	
				highlighted = null;
			}
		}
		else if(GUI.State == STATE.LEVELSELECTSPECIAL) {
			if(highlighted == null) {	
				if (clickInBounds(gui.getLevelSelectSpecial().easyDif))
					highlighted = gui.getLevelSelectSpecial().easyDif;
				else if (clickInBounds(gui.getLevelSelectSpecial().medDif))
					highlighted = gui.getLevelSelectSpecial().medDif;
				else if (clickInBounds(gui.getLevelSelectSpecial().hardDif))
					highlighted = gui.getLevelSelectSpecial().hardDif;
				else if (clickInBounds(gui.getLevelSelectSpecial().insaneDif))
					highlighted = gui.getLevelSelectSpecial().insaneDif;
				else if (clickInBounds(gui.getLevelSelectSpecial().multBy2))
					highlighted = gui.getLevelSelectSpecial().multBy2;
				else if (clickInBounds(gui.getLevelSelectSpecial().multBy3))
					highlighted = gui.getLevelSelectSpecial().multBy3;
				else if (clickInBounds(gui.getLevelSelectSpecial().multBy4))
					highlighted = gui.getLevelSelectSpecial().multBy4;
				else if (clickInBounds(gui.getLevelSelectSpecial().multBy5))
					highlighted = gui.getLevelSelectSpecial().multBy5;
				else if (clickInBounds(gui.getLevelSelectSpecial().divBy2))
					highlighted = gui.getLevelSelectSpecial().divBy2;
				else if (clickInBounds(gui.getLevelSelectSpecial().divBy3))
					highlighted = gui.getLevelSelectSpecial().divBy3;
				else if (clickInBounds(gui.getLevelSelectSpecial().divBy4))
					highlighted = gui.getLevelSelectSpecial().divBy4;
				else if (clickInBounds(gui.getLevelSelectSpecial().divBy5))
					highlighted = gui.getLevelSelectSpecial().divBy5;
				else if (clickInBounds(gui.getLevelSelectSpecial().isItDivisible))
					highlighted = gui.getLevelSelectSpecial().isItDivisible;
				else if (clickInBounds(gui.getLevelSelectSpecial().Endless)) 
					highlighted = gui.getLevelSelectSpecial().Endless;
				else if (clickInBounds(gui.getLevelSelectSpecial().GenerateSet)) 
					highlighted = gui.getLevelSelectSpecial().GenerateSet;
				else if(clickInBounds(gui.getLevelSelectSpecial().Home))
					highlighted = gui.getLevelSelectSpecial().Home;
				
				try{highlighted.setHighlighted(true);}catch(Exception ex){}
				
			}else if(!clickInBounds(highlighted)) {
				highlighted.setHighlighted(false);	
				highlighted = null;
			}
		}
		else if (GUI.State == STATE.ACHIEVEMENTMENU) {
				
			
			if(gui.getAchMenu().achPage == AchPage.home) {
				if(highlighted == null) {
					if(clickInBounds(gui.getAchMenu().getHomePage().getBack())) 
						highlighted = gui.getAchMenu().getHomePage().getBack();
					else if(clickInBounds(gui.getAchMenu().getHomePage().vanillaBtn)) 
						highlighted = gui.getAchMenu().getHomePage().vanillaBtn;
					else if(clickInBounds(gui.getAchMenu().getHomePage().challengeBtn)) 
						highlighted = gui.getAchMenu().getHomePage().challengeBtn;
					else if(clickInBounds(gui.getAchMenu().getHomePage().myriadBtn)) 
						highlighted = gui.getAchMenu().getHomePage().myriadBtn;
					else if(clickInBounds(gui.getAchMenu().getHomePage().potpourriBtn)) 
						highlighted = gui.getAchMenu().getHomePage().potpourriBtn;

					try{highlighted.setHighlighted(true);}catch(Exception ex){}
					
				}else if(!clickInBounds(highlighted)) {
					highlighted.setHighlighted(false);	
					highlighted = null;
				}
			}
			else if(gui.getAchMenu().achPage == AchPage.vanilla) {
			//This check makes highlighting work faster, rather than checking and unchecking each box
				if(highlighted == null) {
					for(ArrayList<Achievement> row: AllAchievements.vanillaAchievements) {
						for(Achievement ach: row) {
							if(clickInBounds(ach.getBox())) {
								highlighted = ach.getBox();
								break;
							}
						}
					}
					if(clickInBounds(gui.getAchMenu().getHomePage().getBack())) 
						highlighted = gui.getAchMenu().getVanillaPage().getBack();
					
					try{highlighted.setHighlighted(true);}catch(Exception ex){}

				}else if(!clickInBounds(highlighted)) {
						highlighted.setHighlighted(false);	
						highlighted = null;
				}
			}//else if vanilla
			
		
		}
	}
		
			
	public boolean clickInBounds(Rectangle rectangle) {
		if((mx >= rectangle.x && mx <= rectangle.x + rectangle.width) && (my >= rectangle.y && my <= rectangle.y + rectangle.height)){
			return true;
		}
		else return false;
	}

	
}
