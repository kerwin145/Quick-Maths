import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import k_Methods.MoColors;
import k_Methods.RectanglePlus;
import k_Methods.RectanglePlus.gradientFormat;
import k_Methods.stringGraphics;


public class LevelSelectSpecial extends QuestionSelectPage{
	
	LevelSelectSpecial(GUI gui){
		super(gui);
	}
	
	int x1 = 120, y1 = 150;
	int buttonWidth1 = 60, buttonHeight1 = 60;
	int spacing1 = (gui.WIDTH * gui.SCALE - buttonWidth1*8 - x1)/8;
	int spacing2 = 80;
	int y2 = (int)(y1 + buttonHeight1 * 2.5); 
	int y3 = (int)(y2 + buttonHeight1 * 2.5); 
	
	public boolean renderHelp = false;

	//operation game mode colors
	Color[] OperationsButtonColors1 = {MoColors.dodgerBlue, MoColors.dodgerBlue};
	Color[] OperationsButtonColors2 = {MoColors.darkTurquoise, MoColors.mediumBlue};
	Color[] OperationsBorderColors = {Color.gray, MoColors.chartreuse};
	Color[] OperationsFontColors = {Color.white};	
	//multiplciation game modes
	public RectanglePlus multBy2 = new RectanglePlus(x1, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "x2");
	public RectanglePlus multBy3 = new RectanglePlus(x1 + buttonWidth1 + spacing1, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "x3");
	public RectanglePlus multBy4 = new RectanglePlus(x1 + buttonWidth1*2 + spacing1*2, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "x4");
	public RectanglePlus multBy5 = new RectanglePlus(x1 + buttonWidth1*3 + spacing1*3, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "x5");
	
	//division game modes
	public RectanglePlus divBy2 = new RectanglePlus(x1 + buttonWidth1*4 + spacing1*4, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "÷2");
	public RectanglePlus divBy3 = new RectanglePlus(x1 + buttonWidth1*5 + spacing1*5, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "÷3");
	public RectanglePlus divBy4 = new RectanglePlus(x1 + buttonWidth1*6 + spacing1*6, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "÷4");
	public RectanglePlus divBy5 = new RectanglePlus(x1 + buttonWidth1*7 + spacing1*7, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "÷5");
	
	int buttonWidth2 = 100, buttonHeight = 50;

	//super special game modes	
	//String[] IsItDivisibleText = {"Is it", "Divisible?"};
	
	public RectanglePlus isItDivisible = new RectanglePlus(x1, y2, buttonWidth2, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt2, OperationsFontColors, "Is it Divisible");
	public RectanglePlus squaringNumberThatEndIn5 = new RectanglePlus(x1 + buttonWidth1 + spacing2, y2, buttonWidth2, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "");
	public RectanglePlus multBy11 = new RectanglePlus(x1 + buttonWidth1*2 + spacing2*2, y2, buttonWidth2, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "");

	public RectanglePlus easyDif = new RectanglePlus
			(120, y3, buttonWidth2, buttonHeight1, easyDifColor1, easyDifColor2, true, gradientFormat.horizontal, easyDifBorder,
			fnt2,easyDifFontColor, "Easy");
	public RectanglePlus medDif = new RectanglePlus
			(120 + buttonWidth2 + spacing1, y3, buttonWidth2, buttonHeight1, medDifColor1, medDifColor2, true, gradientFormat.horizontal, medDifBorder,
			fnt2, medDifFontColor, "Medium");
	public RectanglePlus hardDif = new RectanglePlus
			(120 + buttonWidth2*2 + spacing1*2, y3, buttonWidth2, buttonHeight1, hardDifColor1, hardDifColor2, true, gradientFormat.horizontal, hardDifBorder,
			fnt2, hardDifFontColor, "Hard");
	public RectanglePlus insaneDif = new RectanglePlus
			(120 + buttonWidth2*3 + spacing1*3, y3, buttonWidth2, buttonHeight1, insaneDifColor1, insaneDifColor2, true, gradientFormat.horizontal, insaneDifBorder,
			fnt2, insaneDifFontColor, "Insane");
	
	public Rectangle insaneTextBox = new Rectangle(insaneDif.x, (int)(insaneDif.y + insaneDif.height * 1.05), buttonWidth2, buttonHeight1);
	String[] insaneText = {"Don't tell me", "ur not gonna use", "ur calculator ;]"};
	
		
	public RectanglePlus[] specialQChosen = {multBy2, multBy3, multBy4, multBy5, 
										 divBy2, divBy3, divBy4, divBy5,
										 isItDivisible, squaringNumberThatEndIn5, multBy11};
	
	public RectanglePlus[] Difficulties = {easyDif, medDif, hardDif, insaneDif};

	public int specialQChosenIndex = -1; //this is the index of the rect list
	public int specialQTypeChosen = -1; //this will be for mult or div
	public int specialQuestionNum2;
		
	public void render(Graphics g){
		super.render(g);
		Graphics2D g2d = (Graphics2D)g;
		
		for (int i = 0; i < specialQChosen.length; i++) {
			if (i == specialQChosenIndex) {
				g.setColor(Color.orange);
				g2d.draw(specialQChosen[i]);
			}
			else {
				g.setColor(Color.gray);
				g2d.draw(specialQChosen[i]);
			}
		}
		
		g.setFont(fntHeader);
		g.setColor(Color.white);
		g.drawString("Multiplication: ", multBy2.x, (int)(multBy2.y - multBy2.height/2));
		g.drawString("Division: ", divBy2.x, (int)(divBy2.y - divBy2.height/2));
		g.drawString("Super Special Questions", isItDivisible.x, isItDivisible.y -isItDivisible.height/2);
		for (int i = 0; i < specialQChosen.length; i++)
			specialQChosen[i].draw(g2d);

		for (int i = 0; i < Difficulties.length; i++) 	
			Difficulties[i].draw(g2d);
			
		
		g.setColor(Color.white);
		g.setFont(fntHeader);
		g.drawString("Difficulty", easyDif.x, easyDif.y - easyDif.height/2);
		
		}

	void renderHelp(Graphics g) {
		
	}

	boolean isSetReady() {
		return (numQuestionsInput.retrieveNum() > 0 || endlessQuestions) && (specialQChosenIndex > -1) && (difficulty != -1);
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
		for(int i = 0; i < Difficulties.length; i++) {
			if (i == difficulty) {
				Difficulties[i].setColorState(1);
			}
			else
				Difficulties[i].setColorState(0);
		}
	}
	
	public void setQuestionType(int specialQChosenIndex){
		if(specialQChosenIndex >= 0 && specialQChosenIndex <= 3){
			specialQTypeChosen = 2;
			specialQuestionNum2 = specialQChosenIndex + 2; //instead of going for if statements
		}
		if(specialQChosenIndex >= 4 && specialQChosenIndex <= 7){
			specialQTypeChosen = 3;
			specialQuestionNum2 = specialQChosenIndex - 2; 
		}
		if(specialQChosenIndex >= 8 && specialQChosenIndex <= 10){
			
		}
		this.specialQChosenIndex = specialQChosenIndex;
		
		for(int i = 0; i < specialQChosen.length; i++) {
			if(i == specialQChosenIndex)
				specialQChosen[i].setColorState(1);
			else
				specialQChosen[i].setColorState(0);

		}
		
	}
		
	
}
