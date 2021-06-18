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
	String correctText;
	String questionType;
	Color percentColor;
	Random rnd = new Random();
	
	String[] perfect = {"UNIVVVERSSSE BRAIN", "Absolute POGCHAMP", "You kno da wae", "WawaWeewa", "SHEEEeeesh"};
	String[] good = {"GALAXY BRAIN", "Lessgo", "NOICE", "Poggers", "*clap* *clap* *clap*", "Verryyy nice"};
	String[] ok = {"Not bad", "A is for Average. B is for Dissapoint. DONT BE A DISSAPOINTMENT", "Hmmmmm", "Eeetss...ok"};
	String[] bad = {"tiny brain", "You do not da wae", "Welp you can only go uphill from here"};
	Color splashTextColor;

	public Rectangle HomePage = new Rectangle(gui.WIDTH * gui.SCALE - 140, 15, 120, 20);
	
	Font fnt0 = new Font("Arial", Font.ITALIC, gui.HEIGHT * gui.SCALE / 12);
	Font fnt1 = new Font("Garamond", Font.BOLD, HomePage.height);
	Font fntPlay= new Font("Arial", Font.BOLD, 25);
	Font fntNormal = new Font("Garamond", Font.PLAIN, 30);
	Font fntSmallish = new Font("Garamond", Font.PLAIN, 20);
	
	int x1 = 80, y1 = 80, y2 = gui.HEIGHT * gui.SCALE / 3;
	int spacing = (int)(fntNormal.getSize() * 1.5);
	
	public Rectangle playButton = new Rectangle(x1, gui.HEIGHT * gui.SCALE - 100, 150, 50);
	public Rectangle rollBack = new Rectangle(x1, (int)(y2 + spacing * 6.5), 150, 50);
	boolean rolledBack = false;

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
		if(gui.getTitlePage().questionPage == gui.getTitlePage().questionPage.SpecialYN )
			g.drawString("Time Spent: " + qPageYN.timeMinutes + "' " +  qPageYN.timeSeconds + "\"", x1, y2 + spacing*3);
		else
			g.drawString("Time Spent: " + qPage.timeMinutes + "' " +  qPage.timeSeconds + "\"", x1, y2 + spacing*3);
		
		if(qPage.askToRollBack && !rolledBack){
			g.setColor(Color.orange);
			g2d.draw(rollBack);
			g.setFont(fntPlay);
			g.setColor(Color.black);
			g.drawString("Roll Back", rollBack.x + 10, rollBack.y + 30);
			g.setFont(fntSmallish);
			g.drawString("Your average time for this set was abnoramlly high.", rollBack.x + rollBack.width + 20, rollBack.y + 15);
			g.drawString("Would you like to roll back time data for this round?", rollBack.x + rollBack.width + 20, rollBack.y + 15 + fntNormal.getSize());
		}
		else if (rolledBack){
			g.setColor(Color.gray);
			g2d.draw(rollBack);
			g.setFont(fntPlay);
			g.drawString("Roll Back", rollBack.x + 10, rollBack.y + 30);
			g.setColor(Color.black);
			g.setFont(fntSmallish);
			g.drawString("Data successfully rolled back.", rollBack.x + rollBack.width + 20, rollBack.y + 15);
			g.drawString("Time average data for this round will not be counted.", rollBack.x + rollBack.width + 20, rollBack.y + 15 + fntNormal.getSize());
		}
		
		g.setColor(splashTextColor);
		g.drawString(splashText, x1, y2 + spacing*5);

		g.setColor(Color.black);
		g.setFont(fnt0);
		g.drawString("Score: ", (int)(gui.WIDTH * gui.SCALE - fnt0.getSize() * 5), (int)(gui.HEIGHT * gui.SCALE - fnt0.getSize() * 2));
		g.setColor(percentColor);
		g.drawString(correctText, (int)(gui.WIDTH * gui.SCALE - fnt0.getSize() * 5), (int)(gui.HEIGHT * gui.SCALE - fnt0.getSize()));
		
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
		difficulty = "" + qPage.getDifficulty();
		dififcultyToString();
		
		rolledBack = false;		
		
		splashText = setSplahText();
		splashTextColor = randColor();
		
		questionType = getquestionType();

		achCheck.checkVanillaAch();
		gui.getAchPage().updateAchievementsWithUserData();
		
		System.out.println("Total average deviation:"  + qPage.timeAverageDeviationAverage);
	}
	
	public void initializeSpecial(){
		qPage = gui.getQuestionPage();
		
		correct = qPage.numCorrect;
		totalQ = qPage.numQuestions;
		calculateScore(correct, totalQ);
		difficulty = "" + qPage.getDifficulty();
		dififcultyToString();
		
		rolledBack = false;		

		splashText = setSplahText();
		splashTextColor = randColor();
		
		String questionSpecialTypeText = (qPage.questionSpecialType == 2) ? "*" : "/";
		questionType = "Specific Question Type Chosen: " + questionSpecialTypeText + qPage.questionSpecialNum2;
		
	}
	
	public void initializeYN(){
		qPageYN = gui.getQuestionPageYesNo();

		correct = qPageYN.numCorrect;
		totalQ = qPageYN.numQuestions;
		calculateScore(correct, totalQ);
		difficulty = "" + gui.getQuestionPageYesNo().getDifficulty();
		dififcultyToString();
		
		splashText = setSplahText();
		splashTextColor = randColor();
				
		questionType = "Question Type: Is it divisible?";
	}
	
	public void calculateScore(int numCorrect, int totalQuestions){
		percentCorrect = (double)correct/totalQ;

		if(totalQuestions > 0 ){
			correctText =  "" + Math.round(percentCorrect * 100); 
			percentColor = new Color((int)((1-percentCorrect) * 255), (int)(percentCorrect * 255), 0);
			//percentColor = new Color(0, 255, 0);
		}
		else{
			correctText = ">_>";
			double randomCorrect = 1-rnd.nextDouble();
			percentColor = new Color((int)((1-randomCorrect) * 255), (int)(randomCorrect * 255), 0);
		}
		System.out.println("Percent Correct: " + percentCorrect);
	}
	
	public String randFromArray(String[] input){
		int random = rnd.nextInt(input.length);
		//System.out.println("Array Lenght:" + random);
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
	
	public void rollBack(){
		for(int i = 0; i < gui.uData.timeAverageSum.length; i++)
		{
			for(int j = 0; j <gui.uData.timeAverageSum[i].length;j++){
				gui.uData.timeAverageSum[i][j] -= gui.uData.tempTimeAverageSum[i][j];
				gui.uData.timeAverageCount[i][j] -= gui.uData.tempTimeAverageCount[i][j];
			}
		}
	}//rollback
}
