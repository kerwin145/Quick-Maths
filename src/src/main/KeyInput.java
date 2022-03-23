package src.main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import src.main.Achievements.AchievementMenu.AchPage;

public class KeyInput extends KeyAdapter{

	GUI gui;
	LevelSelect levSelectPage;
	LevelSelectSpecial levSelectPageSpecial;
	TitlePage titlePage;
	QuestionPageNumber qPage;
	QuestionPageYesNo qPageYN;
	ResultsPage results;
	
	boolean rightPressed = false, leftPressed = false;
	
	public KeyInput(GUI gui) {
		this.gui = gui;
		
		levSelectPage = gui.getLevSelect();
		titlePage = gui.getTitlePage();
		qPage = gui.getQuestionPage();
		qPageYN = gui.getQuestionPageYesNo();
		results = gui.getResultsPage();
		levSelectPageSpecial = gui.getLevelSelectSpecial();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (gui.State == gui.State.TITLE) {
			
		}
		
		else if (gui.State == gui.State.LEVELSELECT) {
			levSelectPage.numQuestionsInput.updateText(key);
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				if(levSelectPage.isSetReady()){
					gui.State = gui.State.QUESTIONROUNDNUMBER;
					qPage.initializeRound();	
				}
			}
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				gui.State = gui.State.TITLE;
		}
		
		else if (gui.State == gui.State.LEVELSELECTSPECIAL) {
			levSelectPageSpecial.numQuestionsInput.updateText(key);
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				if(levSelectPageSpecial.specialQChosenIndex <= 7 && levSelectPageSpecial.isSetReady()){
					qPage.initializeRoundSpecial();
					gui.State = gui.State.QUESTIONROUNDNUMBER;
				}
				else if(levSelectPageSpecial.specialQChosenIndex == 8 && levSelectPageSpecial.isSetReady()){
					qPageYN.initializeRound();
					gui.State = gui.State.QUESTIONROUNDYESNO;
				}
			}
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				gui.State = gui.State.TITLE;
		}
		
		else if (gui.State == gui.State.QUESTIONROUNDNUMBER) {
			qPage.inputTextAnswer.updateText(key);
			
			if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER){
				qPage.submitAnswer();
			}
			
			char testPass = e.getKeyChar();
			if(qPage.finishClicked){
				if(testPass == qPage.getPass()){
					qPage.numQuestions = qPage.currentQuestion-1;
					qPage.switchToResults();
					qPage.finishClicked = false;
				}
			}
			qPage.finishClicked = false;
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if((titlePage.questionPage == titlePage.questionPage.Special || titlePage.questionPage == titlePage.questionPage.Normal) && !qPage.askForConfirm) {
					qPage.timerPause();
					qPage.timeQuestionStartMillis += qPage.deltaMillisFix;
				}
					gui.State = gui.State.TITLE;
			}
			

		}	
		
		else if (gui.State == gui.State.QUESTIONROUNDYESNO) {
			qPage.inputTextAnswer.updateText(key);
			if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER){
				qPageYN.submitAnswer();
			}
			if(e.getKeyCode() == KeyEvent.VK_UP){
				qPageYN.autoConfirm = !qPageYN.autoConfirm;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				leftPressed = true;
				rightPressed = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				rightPressed = true;
				leftPressed = false;
			}

			char testPass = e.getKeyChar();
			if(qPageYN.finishClicked){
				if(testPass == qPageYN.getPass()){
					qPageYN.numQuestions = qPageYN.currentQuestion-1;
					qPageYN.switchToResults();
					qPageYN.finishClicked = false;
				}
			}
			qPageYN.finishClicked = false;
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if(titlePage.questionPage == titlePage.questionPage.SpecialYN && !qPageYN.askForConfirm) {
					qPageYN.timerPause();
					qPageYN.timeQuestionStartMillis += qPageYN.deltaMillisFix;
				}
				gui.State = gui.State.TITLE;
			}
		}	
		
		else if(gui.State == gui.State.ACHIEVEMENTMENU){
	
			if(gui.getAchMenu().achPage == AchPage.home) {
				
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
					gui.State = gui.State.TITLE;	
			}
			
			if(gui.getAchMenu().achPage == AchPage.vanilla) {
				
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
					gui.getAchMenu().achPage = AchPage.home;	
			}
		}
		
		else if(gui.State == gui.State.RESULTS){
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				if(titlePage.questionPage == titlePage.questionPage.Normal)
					gui.State = gui.State.LEVELSELECT;
				else if(titlePage.questionPage == titlePage.questionPage.Special || titlePage.questionPage == titlePage.questionPage.SpecialYN)
					gui.State = gui.State.LEVELSELECTSPECIAL;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				gui.State = gui.State.TITLE;
			
		}
	}
	
	public void keyReleased(KeyEvent e) {
	
		if (gui.State == gui.State.QUESTIONROUNDYESNO) {
			
			if(e.getKeyCode() == KeyEvent.VK_LEFT && leftPressed){
				qPageYN.yesNo = 1;
				leftPressed = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT && rightPressed){
				qPageYN.yesNo = 0;
				rightPressed = false;
			}
		
		}
	}
}
	
	