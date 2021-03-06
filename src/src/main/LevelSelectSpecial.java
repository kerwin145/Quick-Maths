package src.main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import k_Methods.MoColors;
import k_Methods.RectanglePlus;
import k_Methods.RectanglePlus.gradientFormat;
import k_Methods.Rectangle_;
import k_Methods.stringGraphics;


public class LevelSelectSpecial extends QuestionSelectPage{
	int x1 = 120, y1 = 150;
	int buttonWidth1 = 60, buttonHeight1 = 60;
	int buttonWidth2 = 100, buttonHeight = 50;

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
	
	public Rectangle_ multBy2 = new Rectangle_(x1 , y1, buttonWidth1, buttonHeight1, "x2", null, fnt0, Color.white, new Color[]{Color.gray, MoColors.chartreuse}, new Color[][] {{MoColors.dodgerBlue, MoColors.darkTurquoise},  {MoColors.dodgerBlue, MoColors.mediumBlue}}, Rectangle_.gradientFormat.vertical, 1, 2, true);
	public Rectangle_ multBy3 = new Rectangle_(x1 + buttonWidth1 + spacing1, y1, buttonWidth1, buttonHeight1, "x3", null, fnt0, Color.white, new Color[]{Color.gray, MoColors.chartreuse}, new Color[][] {{MoColors.dodgerBlue, MoColors.darkTurquoise},  {MoColors.dodgerBlue, MoColors.mediumBlue}}, Rectangle_.gradientFormat.vertical, 1, 2, true);
	public Rectangle_ multBy4 = new Rectangle_(x1 + buttonWidth1*2 + spacing1*2, y1, buttonWidth1, buttonHeight1, "x4", null, fnt0, Color.white, new Color[]{Color.gray, MoColors.chartreuse}, new Color[][] {{MoColors.dodgerBlue, MoColors.darkTurquoise},  {MoColors.dodgerBlue, MoColors.mediumBlue}}, Rectangle_.gradientFormat.vertical, 1, 2, true);
	public Rectangle_ multBy5 = new Rectangle_(x1 + buttonWidth1*3 + spacing1*3, y1, buttonWidth1, buttonHeight1, "x5", null, fnt0, Color.white, new Color[]{Color.gray, MoColors.chartreuse}, new Color[][] {{MoColors.dodgerBlue, MoColors.darkTurquoise},  {MoColors.dodgerBlue, MoColors.mediumBlue}}, Rectangle_.gradientFormat.vertical, 1, 2, true);
	
	public Rectangle_ divBy2 = new Rectangle_(x1 + buttonWidth1*4 + spacing1*4, y1, buttonWidth1, buttonHeight1, "?2", null, fnt0, Color.white, new Color[]{Color.gray, MoColors.chartreuse}, new Color[][] {{MoColors.dodgerBlue, MoColors.darkTurquoise},  {MoColors.dodgerBlue, MoColors.mediumBlue}}, Rectangle_.gradientFormat.vertical, 1, 2, true);
	public Rectangle_ divBy3 = new Rectangle_(x1 + buttonWidth1*5 + spacing1*5, y1, buttonWidth1, buttonHeight1, "?3", null, fnt0, Color.white, new Color[]{Color.gray, MoColors.chartreuse}, new Color[][] {{MoColors.dodgerBlue, MoColors.darkTurquoise},  {MoColors.dodgerBlue, MoColors.mediumBlue}}, Rectangle_.gradientFormat.vertical, 1, 2, true);
	public Rectangle_ divBy4 = new Rectangle_(x1 + buttonWidth1*6 + spacing1*6, y1, buttonWidth1, buttonHeight1, "?4", null, fnt0, Color.white, new Color[]{Color.gray, MoColors.chartreuse}, new Color[][] {{MoColors.dodgerBlue, MoColors.darkTurquoise},  {MoColors.dodgerBlue, MoColors.mediumBlue}}, Rectangle_.gradientFormat.vertical, 1, 2, true);
	public Rectangle_ divBy5 = new Rectangle_(x1 + buttonWidth1*7 + spacing1*7, y1, buttonWidth1, buttonHeight1, "?5", null, fnt0, Color.white, new Color[]{Color.gray, MoColors.chartreuse}, new Color[][] {{MoColors.dodgerBlue, MoColors.darkTurquoise},  {MoColors.dodgerBlue, MoColors.mediumBlue}}, Rectangle_.gradientFormat.vertical, 1, 2, true);
	
