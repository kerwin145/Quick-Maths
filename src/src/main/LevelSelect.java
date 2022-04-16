package src.main;
import k_Methods.stringGraphics;
import k_Methods.Rectangle_.gradientFormat;
import k_Methods.Rectangle_.textPosition;

import java.awt.Graphics2D;
import java.awt.Graphics;
import k_Methods.MoColors;
import java.awt.Rectangle;
import k_Methods.RectanglePlus;
import k_Methods.Rectangle_;
import java.awt.Color;


public class LevelSelect extends QuestionSelectPage
{
    public boolean addChosen = false;
    public boolean subChosen = false;
    public boolean multChosen = false;
    public boolean divChosen = false;
    int spacing1 = 80;
    int buttonWidth1 = 60;
    int buttonHeight1 = 60;
    Color[] OperationsButtonColors1a;
    Color[] OperationsButtonColors2a;
    Color[] OperationsButtonBorder_a;
    Color[] OperationsButtonColors1b;
    Color[] OperationsButtonColors2b;
    Color[] OperationsButtonBorder_b;
    Color[] OperationsFontColor;
    public Rectangle_ AddChoose = new Rectangle_(120, 120, buttonWidth1, buttonHeight1, "+", null, fnt0, Color.white, new Color[] { Color.gray, MoColors.chartreuse}, new Color[][] {new Color[] { MoColors.orange, MoColors.salmon}, new Color[] {MoColors.dodgerBlue, MoColors.aqua }}, gradientFormat.vertical, 1, 2, true),
            SubChoose = new Rectangle_(120 + buttonWidth1 + spacing1, 120, buttonWidth1, buttonHeight1, "-", null, fnt0, Color.white, new Color[] {Color.gray, MoColors.chartreuse, MoColors.chartreuse}, new Color[][] {new Color[] { MoColors.orange, MoColors.salmon}, new Color[] {MoColors.dodgerBlue, MoColors.aqua }, new Color[] {MoColors.aqua, MoColors.dodgerBlue}}, gradientFormat.vertical, 1, 2, true),
            MultChoose = new Rectangle_(120 + buttonWidth1 * 2 + spacing1 * 2, 120, buttonWidth1, buttonHeight1, "x", null, fnt0, Color.white, new Color[] { Color.gray, MoColors.chartreuse}, new Color[][] {new Color[] { MoColors.orange, MoColors.salmon}, new Color[] {MoColors.dodgerBlue, MoColors.aqua }}, gradientFormat.vertical, 1, 2, true),
            DivChoose = new Rectangle_(120 + buttonWidth1 * 3 + spacing1 * 3, 120, buttonWidth1, buttonHeight1, "/", null, fnt0, Color.white, new Color[] { Color.gray, MoColors.chartreuse}, new Color[][] {new Color[] { MoColors.orange, MoColors.salmon}, new Color[] {MoColors.dodgerBlue, MoColors.aqua }, new Color[] {MoColors.aqua, MoColors.dodgerBlue}}, gradientFormat.vertical, 1, 2, true);

    private Rectangle_[] qTypes = new Rectangle_[] { AddChoose, SubChoose, MultChoose, DivChoose };
    private boolean[] qChosen  = new boolean[] { addChosen, subChosen, multChosen, divChosen };;
    public boolean onlyPositive = false;
    public boolean perfectDivisors = false;
    public Rectangle onlyPositiveTextBox = new Rectangle(SubChoose.x - (int)(buttonWidth1 * 0.25), (int)(SubChoose.y + SubChoose.height * 1.05), (int)(buttonWidth1 * 1.5), buttonHeight1),
            perfectDivisorsTextBox = new Rectangle(DivChoose.x - (int)(buttonWidth1 * 0.25), (int)(DivChoose.y + DivChoose.height * 1.05), (int)(buttonWidth1 * 1.5), buttonHeight1+30);

    int buttonY2 = (int) (AddChoose.height * 2.5 + AddChoose.y);
    int buttonWidth2 = 100;
    int buttonHeight = 50;
    
