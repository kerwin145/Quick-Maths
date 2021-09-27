import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import k_Methods.MoColors;
import k_Methods.RectanglePlus;
import k_Methods.RectanglePlus.gradientFormat;


public class QuestionPageYesNo extends QuestionPageNumber{
	//a pity that QuestionPageNumber is a bit too complex
	
	//state 0 is for neutral, state 1 is for chosen. State 2 is for correct choice, state 3 is for incorrect choice
	Color[] yesNoColors1 = {MoColors.deepSkyBlue, MoColors.greenYellow, MoColors.greenYellow, MoColors.orangeRed};
	Color[] yesNoColors2 = {MoColors.cadetBlue, MoColors.aqua, MoColors.limeGreen, MoColors.gray};
	Color[] yesNoBorderColors = {MoColors.white, MoColors.chartreuse, MoColors.greenYellow, MoColors.fireBrick};
	Color[] yesNoFontColors = {MoColors.white};
	
	public RectanglePlus yes = new RectanglePlus(fnt0.getSize(), (int)(gui.HEIGHT * gui.SCALE / 2), 120, 50, yesNoColors1, yesNoColors2, true, gradientFormat.horizontal, yesNoBorderColors,
			fntSplash, yesNoFontColors, "Yes");
	
	public RectanglePlus no = new RectanglePlus(fnt0.getSize()*2 + yes.width, (int)(gui.HEIGHT * gui.SCALE / 2), 120, 50, yesNoColors1, yesNoColors2, true, gradientFormat.horizontal, yesNoBorderColors,
			fntSplash, yesNoFontColors, "No");
	
	public int yesNo = -1; //0 is no 1 is yes

	public boolean autoConfirm = false;
	
	Color[] autoConfirmColor1 = {MoColors.deepSkyBlue, MoColors.dodgerBlue};
	Color[] autoConfirmColor2 = {MoColors.lightBlue, MoColors.aqua };
	Color[] autoConfirmBorderColor = {Color.gray, MoColors.chartreuse};
	Color[] autoConfirmTextColor = {Color.white, Color.white};
	
	public RectanglePlus AutoConfirm = new RectanglePlus((int)(InfoBox.x + InfoBox.width * 1.2), InfoBox.y, 200, InfoBox.height, autoConfirmColor1, autoConfirmColor2,
			true, gradientFormat.horizontal, autoConfirmBorderColor, fnt3, autoConfirmTextColor, "Auto Confirm");

	boolean displayingAnswer = false;
	
	public QuestionPageYesNo(GUI gui) {
		super(gui);
	}

	public void render(Graphics g){
		Graphics2D g2d =  (Graphics2D)g;
		
		renderNotQuestion(g);

		if(question != null){
			g.setColor(Color.white);
			g.setFont(fnt0);
			g.drawString(question.getQuestionText(), (int)(fnt0.getSize()), (int)(gui.HEIGHT * gui.SCALE / 2 - fnt0.getSize()) );

			if(!displayingAnswer) {
				if(yesNo == 1){
					yes.setColorState(1);
					no.setColorState(0);
				}
				else if(yesNo == 0){
					yes.setColorState(0);
					no.setColorState(1);
				}
				else {
					yes.setColorState(0);
					no.setColorState(0);
				}
			}
			
			yes.draw(g2d);
			no.draw(g2d);
		}


		if(autoConfirm) AutoConfirm.setColorState(1);
		else AutoConfirm.setColorState(0);
		AutoConfirm.draw(g2d);
		
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

		g.drawString("Hotkey: Left arrow \u2190", yes.x, yes.y + yes.height + fntNormal.getSize());
		g.drawString("Hotkey: Right arrow \u2192", no.x, no.y + no.height + fntNormal.getSize());

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

			else if(currentQuestion <= numQuestions || endlessQuestions){ //Gen question if not a warning
				genQuestion();
				timerUnPause();
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
		submitAnswer.setColorState(0);
	}

	public void incorrect() {
		warned = false;
		yesNo = -1;
		displayingAnswer = true;
		if(question.getAnswer() == 0) {
			yes.setColorState(3);
			no.setColorState(2);
			splashText = randFromArray(incorrect) + " (It wasn't divisisble.)";
		}
		else if(question.getAnswer() == 1) {
			yes.setColorState(2);
			no.setColorState(3);
			splashText = randFromArray(incorrect) + " (It was divisible.)";
		}
		submitColor = Color.orange;
		timerPause();
		askForConfirm = true;
		submitAnswer.setColorState(1);

	}

	public void genQuestion(){
		if(currentQuestion == numQuestions && !endlessQuestions) switchToResults();

		question = new Question(difficulty);
		currentQuestion++;
		displayingAnswer = false;
		submitAnswer.setColorState(0);
	}

	public void initializeRound(){
		baseInit(gui.getLevelSelectSpecial());
		gui.getTitlePage().questionPage = gui.getTitlePage().questionPage.SpecialYN;
		endlessQuestions = gui.getLevelSelectSpecial().endlessQuestions;

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


