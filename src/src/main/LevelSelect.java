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
    public boolean addChosen;
    public boolean subChosen;
    public boolean multChosen;
    public boolean divChosen;
    int spacing1;
    int buttonWidth1;
    int buttonHeight1;
    Color[] OperationsButtonColors1a;
    Color[] OperationsButtonColors2a;
    Color[] OperationsButtonBorder_a;
    Color[] OperationsButtonColors1b;
    Color[] OperationsButtonColors2b;
    Color[] OperationsButtonBorder_b;
    Color[] OperationsFontColor;
    public Rectangle_ AddChoose, SubChoose, MultChoose, DivChoose;
    private Rectangle_[] qTypes;
    private boolean[] qChosen;
    public boolean onlyPositive;
    public boolean perfectDivisors;
    public Rectangle onlyPositiveTextBox;
    public Rectangle perfectDivisorsTextBox;
    int buttonY2;
    int buttonWidth2;
    int buttonHeight;
    public Rectangle_ easyDif, medDif, hardDif, insaneDif;

    public Rectangle insaneTextBox;
    public Rectangle_[] Difficulties;
    int buttonY3;
    int challengeLvl = 0;
    
    Rectangle_ challenge0;
    Rectangle_ challenge1;
    Rectangle_ challenge2;
    Rectangle_ challenge3;
    
    public Rectangle_[] challengeButtons;
    
    LevelSelect(final GUI gui) {
        super(gui);
        addChosen = false;
        subChosen = false;
        multChosen = false;
        divChosen = false;
        spacing1 = 80;
        buttonWidth1 = 60;
        buttonHeight1 = 60;
        
        /*
        OperationsButtonColors1a = new Color[] { MoColors.orange, MoColors.dodgerBlue };
        OperationsButtonColors2a = new Color[] { MoColors.salmon, MoColors.aqua };
        OperationsButtonBorder_a = new Color[] { Color.gray, MoColors.chartreuse };
        OperationsButtonColors1b = new Color[] { MoColors.orange, MoColors.dodgerBlue, MoColors.darkTurquoise };
        OperationsButtonColors2b = new Color[] { MoColors.salmon, MoColors.aqua, MoColors.aqua };
        OperationsButtonBorder_b = new Color[] { Color.gray, MoColors.chartreuse };
        OperationsFontColor = new Color[] { Color.white };
        */
        
        AddChoose = new Rectangle_(120, 120, buttonWidth1, buttonHeight1);
        AddChoose.setGradientFormat(gradientFormat.vertical);
        AddChoose.setBackgroundColors(new Color[][] {new Color[] { MoColors.orange, MoColors.salmon},
        											 new Color[] {MoColors.dodgerBlue, MoColors.aqua }});
        AddChoose.setBorderColors(new Color[] { Color.gray, MoColors.chartreuse });
        AddChoose.setBorderThickness(2);
        AddChoose.setFont(fnt0);
        AddChoose.setFontColor(Color.white);
        AddChoose.setText("+");
        
        SubChoose = new Rectangle_(120 + buttonWidth1 + spacing1, 120, buttonWidth1, buttonHeight1);
        SubChoose.setGradientFormat(gradientFormat.vertical);
        SubChoose.setBackgroundColors(new Color[][] {new Color[] { MoColors.orange, MoColors.salmon},
													 new Color[] {MoColors.dodgerBlue, MoColors.aqua }});
        SubChoose.setBorderColors(new Color[] { Color.gray, MoColors.chartreuse, MoColors.chartreuse });
        SubChoose.setBorderThickness(2);
        SubChoose.setFont(fnt0);
        SubChoose.setFontColor(Color.white);
        SubChoose.setText("-");
        
        MultChoose = new Rectangle_(120 + buttonWidth1 * 2 + spacing1 * 2, 120, buttonWidth1, buttonHeight1);
        MultChoose.setGradientFormat(gradientFormat.vertical);
        MultChoose.setBackgroundColors(new Color[][] {new Color[] { MoColors.orange, MoColors.salmon},
			 										 new Color[] {MoColors.dodgerBlue, MoColors.aqua }});
        MultChoose.setBorderColors(new Color[] { Color.gray, MoColors.chartreuse });
        MultChoose.setBorderThickness(2);
        MultChoose.setFont(fnt0);
        MultChoose.setFontColor(Color.white);
        MultChoose.setText("x");
        
        DivChoose = new Rectangle_(120 + buttonWidth1 * 3 + spacing1 * 3, 120, buttonWidth1, buttonHeight1);
        DivChoose.setGradientFormat(gradientFormat.vertical);
        DivChoose.setBackgroundColors(new Color[][] {new Color[] { MoColors.orange, MoColors.salmon},
			 									 	 new Color[] {MoColors.dodgerBlue, MoColors.aqua }});
        DivChoose.setBorderColors(new Color[] { Color.gray, MoColors.chartreuse, MoColors.chartreuse });
        DivChoose.setBorderThickness(2);
        DivChoose.setFont(fnt0);
        DivChoose.setFontColor(Color.white);
        DivChoose.setText("/");
        
        
       //AddChoose = new RectanglePlus(120, 150, buttonWidth1, buttonHeight1, OperationsButtonColors1a, OperationsButtonColors2a, true, RectanglePlus.gradientFormat.vertical, OperationsButtonBorder_a, fnt0, OperationsFontColor, "+");
       //SubChoose = new RectanglePlus(120 + buttonWidth1 + spacing1, 150, buttonWidth1, buttonHeight1, OperationsButtonColors1b, OperationsButtonColors2b, true, RectanglePlus.gradientFormat.vertical, OperationsButtonBorder_b, fnt0, OperationsFontColor, "-");
       // MultChoose = new RectanglePlus(120 + buttonWidth1 * 2 + spacing1 * 2, 150, buttonWidth1, buttonHeight1, OperationsButtonColors1a, OperationsButtonColors2a, true, RectanglePlus.gradientFormat.vertical, OperationsButtonBorder_a, fnt0, OperationsFontColor, "x");
       //DivChoose = new RectanglePlus(120 + buttonWidth1 * 3 + spacing1 * 3, 150, buttonWidth1, buttonHeight1, OperationsButtonColors1b, OperationsButtonColors2b, true, RectanglePlus.gradientFormat.vertical, OperationsButtonBorder_b, fnt0, OperationsFontColor, "\u00f7");
       //qTypes = new RectanglePlus[] { AddChoose, SubChoose, MultChoose, DivChoose };
        
        qTypes = new Rectangle_[] { AddChoose, SubChoose, MultChoose, DivChoose };    
        qChosen = new boolean[] { addChosen, subChosen, multChosen, divChosen };
        onlyPositive = false;
        perfectDivisors = false;
        onlyPositiveTextBox = new Rectangle(SubChoose.x - (int)(buttonWidth1 * 0.25), (int)(SubChoose.y + SubChoose.height * 1.05), (int)(buttonWidth1 * 1.5), buttonHeight1);
        perfectDivisorsTextBox = new Rectangle(DivChoose.x - (int)(buttonWidth1 * 0.25), (int)(DivChoose.y + DivChoose.height * 1.05), (int)(buttonWidth1 * 1.5), buttonHeight1+30);
        
        buttonY2 = (int) (AddChoose.height * 2.5 + AddChoose.y);
        buttonWidth2 = 100;
        buttonHeight = 50;
        
        
        easyDif = new Rectangle_(120, buttonY2, buttonWidth2, buttonHeight1);
        easyDif.setGradientFormat(gradientFormat.vertical);
        easyDif.setBackgroundColors(easyDifBackgroundColors);
        easyDif.setBorderColors(easyDifBorder);
        easyDif.setFontColor(Color.white);
        easyDif.setBorderThickness(3);
        easyDif.setFont(fnt2);
        easyDif.setText("Easy");
        
        medDif = new Rectangle_(120  + buttonWidth2 + spacing1, buttonY2, buttonWidth2, buttonHeight1);
        medDif.setGradientFormat(gradientFormat.vertical);
        medDif.setBackgroundColors(medDifBackgroundColors);
        medDif.setBorderColors(medDifBorder);
        medDif.setFontColor(Color.white);
        medDif.setBorderThickness(3);
        medDif.setFont(fnt2);
        medDif.setText("Medium");
        
        hardDif = new Rectangle_(120  + buttonWidth2 * 2 + spacing1 * 2, buttonY2, buttonWidth2, buttonHeight1);
        hardDif.setGradientFormat(gradientFormat.vertical);
        hardDif.setBackgroundColors(hardDifBackgroundColors);
        hardDif.setBorderColors(hardDifBorder);
        hardDif.setFontColor(Color.white);
        hardDif.setBorderThickness(3);
        hardDif.setFont(fnt2);
        hardDif.setText("Hard");
        
        insaneDif = new Rectangle_(120 + buttonWidth2 * 3 + spacing1 * 3, buttonY2, buttonWidth2, buttonHeight1);
        insaneDif.setGradientFormat(gradientFormat.vertical);
        insaneDif.setBackgroundColors(insaneDifBackgroundColors);
        insaneDif.setBorderColors(insaneDifBorder);
        insaneDif.setFontColor(Color.white);
        insaneDif.setBorderThickness(3);
        insaneDif.setFont(fnt2);
        insaneDif.setText("Insane");
        
         
        //easyDif = new RectanglePlus(120, buttonY2, buttonWidth2, buttonHeight1, easyDifColor1, easyDifColor2, true, RectanglePlus.gradientFormat.horizontal, easyDifBorder, fnt2, easyDifFontColor, "Easy");
        //medDif = new RectanglePlus(120 + buttonWidth2 + spacing1, buttonY2, buttonWidth2, buttonHeight1, medDifColor1, medDifColor2, true, RectanglePlus.gradientFormat.horizontal, medDifBorder, fnt2, medDifFontColor, "Medium");
        //hardDif = new RectanglePlus(120 + buttonWidth2 * 2 + spacing1 * 2, buttonY2, buttonWidth2, buttonHeight1, hardDifColor1, hardDifColor2, true, RectanglePlus.gradientFormat.horizontal, hardDifBorder, fnt2, hardDifFontColor, "Hard");
        //insaneDif = new RectanglePlus(120 + buttonWidth2 * 3 + spacing1 * 3, buttonY2, buttonWidth2, buttonHeight1, insaneDifColor1, insaneDifColor2, true, RectanglePlus.gradientFormat.horizontal, insaneDifBorder, fnt2, insaneDifFontColor, "Insane");
        insaneTextBox = new Rectangle(insaneDif.x, (int)(insaneDif.y + insaneDif.height * 1.05), buttonWidth2, buttonHeight1);
        Difficulties = new Rectangle_[] { easyDif, medDif, hardDif, insaneDif };
        
        
        buttonY3 = (int) (AddChoose.height*2.5 + easyDif.y);
        /*
        challenge0 = new Rectangle_(120, buttonY3, buttonWidth1, buttonHeight1);
        challenge0.setGradientFormat(gradientFormat.vertical);
        challenge0.setBackgroundColors(new Color[] {MoColors.lightGray, MoColors.honeydew});
        challenge0.setBackgroundColors2(new Color[] {MoColors.gainsboro, MoColors.silver});
        challenge0.setBorderColors(new Color[] {MoColors.gray, MoColors.springGreen});
        challenge0.setFontColor(MoColors.darkSlateGray);
        challenge0.setBorderThickness(2);
        challenge0.setFont(fnt2);
        challenge0.setText("OFF");
        */
        
        challenge1 = new Rectangle_(120, buttonY3, buttonWidth1, buttonHeight1);
        challenge1.setGradientFormat(gradientFormat.vertical);
        challenge1.setBackgroundColors(new Color[][]{new Color[] {MoColors.silver, MoColors.gainsboro}, new Color[] {MoColors.papayaWhip, MoColors.silver}}); 
        challenge1.setBorderColors(new Color[] {MoColors.gray, MoColors.navajoWhite});
        challenge1.setFontColor(MoColors.darkSlateGray);
        challenge1.setBorderThickness(2);
        challenge1.setFont(fnt2);
        challenge1.setText("I");

        
        challenge2 = new Rectangle_(120 + buttonWidth1 + spacing1, buttonY3, buttonWidth1, buttonHeight1);
        challenge2.setGradientFormat(gradientFormat.vertical);
        challenge2.setBackgroundColors(new Color[][]{new Color[] {MoColors.gainsboro, MoColors.gray}, new Color[] {MoColors.pink, MoColors.silver}}); 
        challenge2.setBorderColors(new Color[] {MoColors.gray, MoColors.IndianRed});
        challenge2.setFontColor(MoColors.darkSlateGray);
        challenge2.setBorderThickness(2);
        challenge2.setFont(fnt2);
        challenge2.setText("II");

        
        challenge3 = new Rectangle_(120 + buttonWidth1*2 + spacing1*2, buttonY3, buttonWidth1, buttonHeight1);
        challenge3.setGradientFormat(gradientFormat.vertical);
        challenge3.setBackgroundColors(new Color[][]{new Color[] {MoColors.gray, MoColors.darkGray}, new Color[] {MoColors.thistle, MoColors.silver}}); 
        challenge3.setBorderColors(new Color[] {MoColors.gray, MoColors.mediumVioletRed});
        challenge3.setFontColor(MoColors.darkSlateGray);
        challenge3.setBorderThickness(2);
        challenge3.setFont(fnt2);
        challenge3.setText("III");

        challengeButtons = new Rectangle_[]{challenge1, challenge2, challenge3};
    }
    
    @Override
    public void render(final Graphics g) {
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
        g.drawString("Vanishing Challenge", challenge1.x, challenge1.y - challenge1.height / 2);
        for(Rectangle_ i: challengeButtons)
        	i.draw(g2d);
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
        g.drawString("Number Text Field:", numQuestionsInput.getX() + numQuestionsInput.getWidth() + 5, numQuestionsInput.getY() + fntNormal.getSize());
        g.drawString("Click to activate typing.", numQuestionsInput.getX() + numQuestionsInput.getWidth() + 5, numQuestionsInput.getY() + fntNormal.getSize() * 2);
        g.drawString("Choose an operation, difficulty,", GenerateSet.x - GenerateSet.width * 2, GenerateSet.y + fntNormal.getSize());
        g.drawString("and problem set length to begin.", GenerateSet.x - GenerateSet.width * 2, GenerateSet.y + fntNormal.getSize() * 2);
        g.drawString("(Hot key: \"Enter\")", GenerateSet.x - GenerateSet.width * 2, GenerateSet.y + fntNormal.getSize() * 3);
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
    
    public void setChallengeLvl(int dif) {
    	//if current one is already selected, and you want to turn challenge off
    	if(dif == challengeLvl) {
    		challengeButtons[dif-1].setColors(0);
    		challengeLvl = 0;
    	}else {
    		challengeLvl = dif;
			for(int i = 0; i < challengeButtons.length; i++) {
				if(dif-1 == i)
					challengeButtons[i].setColors(1);
				else challengeButtons[i].setColors(0);
			}
    	}
	}
    
    public int getChallengeLvl() {return challengeLvl;}
    public int getChallengeLvlTime() {
    	if(challengeLvl == 1) 
    		return 13000;
    	else if (challengeLvl == 2)
    		return 8500;
    	else if (challengeLvl == 3)
    		return 5000;
    	else 
    		return -1;
    }
    public int getChallengeDecayStartTime() {//marks the start of the decay
    	if(challengeLvl == 1) 
    		return 7000;
    	else if (challengeLvl == 2)
    		return 5500;
    	else if (challengeLvl == 3)
    		return 3500;
    	else 
    		return -1;
    }

    
    
	
}