    public Rectangle_ easyDif = new Rectangle_(120, buttonY2, buttonWidth2, buttonHeight1, "Easy", null, fnt2, Color.white, easyDifBorder, easyDifBackgroundColors, gradientFormat.vertical, 1, 2, true),
            medDif = new Rectangle_(120 + buttonWidth2 + spacing1, buttonY2, buttonWidth2, buttonHeight1, "Medium", null, fnt2, Color.white, medDifBorder, medDifBackgroundColors, gradientFormat.vertical, 1, 2, true),
            hardDif = new Rectangle_(120 + buttonWidth2 * 2 + spacing1 * 2, buttonY2, buttonWidth2, buttonHeight1, "Hard", null, fnt2, Color.white, hardDifBorder, hardDifBackgroundColors, gradientFormat.vertical, 1, 2, true),
            insaneDif = new Rectangle_(120 + buttonWidth2 * 3 + spacing1 * 3, buttonY2, buttonWidth2, buttonHeight1, "Insane", null, fnt2, Color.white, insaneDifBorder, insaneDifBackgroundColors, gradientFormat.vertical, 1, 2, true);

    public Rectangle insaneTextBox = new Rectangle(insaneDif.x, (int)(insaneDif.y + insaneDif.height * 1.05), buttonWidth2, buttonHeight1);
    public Rectangle_[] Difficulties = {easyDif, medDif, hardDif, insaneDif };

	int challengeHeaderY1 = (int) (buttonHeight1*2 + easyDif.y);
	int challengeHeaderY2 = (int) (buttonHeight1*2.4 + easyDif.y);
	int buttonY3 = challengeHeaderY2 + (int)(buttonHeight1 *.2) + fnt2.getSize();
    int vanishLvl = 0;
    int strikeOutLvl = 0;
    
    //Hmm i could make these challenges inherit a challenge rectangle that extends rectangle_ but o well
    Color[] vanishChallengeBorderColors = {MoColors.gray, MoColors.navajoWhite, MoColors.IndianRed, MoColors.mediumVioletRed};
    Color[][] vanishChallengeBackgroundColors = {{MoColors.gainsboro, MoColors.gray}, {MoColors.papayaWhip, MoColors.silver}, {MoColors.pink, MoColors.silver},{MoColors.thistle, MoColors.silver}};
    Rectangle_ vanishChallengeHeader = new Rectangle_(120 + buttonWidth1/2, challengeHeaderY2, 150, fntHeader2.getSize());;
    Rectangle_  vanishChallenge = new Rectangle_(00, buttonY3, buttonWidth1, buttonHeight1, new String[] {"I", "I", "II", "III"}, null, fnt2, MoColors.slateGray, vanishChallengeBorderColors, vanishChallengeBackgroundColors, gradientFormat.vertical, 1, 2, true);
    Rectangle vanishTextBox; 

    Color[] strikeOutChallengeChallengeBorderColors = {MoColors.gray, MoColors.navajoWhite, MoColors.IndianRed, MoColors.mediumVioletRed};
    Color[][] strikeOutChallengeBorderColors = {{MoColors.gainsboro, MoColors.gray}, {MoColors.papayaWhip, MoColors.silver}, {MoColors.pink, MoColors.silver},{MoColors.thistle, MoColors.silver}};
    Rectangle_ strikeOutChallengeHeader = new Rectangle_(vanishChallengeHeader.x + vanishChallengeHeader.width, challengeHeaderY2, 150, fntHeader2.getSize());;
    Rectangle_  strikeOutChallenge = new Rectangle_(00, buttonY3, buttonWidth1, buttonHeight1, new String[] {"I", "I", "II", "III"}, null, fnt2, MoColors.slateGray, vanishChallengeBorderColors, vanishChallengeBackgroundColors, gradientFormat.vertical, 1, 2, true);
    Rectangle strikeOutTextBox; 
    
