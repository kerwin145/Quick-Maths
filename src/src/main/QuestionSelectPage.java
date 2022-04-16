package src.main;
import k_Methods.stringGraphics;
import k_Methods.Rectangle_.gradientFormat;
import k_Methods.Rectangle_.textPosition;

import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import k_Methods.MoColors;
import java.awt.Image;
import java.awt.Rectangle;
import k_Methods.RectanglePlus;
import k_Methods.Rectangle_;

import java.awt.Color;
import java.awt.Font;

public abstract class QuestionSelectPage
{
    GUI gui;
    int buttonWidth1;
    int buttonHeight1;
    public int difficulty;
    public boolean endlessQuestions;
    boolean renderHelp;
    Font fnt0;
    Font fnt1;
    Font fnt2;
    Font fnt3;
    Font fntBegin;
    Font fntHeader;
    Font fntHeader2;
    Font fntNormal;
    Font fntSmall;
    Font fntNormal2;
    Color[] GenerateSetColor1;
    Color[] GenerateSetColor2;
    Color[] GenerateSetBorderColor;
    Color[] GenerateSetTextColor;
    Color[] EndlessColor1;
    Color[] EndlessColor2;
    Color[] EndlessBorderColor;
    Color[] EndlessTextColor;
    public NumberTextField numQuestionsInput;
    public Rectangle_ Home;
    public Rectangle_ GenerateSet;
    public Rectangle_ Endless;
    public Rectangle InfoBox;

    Color[] easyDifBorder = new Color[] { Color.gray, MoColors.chartreuse };
    Color[] medDifBorder = new Color[] { Color.gray, MoColors.yellow };
    Color[] hardDifBorder = new Color[] { Color.gray, MoColors.red };
    Color[] insaneDifBorder = new Color[] { Color.gray, MoColors.darkViolet };


    
    Color[][] easyDifBackgroundColors = new Color[][] {new Color[] {MoColors.greenYellow, MoColors.darkSeaGreen}, 
    												   new Color[] {MoColors.greenYellow, MoColors.limeGreen}};
    Color[][] medDifBackgroundColors = new Color[][] {new Color[] {MoColors.goldenrod, MoColors.khaki}, 
												 	  new Color[] {MoColors.goldenrod, MoColors.gold}};
    Color[][] hardDifBackgroundColors = new Color[][] {new Color[] {MoColors.coral, MoColors.lightSalmon}, 
    												   new Color[] {MoColors.tomato, MoColors.coral, MoColors.orangeRed}};
    Color[][] insaneDifBackgroundColors = new Color[][] {new Color[] {MoColors.mediumVioletRed, MoColors.mediumOrchid},
    													 new Color[] {MoColors.mediumOrchid, MoColors.mediumVioletRed, MoColors.orchid}};

    													 
    Color[][] generateSetBackgroundColors =  new Color[][] {new Color[] {MoColors.skyBlue, MoColors.royalBlue}, new Color[] {MoColors.dodgerBlue, MoColors.royalBlue}};
    Color[][] endlessBackgroundColors =  new Color[][] {new Color[] {MoColors.lightSlateGray, MoColors.lightBlue}, new Color[] {MoColors.turquoise, MoColors.darkTurquoise}};
    Image background;
    
