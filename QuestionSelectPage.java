import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import k_Methods.MoColors;
import k_Methods.RectanglePlus;
import k_Methods.stringGraphics;
import k_Methods.RectanglePlus.gradientFormat;

public abstract class QuestionSelectPage {

	GUI gui;

	private String beginText = "Begin!";
	int buttonWidth1 = 60, buttonHeight1 = 60;

	public int difficulty = -1;
	public boolean endlessQuestions = false;

	boolean renderHelp;
	public QuestionSelectPage(GUI gui){
		this.gui = gui;
	}

	Font fnt0 = new Font("Lucida Bright", Font.PLAIN, (int)(buttonHeight1/1.6));
	Font fnt1 = new Font("Lucida Bright", Font.BOLD, 15);
	Font fnt2 = new Font("Lucida Bright", Font.PLAIN, 20);
	Font fnt3 = new Font("Georgia", Font.PLAIN, (int)(40/1.5));
	Font fntHeader = new Font("Lucida Bright", Font.BOLD, (int)(buttonHeight1/2));
	Font fntNormal = new Font("Garamond", Font.PLAIN, (int)(buttonHeight1/4));
	Font fntSmall = new Font("Times", Font.PLAIN, (int)(buttonHeight1/6));
	Font fntNormal2 = new Font("Garamond", Font.PLAIN, (int)(buttonHeight1/3.5));

	Color[] GenerateSetColor1 = {MoColors.deepSkyBlue, MoColors.royalBlue};
	Color[] GenerateSetColor2 = {Color.gray, MoColors.deepSkyBlue };
	Color[] GenerateSetBorderColor = {Color.gray, Color.cyan};
	Color[] GenerateSetTextColor = {Color.white};
	
	Color[] EndlessColor1 = {MoColors.orange, MoColors.dodgerBlue};
	Color[] EndlessColor2 = {MoColors.salmon, MoColors.aqua };
	Color[] EndlessBorderColor = {Color.gray, MoColors.chartreuse};
	Color[] EndlessTextColor = {Color.white, Color.white};
	
	public NumberTextField numQuestionsInput = new NumberTextField(120, (int)(gui.HEIGHT *gui.SCALE * .85), 200, 35, "0", false);

	public RectanglePlus HomePage = new RectanglePlus(gui.WIDTH * gui.SCALE - 140, 15, 120, 20,  
			MoColors.royalBlue, MoColors.deepSkyBlue, true, gradientFormat.horizontal, MoColors.navy,
			fnt1, Color.white, "Home"); 
	
	public RectanglePlus GenerateSet = new RectanglePlus(HomePage.x, (int)((gui.HEIGHT * gui.SCALE) * .85), buttonWidth1 * 2, buttonHeight1,  
			GenerateSetColor1, GenerateSetColor2, true, gradientFormat.horizontal, GenerateSetBorderColor,
			fnt3, GenerateSetTextColor, beginText); 
	
	public RectanglePlus Endless = new RectanglePlus
			(numQuestionsInput.getX(), (int)(numQuestionsInput.getY() + numQuestionsInput.getHeight() * 1.25) , (int)(buttonWidth1 * 2.75), numQuestionsInput.getHeight(), 
			EndlessColor1, EndlessColor2, true, gradientFormat.horizontal, EndlessBorderColor,
			fnt1, EndlessTextColor, "Endless Questions");
	
//	public Rectangle GenerateSet = new Rectangle(HomePage.x + HomePage.width - buttonWidth1*2, (int)((gui.HEIGHT * gui.SCALE) * .85), (buttonWidth1 * 2), buttonHeight1);
	public Rectangle InfoBox = new Rectangle(20, 20, 40, 40);

	
	Color[] easyDifColor1 = {MoColors.greenYellow, MoColors.greenYellow};
	Color[] easyDifColor2 = {Color.gray, MoColors.limeGreen};
	Color[] easyDifBorder = {Color.gray, MoColors.chartreuse};
	Color[] easyDifFontColor = {Color.white, MoColors.green};
	
	Color[] medDifColor1 = {MoColors.goldenrod, MoColors.goldenrod};
	Color[] medDifColor2 = {Color.gray, MoColors.gold};
	Color[] medDifBorder = {Color.gray, MoColors.yellow};
	Color[] medDifFontColor = {Color.white, MoColors.yellow};
	
	Color[] hardDifColor1 = {MoColors.coral, MoColors.coral};
	Color[] hardDifColor2 = {Color.gray, MoColors.orangeRed};
	Color[] hardDifBorder = {Color.gray, MoColors.red};
	Color[] hardDifFontColor = {Color.white, MoColors.fireBrick};
	
	Color[] insaneDifColor1 = {MoColors.mediumVioletRed, MoColors.mediumVioletRed};
	Color[] insaneDifColor2 = {Color.gray, MoColors.purple};
	Color[] insaneDifBorder = {Color.gray, MoColors.darkViolet};
	Color[] insaneDifFontColor = {Color.white, MoColors.magenta};
	
	Image background = new ImageIcon("backgrounds/quickMathsLevelSelectBackground1.jpg").getImage().getScaledInstance(gui.WIDTH * gui.SCALE, gui.HEIGHT * gui.SCALE, Image.SCALE_DEFAULT);

	public void render(Graphics g){
		
		g.drawImage(background, 0, 0, null);
		
		Graphics2D g2d = (Graphics2D)g;

		HomePage.draw(g2d);

		g.setColor(Color.white);
		g.setFont(fnt1);
		g.drawString("Number of Questions", numQuestionsInput.getX(), numQuestionsInput.getY() - numQuestionsInput.getHeight()/2);
		numQuestionsInput.render(g, !endlessQuestions);

		if(endlessQuestions) Endless.setColorState(1);
		else Endless.setColorState(0);
		Endless.draw(g2d);

		if(numQuestionsInput.retrieveNum()>10000)
			GenerateSet.setText("X_X");
		else if(numQuestionsInput.retrieveNum()>1000)
			GenerateSet.setText("Um...");
		else if(numQuestionsInput.retrieveNum()>100) {
			GenerateSet.setText("Really?");
		}
		else if (numQuestionsInput.retrieveNum() >= 0 && numQuestionsInput.retrieveNum() <= 100)
			GenerateSet.setText("Begin!");
		else
			GenerateSet.setText("Stahhp");

		if (isSetReady())
			GenerateSet.setColorState(1);
		else GenerateSet.setColorState(0);
		
		GenerateSet.draw(g2d);

		g.setColor(Color.white);
		g2d.draw(InfoBox);
		g.setFont(fnt3);
		stringGraphics.drawStringCentered("i", InfoBox, g);

		if(renderHelp) renderHelp(g);

	}

	abstract void renderHelp(Graphics g);
	abstract boolean isSetReady();
	abstract public void setDifficulty(int diff);
	public int getQuestionDifficulty() {return difficulty;}

}


