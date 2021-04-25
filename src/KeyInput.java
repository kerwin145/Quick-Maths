import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	GUI gui;
	LevelSelect levSelectPage;
	TitlePage titlePage;
	QuestionPage qPage;
	ResultsPage results;
	
	public KeyInput(GUI gui) {
		this.gui = gui;
		
		levSelectPage = gui.getLevSelect();
		titlePage = gui.getTitlePage();
		qPage = gui.getQuestionPage();
		results = gui.getResultsPage();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (gui.State == gui.State.TITLE) {
			
		}
		
		else if (gui.State == gui.State.LEVELSELECT) {
			levSelectPage.numQuestionsInput.updateText(key);
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				if(levSelectPage.isSetReady()){
					gui.State = gui.State.QUESTIONROUND;
					qPage.initializeRound();	
				}

			}
			//System.out.println(key);//test 
		}
		
		else if (gui.State == gui.State.QUESTIONROUND) {
			qPage.inputTextAnswer.updateText(key);
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				qPage.submitAnswer();
			}
		}	
		
		else if(gui.State == gui.State.RESULTS){
			
		}


	}
	
	public void keyReleased(KeyEvent e) {
	
		
	}
	
	
}
