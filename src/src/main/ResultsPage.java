package src.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import k_Methods.MoColors;
import k_Methods.RectanglePlus;
import k_Methods.RectanglePlus.gradientFormat;
import src.main.Achievements.AchievementCheck;

public class ResultsPage {
	
	GUI gui;
	QuestionPageNumber qPage;
	QuestionPageYesNo qPageYN;
	AchievementCheck achCheck;
	
	int correct;
	int totalQ;
	String difficulty;
	int challengeLvl;
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
		
	Font fnt0 = new Font("Lucida Bright", Font.ITALIC, gui.HEIGHT * gui.SCALE / 12);
	Font fnt1 = new Font("Lucida Bright", Font.BOLD, 15);
	Font fnt2 = new Font("Lucida Bright", Font.BOLD, 22);
	Font fntNormal = new Font("Garamond", Font.PLAIN, 30);
	Font fntSmallish = new Font("Garamond", Font.PLAIN, 20);
	
	public RectanglePlus HomePage = new RectanglePlus(gui.WIDTH * gui.SCALE - 140, 15, 120, 20,  
			MoColors.royalBlue, MoColors.deepSkyBlue, true, gradientFormat.horizontal, MoColors.navy,
			fnt1, Color.white, "Home");
	
	int x1 = 80, y1 = 80, y2 = gui.HEIGHT * gui.SCALE / 3;
	int spacing = (int)(fntNormal.getSize() * 1.5);
	
	Color[] rollBackColor1 = {MoColors.darkTurquoise, MoColors.lemonChiffon};
	Color[] rollBackColor2 = {MoColors.dodgerBlue, MoColors.silver};
	Color[] rollBackBorderColor = {MoColors.orange, MoColors.lightGoldenrodYellow};
	Color[] rollBackFontColor= {MoColors.white, MoColors.gray};
	
	public RectanglePlus newRound = new RectanglePlus(x1, gui.HEIGHT * gui.SCALE - 100, 150, 50, MoColors.royalBlue, MoColors.deepSkyBlue, true, gradientFormat.horizontal, MoColors.chartreuse,
			fnt2, Color.white, "New Round");
	public RectanglePlus rollBack = new RectanglePlus(x1, newRound.y - newRound.height - 15, 150, 50, rollBackColor1, rollBackColor2, true, gradientFormat.horizontal, rollBackBorderColor,
			fnt1, rollBackFontColor, "Roll Back");	
	
	boolean rolledBack = false;
	
	int memeWidth = 560, memeHeight = 315;
	
