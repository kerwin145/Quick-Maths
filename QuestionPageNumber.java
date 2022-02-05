import k_Methods.stringGraphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class QuestionPageNumber {

	GUI gui;
	private ArrayList<Integer> questionTypes = new ArrayList<Integer>();

	public int numQuestions = 0;
	public int numCorrect = 0;
	public int currentQuestion = 0;
	protected int difficulty; //from 1 as easy to 4 as insane
	public int questionSpecialNum2 = -1; //used for specific mult or division
	public int questionSpecialType = -1; //2 for mult, 3 for division
	private boolean onlyPositive, perfectDivisor;
	public boolean endlessQuestions = false;

	private int setType;

	String splashText = "";
	String splashText2 = "";
	boolean askForConfirm = false; //used when your answer is wrong
	public Color submitColor = Color.black;
	public boolean renderHelp = false;
	public boolean warned = false;

	protected Question question;
	Random rnd = new Random();
	private dataUpdater dataUpdater;

	public long timeNaught = 0, timeNow = 0, deltaMillis = 0;
	public float timeSeconds = 0;
	public int timeMinutes = 0;
	public String timerText = "";
	public int timerTextHeight, timerTextLength;
	boolean startReseted = true; //make sure that the time starts when the question is properly loaded.
	public boolean timerHidden = false;

	public double timeAverageDeviationSum = 0; //calculates the cumulative time dif ratio of individual question time to the average time. If greater than 2, than you can revoke time data for that round.
	public int timeAverageDeviationCount = 0;
	public double timeAverageDeviationAverage = 0;

	public boolean askToRollBack = false;

	long refTimeAverageSum[][] = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
	int refTimeAverageCount[][] = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};

	long timeNowFix, deltaMillisFix;//used to take into account time spent on title screen and/or when on askforconfirm
	boolean timerPaused = false;

	long timeQuestionStartMillis, timeQuestionEndMillis;//used to time how long it takes to solve a problem.

	int inputAnswer;

	String[] correct = {"Nice!", "Correct!", "Keep it up!", "BIG BRAIN", "U r very smart", "Yessir", "Show me da wae", "I call hacks", "Valid Fo' Sho'"};
	String[] incorrect = {"Oops!", "You still got this!", "Tiny brain?", "Come on man", "Not time to give up yet!", "NOOOOOOoooooooooo", "I II II L"};
	String[] almostThere = {"Bah", "Oof", "Sadge"};

	String[] difList = {"Easy", "Medium", "Hard", "Insane"}; //used for getdifficulty method, nothing much else

	Font fnt0 = new Font("Arial", Font.ITALIC|Font.BOLD, gui.HEIGHT * gui.SCALE / 8);
	Font fnt2 = new Font("Arial", Font.ITALIC, gui.HEIGHT * gui.SCALE / 15);
	Font fntNormal = new Font("Garamond", Font.PLAIN, 15);

	int width1 = gui.WIDTH * gui.SCALE /4;
	NumberTextField inputTextAnswer = new NumberTextField(fnt0.getSize() + fnt2.getSize() * 7/2, gui.HEIGHT * gui.SCALE / 2, width1, 50, "0", true);
	public Rectangle HomePage = new Rectangle(gui.WIDTH * gui.SCALE - 140, 15, 120, 20);
	Font fnt1 = new Font("Garamond", Font.BOLD, HomePage.height);
	Font fntSplash = new Font("Garamond", Font.PLAIN, inputTextAnswer.getHeight());
	Font fnt1Plain = new Font("Garamond", Font.PLAIN, HomePage.height);

	public Rectangle submitAnswer = new Rectangle(inputTextAnswer.getX() + inputTextAnswer.getWidth() + 60, inputTextAnswer.getY(), 50, 50);
	public Rectangle InfoBox = new Rectangle(20, 20, 40, 40);
	Font fnt3 = new Font("Georgia", Font.PLAIN, (int)(InfoBox.width/1.5));;

	private char finishSetPassword;
	public Rectangle finishSet = new Rectangle(HomePage.x, gui.HEIGHT * gui.SCALE - 45, 120, 30);
	public boolean finishClicked = false;

	public QuestionPageNumber (GUI gui){
		this.gui = gui;
		dataUpdater = new dataUpdater(gui.getUdata());
	}

	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;

		if(question != null){
			g.setColor(Color.black);
			g.setFont(fnt0);
			g.drawString(question.getQuestionText(), (int)(fnt0.getSize()), (int)(gui.HEIGHT * gui.SCALE / 2 - fnt0.getSize()) );

			g.setFont(fnt2);
			g.drawString("Answer", fnt0.getSize() , inputTextAnswer.getY() + fnt0.getSize()/2);
			inputTextAnswer.render(g, true);

		}

		renderNotQuestion(g);
		if(renderHelp) renderHelp(g);

	}

	public void renderHelp(Graphics g) {

		g.setFont(fntNormal);
		g.setColor(Color.gray);
		g.drawString("Submit: Submit and check your answer", (int)(submitAnswer.x + submitAnswer.width * 1.5), submitAnswer.y + fntNormal.getSize());
		g.drawString("(Hot key: \"Space\")", (int)(submitAnswer.x + submitAnswer.width * 1.5), submitAnswer.y + fntNormal.getSize()*2);
		if(!timerHidden)
			g.drawString("Timer: Click to hide.", (int)(gui.WIDTH * gui.SCALE - HomePage.width*1.8),  (int)(HomePage.y + HomePage.height * 5));
		else
			g.drawString("Timer: Click to show.", (int)(gui.WIDTH * gui.SCALE - HomePage.width*1.8),  (int)(HomePage.y + HomePage.height * 5));

	}

	public void renderNotQuestion(Graphics g){
		Graphics2D g2d = (Graphics2D)g;

		if (warned)
			splashText = "Are you sure?";
		g.setFont(fntSplash);
		g.setColor(Color.black);
		g.drawString(splashText, fnt0.getSize(), (int)(inputTextAnswer.getY() + inputTextAnswer.getHeight() * 2));
		g.setColor(new Color(39, 201, 14));
		g.drawString(splashText2, fnt0.getSize(), (int)(inputTextAnswer.getY() + inputTextAnswer.getHeight() * 3));

		g.setColor(submitColor);
		g2d.draw(submitAnswer);
		stringGraphics.drawStringCentered("->", submitAnswer, g);

		g.setColor(Color.black);
		g2d.draw(HomePage);
		g2d.setFont(fnt1);
		stringGraphics.drawStringCentered("Home", HomePage, g);

		g2d.draw(finishSet);
		stringGraphics.drawStringCentered("Finish Set", finishSet, g);

		if(finishClicked){
			g.setFont(fnt1Plain);
			g.drawString("Type: " + "\"" + finishSetPassword + "\"" + " to continue.", gui.WIDTH * gui.SCALE - HomePage.width*3, finishSet.y + fnt1Plain.getSize());
		}
		g.setFont(fnt1);
		g.setColor(Color.gray);
		if(!endlessQuestions) {
			g.drawString("Q " + currentQuestion + "/" + numQuestions, (int) (gui.WIDTH * gui.SCALE - HomePage.width * 2.2), (int) (HomePage.y + HomePage.height * 1.5));
			g.drawString("Correct: " + numCorrect + "/" + (currentQuestion - 1), (int) (gui.WIDTH * gui.SCALE - HomePage.width * 2.2), (int) (HomePage.y + HomePage.height * 2.75));
		}
		else{
			g.drawString("Q " + currentQuestion + "/ --", (int) (gui.WIDTH * gui.SCALE - HomePage.width * 2.2), (int) (HomePage.y + HomePage.height * 1.5));
			g.drawString("Correct: " + numCorrect + "/ --", (int) (gui.WIDTH * gui.SCALE - HomePage.width * 2.2), (int) (HomePage.y + HomePage.height * 2.75));

		}
		timerTextLength = g.getFontMetrics().stringWidth(timerText);
		timerTextHeight = fnt1.getSize();
		if(!timerHidden)
			timerText = "Timer: " + timeMinutes + "' " +  timeSeconds + "\"";
		else
			timerText = "Timer: --";
		g.drawString(timerText, (int)(gui.WIDTH * gui.SCALE - HomePage.width*2.2), (int)(HomePage.y + HomePage.height * 4));


		g2d.draw(InfoBox);
		g.setFont(fnt3);
		stringGraphics.drawStringCentered("i", InfoBox, g);
	}

	public void tick(){
		baseTick();
	}

	public void baseTick(){
		//doesn't even make a .01 second difference (tested with slo mo camera) but whatever
		if(!startReseted){
			timeNaught = System.currentTimeMillis();
			startReseted = true;
		}

		timeNow = System.currentTimeMillis();

		if(!timerPaused)
			deltaMillis = Math.abs(timeNow - timeNaught);

		timeSeconds = (float)deltaMillis/1000;
		//	System.out.println("DetaMilis at QuestionPageNumber " + deltaMillis);

		if(deltaMillis > 60 * 1000){
			timeNaught = System.currentTimeMillis();
			deltaMillis -= 60 * 1000;
			timeSeconds -= 60;
			timeMinutes++;
		}

	}
	public void submitAnswer() {
		inputAnswer = inputTextAnswer.retrieveNum();
		renderHelp = false;
		splashText2 = "";

		if (warned){ //you have been wanred, proceed to check answers
			timeQuestionEndMillis = System.currentTimeMillis();
			askForConfirm = false;
			checkAnswer();
		}

		else if (askForConfirm){
			//if u u got the question wrong, wait.
			if(currentQuestion <= numQuestions || endlessQuestions){
				genQuestion();
				timerUnPause();
				splashText = "";
				submitColor = Color.black;
				askForConfirm = false;
			}
		}

		else if (!askForConfirm || !warned){
			timeQuestionEndMillis = System.currentTimeMillis();
			checkAnswer();

		}

	}
	//warn will not have user analytics update
	public void checkAnswer() {
		if(inputAnswer == question.getAnswer())
			correct();
		else if ((inputAnswer > 0) && (inputAnswer == -question.getAnswer()))//if u put a positive answer instead of the negative one
			reversed();
		else if(inputAnswer == 0 && question.getAnswer() != 0 && warned == false)
			warn();
		else
			incorrect();
	}

	public void correct() {
		numCorrect++;
		warned = false;
		dataUpdater.updateQuestionComplete(question.getType(), difficulty);
		dataUpdater.updateQuestionCorrect(question.getType(), difficulty);

		if(gui.uData.recordTimeIndividual[question.getType()][difficulty] == 0
				|| (timeQuestionEndMillis-timeQuestionStartMillis) < gui.uData.recordTimeIndividual[question.getType()][difficulty])
			splashText2 = "New Best Question Time!";
		dataUpdater.updateTime(question.getType(), difficulty, timeQuestionEndMillis - timeQuestionStartMillis);

		//---this code chunk could be in dataUpdater, but would serve better in the results page.
		double timeAverage;
		if(refTimeAverageCount[question.getType()][difficulty] != 0)
			timeAverage = (double)(refTimeAverageSum[question.getType()][difficulty])/refTimeAverageCount[question.getType()][difficulty];
		else timeAverage = 0;
		//System.out.println(refTimeAverageSum[question.getType()][difficulty]  + "and" + refTimeAverageCount[question.getType()][difficulty]);

		if( timeAverage != 0){
			timeAverageDeviationCount++;
			timeAverageDeviationSum += (timeQuestionEndMillis - timeQuestionStartMillis - timeAverage)/timeAverage;
			timeAverageDeviationAverage = timeAverageDeviationSum/timeAverageDeviationCount;
		}
		//---

		splashText = randFromArray(correct);

		//System.out.println("Total time spent at type: " + question.getType() + " difficulty: " + difficulty + " is " + gui.uData.timeAverageSum[question.getType()][difficulty]/1000.0);
		//System.out.println("Question end: " + timeQuestionEndMillis + ", start: " + timeQuestionStartMillis + "difference: " + (timeQuestionEndMillis-timeQuestionStartMillis));

		genQuestion();

	}

	public void warn() {
		warned = true;
	}

	public void reversed() {
		warned = false;
		splashText = randFromArray(almostThere) + " (Answer was " + question.getAnswer() + ")";
		submitColor = Color.orange;
		dataUpdater.updateQuestionComplete(question.getType(), difficulty);
		timerPause();
		askForConfirm = true;
	}

	public void incorrect() {
		warned = false;
		splashText = randFromArray(incorrect) + " (Answer was " + question.getAnswer() + ")";
		submitColor = Color.orange;
		dataUpdater.updateQuestionComplete(question.getType(), difficulty);
		timerPause();
		askForConfirm = true;
	}

	public void genQuestion(){
		if(currentQuestion == numQuestions && !endlessQuestions){
			timerPaused = false;
			switchToResults();
		}

		else {
			if(setType == 0)
				question = new Question(questionTypes, difficulty, onlyPositive, perfectDivisor);
			else if (setType == 1)
				question = new Question(questionSpecialType, difficulty, questionSpecialNum2);

			currentQuestion++;
			inputTextAnswer.setText("0");
		}

		timeQuestionStartMillis = System.currentTimeMillis();
	}

	public void initializeRound(){
		baseInit(gui.getLevSelect());

		boolean qChosen[] = gui.getLevSelect().getQChosen();
		//go through the list of operations checked and add them to the questionTypes list.
		for (int i = 0; i < qChosen.length; i++){
			if(qChosen[i]) questionTypes.add(i);
		}
		setType = 0;

		gui.getTitlePage().questionPage = gui.getTitlePage().questionPage.Normal;
		endlessQuestions = gui.getLevSelect().endlessQuestions;

		inputTextAnswer.attemptFocus(inputTextAnswer.getX(), inputTextAnswer.getY());
		inputTextAnswer.setText("0");

		onlyPositive = gui.getLevSelect().onlyPositive;
		perfectDivisor = gui.getLevSelect().perfectDivisors;

		setType = 0;
		genQuestion();

	}

	//special nums
	public void initializeRoundSpecial(){
		baseInit(gui.getLevelSelectSpecial());

		questionSpecialNum2 = gui.getLevelSelectSpecial().specialQuestionNum2;
		questionSpecialType = gui.getLevelSelectSpecial().specialQTypeChosen;

		gui.getTitlePage().questionPage = gui.getTitlePage().questionPage.Special;
		endlessQuestions = gui.getQuestionPage().endlessQuestions;

		inputTextAnswer.attemptFocus(inputTextAnswer.getX(), inputTextAnswer.getY());
		inputTextAnswer.setText("0");
		setType = 1;
		genQuestion();
	}

	public void switchToResults() {
		gui.getTitlePage().setFinished = true;
		gui.getTitlePage().setSpecialFinished = true;
		if(setType == 0)
			gui.getResultsPage().initialize();
		else if(setType == 1)
			gui.getResultsPage().initializeSpecial();

		if(timeAverageDeviationAverage >= 2.0){
			askToRollBack = true;
		}
		gui.State = gui.State.RESULTS;

	}

	public void baseInit(QuestionSelectPage qPageSelect){
		questionTypes.clear();
		currentQuestion = 0;
		numCorrect = 0;

		if(gui.getLevelSelectSpecial() == qPageSelect){
			gui.getTitlePage().setSpecialFinished = false;
			gui.getTitlePage().setFinished = true;

		}
		else if(gui.getLevSelect() == qPageSelect){
			gui.getTitlePage().setSpecialFinished = true;
			gui.getTitlePage().setFinished = false;
		}

		difficulty = qPageSelect.getQuestionDifficulty();
		numQuestions = qPageSelect.numQuestionsInput.retrieveNum();

		splashText = "";
		splashText2 = "";
		warned = false;
		askForConfirm = false;

		submitColor = Color.black;

		timeNaught = System.currentTimeMillis();
		deltaMillis = 0;
		timeMinutes = 0;
		timerPaused = false;
		startReseted = false;
		//no need to reset timeSeconds since it is activelly updated.

		long[][] timeAverageSumEmpty = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
		int[][] timeAverageCountEmpty = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};

		gui.uData.tempTimeAverageCount = timeAverageCountEmpty;
		gui.uData.tempTimeAverageSum = timeAverageSumEmpty;

		//refTimeAverageSum = gui.uData.timeAverageSum;
		//refTimeAverageCount = gui.uData.timeAverageCount;
		for(int i = 0; i < gui.uData.timeAverageSum.length; i++){
			for(int j = 0; j <gui.uData.timeAverageSum[i].length; j++){
				refTimeAverageSum[i][j] = gui.uData.timeAverageSum[i][j];
				refTimeAverageCount[i][j] = gui.uData.timeAverageCount[i][j];
			}
		}

		timeAverageDeviationAverage = 0;
		timeAverageDeviationCount = 0;
		timeAverageDeviationSum = 0;
		askToRollBack = false;

	}

	//these two methods will be invoked before the askForConfirm becomes true.
	public void timerPause(){
		timerPaused = true;
		timeNowFix = timeNow;
		System.out.println("Timer paused!");
	}

	public void timerUnPause(){
		if(timerPaused){
			//the unpause will go through a confirm check on the home page
			timerPaused = false;
			deltaMillisFix = System.currentTimeMillis() - timeNowFix;
			timeNaught += deltaMillisFix;
			//deltamillisfix will be added to qtimestart when resume button clicked, not when wait for confirm is over
			System.out.println("deltamillisfix: " + deltaMillisFix + "Timer resume!");
		}
	}
	public String randFromArray(String[] input){
		int random = rnd.nextInt(input.length);
		//System.out.println("Array Lenght:" + random);
		return input[random];
	}

	public String getDifficultyList(){
		return difList[difficulty];
	}
	public int getDifficulty() {
		return difficulty;
	}

	public ArrayList<Integer> getQuestionTypes() {
		return questionTypes;
	}

	public int getNumQuestions() {return numQuestions;}

	public void genSkipSetPassword(){finishSetPassword = (char)(rnd.nextInt(26)+97);}
	public char getPass(){return finishSetPassword;}

}