    LevelSelect(GUI gui) {
        super(gui);
    
        vanishChallengeHeader.setFont(fntHeader2);
        vanishChallengeHeader.setText("Vanish");
        vanishChallengeHeader.setWidthMatchText();
        vanishChallengeHeader.setTextPosition(textPosition.left);
        vanishChallengeHeader.setFontColor(Color.white);
        vanishChallenge.setXCenteredTo(vanishChallengeHeader);

        strikeOutChallengeHeader.setFont(fntHeader2);
        strikeOutChallengeHeader.setText("Strike Out");
        strikeOutChallengeHeader.setWidthMatchText();
        strikeOutChallengeHeader.setTextPosition(textPosition.left);
        strikeOutChallengeHeader.setFontColor(Color.white);
        strikeOutChallenge.setXCenteredTo(strikeOutChallengeHeader);
        
        strikeOutTextBox = new Rectangle(strikeOutChallenge.x, (int) (strikeOutChallenge.y + strikeOutChallenge.height * 1.2), buttonWidth1, buttonHeight1);    
        vanishTextBox = new Rectangle(vanishChallenge.x - 20, (int) (vanishChallenge.y + vanishChallenge.height * 1.2), buttonWidth1 + 40, buttonHeight1);    

    }
    
    public void render(Graphics g) {
        super.render(g);
        final Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.gray);
        g.setFont(fntHeader);
        g.setColor(Color.white);
        g.drawString("Operation", AddChoose.x, AddChoose.y - AddChoose.height / 2);
        for (int i = 0; i < qTypes.length; ++i) {
            qTypes[i].draw(g2d);
        }
        g.setFont(fntNormal);
        g.setColor(Color.white);
        if (qChosen[1]) {
            if (onlyPositive) {
                stringGraphics.drawStringFlow("Answers will only be > or = to 0.", onlyPositiveTextBox, textPosition.top, g2d);
            }
            else {
                stringGraphics.drawStringFlow("Answers will be integers.", onlyPositiveTextBox, textPosition.top, g2d);
            }
        }
        if (qChosen[3]) {
            if (perfectDivisors) {
                stringGraphics.drawStringFlow("Answers will have no remainder.", perfectDivisorsTextBox, textPosition.top, g2d);
            }
            else {
                stringGraphics.drawStringFlow("Answers will be rounded down to nearest whole number.", perfectDivisorsTextBox, textPosition.top, g2d);
            }
        }
        if (difficulty == 3) {
            stringGraphics.drawStringFlow("Don't tell me ur not gonna use ur calcultor :]", insaneTextBox, textPosition.top, g2d);
        }
        g.setColor(Color.white);
        g.setFont(fntHeader);
        g.drawString("Difficulty", easyDif.x, easyDif.y - easyDif.height / 2);
        for(Rectangle_ i: Difficulties)
        	i.draw(g2d);
        
        g.setColor(Color.white);
        g.setFont(fntHeader);
        g.drawString("Challenge Modes", easyDif.x, challengeHeaderY1);
        vanishChallengeHeader.draw(g2d);
        vanishChallenge.draw(g2d);
        strikeOutChallengeHeader.draw(g2d);
        strikeOutChallenge.draw(g2d);
        
