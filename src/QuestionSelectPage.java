import k_Methods.stringGraphics;
import k_Methods.Rectangle_.gradientFormat;

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
    public RectanglePlus HomePage;
    public Rectangle_ GenerateSet;
    public Rectangle_ Endless;
    public Rectangle InfoBox;
    Color[] easyDifColor1;
    Color[] easyDifColor2;
    Color[] easyDifBorder;
    Color[] easyDifFontColor;
    Color[] medDifColor1;
    Color[] medDifColor2;
    Color[] medDifBorder;
    Color[] medDifFontColor;
    Color[] hardDifColor1;
    Color[] hardDifColor2;
    Color[] hardDifBorder;
    Color[] hardDifFontColor;
    Color[] insaneDifColor1;
    Color[] insaneDifColor2;
    Color[] insaneDifBorder;
    Color[] insaneDifFontColor;
    Image background;
    
    public QuestionSelectPage(GUI gui) {
        buttonWidth1 = 60;
        buttonHeight1 = 60;
        difficulty = -1;
        endlessQuestions = false;
        fnt0 = new Font("Ebrima", Font.BOLD, (int)(buttonHeight1 / 1.6));
        fnt1 = new Font("Lucida Bright", 1, 15);
        fnt2 = new Font("Microsoft Uighur", Font.BOLD, 32);
        fnt3 = new Font("Georgia", 0, 17);
        fntBegin = new Font("Georgia", 0, 22);
        fntHeader = new Font("Lucida Bright", 1, buttonHeight1 / 2);
        fntNormal = new Font("Garamond", 0, buttonHeight1 / 4);
        fntSmall = new Font("Times", 0, buttonHeight1 / 6);
        fntNormal2 = new Font("Garamond", 0, (int)(buttonHeight1 / 3.5));
        GenerateSetColor1 = new Color[] { MoColors.skyBlue, MoColors.dodgerBlue };
        GenerateSetColor2 = new Color[] { MoColors.royalBlue, MoColors.royalBlue };
        GenerateSetBorderColor = new Color[] { Color.gray, Color.cyan };
        GenerateSetTextColor = new Color[] { MoColors.silver, Color.white};
        EndlessColor1 = new Color[] { MoColors.lightSlateGray, MoColors.turquoise };
        EndlessColor2 = new Color[] { MoColors.lightBlue, MoColors.darkTurquoise };
        EndlessBorderColor = new Color[] { MoColors.slateGray, MoColors.aquamarine };
        EndlessTextColor = new Color[] { Color.white, Color.white };
        numQuestionsInput = new NumberTextField((int)(gui.WIDTH * gui.SCALE -260), (int)(gui.HEIGHT * gui.SCALE - 120), 220, 35, "0", false);
        HomePage = new RectanglePlus((int)(gui.WIDTH * gui.SCALE - 140), 15, 120, 20, MoColors.royalBlue, MoColors.deepSkyBlue, true, RectanglePlus.gradientFormat.horizontal, MoColors.navy, fnt1, Color.white, "Home");
       

        GenerateSet = new Rectangle_((int)(numQuestionsInput.getX() + numQuestionsInput.getWidth() * 2.0 / 5.0), (int)(numQuestionsInput.getY() + numQuestionsInput.getHeight() * 1.25), (int)(numQuestionsInput.getWidth() * 3.0/5), numQuestionsInput.getHeight());
        GenerateSet.setGradientFormat(gradientFormat.vertical);
        GenerateSet.setBackgroundColors(GenerateSetColor1);
        GenerateSet.setBackgroundColors2(GenerateSetColor2);
        GenerateSet.setBorderColor(MoColors.skyBlue);
        GenerateSet.setFontColors(GenerateSetTextColor);
        GenerateSet.setFont(fntBegin);
        GenerateSet.setText("---");
        
        //GenerateSet = new RectanglePlus((int)(numQuestionsInput.getX() + numQuestionsInput.getWidth() * 2.0 / 5.0), (int)(numQuestionsInput.getY() + numQuestionsInput.getHeight() * 1.25), (int)(numQuestionsInput.getWidth() * 3.0/5),
        	//	numQuestionsInput.getHeight(), GenerateSetColor1, GenerateSetColor2, true, RectanglePlus.gradientFormat.horizontal, GenerateSetBorderColor, fnt3, GenerateSetTextColor, "Begin!");
       
        Endless = new Rectangle_(numQuestionsInput.getX(), (int)(numQuestionsInput.getY() + numQuestionsInput.getHeight() * 1.25), (int)(numQuestionsInput.getWidth() * 2.0 / 5.0 - 15), numQuestionsInput.getHeight());
        Endless.setGradientFormat(gradientFormat.vertical);
        Endless.setBackgroundColors(EndlessColor1);
        Endless.setBackgroundColors2(EndlessColor2);
        Endless.setBorderColors(EndlessBorderColor);
        Endless.setFontColors(EndlessTextColor);
        Endless.setBorderThickness(2);
        Endless.setFont(fnt3);
        Endless.setText("Endless");
        
        //Endless = new RectanglePlus(numQuestionsInput.getX(), (int)(numQuestionsInput.getY() + numQuestionsInput.getHeight() * 1.25), (int)(numQuestionsInput.getWidth() * 2.0 / 5.0 - 15), numQuestionsInput.getHeight(), 
        //EndlessColor1, EndlessColor2, true, RectanglePlus.gradientFormat.horizontal, EndlessBorderColor, fnt1, EndlessTextColor, "Endless");
        InfoBox = new Rectangle(20, 20, 40, 40);
        easyDifColor1 = new Color[] { MoColors.greenYellow, MoColors.greenYellow };
        easyDifColor2 = new Color[] { MoColors.darkSeaGreen, MoColors.limeGreen };
        easyDifBorder = new Color[] { Color.gray, MoColors.chartreuse };
        easyDifFontColor = new Color[] { Color.white, MoColors.white };
        medDifColor1 = new Color[] { MoColors.goldenrod, MoColors.goldenrod };
        medDifColor2 = new Color[] { MoColors.khaki, MoColors.gold };
        medDifBorder = new Color[] { Color.gray, MoColors.yellow };
        medDifFontColor = new Color[] { Color.white, MoColors.white };
        hardDifColor1 = new Color[] { MoColors.coral, MoColors.coral };
        hardDifColor2 = new Color[] { MoColors.lightSalmon, MoColors.orangeRed };
        hardDifBorder = new Color[] { Color.gray, MoColors.red };
        hardDifFontColor = new Color[] { Color.white, MoColors.white };
        insaneDifColor1 = new Color[] { MoColors.mediumVioletRed, MoColors.mediumVioletRed };
        insaneDifColor2 = new Color[] { MoColors.violet, MoColors.purple };
        insaneDifBorder = new Color[] { Color.gray, MoColors.darkViolet };
        insaneDifFontColor = new Color[] { Color.white, MoColors.white };
        background = new ImageIcon("images/quickMathsLevelSelectBackground1.jpg").getImage().getScaledInstance(600 * 2, 333 * 2, 1);
        this.gui = gui;
    }
    
    public void render(final Graphics g) {
        g.drawImage(this.background, 0, 0, null);
        final Graphics2D g2d = (Graphics2D)g;
        this.HomePage.draw(g2d);
        g.setColor(Color.white);
        g.setFont(this.fnt1);
        g.drawString("Number of Questions", this.numQuestionsInput.getX(), this.numQuestionsInput.getY() - this.numQuestionsInput.getHeight() / 2);
        this.numQuestionsInput.render(g, !this.endlessQuestions);
        if (this.endlessQuestions) {
            this.Endless.setColors(1);
        }
        else {
            this.Endless.setColors(0);
        }
        this.Endless.draw(g2d);
        
        if (this.numQuestionsInput.retrieveNum() > 10000) {
            this.GenerateSet.setText("X_X");
        }
        else if (this.numQuestionsInput.retrieveNum() > 1000) {
            this.GenerateSet.setText("Um...");
        }
        else if (this.numQuestionsInput.retrieveNum() > 100) {
            this.GenerateSet.setText("Really?");
        }
        else if (this.numQuestionsInput.retrieveNum() >= 0 && this.numQuestionsInput.retrieveNum() <= 100 && isSetReady()) {
            this.GenerateSet.setText("Begin!");
        }
        else {
            this.GenerateSet.setText("___");
        }
        if (this.isSetReady()) {
            this.GenerateSet.setColors(1);
            GenerateSet.setBorderThickness(2);
        }
        else {
            this.GenerateSet.setColors(0);
            GenerateSet.setBorderThickness(1);
        }
        this.GenerateSet.draw(g2d);
        g.setColor(Color.white);
        g2d.draw(this.InfoBox);
        g.setFont(this.fnt3);
        stringGraphics.drawStringCentered("i", this.InfoBox, g);
        if (this.renderHelp) {
            this.renderHelp(g);
        }
    }
    
    abstract void renderHelp(final Graphics p0);
    
    abstract boolean isSetReady();
    
    public abstract void setDifficulty(final int p0);
    
    public int getQuestionDifficulty() {
        return this.difficulty;
    }
}