    public QuestionSelectPage(GUI gui) {
        buttonWidth1 = 60;
        buttonHeight1 = 60;
        difficulty = -1;
        endlessQuestions = false;
        fnt0 = new Font("Ebrima", Font.PLAIN, (int)(buttonHeight1 / 1.6));
        fnt1 = new Font("Lucida Bright", 1, 15);
        fnt2 = new Font("Microsoft Uighur", Font.PLAIN, 32);
        fnt3 = new Font("Georgia", 0, 17);
        fntBegin = new Font("Georgia", 0, 22);
        fntHeader = new Font("Lucida Bright", 1, buttonHeight1 / 2);
        fntHeader2 = new Font("Garamond", 1, (int)(buttonHeight1 / 2.3)); 
        fntNormal = new Font("Garamond", 0, buttonHeight1 / 4);
        fntSmall = new Font("Times", 0, buttonHeight1 / 6);
        fntNormal2 = new Font("Garamond", 0, (int)(buttonHeight1 / 3.5));
        
              
        numQuestionsInput = new NumberTextField((int)(gui.WIDTH * gui.SCALE -260), (int)(gui.HEIGHT * gui.SCALE - 120), 220, 35, "0", false);
        numQuestionsInput.setAlwaysFocused(true);
        
        Home = new Rectangle_((int)(gui.WIDTH * gui.SCALE - 140), 15, 120, 20);
        Home.addBackgroundColor(new Color[]{MoColors.royalBlue, MoColors.deepSkyBlue});
        Home.setGradientFormat(gradientFormat.vertical);
        Home.setBorderColor(MoColors.navy);
        Home.setFont(fnt1);
        Home.setFontColor(Color.white);
        Home.setText("Back");
        Home.setHasDarkenedColors(true);
		
        GenerateSet = new Rectangle_((int)(numQuestionsInput.getX() + numQuestionsInput.getWidth() * 2.0 / 5.0), (int)(numQuestionsInput.getY() + numQuestionsInput.getHeight() * 1.25), (int)(numQuestionsInput.getWidth() * 3.0/5), numQuestionsInput.getHeight());
        GenerateSet.setGradientFormat(gradientFormat.vertical);
        GenerateSet.setBackgroundColors(generateSetBackgroundColors);
        GenerateSet.setBorderColor(MoColors.skyBlue);
        GenerateSet.setFont(fntBegin);
        GenerateSet.setText("---");
        GenerateSet.setHasDarkenedColors(true);

  
        Endless = new Rectangle_(numQuestionsInput.getX(), (int)(numQuestionsInput.getY() + numQuestionsInput.getHeight() * 1.25), (int)(numQuestionsInput.getWidth() * 2.0 / 5.0 - 15), numQuestionsInput.getHeight());
        Endless.setGradientFormat(gradientFormat.vertical);
        Endless.setBackgroundColors(endlessBackgroundColors);
        Endless.setFont(fnt3);
        Endless.setText("Endless");
        Endless.setHasDarkenedColors(true);

        
        //Endless = new RectanglePlus(numQuestionsInput.getX(), (int)(numQuestionsInput.getY() + numQuestionsInput.getHeight() * 1.25), (int)(numQuestionsInput.getWidth() * 2.0 / 5.0 - 15), numQuestionsInput.getHeight(), 
        //EndlessColor1, EndlessColor2, true, RectanglePlus.gradientFormat.horizontal, EndlessBorderColor, fnt1, EndlessTextColor, "Endless");
        InfoBox = new Rectangle(20, 20, 40, 40);

        background = new ImageIcon("images/quickMathsLevelSelectBackground1.jpg").getImage().getScaledInstance(600 * 2, 333 * 2, 1);
        this.gui = gui;
    }
    
    public void render(final Graphics g) {
        g.drawImage(background, 0, 0, null);
        final Graphics2D g2d = (Graphics2D)g;
        Home.draw(g2d);
        g.setColor(Color.white);
        g.setFont(fnt1);
        g.drawString("Number of Questions", numQuestionsInput.getX(), numQuestionsInput.getY() - numQuestionsInput.getHeight() / 2);
        numQuestionsInput.render(g, !endlessQuestions);
        if (endlessQuestions) {
        	Endless.setFontColor(MoColors.white);
        	Endless.setBorderColor(MoColors.aquamarine);
        	Endless.setBorderThickness(2);
            Endless.setColors(1);
        }
        else {
        	Endless.setFontColor(MoColors.gainsboro);
        	Endless.setBorderColor(MoColors.slateGray);
        	Endless.setBorderThickness(1);
            Endless.setColors(0);
        }
        Endless.draw(g2d);
        
        if (numQuestionsInput.retrieveNum() > 10000) {
            GenerateSet.setText("X_X");
        }
        else if (numQuestionsInput.retrieveNum() > 1000) {
            GenerateSet.setText("Um...");
        }
        else if (numQuestionsInput.retrieveNum() > 100) {
            GenerateSet.setText("Really?");
        }
        else if (numQuestionsInput.retrieveNum() >= 0 && numQuestionsInput.retrieveNum() <= 100 && isSetReady()) {
            GenerateSet.setText("Begin!");
        }
        else {
            GenerateSet.setText("___");
        }
        if (isSetReady()) {
            GenerateSet.setColors(1);
            GenerateSet.setFontColor(Color.white);
            GenerateSet.setBorderThickness(2);
        }
        else {
            GenerateSet.setColors(0);
            GenerateSet.setFontColor(MoColors.silver);
            GenerateSet.setBorderThickness(1);
        }
        GenerateSet.draw(g2d);
        g.setColor(Color.white);
        g2d.draw(InfoBox);
        g.setFont(fnt3);
        stringGraphics.drawStringCentered("i", InfoBox, g);
        if (renderHelp) {
            renderHelp(g);
        }
    }
    
    abstract void renderHelp(final Graphics p0);
    
    abstract boolean isSetReady();
    
    public abstract void setDifficulty(final int p0);
    
    public int getQuestionDifficulty() {
        return this.difficulty;
    }
}
