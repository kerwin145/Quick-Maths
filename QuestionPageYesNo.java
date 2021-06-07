import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class QuestionPageYesNo extends QuestionPageNumber{
	//a pity that QuestionPageNumber is a bit too complex 
	
	public Rectangle Yes = new Rectangle(fnt0.getSize(), (int)(gui.HEIGHT * gui.SCALE / 2), 120, 50);
	public Rectangle No = new Rectangle(fnt0.getSize()*2 + Yes.width, (int)(gui.HEIGHT * gui.SCALE / 2), 120, 50);
	public int yesNo = -1; //0 is no 1 is yes
		
	public boolean autoConfirm = false;
	public Rectangle AutoConfirm = new Rectangle((int)(InfoBox.x + InfoBox.width * 1.2), InfoBox.y, 200, InfoBox.height);
	
	public QuestionPageYesNo(GUI gui) {
		super(gui);
	}
	
	public void render(Graphics g){
		Graphics2D g2d =  (Graphics2D)g;
		
		if(question != null){
			g.setColor(Color.black);
			g.setFont(fnt0);
			g.drawString(question.getQuestionText(), (int)(fnt0.getSize()), (int)(gui.HEIGHT * gui.SCALE / 2 - fnt0.getSize()) );
		
			if(yesNo == 1){
				g.setColor(Color.orange);
				g2d.draw(Yes);
				g.setColor(Color.black);
				g2d.draw(No);
			}
			else if(yesNo == 0){
				g.setColor(Color.orange);
				g2d.draw(No);
				g.setColor(Color.black);
				g2d.draw(Yes);	
			}
			else {
				g.setColor(Color.black);
				g2d.draw(Yes);
				g2d.draw(No);
			}
			g.setFont(fntSplash);
			g.drawString("Yes", Yes.x + 10, Yes.y + Yes.height-5);
			g.drawString("No", No.x + 10, No.y + No.height-5);
			
		}
		

		if(autoConfirm) g.setColor(Color.orange);
		g2d.draw(AutoConfirm);
		g.setFont(fnt3);
		g.setColor(Color.gray);
		g.drawString("Auto Confirm", (int)(AutoConfirm.x + + fnt3.getSize() * 0.6), AutoConfirm.y + fnt3.getSize());
		
		renderNotQuestion(g);
		if(renderHelp) renderHelp(g);
	}
	
	public void tick(){
		if(autoConfirm && yesNo != -1 && (!askForConfirm || (warned))){
			//if autoconfirm is on and answer is not null AND either ask for confirm is not on, or you have been warned (and thus askforconfrim is on)
			submitAnswer();
		}
		baseTick();
	}
	
	public void renderHelp(Graphics g) {
		g.setFont(fntNormal);
		g.setColor(Color.gray);
		g.drawString("If turned on, your answers will", AutoConfirm.x, AutoConfirm.y + AutoConfirm.height + fntNormal.getSize());
		g.drawString("automatically submit upon", AutoConfirm.x, AutoConfirm.y + AutoConfirm.height + fntNormal.getSize()*2);
		g.drawString("choosing \"Yes\" or \"no\".", AutoConfirm.x, AutoConfirm.y + AutoConfirm.height + fntNormal.getSize()*3);
		g.drawString("Hotkey: Up Arrow \u2191", AutoConfirm.x, AutoConfirm.y + AutoConfirm.height + fntNormal.getSize()*4);

		g.drawString("Hotkey: Left arrow \u2190", Yes.x, Yes.y + Yes.height + fntNormal.getSize());
		g.drawString("Hotkey: Right arrow \u2192", No.x, No.y + No.height + fntNormal.getSize());
		
		g.drawString("Submit: Submit and check your answer", (int)(submitAnswer.x + submitAnswer.width * 1.5), submitAnswer.y + fntNormal.getSize());
		g.drawString("(Hot key: \"Space\")", (int)(submitAnswer.x + submitAnswer.width * 1.5), submitAnswer.y + fntNormal.getSize()*2);
		if(!timerHidden)
			g.drawString("Timer: Click to hide.", (int)(gui.WIDTH * gui.SCALE - HomePage.width*1.8),  (int)(HomePage.y + HomePage.height * 5));
		else
			g.drawString("Timer: Click to show.", (int)(gui.WIDTH * gui.SCALE - HomePage.width*1.8),  (int)(HomePage.y + HomePage.height * 5));

	}
	
	public void submitAnswer(){
		renderHelp = false;
		
		if (askForConfirm){ //after you finish looking at the answer when u get the answer wrong
				
				if (warned){ //you have been wanred, proceed to check answers
					askForConfirm = false;
					checkAnswer();
				}
				
				else if(currentQuestion <= numQuestions){ //Gen question if not a warning
					genQuestion();
					submitColor = Color.black;
					askForConfirm = false; //next time you go through the method, warn will be false
					yesNo = -1;
					splashText = "";

				}
			}
		
		else{
			checkAnswer();
		}
		
	}
	
	public void checkAnswer(){
		if(yesNo == question.getAnswer()) 
			correct();
		else if(yesNo == -1 && warned == false)
			warn();
		else
			incorrect();
	}
	
	public void correct() {
		numCorrect++;
		warned = false;
		yesNo = -1;
		splashText = randFromArray(correct);
		genQuestion();	
		
	}

	public void incorrect() {
		warned = false;
		yesNo = -1;
		if(question.getAnswer() == 0)
			splashText = randFromArray(incorrect) + " (It wasn't divisisble.)";
		else if(question.getAnswer() == 1)
			splashText = randFromArray(incorrect) + " (It was divisible.)";
		submitColor = Color.orange;
		askForConfirm = true;
	}

	public void genQuestion(){
		if(currentQuestion == numQuestions) switchToResults();
		
		question = new Question(difficulty);
		currentQuestion++;
	}
	
	public void initializeRound(){
		baseInit(gui.getLevelSelectSpecial());
		gui.getTitlePage().questionPage = gui.getTitlePage().questionPage.SpecialYN;

		genQuestion();

	}
	
	public void switchToResults(){
		gui.getTitlePage().setSpecialFinished = true;
		gui.getTitlePage().setFinished = true;
		gui.getResultsPage().initializeYN();
		System.out.println("Switching from qPageYN to results");
		gui.State = gui.State.RESULTS;
	}

		

	

}