	/*
	public Rectangle_ multBy2 = new RectanglePlus(x1, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "x2");
	public Rectangle_ multBy3 = new RectanglePlus(x1 + buttonWidth1 + spacing1, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "x3");
	public Rectangle_ multBy4 = new RectanglePlus(x1 + buttonWidth1*2 + spacing1*2, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "x4");
	public Rectangle_ multBy5 = new RectanglePlus(x1 + buttonWidth1*3 + spacing1*3, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "x5");
	
	//division game modes
	public Rectangle_ divBy2 = new RectanglePlus(x1 + buttonWidth1*4 + spacing1*4, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "?2");
	public Rectangle_ divBy3 = new RectanglePlus(x1 + buttonWidth1*5 + spacing1*5, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "?3");
	public Rectangle_ divBy4 = new RectanglePlus(x1 + buttonWidth1*6 + spacing1*6, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "?4");
	public Rectangle_ divBy5 = new RectanglePlus(x1 + buttonWidth1*7 + spacing1*7, y1, buttonWidth1, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "?5");
	*/

	public Rectangle_ isItDivisible = new Rectangle_(x1 , y2, buttonWidth2, buttonHeight1, "Is it divisible", null, fnt2, Color.white, new Color[]{Color.gray, MoColors.chartreuse}, new Color[][] {{MoColors.dodgerBlue, MoColors.darkTurquoise},  {MoColors.dodgerBlue, MoColors.mediumBlue}}, Rectangle_.gradientFormat.vertical, 1, 2, true);
	public Rectangle_ squaringNumberThatEndsIn5 = new Rectangle_(x1 + buttonWidth1 + spacing2, y2, buttonWidth2, buttonHeight1, "", null, fnt2, Color.white, new Color[]{Color.gray, MoColors.chartreuse}, new Color[][] {{MoColors.dodgerBlue, MoColors.darkTurquoise},  {MoColors.dodgerBlue, MoColors.mediumBlue}}, Rectangle_.gradientFormat.vertical, 1, 2, true);
	public Rectangle_ multBy11 = new Rectangle_(x1 + buttonWidth1*2 + spacing2*2, y2, buttonWidth2, buttonHeight1, "", null, fnt2, Color.white, new Color[]{Color.gray, MoColors.chartreuse}, new Color[][] {{MoColors.dodgerBlue, MoColors.darkTurquoise},  {MoColors.dodgerBlue, MoColors.mediumBlue}}, Rectangle_.gradientFormat.vertical, 1, 2, true);

	
	/*
	public Rectangle_ isItDivisible = new RectanglePlus(x1, y2, buttonWidth2, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt2, OperationsFontColors, "Is it Divisible");
	public Rectangle_ squaringNumberThatEndsIn5 = new RectanglePlus(x1 + buttonWidth1 + spacing2, y2, buttonWidth2, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "");
	public Rectangle_ multBy11 = new RectanglePlus(x1 + buttonWidth1*2 + spacing2*2, y2, buttonWidth2, buttonHeight1, OperationsButtonColors1, OperationsButtonColors2,	
			true, gradientFormat.horizontal, OperationsBorderColors, fnt0, OperationsFontColors, "");
	*/
	
	public Rectangle_ easyDif, medDif, hardDif, insaneDif;
	public Rectangle_ insaneTextBox;
	String[] insaneText = {"Don't tell me", "ur not gonna use", "ur calculator ;]"};
	
		
	public Rectangle_[] specialQChosen = {multBy2, multBy3, multBy4, multBy5, 
										 divBy2, divBy3, divBy4, divBy5,
										 isItDivisible, squaringNumberThatEndsIn5, multBy11};
	
	public Rectangle_[] Difficulties;  

	public int specialQChosenIndex = -1; //this is the index of the rect list
	public int specialQTypeChosen = -1; //this will be for mult or div
	public int specialQuestionNum2;
	
	LevelSelectSpecial(GUI gui){
		super(gui);
		easyDif = (Rectangle_) gui.getLevSelect().easyDif.clone();
		medDif = (Rectangle_) gui.getLevSelect().medDif.clone();
		hardDif = (Rectangle_) gui.getLevSelect().hardDif.clone();
		insaneDif = (Rectangle_) gui.getLevSelect().insaneDif.clone();
		easyDif.y = y3;
		medDif.y = y3;
		hardDif.y = y3;
		insaneDif.y = y3;
		Difficulties = new Rectangle_[]{easyDif, medDif, hardDif, insaneDif};
	}
	
		
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

		for (Rectangle_ i: Difficulties) 
			i.draw(g2d);
			
		
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
				Difficulties[i].setColors(1);
			}
			else
				Difficulties[i].setColors(0);
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
				specialQChosen[i].setColors(1);
			else
				specialQChosen[i].setColors(0);

		}
		
	}
		
	
}