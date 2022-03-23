package src.main;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Color;
import k_Methods.MoColors;
import java.awt.Image;
import k_Methods.RectanglePlus;
import java.awt.Font;


public class TitlePage
{
    GUI gui;
    QuestionPageNumber qPage;
    QuestionPageYesNo qPageYN;
    Font fnt0;
    Font fnt1;
    Font fnt2;
    public RectanglePlus playButton;
    public RectanglePlus playSpecialButton;
    public RectanglePlus resumeButton;
    public RectanglePlus achievementsButton;
    public RectanglePlus quitButton;
    Image background;
    public boolean setFinished;
    public boolean setSpecialFinished;
    public PreviousPage questionPage;
    
    TitlePage(final GUI gui) {
        this.fnt0 = new Font("Linux Libertine Display G", 1, 50);
        this.fnt1 = new Font("Bodoni MT", 1, 25);
        this.fnt2 = new Font("Garamond", 1, 20);
        this.playButton = new RectanglePlus(100, 150, 150, 50, MoColors.royalBlue, MoColors.deepSkyBlue, true, RectanglePlus.gradientFormat.horizontal, MoColors.orange, this.fnt1, Color.white, "Start");
        this.playSpecialButton = new RectanglePlus(100, 230, 150, 50, MoColors.royalBlue, MoColors.deepSkyBlue, true, RectanglePlus.gradientFormat.horizontal, MoColors.magenta, this.fnt1, Color.white, "Special");
        this.resumeButton = new RectanglePlus(100, 310, 150, 50, MoColors.royalBlue, MoColors.deepSkyBlue, true, RectanglePlus.gradientFormat.horizontal, MoColors.blue, this.fnt1, Color.white, "Resume");
        this.achievementsButton = new RectanglePlus(100, 390, 150, 50, MoColors.royalBlue, MoColors.deepSkyBlue, MoColors.dodgerBlue, MoColors.blueViolet, RectanglePlus.gradientFormat.horizontal, MoColors.gold, this.fnt1, Color.white, "Medals");
        this.quitButton = new RectanglePlus(100, 470, 150, 50, MoColors.royalBlue, MoColors.deepSkyBlue, MoColors.dodgerBlue, MoColors.blueViolet, RectanglePlus.gradientFormat.horizontal, MoColors.red, this.fnt1, Color.white, "Quit");
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
        this.playButton.draw(g2d);
        this.playSpecialButton.draw(g2d);
        this.resumeButton.draw(g2d);
        this.achievementsButton.draw(g2d);
        this.quitButton.draw(g2d);
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
