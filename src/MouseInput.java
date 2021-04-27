import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
	
	GUI gui;
	LevelSelect levSelectPage;
	TitlePage titlePage;
	QuestionPage qPage;
	ResultsPage results;
	AchievementPages achPage;
	int mx = -1, my = -1;
	
	public MouseInput(GUI gui) {
		this.gui = gui;
		levSelectPage = gui.getLevSelect();
		titlePage = gui.getTitlePage();
		qPage = gui.getQuestionPage();
		results = gui.getResultsPage();
		achPage = gui.getAchPage();
	}
	
	public void mouseClicked(MouseEvent e) {
		mx = e.getX(); 
		my = e.getY();
		
		if (gui.State == gui.State.TITLE) {
			
			if(clickInBounds(titlePage.playButton)) { gui.State = gui.State.LEVELSELECT;}
			if(clickInBounds(titlePage.resumeButton) && !qPage.getSetFinished()) {gui.State = gui.State.QUESTIONROUND;}
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

			if(clickInBounds(levSelectPage.easyDif)) {levSelectPage.setQuestionDifficulty(0);}
			if(clickInBounds(levSelectPage.medDif)) {levSelectPage.setQuestionDifficulty(1);}
			if(clickInBounds(levSelectPage.hardDif)) {levSelectPage.setQuestionDifficulty(2);}
			if(clickInBounds(levSelectPage.insaneDif)) {levSelectPage.setQuestionDifficulty(3);}
	
			if(clickInBounds(levSelectPage.GenerateSet)) {
				gui.State = gui.State.QUESTIONROUND;
				qPage.initializeRound();
			}
			
			if(clickInBounds(levSelectPage.InfoBox)) {
				levSelectPage.renderHelp = !levSelectPage.renderHelp;
			}
			
		}//levelselect
		
		else if (gui.State == gui.State.QUESTIONROUND) {
			
			qPage.inputTextAnswer.attemptFocus(mx, my);
			
			if(clickInBounds(qPage.HomePage)) {gui.State = gui.State.TITLE;}
			if(clickInBounds(qPage.submitAnswer)) {qPage.submitAnswer();}
			if(clickInBounds(qPage.InfoBox)){qPage.renderHelp = !qPage.renderHelp;}
		
		}//question round
		
		else if(gui.State == gui.State.RESULTS){
			
			if(clickInBounds(results.HomePage)) {gui.State = gui.State.TITLE;}
			if(clickInBounds(results.playButton)) {gui.State = gui.State.LEVELSELECT;}
			
		}//results
		
		else if (gui.State == gui.State.ACHIEVEMENTS){
			if(clickInBounds(achPage.HomePage)) {gui.State = gui.State.TITLE;};
						
			if(achPage.getPageIndex() == 0){
				for(int type = 0; type < achPage.achievementButtons.size(); type++) {
					for(int operation = 0; operation < achPage.achievementButtons.get(type).size(); operation++) {
						if(clickInBounds(achPage.achievementButtons.get(type).get(operation))){
							achPage.setSelectedAchievement(achPage.getVanillaAchievementList().get(type).get(operation));
						}	
					}
				}
				
				if(clickInBounds(achPage.nextSlide)) {achPage.setPageIndex(achPage.getPageIndex() + 1);}
				
			}
			
			else if (achPage.getPageIndex() == 1){
				if(clickInBounds(achPage.prevSlide)) {achPage.setPageIndex(achPage.getPageIndex() - 1);}
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

	public boolean clickInBounds(Rectangle rectangle) {
		if((mx >= rectangle.x && mx <= rectangle.x + rectangle.width) && (my >= rectangle.y && my <= rectangle.y + rectangle.height)){
			return true;
		}
		else return false;
	}
}