	Image goodJob1 = new ImageIcon("images/GoodJob1.jpeg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image goodJob2 = new ImageIcon("images/GoodJob2.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image goodJob3 = new ImageIcon("images/GoodJob3.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image goodJob4 = new ImageIcon("images/GoodJob4.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image goodJob5 = new ImageIcon("images/GoodJob5.png").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image goodJob6 = new ImageIcon("images/GoodJob6.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image goodJob7 = new ImageIcon("images/GoodJob7.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image goodJob8 = new ImageIcon("images/GoodJob8.jpeg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image goodJob9 = new ImageIcon("images/GoodJob9.png").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image goodJob10 = new ImageIcon("images/GoodJob10.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image goodJob11 = new ImageIcon("images/GoodJob11.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);

	Image okayJob1 = new ImageIcon("images/OkayJob1.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image okayJob2 = new ImageIcon("images/OkayJob2.png").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image okayJob3 = new ImageIcon("images/OkayJob3.jpeg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image okayJob4 = new ImageIcon("images/OkayJob4.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image okayJob5 = new ImageIcon("images/OkayJob5.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image okayJob6 = new ImageIcon("images/OkayJob6.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image okayJob7 = new ImageIcon("images/OkayJob7.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	//Image okayJob8 = new ImageIcon("images/OkayJob8.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image okayJob9 = new ImageIcon("images/OkayJob9.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);

	Image badJob1 = new ImageIcon("images/BadJob1.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image badJob2 = new ImageIcon("images/BadJob2.png").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image badJob3 = new ImageIcon("images/BadJob3.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image badJob4 = new ImageIcon("images/BadJob4.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image badJob5 = new ImageIcon("images/BadJob5.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image badJob6 = new ImageIcon("images/BadJob6.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image badJob7 = new ImageIcon("images/BadJob7.jpeg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image badJob8 = new ImageIcon("images/BadJob8.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);
	Image badJob9 = new ImageIcon("images/BadJob9.jpg").getImage().getScaledInstance(memeWidth, memeHeight, Image.SCALE_DEFAULT);

	Image[] goodJobBackgrounds = {goodJob1, goodJob2, goodJob3, goodJob4, goodJob5, goodJob6, goodJob7, goodJob8, goodJob9, goodJob10, goodJob11};
	Image[] okayJobBackgrounds = {okayJob1, okayJob2, okayJob3, okayJob4, okayJob5, okayJob6, okayJob7, okayJob9};
	Image[] badJobBackgrounds = {badJob1, badJob2, badJob3, badJob4, badJob5, badJob6, badJob7, badJob8, badJob9};
	
	Image resultMeme =  null;
	Image background =  new ImageIcon("images/quickMathsLevelSelectBackground1.jpg").getImage().getScaledInstance(gui.WIDTH * gui.SCALE, gui.HEIGHT * gui.SCALE, Image.SCALE_DEFAULT);
	
	public ResultsPage(GUI gui){
		this.gui = gui;
		achCheck = new AchievementCheck(gui);
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;

		g.drawImage(background, 0, 0, null);
		g.drawImage(resultMeme, (int)(gui.WIDTH * gui.SCALE * .48), (int)(gui.HEIGHT * gui.SCALE * .21), null);
		
		g.setColor(Color.white);
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
		
		if(challengeLvl > 0) 
				g.drawString("Vanish Challenge Level: " + challengeLvl, x1, y2 + spacing*4);

		
		
		g.setColor(splashTextColor);
		g.drawString(splashText, x1, y2 + (int)(spacing*5.5));

		g.setColor(Color.white);
		g.setFont(fnt0);
		g.drawString("Score: ", (int)(gui.WIDTH * gui.SCALE - fnt0.getSize() * 5), (int)(gui.HEIGHT * gui.SCALE - fnt0.getSize() * 2));
		g.setColor(percentColor);
		g.drawString(correctText, (int)(gui.WIDTH * gui.SCALE - fnt0.getSize() * 5), (int)(gui.HEIGHT * gui.SCALE - fnt0.getSize()));
		
		try {
			if(qPage.askToRollBack && !rolledBack){
				rollBack.setColorState(0);
				rollBack.draw(g2d);	
				g.setColor(Color.black);
				g.setFont(fntSmallish);
				g.drawString("Your average time for this set was abnoramlly high.", rollBack.x + rollBack.width + 20, rollBack.y + 15);
				g.drawString("Would you like to roll back time data for this round?", rollBack.x + rollBack.width + 20, rollBack.y + 15 + fntNormal.getSize());
			}
			else if (rolledBack){
				rollBack.setColorState(1);
				rollBack.draw(g2d);				
				g.setColor(Color.black);
				g.setFont(fntSmallish);
				g.drawString("Data successfully rolled back.", rollBack.x + rollBack.width + 20, rollBack.y + 15);
				g.drawString("Time average data for this round will not be counted.", rollBack.x + rollBack.width + 20, rollBack.y + 15 + fntNormal.getSize());
			}	

		}catch(NullPointerException e) {}
		HomePage.draw(g2d);
		newRound.draw(g2d);
		
	}
	
	public void initialize() {
		qPage = gui.getQuestionPage();
				
		correct = qPage.numCorrect;
		totalQ = qPage.numQuestions;
		calculateScore(correct, totalQ);
		difficulty = "" + qPage.getDifficulty();
		dififcultyToString();
		challengeLvl = gui.getLevSelect().challengeLvl;
		
		rolledBack = false;		
		
		splashText = setSplahText();
		splashTextColor = randColor();
		
		questionType = getquestionType();

		achCheck.updateAchievements();
		
		System.out.println("Total average deviation:"  + qPage.timeAverageDeviationAverage);
	}
	
	public void initializeSpecial(){
		qPage = gui.getQuestionPage();
		
		correct = qPage.numCorrect;
		totalQ = qPage.numQuestions;
		calculateScore(correct, totalQ);
		difficulty = "" + qPage.getDifficulty();
		dififcultyToString();
		challengeLvl = -1;
		
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
		
		if(percentCorrect >= 0.85) {
			resultMeme = randFromArray(goodJobBackgrounds);
		}
		else if(percentCorrect > 0.65 && percentCorrect < 0.85) {
			resultMeme = randFromArray(okayJobBackgrounds);
		}
		else 
			resultMeme = randFromArray(badJobBackgrounds);

		
	}
	
	public String randFromArray(String[] input){
		int random = rnd.nextInt(input.length);
		//System.out.println("Array Lenght:" + random);
		return input[random];
	}
	
	public Image randFromArray(Image[] input) {
		int random = rnd.nextInt(input.length);
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