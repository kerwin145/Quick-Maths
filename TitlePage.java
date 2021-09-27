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

public class TitlePage {
	
	GUI gui;
	QuestionPageNumber qPage;
	QuestionPageYesNo qPageYN;
	TitlePage (GUI gui){
		this.gui = gui;
	}

	Font fnt0 = new Font("Linux Libertine Display G", Font.BOLD, 50);
	Font fnt1 = new Font("Bodoni MT", Font.BOLD, 25);
	Font fnt2 = new Font("Garamond", Font.BOLD, 20);
	
	public RectanglePlus playButton = new RectanglePlus
			(100, 150, 150, 50, MoColors.royalBlue, MoColors.deepSkyBlue, true, gradientFormat.horizontal, MoColors.orange,
			fnt1, Color.white, "Start");

	public RectanglePlus playSpecialButton = new RectanglePlus
			(100, 230, 150, 50, MoColors.royalBlue, MoColors.deepSkyBlue, true, gradientFormat.horizontal, MoColors.magenta,
			fnt1, Color.white, "Special");
	
	public RectanglePlus resumeButton = new RectanglePlus
			(100, 310, 150, 50, MoColors.royalBlue, MoColors.deepSkyBlue, true, gradientFormat.horizontal, MoColors.blue,
			fnt1, Color.white, "Resume");
	
	public RectanglePlus achievementsButton = new RectanglePlus
			(100, 390, 150, 50, MoColors.royalBlue, MoColors.deepSkyBlue, MoColors.dodgerBlue, MoColors.blueViolet, gradientFormat.horizontal, MoColors.gold,
			fnt1, Color.white, "Medals");
	
	public RectanglePlus quitButton = new RectanglePlus
			(100, 470, 150, 50, MoColors.royalBlue, MoColors.deepSkyBlue, MoColors.dodgerBlue, MoColors.blueViolet, gradientFormat.horizontal, MoColors.red,
			fnt1, Color.white, "Quit");

	Image background = new ImageIcon("backgrounds/quickMathsLevelSelectBackground2.jpg").getImage().getScaledInstance(gui.WIDTH * gui.SCALE, gui.HEIGHT * gui.SCALE, Image.SCALE_DEFAULT);

	public boolean setFinished = true, setSpecialFinished = true;
		
	public static enum PreviousPage{
		Normal,
		Special,
		SpecialYN;
	}
	public static PreviousPage questionPage;

	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		qPage = gui.getQuestionPage();
		qPageYN = gui.getQuestionPageYesNo();
		
		g.drawImage(background, 0, 0, null);
		
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Quick Maths", 100, 100);
		
		if(!setFinished) {
			playButton.setText("Restart");
		}
		else {
			playButton.setText("Play");
		}
		if(!setSpecialFinished) {
			playSpecialButton.setText("Restart");
		}
		else {
			playSpecialButton.setText("Special");
		}
		
		
		if(!(setFinished && setSpecialFinished)){
			//resumeButton.setTextColor(Color.green);
			resumeButton.setText("Resume!");
		}
		else {
			//resumeButton.setTextColor(Color.gray);
			resumeButton.setText("___");
		}
	
		
		playButton.draw(g2d);
		playSpecialButton.draw(g2d);
		resumeButton.draw(g2d);
		achievementsButton.draw(g2d);
		quitButton.draw(g2d);		

		g.setFont(fnt2);
		g.setColor(Color.gray);
		if(!(setFinished && setSpecialFinished)){
			if(questionPage == questionPage.Normal || questionPage == questionPage.Special){
				g.drawString("Q " + qPage.currentQuestion + "/" + qPage.numQuestions, (int)(gui.WIDTH * gui.SCALE - qPage.HomePage.width*2.2), (int)(qPage.HomePage.y + qPage.HomePage.height * 1.5));
				g.drawString("Correct: " + qPage.numCorrect + "/" + (qPage.currentQuestion - 1), (int)(gui.WIDTH * gui.SCALE - qPage.HomePage.width*2.2),  (int)(qPage.HomePage.y + qPage.HomePage.height * 2.75));
				g.drawString("Timer: " + qPage.timeMinutes + "' " +  qPage.timeSeconds + "\"", 
							(int)(gui.WIDTH * gui.SCALE - qPage.HomePage.width*2.2), 
							(int)(qPage.HomePage.y + qPage.HomePage.height * 4));
			}
			else if(questionPage == questionPage.SpecialYN){
				g.drawString("Q " + qPageYN.currentQuestion + "/" + qPageYN.numQuestions, (int)(gui.WIDTH * gui.SCALE - qPage.HomePage.width*2.2), (int)(qPage.HomePage.y + qPage.HomePage.height * 1.5));
				g.drawString("Correct: " + qPageYN.numCorrect + "/" + (qPageYN.currentQuestion - 1), (int)(gui.WIDTH * gui.SCALE - qPage.HomePage.width*2.2),  (int)(qPage.HomePage.y + qPage.HomePage.height * 2.75));
				g.drawString("Timer: " + qPageYN.timeMinutes + "' " +  qPageYN.timeSeconds + "\"", 
						(int)(gui.WIDTH * gui.SCALE - qPageYN.HomePage.width*2.2), 
						(int)(qPageYN.HomePage.y + qPageYN.HomePage.height * 4));
			}
		}
		
	}
	
	public void renderHelp(Graphics g) {
		
	}

}

