import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	GUI gui;
	LevelSelect levSelectPage;
	LevelSelectSpecial levSelectPageSpecial;
	TitlePage titlePage;
	QuestionPageNumber qPage;
	QuestionPageYesNo qPageYN;
	ResultsPage results;
	AchievementPages achPage;
	
	boolean rightPressed = false, leftPressed = false;
	
	public KeyInput(GUI gui) {
		this.gui = gui;
		
		levSelectPage = gui.getLevSelect();
		titlePage = gui.getTitlePage();
		qPage = gui.getQuestionPage();
		qPageYN = gui.getQuestionPageYesNo();
		results = gui.getResultsPage();
		levSelectPageSpecial = gui.getLevelSelectSpecial();
		achPage = gui.getAchPage();
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
			
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
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
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
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
		
		else if(gui.State == gui.State.ACHIEVEMENTS){
			if(e.getKeyCode() == KeyEvent.VK_RIGHT && achPage.pageIndex < achPage.numPages-1) {achPage.setPageIndex(achPage.getPageIndex() + 1);}
			if(e.getKeyCode() == KeyEvent.VK_LEFT  && achPage.pageIndex > 0) {achPage.setPageIndex(achPage.getPageIndex() - 1);}
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				gui.State = gui.State.TITLE;
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
