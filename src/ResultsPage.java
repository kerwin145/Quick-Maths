import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class ResultsPage {
	
	GUI gui;
	QuestionPageNumber qPage;
	QuestionPageYesNo qPageYN;
	AchievementCheck achCheck;
	
	int correct;
	int totalQ;
	String difficulty;
	String splashText;
	double percentCorrect;
	String questionType;
	Color percentColor;
	Random rnd = new Random();
	
	String[] perfect = {"UNIVVVERSSSE BRAIN", "Absolute POGCHAMP", "You kno da wae", "WawaWeewa"};
	String[] good = {"GALAXY BRAIN", "SHEEEeeesh", "NOICE", "Poggers", "*clap* *clap* *clap*", "Verryyy nice"};
	String[] ok = {"Not bad", "A is for Average. B is for Dissapoint. DONT BE A DISSAPOINTMENT", "Hmmmmm", "Eeetss...ok"};
	String[] bad = {"tiny brain", "You do not da wae", "Welp you can only go uphill from here"};
	Color splashTextColor;

	public Rectangle HomePage = new Rectangle(gui.WIDTH * gui.SCALE - 140, 15, 120, 20);
	
	Font fnt0 = new Font("Arial", Font.ITALIC, gui.HEIGHT * gui.SCALE / 12);
	Font fnt1 = new Font("Garamond", Font.BOLD, HomePage.height);
	Font fntPlay= new Font("Arial", Font.BOLD, 25);
	Font fntNormal = new Font("Garamond", Font.PLAIN, 30);
	
	int x1 = 80, y1 = 80, y2 = gui.HEIGHT * gui.SCALE / 3;
	int spacing = (int)(fntNormal.getSize() * 1.5);
	
	public Rectangle playButton = new Rectangle(x1, gui.HEIGHT * gui.SCALE - 100, 150, 50);

	public ResultsPage(GUI gui){
		this.gui = gui;
		achCheck = new AchievementCheck(this, gui);
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;

		g.setFont(fnt0);
		g.drawString("Result: ", x1, y1);
		g.setFont(fntNormal);
		g.drawString("You got: " + correct + " out of " + totalQ + " Correct!", x1, y2 );
		g.drawString(questionType , x1, y2 + spacing);
		g.drawString("Difficulty: " + difficulty, x1, y2 + spacing*2);
		g.setColor(splashTextColor);
		g.drawString(splashText, x1, y2 + spacing*4);

		g.setColor(Color.black);
		g.setFont(fnt0);
		g.drawString("Score: ", (int)(gui.WIDTH * gui.SCALE - fnt0.getSize() * 5), (int)(gui.HEIGHT * gui.SCALE - fnt0.getSize() * 2));
		g.setColor(percentColor);
		g.drawString("" + ((int)(percentCorrect * 100)), (int)(gui.WIDTH * gui.SCALE - fnt0.getSize() * 5), (int)(gui.HEIGHT * gui.SCALE - fnt0.getSize()));
		
		g.setColor(Color.black);
		g2d.draw(HomePage);
		g2d.setFont(fnt1);
		g.drawString("Home", HomePage.x + HomePage.width/4, (int)(HomePage.y + HomePage.height * 0.85));
		
		g.setColor(Color.orange);
		g2d.draw(playButton);
		g.setFont(fntPlay);
		g.setColor(Color.black);
		g.drawString("New Game", playButton.x + 10, playButton.y + 30);
		
	}
	
	public void initialize() {
		qPage = gui.getQuestionPage();
		
		correct = qPage.numCorrect;
		totalQ = qPage.numQuestions;
		calculateScore(correct, totalQ);
		percentCorrect = (double)correct/totalQ; 
		difficulty = "" + qPage.getDifficulty();
		dififcultyToString();
		
		splashText = setSplahText();
		splashTextColor = randColor();
		System.out.println("Percent Correct: " + percentCorrect);
		
		questionType = getquestionType();

		achCheck.checkVanillaAch();
		gui.getAchPage().updateAchievementsWithUserData();
	}
	
	public void initializeSpecial(){
		qPage = gui.getQuestionPage();
		
		correct = qPage.numCorrect;
		totalQ = qPage.numQuestions;
		calculateScore(correct, totalQ);
		percentCorrect = (double)correct/totalQ;
		difficulty = "" + qPage.getDifficulty();
		dififcultyToString();
		
		splashText = setSplahText();
		splashTextColor = randColor();
		System.out.println("Percent Correct: " + percentCorrect);
		
		String questionSpecialTypeText = (qPage.questionSpecialType == 2) ? "*" : "/";
		questionType = "Specific Question Type Chosen: " + questionSpecialTypeText + qPage.questionSpecialNum2;
		
	}
	
	public void initializeYN(){
		qPageYN = gui.getQuestionPageYesNo();

		correct = qPageYN.numCorrect;
		totalQ = qPageYN.numQuestions;
		calculateScore(correct, totalQ);
		difficulty = "" + gui.getLevelSelectSpecial().questionDifficulty;
		dififcultyToString();
		
		splashText = setSplahText();
		splashTextColor = randColor();
		System.out.println("Percent Correct: " + percentCorrect);
		
		questionType = "Question Type: Is it divisible?";
	}
	
	public void calculateScore(int numCorrect, int totalQuestions){
		if(totalQuestions > 0 )
			percentCorrect = (double)numCorrect/totalQuestions; 
		else percentCorrect = 0;
		
		percentColor = new Color((int)((1-percentCorrect) * 255), (int)(percentCorrect * 255), 0);

	}
	
	public String randFromArray(String[] input){
		int random = rnd.nextInt(input.length);
		System.out.println("Array Lenght:" + random);
		return input[random];
	}
	
	private String setSplahText() {
		if(percentCorrect == 1) 
			return randFromArray(perfect);
		else if (percentCorrect >= 0.85 && percentCorrect < 1)
			return randFromArray(good);
		else if (percentCorrect >= 0.65 && percentCorrect < 0.85)
			return randFromArray(ok);
		else 
			return randFromArray(bad);
	}
	
	private String getquestionType() {
		questionType = "";
		
		String[] operations = {"Addition", "Subtraction", "Multiplication", "Division"}; //since questiontypes is a list of integers, we need to connect this to a list of string to translate to english
		for (int i = 0; i < qPage.getQuestionTypes().size(); i++) {
			questionType += operations[qPage.getQuestionTypes().get(i)] + ", ";
		}
		return "Operations chosen: " + questionType.substring(0, questionType.length() - 2); //cut off the comma at the end
	}
	
	public Color randColor(){
		int num1 = 0, num2 = 0, num3 = 0;
		while(num1 + num2 + num3 < 80){ //don't be too transparent
			num1 = rnd.nextInt(256);
			num2 = rnd.nextInt(256);
			num3 = rnd.nextInt(256);
		}	
		return new Color(num1,num2,num3);
	}
	
	public double getPercentCorrect() {return percentCorrect;}
	
	public AchievementCheck getAchCheck() {return achCheck;}
	
	private void dififcultyToString(){
		switch(Integer.parseInt(difficulty)){
		case 0:
			difficulty = "Easy";
			break;
		case 1:
			difficulty = "Medium";
			break;
		case 2:
			difficulty = "Hard";
			break;
		case 3:
			difficulty = "Insane";
			break;
		default:
			difficulty = "Huh?";
			break;
		
		}
	}
}
