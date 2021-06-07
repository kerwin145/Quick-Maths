import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class QuestionPage {

	GUI gui;
	private UserData uData;
	private ArrayList<Integer> questionTypes = new ArrayList<Integer>();
	
	public int numQuestions = 0;
	public int numCorrect = 0;
	public boolean setFinished = true, setSpecialFinished = true;
	private int currentQuestion = 0;
	private int difficulty; //from 1 as easy to 4 as insane
	public int questionSpecialNum2 = -1; //used for specific mult or division
	public int questionSpecialType = -1; //2 for mult, 3 for division
	private boolean onlyPositive, perfectDivisor;
	
	public int setType;
	
	String splashText = "";
	boolean askForConfirm = false; //used when your answer is wrong
	private Color submitColor = Color.black;
	public boolean renderHelp = false;
	public boolean warned = false;
	
	private Question question;
	Random rnd = new Random();
	private dataUpdater dataUpdater;

	int inputAnswer;
	
	String[] correct = {"Nice!", "Correct!", "Yes", "Keep it up!", "BIG BRAIN", "U r very smart", "Yessir"};
	String[] incorrect = {"Oops!", "You still got this!", "Tiny brain?", "Come on man", "Not time to give up yet!"};
	String[] almostThere = {"Bah", "Oof", "Sadge"};

	String[] difList = {"Easy", "Medium", "Hard", "Insane"}; //used for getdifficulty method, nothing much else

	Font fnt0 = new Font("Arial", Font.ITALIC|Font.BOLD, gui.HEIGHT * gui.SCALE / 8);
	Font fnt2 = new Font("Arial", Font.PLAIN, gui.HEIGHT * gui.SCALE / 12);
	Font fntNormal = new Font("Garamond", Font.PLAIN, 15);
	
	int width1 = gui.WIDTH * gui.SCALE /4;
	NumberTextField inputTextAnswer = new NumberTextField(fnt0.getSize() + fnt2.getSize() * 7/2, gui.HEIGHT * gui.SCALE / 2, width1, 50, "0", true);
	public Rectangle HomePage = new Rectangle(gui.WIDTH * gui.SCALE - 140, 15, 120, 20);
	Font fnt1 = new Font("Garamond", Font.BOLD, HomePage.height);
	
	public Rectangle submitAnswer = new Rectangle(inputTextAnswer.getX() + inputTextAnswer.getWidth() + 60, inputTextAnswer.getY(), 50, 50);
	public Rectangle InfoBox = new Rectangle(20, 20, 40, 40);
	Font fnt3 = new Font("Georgia", Font.PLAIN, (int)(InfoBox.width/1.5));;

	public QuestionPage (GUI gui){
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
			inputTextAnswer.render(g);
			if (warned) 
				splashText = "Are you sure?";
			g.drawString(splashText, fnt0.getSize(), (int)(inputTextAnswer.getY() + inputTextAnswer.getHeight() * 2));

			g.setColor(submitColor);
			g2d.draw(submitAnswer);
			g.drawString("->", submitAnswer.x, (int)(submitAnswer.y + submitAnswer.height * 0.87));
						
			g.setFont(fnt1);
			g.setColor(Color.gray);
			g.drawString("Q " + currentQuestion + "/" + numQuestions, gui.HEIGHT * gui.SCALE - 40, (int)(HomePage.y + HomePage.height * 1.5));
			g.drawString("Correct: " + numCorrect + "/" + (currentQuestion - 1), gui.HEIGHT * gui.SCALE - 40,  (int)(HomePage.y + HomePage.height * 3));
		}
		
		g.setColor(Color.black);
		g2d.draw(HomePage);
		g2d.setFont(fnt1);
		g.drawString("Home", HomePage.x + HomePage.width/4, (int)(HomePage.y + HomePage.height * 0.85));
		
		g.setColor(Color.gray);
		g2d.draw(InfoBox);
		g.setFont(fnt3);
		g.drawString("i", (int)(InfoBox.x + fnt3.getSize() * 0.6), (int)(InfoBox.y  + fnt3.getSize()));
		
		if(renderHelp) renderHelp(g);
		
	}
	
	public void renderHelp(Graphics g) {
		g.setFont(fntNormal);
		g.drawString("Number Text Field:", inputTextAnswer.getX(), inputTextAnswer.getY() + fntNormal.getSize() * -3);
		g.drawString("Click to activate typing.", inputTextAnswer.getX(), inputTextAnswer.getY() + fntNormal.getSize()* -2);
		g.drawString("Type \"-\" to negate number.", inputTextAnswer.getX(), inputTextAnswer.getY() + fntNormal.getSize()*-1);

		g.drawString("Submit: Submit and check your answer", (int)(submitAnswer.x + submitAnswer.width * 1.5), submitAnswer.y + fntNormal.getSize());
		g.drawString("(Hot key: \"Enter\")", (int)(submitAnswer.x + submitAnswer.width * 1.5), submitAnswer.y + fntNormal.getSize()*2);

	}
	
	public void submitAnswer() {
		inputAnswer = inputTextAnswer.retrieveNum();
		renderHelp = false;
		
		if (askForConfirm){ //after you finish looking at the answer when u get the answer wrong
			
			if (warned){ //you have been wanred, proceed to check answers
				askForConfirm = false;
				checkAnswer();
			}
			
			else if(currentQuestion <= numQuestions){ //Gen question if not a warning
				genQuestion();
				splashText = "";
				submitColor = Color.black;
				askForConfirm = false;
			}
		}
		
		else{
			checkAnswer();
		}
		
	}
	//warn will not haev useranalytics update
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
		splashText = randFromArray(correct);
		dataUpdater.updateQuestionComplete(question.getType(), difficulty);
		dataUpdater.updateQuestionCorrect(question.getType(), difficulty);
		
		genQuestion();	
		
	}
	
	
	public void warn() {
		warned = true;
		askForConfirm = true;
	}
	public void reversed() {
		warned = false;
		splashText = randFromArray(almostThere) + " (Answer was " + question.getAnswer() + ")";
		submitColor = Color.orange;
		dataUpdater.updateQuestionComplete(question.getType(), difficulty);
		askForConfirm = true;
	}
	
	public void incorrect() {
		warned = false;
		splashText = randFromArray(incorrect) + " (Answer was " + question.getAnswer() + ")";
		submitColor = Color.orange;
		dataUpdater.updateQuestionComplete(question.getType(), difficulty);
		askForConfirm = true;
	}
	
	public void genQuestion(){
		if(currentQuestion == numQuestions) switchToResults();

		else {
			if(setType == 0)
				question = new Question(questionTypes, difficulty, onlyPositive, perfectDivisor);
			else if (setType == 1)
				question = new Question(questionSpecialType, difficulty, questionSpecialNum2);
			
			currentQuestion++;
			inputTextAnswer.setText("0");			
		}

	}
	
	public void initializeRound(){
		//reset question types
		questionTypes.clear();
		
		boolean qChosen[] = gui.getLevSelect().getQChosen();
		//go through the list of operations checked and add them to the questionTypes list. 
		for (int i = 0; i < qChosen.length; i++){
			if(qChosen[i]) questionTypes.add(i);
		}
		setType = 0;
		currentQuestion = 0;
		numCorrect = 0;
		numQuestions = gui.getLevSelect().numQuestionsInput.retrieveNum();
		difficulty = gui.getLevSelect().getQuestionDifficulty();
		
		//only one of these can be false at the same time. 
		setFinished = false;
		setSpecialFinished = true;
		
		inputTextAnswer.setText("0");
		splashText = "";
		warned = false;
		askForConfirm = false;
		onlyPositive = gui.getLevSelect().onlyPositive;
		perfectDivisor = gui.getLevSelect().perfectDivisors;
		
		setType = 0;	
		genQuestion();
		
	}
	
	public void initializeRoundSpecial(){
		questionTypes.clear();
		
		currentQuestion = 0;
		numCorrect = 0;
		numQuestions = gui.getLevelSelectSpecial().numQuestionsInput.retrieveNum();

		questionSpecialNum2 = gui.getLevelSelectSpecial().specialQuestionNum2;
		questionSpecialType = gui.getLevelSelectSpecial().specialQTypeChosen;
		difficulty = gui.getLevelSelectSpecial().getQuestionDifficulty();
		
		setSpecialFinished = false;
		setFinished = true;
		
		inputTextAnswer.setText("0");
		splashText = "";
		warned = false;
		askForConfirm = false;
		
		setType = 1;	
		genQuestion();
	}
	
	private void switchToResults() {
		setFinished = true;
		setSpecialFinished = true;
		if(setType == 0)
			gui.getResultsPage().initialize();
		else if(setType == 1)
			gui.getResultsPage().initializeSpecial();
		gui.State = gui.State.RESULTS;

	}
	
	public String randFromArray(String[] input){
		int random = rnd.nextInt(input.length);
		//System.out.println("Array Lenght:" + random);
		return input[random];
	}
	public boolean getSetNormalFinished() {return setFinished;}
	public boolean getSetSpecialFinised() {return setSpecialFinished;}
	
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
	

}
