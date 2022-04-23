package src.main;
import k_Methods.MoColors;
import k_Methods.RectanglePlus;
import k_Methods.stringGraphics;
import k_Methods.RectanglePlus.gradientFormat;
import src.main.Achievements.AchievementCheck;
import k_Methods.Rectangle_;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class QuestionPageNumber {

	GUI gui;
	private ArrayList<Integer> questionTypes = new ArrayList<Integer>();

	public int numQuestions = 0;
	public int numCorrect = 0;
	public int currentQuestion = 0;
	public int quesionsCompleted = 0;
	protected int difficulty; //from 1 as easy to 4 as insane
	public int vanishLvl = 0;
	public int strikeOutLvl = 0;
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
	private AchievementCheck achCheck;
	Random rnd = new Random();
	private dataUpdater dataUpdater;

	public long timeNaught = 0, timeNow = 0, deltaMillis = 0;
	public float timeSeconds = 0;
	public int timeMinutes = 0;
	public String timerText = "";
	public int timerTextHeight, timerTextLength;
	boolean startReseted = true; //make sure that the time starts when the question is properly loaded.
	public boolean timerHidden = false;
	
	public long questionDisplayTime = 0;
	public long decayStartTime = 0;
	public int strikeOutLives = 0;

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

	String[] correct = {"Nice!", "Correct!", "Keep it up!", "BIG BRAIN", "U r very smart", "Yessir", "Show me da wae", "I call hacks", "Valid Fo' Sho'", "Based", "You're cracked"};
	String[] incorrect = {"Oops!", "You still got this!", "Tiny brain?", "Come on man", "Not time to give up yet!", "NOOOOOOoooooooooo", "I II II L"};
	String[] almostThere = {"Bah", "Oof", "Sadge", "Crinj"};

	String[] difList = {"Easy", "Medium", "Hard", "Insane"}; //used for getdifficulty method, nothing much else

	Font fnt0 = new Font("Cambria Math", Font.BOLD, gui.HEIGHT * gui.SCALE / 8);
	Font fnt1 = new Font("Lucida Bright", Font.BOLD, 15);
	Font fnt1Small = new Font("Lucida Bright", Font.BOLD, 12);
	Font fnt2 = new Font("Lucida Bright", Font.PLAIN, gui.HEIGHT * gui.SCALE / 15);
	Font fntNormal = new Font("Garamond", Font.PLAIN, 15);
	Font fntHeart = new Font("Garamond", Font.BOLD, 40);

	int width1 = gui.WIDTH * gui.SCALE /4;
	int textSpacing = 5;
	
	NumberTextField inputTextAnswer = new NumberTextField(fnt0.getSize() + fnt2.getSize() * 4, gui.HEIGHT * gui.SCALE / 2, width1, 52, "0", true);	
	
	public RectanglePlus HomePage = new RectanglePlus(gui.WIDTH * gui.SCALE - 150, 15, 130, 20,  
			MoColors.royalBlue, MoColors.deepSkyBlue, true, gradientFormat.horizontal, MoColors.navy,
			fnt1, Color.white, "Home");
	
	Font fntTimer = new Font("Garamond", Font.BOLD, HomePage.height);
	Font fntSplash = new Font("Garamond", Font.PLAIN, inputTextAnswer.getHeight());
	Font fnt1Plain = new Font("Garamond", Font.PLAIN, HomePage.height);
	Font fntSubmit = new Font("Garamond", Font.PLAIN, 30);

	//state one is normal, two is incorrect, three is warn
	Color[][] submitBackgroundColor = new Color[][] {new Color[] {MoColors.dodgerBlue, MoColors.aqua}, 
		new Color[] {MoColors.orange, MoColors.salmon}, new Color[] {MoColors.orange, MoColors.aqua}};
	Color[] submitBorderColor = {MoColors.chartreuse, MoColors.orange};
	
	Rectangle_ submitAnswer;
	
	public Rectangle InfoBox = new Rectangle(20, 20, 40, 40);
	Font fnt3 = new Font("Georgia", Font.PLAIN, (int)(InfoBox.width/1.5));;

	Color[] finishSetColor1 = {MoColors.orange, MoColors.dodgerBlue};
	Color[] finishSetColor2 = {MoColors.salmon, MoColors.aqua };
	Color[] finishSetBorderColor = {Color.gray, MoColors.chartreuse};
	Color[] finishSetTextColor = {Color.white, Color.white};
	
	private char finishSetPassword;
	public RectanglePlus finishSet = new RectanglePlus(HomePage.x, gui.HEIGHT * gui.SCALE - 45, 130, 30,
			finishSetColor1, finishSetColor2, true, gradientFormat.horizontal, finishSetBorderColor,
			fnt1, finishSetTextColor, "Finish Set");
	public boolean finishClicked = false;

	Image background = new ImageIcon("images/quickMathsBackground4.jpg").getImage().getScaledInstance(gui.WIDTH * gui.SCALE, gui.HEIGHT * gui.SCALE, Image.SCALE_DEFAULT);

	public QuestionPageNumber (GUI gui){
		this.gui = gui;
		achCheck = gui.getAchCheck();
		dataUpdater = gui.getDataUpdater();
		
		inputTextAnswer.setAlwaysFocused(true);
		
		submitAnswer = new Rectangle_(inputTextAnswer.getX() + inputTextAnswer.getWidth()+ 10, inputTextAnswer.getY(), 50, 50);	
		submitAnswer.setGradientFormat(Rectangle_.gradientFormat.vertical);
		submitAnswer.setBackgroundColors(submitBackgroundColor);
		submitAnswer.setBorderColors(submitBorderColor);
		submitAnswer.setFont(fntSubmit);
		submitAnswer.setFontColor(Color.white);
		submitAnswer.setBorderThickness(2);
		submitAnswer.setText("→");
	}

	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		renderNotQuestion(g);

		if(question != null){
			//this if sees if vanish mode applies
			if(gui.getTitlePage().questionPage == gui.getTitlePage().questionPage.Normal && vanishLvl > 0 && !askForConfirm) {
				long timeSpent = System.currentTimeMillis()-timeQuestionStartMillis;
				if(timeSpent < questionDisplayTime) {
					//System.out.println("Time spent:" + timeSpent + ". Now, under display time");
					if(timeSpent > decayStartTime) {
						//System.out.println("Time spent:" + timeSpent + ". Now, over decay start time");

						g.setColor(new Color(255, 255, 255, (int)((255 *(1 - (timeSpent-decayStartTime)/((double)questionDisplayTime-decayStartTime))))));

					}
				}else g.setColor(new Color(0,0,0,0));
				
			}else
				g.setColor(Color.white);

			g.setFont(fnt0);
			g.drawString(question.getQuestionText(), (int)(fnt0.getSize()), (int)(gui.HEIGHT * gui.SCALE / 2 - fnt0.getSize()) );

			
			g.setColor(Color.white);
			g.setFont(fnt2);
			g.drawString("Answer", fnt0.getSize() , inputTextAnswer.getY() + fnt0.getSize()/2);
			inputTextAnswer.render(g, true);

			if(strikeOutLvl > 0) {
				g.setFont(fntHeart);
				g.setColor(Color.red);
				g.drawString(getLifeText(strikeOutLives), inputTextAnswer.getX(), inputTextAnswer.getY() + inputTextAnswer.getHeight() + fntHeart.getSize());
			}
		}

		if(renderHelp) renderHelp(g);

	}

	public void renderHelp(Graphics g) {

		g.setFont(fntNormal);
		g.setColor(Color.white);
		g.drawString("Submit: Submit and check your answer", (int)(submitAnswer.x + submitAnswer.width * 1.5), submitAnswer.y + fntNormal.getSize());
		g.drawString("(Hot key: \"Space\")", (int)(submitAnswer.x + submitAnswer.width * 1.5), submitAnswer.y + fntNormal.getSize()*2);
		if(!timerHidden)
			g.drawString("Timer: Click to hide.", HomePage.x - 200, (int)(HomePage.y + HomePage.height + fntTimer.getSize()*3 + textSpacing*3));
		else
			g.drawString("Timer: Click to show.", HomePage.x - 200, (int)(HomePage.y + HomePage.height + fntTimer.getSize()*3 + textSpacing*3));

	}

	public void renderNotQuestion(Graphics g){
		Graphics2D g2d = (Graphics2D)g;

		g.drawImage(background, 0, 0, null);

		if (warned)
			splashText = "Are you sure?";
		g.setFont(fntSplash);
		g.setColor(Color.white);
		g.drawString(splashText, fnt0.getSize(), (int)(inputTextAnswer.getY() + inputTextAnswer.getHeight() * 2.7));
		g.setColor(new Color(39, 201, 14));
		g.drawString(splashText2, fnt0.getSize(), (int)(inputTextAnswer.getY() + inputTextAnswer.getHeight() * 3.7));

		submitAnswer.draw(g2d);
		HomePage.draw(g2d);

		if(finishClicked){
			finishSet.setColorState(1);
			finishSet.setFont(fnt1Small); 
			finishSet.setText("Press " + "\"" + finishSetPassword + "\"" + " to confirm.");
		}
		else {
			finishSet.setFont(fnt1); 
			finishSet.setText("Finish Set");
			finishSet.setColorState(0);	
		}
		
		finishSet.draw(g2d);
		
		g.setFont(fntTimer);
		g.setColor(Color.white);
		if(!endlessQuestions) {
			g.drawString("Q " + currentQuestion + "/" + numQuestions, HomePage.x - 200, (int) (HomePage.y + HomePage.height));
			g.drawString("Correct: " + numCorrect + "/" + (quesionsCompleted), HomePage.x - 200, (int) (HomePage.y + HomePage.height + fntTimer.getSize() + textSpacing));
		}
		else{
			g.drawString("Q " + currentQuestion + "/ --" , HomePage.x - 200, (int) (HomePage.y + HomePage.height));
			g.drawString("Correct: " + numCorrect + "/" + (quesionsCompleted), HomePage.x - 200, (int) (HomePage.y + HomePage.height  + fntTimer.getSize() + textSpacing));

		}
		timerTextLength = g.getFontMetrics().stringWidth(timerText);
		timerTextHeight = fnt1.getSize();
		if(!timerHidden)
			timerText = "Timer: " + timeMinutes + "' " +  timeSeconds + "\"";
		else
			timerText = "Timer: --";
		g.drawString(timerText, HomePage.x - 200, (int)(HomePage.y + HomePage.height + fntTimer.getSize()*2 + textSpacing*2));


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
			//if u got the question wrong, wait.
			if(currentQuestion <= numQuestions || endlessQuestions){
				genQuestion();
				timerUnPause();
				splashText = "";
				askForConfirm = false;
			}
		}

		else if (!askForConfirm || !warned){
			timeQuestionEndMillis = System.currentTimeMillis();
			checkAnswer();

		}

	}
	//will also update user analytics and achievement pages
	public void checkAnswer() {
		if(inputAnswer == question.getAnswer()) {
			quesionsCompleted++;
			correct();
		}
		else if ((inputAnswer > 0) && (inputAnswer == -question.getAnswer())){//if u put a positive answer instead of the negative one
			quesionsCompleted++;
			reversed();
		}
		else if(inputAnswer == 0 && question.getAnswer() != 0 && warned == false)
			warn();
		else {
			quesionsCompleted++;
			incorrect();
		}
		
		achCheck.updateAchievements();

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

		submitAnswer.setColors(0);

		genQuestion();

	}

	public void warn() {
		warned = true;
		submitAnswer.setColors(2);
	}

	public void reversed() {
		warned = false;
		splashText = randFromArray(almostThere) + " (Answer was " + question.getAnswer() + ")";
		submitAnswer.setColors(1);
		dataUpdater.updateQuestionComplete(question.getType(), difficulty);
		timerPause();
		askForConfirm = true;
		if(strikeOutLvl > 0) {
			strikeOutLives--;
		}
	}

	public void incorrect() {
		warned = false;
		splashText = randFromArray(incorrect) + " (Answer was " + question.getAnswer() + ")";
		submitAnswer.setColors(1);
		dataUpdater.updateQuestionComplete(question.getType(), difficulty);
		timerPause();
		askForConfirm = true;
		if(strikeOutLvl > 0) {
			strikeOutLives--;
		}
		
	}

	public void genQuestion(){
		if(currentQuestion == numQuestions && !endlessQuestions ||(strikeOutLvl > 0 && strikeOutLives == 0)){
			timerPaused = false;
			switchToResults();
			return;
		}

		else {
			submitAnswer.setColors(0);

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
		
		vanishLvl = gui.getLevSelect().getVanishLvl();
		strikeOutLvl = gui.getLevSelect().getStrikeOutLvl();
		if(strikeOutLvl > 0) {
			strikeOutLives = 4 - strikeOutLvl;
		}
		
		questionDisplayTime = gui.getLevSelect().getVanishTime();
		decayStartTime = gui.getLevSelect().getVanishDecayStartTime();
		System.out.println(questionDisplayTime + " " + decayStartTime);
		
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
		endlessQuestions = gui.getLevelSelectSpecial().endlessQuestions;

		inputTextAnswer.attemptFocus(inputTextAnswer.getX(), inputTextAnswer.getY());
		inputTextAnswer.setText("0");
		setType = 1;
		genQuestion();
	}

	public void switchToResults() {
		
		//System.out.println("Num questions" + numQuestions + ", current Questions" + currentQuestion + ", questions correct" + numCorrect);
		if(endlessQuestions) { //some botched code. 
			numQuestions = currentQuestion;
			if(!askForConfirm)
				numQuestions--;
		}
		
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
		quesionsCompleted = 0;

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

		timeNaught = System.currentTimeMillis();
		deltaMillis = 0;
		timeMinutes = 0;
		timerPaused = false;
		startReseted = false;
		//no need to reset timeSeconds since it is activelly updated.

		long[][] timeAverageSumEmpty = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};
		int[][] timeAverageCountEmpty = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}};

		GUI.uData.tempTimeAverageCount = timeAverageCountEmpty;
		GUI.uData.tempTimeAverageSum = timeAverageSumEmpty;

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
		    //this is for the timer
			timeNaught += deltaMillisFix;
			timeQuestionStartMillis += deltaMillisFix;
		
			
			//deltamillisfix will be added to qtimestart when resume button clicked, not when wait for confirm is over
			System.out.println("deltamillisfix: " + deltaMillisFix + "Timer resume!");
		}
	}
	
	public double getAccuracy() {
		if(currentQuestion == 0)
			return 0;
		else {
			//System.out.println("Accuracy: " + (double)numCorrect/(currentQuestion-1));
			return (double)numCorrect/(currentQuestion-1);
		}
			
	}
	
	public String randFromArray(String[] input){
		int random = rnd.nextInt(input.length);
		//System.out.println("Array Lenght:" + random);
		return input[random];
	}
	
	public String getLifeText(int lives) {
		if(lives <= 0 || lives > 3) return "";
		switch(lives) {
		case 1:
			return "♥";
		case 2:
			return "♥ ♥";
		case 3: 
			return "♥ ♥ ♥";
		}
		return "";
			
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

