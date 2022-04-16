package src.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import k_Methods.MoColors;
import k_Methods.Rectangle_;
import k_Methods.Rectangle_.gradientFormat;
import k_Methods.Rectangle_.textPosition;


public class TitlePage
{
    GUI gui;
    QuestionPageNumber qPage;
    QuestionPageYesNo qPageYN;
    Font fnt0;
    Font fnt1;
    Font fnt2;
    public Rectangle_ playButton;
    public Rectangle_ playSpecialButton;
    public Rectangle_ resumeButton;
    public Rectangle_ achievementsButton;
    public Rectangle_ quitButton;
    Image background;
    public boolean setFinished;
    public boolean setSpecialFinished;
    public PreviousPage questionPage;
    
    int buttonsy1 = 150, buttonSpacing = 80;
    Color[][] titleBackgroundColors = {{MoColors.royalBlue, MoColors.deepSkyBlue}};
    
    
    TitlePage(final GUI gui) {
        this.fnt0 = new Font("Linux Libertine Display G", 1, 50);
        this.fnt1 = new Font("Bodoni MT", 1, 25);
        this.fnt2 = new Font("Garamond", 1, 20);
        
        playButton = new Rectangle_(100, buttonsy1 ,150,50, "Start", textPosition.middle, fnt1, Color.white, new Color[] {MoColors.orange}, titleBackgroundColors, gradientFormat.vertical, 1, 2, true);
        playSpecialButton = new Rectangle_(100, buttonsy1 + buttonSpacing ,150,50, "Special", textPosition.middle, fnt1, Color.white, new Color[] {MoColors.magenta}, titleBackgroundColors, gradientFormat.vertical, 1, 2, true);
        resumeButton = new Rectangle_(100, buttonsy1 + buttonSpacing * 2,150,50, "Resume", textPosition.middle, fnt1, Color.white, new Color[] {MoColors.aqua}, titleBackgroundColors, gradientFormat.vertical, 1, 2, true);
        achievementsButton = new Rectangle_(100, buttonsy1 + buttonSpacing * 3,150,50, "Achievements", textPosition.middle, fnt1, Color.white, new Color[] {MoColors.gold}, titleBackgroundColors, gradientFormat.vertical, 1, 2, true);
        quitButton = new Rectangle_(100, buttonsy1 + buttonSpacing * 4,150,50, "Quit", textPosition.middle, fnt1, Color.white, new Color[] {MoColors.red}, titleBackgroundColors, gradientFormat.vertical, 1, 2, true);
              
        this.background = new ImageIcon("images/quickMathsLevelSelectBackground2.jpg").getImage().getScaledInstance(600 * 2, 333 * 2, 1);
        this.setFinished = true;
        this.setSpecialFinished = true;
        this.gui = gui;
    }
    
    public void render(final Graphics g) {
        final Graphics2D g2d = (Graphics2D)g;
        this.qPage = this.gui.getQuestionPage();
        this.qPageYN = this.gui.getQuestionPageYesNo();
        g.drawImage(this.background, 0, 0, null);
        g.setFont(this.fnt0);
        g.setColor(Color.white);
        g.drawString("Quick Maths", 100, 100);
        if (!this.setFinished) {
            this.playButton.setText("Restart");
        }
        else {
            this.playButton.setText("Play");
        }
        if (!this.setSpecialFinished) {
            this.playSpecialButton.setText("Restart");
        }
        else {
            this.playSpecialButton.setText("Special");
        }
        if (!this.setFinished || !this.setSpecialFinished) {
            this.resumeButton.setText("Resume!");
        }
        else {
            this.resumeButton.setText("___");
        }
        playButton.draw(g2d);
        playSpecialButton.draw(g2d);
        resumeButton.draw(g2d);
        achievementsButton.draw(g2d);
        quitButton.draw(g2d);
        g.setFont(this.fnt2);
        g.setColor(Color.gray);
        if (!this.setFinished || !this.setSpecialFinished) {
            if (questionPage == PreviousPage.Normal || questionPage == PreviousPage.Special) {
            	if(gui.getQuestionPage().endlessQuestions) {
                    g.drawString("Q " + this.qPage.currentQuestion + "/ --" , (int)(600 * 2 - this.qPage.HomePage.width * 2.2), (int)(this.qPage.HomePage.y + this.qPage.HomePage.height * 1.5));
            	}else
            		g.drawString("Q " + this.qPage.currentQuestion + "/" + this.qPage.numQuestions, (int)(600 * 2 - this.qPage.HomePage.width * 2.2), (int)(this.qPage.HomePage.y + this.qPage.HomePage.height * 1.5));
               
            	g.drawString("Correct: " + this.qPage.numCorrect + "/" + (this.qPage.currentQuestion - 1), (int)(600 * 2 - this.qPage.HomePage.width * 2.2), (int)(this.qPage.HomePage.y + this.qPage.HomePage.height * 2.75));
                g.drawString("Timer: " + this.qPage.timeMinutes + "' " + this.qPage.timeSeconds + "\"", (int)(600 * 2 - this.qPage.HomePage.width * 2.2), this.qPage.HomePage.y + this.qPage.HomePage.height * 4);
            }
            else if (questionPage == PreviousPage.SpecialYN) {
                g.drawString("Q " + this.qPageYN.currentQuestion + "/" + this.qPageYN.numQuestions, (int)(600 * 2 - this.qPage.HomePage.width * 2.2), (int)(this.qPage.HomePage.y + this.qPage.HomePage.height * 1.5));
                g.drawString("Correct: " + this.qPageYN.numCorrect + "/" + (this.qPageYN.currentQuestion - 1), (int)(600 * 2 - this.qPage.HomePage.width * 2.2), (int)(this.qPage.HomePage.y + this.qPage.HomePage.height * 2.75));
                g.drawString("Timer: " + this.qPageYN.timeMinutes + "' " + this.qPageYN.timeSeconds + "\"", (int)(600 * 2 - this.qPageYN.HomePage.width * 2.2), this.qPageYN.HomePage.y + this.qPageYN.HomePage.height * 4);
            }
        }
    }
    
    public void renderHelp(final Graphics g) {
    }
    
    public enum PreviousPage
    {
        Normal,
        Special,
        SpecialYN,
    }
}