        g.setFont(fntNormal);
    	g.setColor(Color.white);
        if(vanishLvl > 0) 
        	stringGraphics.drawStringFlow("You will have " + getVanishTime()/1000 + " seconds before the question disappears.", vanishTextBox, textPosition.top, g2d);
        if(strikeOutLvl > 0) 
        	stringGraphics.drawStringFlow("You will have " + (4-strikeOutLvl) + ((strikeOutLvl == 3) ? " life." : " lives. "), strikeOutTextBox, textPosition.top, g2d);
        
    }
    
    public void renderHelp(final Graphics g) {
        g.setFont(fntNormal);
        g.setColor(Color.white);
        g.drawString("Click to Select an operation", DivChoose.x + DivChoose.width + 5, AddChoose.y + fntNormal.getSize());
        g.drawString("You can also select more than one", DivChoose.x + DivChoose.width + 5, AddChoose.y + fntNormal.getSize() * 2);
        g.drawString("operation for your problem set. ", DivChoose.x + DivChoose.width + 5, AddChoose.y + fntNormal.getSize() * 3);
        g.drawString("Approximate grade level equivalencies", (int)(insaneDif.x + insaneDif.width * 1.5), insaneDif.y + fntNormal.getSize());
        g.drawString("Easy: Kindergarten - 5th Grade", (int)(insaneDif.x + insaneDif.width * 1.5), insaneDif.y + fntNormal.getSize() * 2);
        g.drawString("Medium: 3rd Grade - 8th Grade", (int)(insaneDif.x + insaneDif.width * 1.5), insaneDif.y + fntNormal.getSize() * 3);
        g.drawString("Hard: 6th Grade+", (int)(insaneDif.x + insaneDif.width * 1.5), insaneDif.y + fntNormal.getSize() * 4);
        g.drawString("Insane: ???", (int)(insaneDif.x + insaneDif.width * 1.5), insaneDif.y + fntNormal.getSize() * 5);
        g.drawString("Click to activate typing.", (int) (numQuestionsInput.getX() - numQuestionsInput.getWidth()/1.4), numQuestionsInput.getY() + fntNormal.getSize() * 2);
        g.drawString("Choose an operation, difficulty,", (int) (GenerateSet.x - GenerateSet.width * 2.3), GenerateSet.y + fntNormal.getSize());
        g.drawString("and problem set length to begin.", (int) (GenerateSet.x - GenerateSet.width * 2.3), GenerateSet.y + fntNormal.getSize() * 2);
        g.drawString("(Hot key: \"Enter\")", (int) (GenerateSet.x - GenerateSet.width * 2.3), GenerateSet.y + fntNormal.getSize() * 3);
    }
    
    public void tick() {
    }
    
    public boolean[] getQChosen() {
        return qChosen;
    }
    
    public void AddChosen() {
        AddChoose.nextColors();
        qChosen[0] = (AddChoose.getCurrentBorderColorIndex() != 0);
    }
    
    public void SubChosen() {
        SubChoose.nextColors();
        qChosen[1] = (SubChoose.getCurrentBorderColorIndex() != 0);
        if (SubChoose.getCurrentBorderColorIndex() == 2) {
            onlyPositive = true;
        }
        else {
            onlyPositive = false;
        }
    }
    
    public void MultChosen() {
        MultChoose.nextColors();
        qChosen[2] = (MultChoose.getCurrentBorderColorIndex() != 0);
    }
    
    public void DivChosen() {
        DivChoose.nextColors();
        qChosen[3] = (DivChoose.getCurrentBorderColorIndex() != 0);
        if (DivChoose.getCurrentBorderColorIndex() == 2) {
            perfectDivisors = true;
        }
        else {
            perfectDivisors = false;
        }
    }
    
    public boolean isSetReady() {
        return (qChosen[0] || qChosen[1] || qChosen[2] || qChosen[3]) && (numQuestionsInput.retrieveNum() > 0 || endlessQuestions) && difficulty != -1;
    }
    
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        for (int i = 0; i < this.Difficulties.length; ++i) {
            if (i == difficulty) {
                this.Difficulties[i].setColors(1);
            }
            else {
                this.Difficulties[i].setColors(0);
            }
        }
    }
    
    public void cycleVanishLevel() {    	
		vanishLvl++;
		
		if(vanishLvl > 3)
			vanishLvl = 0;
		vanishChallenge.setColors(vanishLvl);    	
	}
    
    public void cycleStrikeOutLevel() {
		strikeOutLvl++;
		
		if(strikeOutLvl > 3)
			strikeOutLvl = 0;
		strikeOutChallenge.setColors(strikeOutLvl);
    }
    
    public int getVanishLvl() {return vanishLvl;}
    public int getStrikeOutLvl() {return strikeOutLvl;}
    
    public int getVanishTime() {
    	if(vanishLvl == 1) 
    		return 13000;
    	else if (vanishLvl == 2)
    		return 8500;
    	else if (vanishLvl == 3)
    		return 5000;
    	else 
    		return -1;
    }
    public int getVanishDecayStartTime() {//marks the start of the decay
    	if(vanishLvl == 1) 
    		return 7000;
    	else if (vanishLvl == 2)
    		return 5500;
    	else if (vanishLvl == 3)
    		return 3500;
    	else 
    		return -1;
    }

    
    
	
}
