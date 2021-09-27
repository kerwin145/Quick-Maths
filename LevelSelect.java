

import k_Methods.MoColors;
import k_Methods.RectanglePlus;
import k_Methods.stringGraphics;
import k_Methods.RectanglePlus.gradientFormat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class LevelSelect extends QuestionSelectPage {
	
	LevelSelect (GUI gui){
		super(gui);
	}
	
	public boolean addChosen = false, subChosen = false, multChosen = false, divChosen = false;
	
	int spacing1 = 80;
	int buttonWidth1 = 60, buttonHeight1 = 60;
	
	Color[] OperationsButtonColors1a = {MoColors.orange, MoColors.dodgerBlue};
	Color[] OperationsButtonColors2a = {MoColors.salmon, MoColors.aqua};
	Color[] OperationsButtonBorder_a = {Color.gray, MoColors.chartreuse};
	
	Color[] OperationsButtonColors1b = {MoColors.orange, MoColors.dodgerBlue, MoColors.darkTurquoise};
	Color[] OperationsButtonColors2b = {MoColors.salmon, MoColors.aqua, MoColors.aqua};
	Color[] OperationsButtonBorder_b = {Color.gray, MoColors.chartreuse};

	Color[] OperationsFontColor = {Color.white};
	
	public RectanglePlus AddChoose = new RectanglePlus
			(120, 150, buttonWidth1, buttonHeight1, OperationsButtonColors1a, OperationsButtonColors2a, true, gradientFormat.vertical, OperationsButtonBorder_a,
			fnt0, OperationsFontColor, "+");
	
	public RectanglePlus SubChoose = new RectanglePlus
			(120 + buttonWidth1 + spacing1, 150, buttonWidth1, buttonHeight1, OperationsButtonColors1b, OperationsButtonColors2b, true, gradientFormat.vertical, OperationsButtonBorder_b,
			fnt0, OperationsFontColor, "-");
			
	public RectanglePlus MultChoose = new RectanglePlus
			(120 + buttonWidth1*2 + spacing1*2, 150, buttonWidth1, buttonHeight1, OperationsButtonColors1a, OperationsButtonColors2a, true, gradientFormat.vertical, OperationsButtonBorder_a,
			fnt0, OperationsFontColor, "x");
	
	public RectanglePlus DivChoose = new RectanglePlus
			(120 + buttonWidth1*3 + spacing1*3, 150, buttonWidth1, buttonHeight1, OperationsButtonColors1b, OperationsButtonColors2b, true, gradientFormat.vertical, OperationsButtonBorder_b,
			fnt0, OperationsFontColor, "÷");
	
	private RectanglePlus[] qTypes = {AddChoose, SubChoose, MultChoose, DivChoose};
	private boolean[] qChosen = {addChosen, subChosen, multChosen, divChosen};
	
	public boolean onlyPositive = false, perfectDivisors = false;

	public Rectangle onlyPositiveTextBox = new Rectangle(SubChoose.x - (int)(buttonWidth1 * .25), (int)(SubChoose.y + SubChoose.height * 1.05), (int)(buttonWidth1 * 1.5), buttonHeight1);
	public Rectangle perfectDivisorsTextBox = new Rectangle(DivChoose.x - (int)(buttonWidth1 * .25), (int)(DivChoose.y + DivChoose.height * 1.05), (int)(buttonWidth1 * 1.5), buttonHeight1);
	
	int buttonY2 = (int)(AddChoose.height * 3 + AddChoose.y), buttonWidth2 = 100, buttonHeight = 50;

	public RectanglePlus easyDif = new RectanglePlus
			(120, buttonY2, buttonWidth2, buttonHeight1, easyDifColor1, easyDifColor2, true, gradientFormat.horizontal, easyDifBorder,
			fnt2,easyDifFontColor, "Easy");
	public RectanglePlus medDif = new RectanglePlus
			(120 + buttonWidth2 + spacing1, buttonY2, buttonWidth2, buttonHeight1, medDifColor1, medDifColor2, true, gradientFormat.horizontal, medDifBorder,
			fnt2, medDifFontColor, "Medium");
	public RectanglePlus hardDif = new RectanglePlus
			(120 + buttonWidth2*2 + spacing1*2, buttonY2, buttonWidth2, buttonHeight1, hardDifColor1, hardDifColor2, true, gradientFormat.horizontal, hardDifBorder,
			fnt2, hardDifFontColor, "Hard");
	public RectanglePlus insaneDif = new RectanglePlus
			(120 + buttonWidth2*3 + spacing1*3, buttonY2, buttonWidth2, buttonHeight1, insaneDifColor1, insaneDifColor2, true, gradientFormat.horizontal, insaneDifBorder,
			fnt2, insaneDifFontColor, "Insane");
	
	public Rectangle insaneTextBox = new Rectangle(insaneDif.x, (int)(insaneDif.y + insaneDif.height * 1.05), buttonWidth2, buttonHeight1);
	
	public RectanglePlus[] Difficulties = {easyDif, medDif, hardDif, insaneDif};
	
	public void render(Graphics g){
		super.render(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g.setColor(Color.gray);
		//g.fillRect(100, 130, buttonWidth1*4 + spacing1*4, 600);

		g.setFont(fntHeader);
		g.setColor(Color.white);
		g.drawString("Operation", AddChoose.x, AddChoose.y - AddChoose.height/2);
		
				
		for (int i = 0; i < qTypes.length; i++) 
			qTypes[i].draw(g2d);		
		
		
		g.setFont(fntNormal);
		g.setColor(Color.white);
		if(qChosen[1]) {
			if(onlyPositive)
				stringGraphics.drawStringFlow("Answers will only be > or = to 0.", onlyPositiveTextBox, g2d);
			else	
				stringGraphics.drawStringFlow("Answers will be integers.", onlyPositiveTextBox, g2d);
		}	
		
		if(qChosen[3]) {
			if(perfectDivisors)
				stringGraphics.drawStringFlow("Answers will have no remainder.", perfectDivisorsTextBox, g2d);
			else	
				stringGraphics.drawStringFlow("Answers will be rounded down to nearest whole number.", perfectDivisorsTextBox, g2d);
		}	
		
		if(difficulty == 3) {
			stringGraphics.drawStringFlow("Don't tell me ur not gonna use ur calcultor :]", insaneTextBox, g2d);
		}

		g.setColor(Color.white);
		g.setFont(fntHeader);
		g.drawString("Difficulty", easyDif.x, easyDif.y - easyDif.height/2);
		
		for (int i = 0; i < Difficulties.length; i++) 
				Difficulties[i].draw(g2d);

	}
	
	public void renderHelp(Graphics g) {
		g.setFont(fntNormal);
		g.setColor(Color.white);
		g.drawString("Click to Select an operation", (int)(DivChoose.x + DivChoose.width + 5), AddChoose.y + fntNormal.getSize());
		g.drawString("You can also select more than one", (int)(DivChoose.x + DivChoose.width + 5), AddChoose.y + fntNormal.getSize()*2); 
		g.drawString("operation for your problem set. ", (int)(DivChoose.x + DivChoose.width + 5), AddChoose.y + fntNormal.getSize()*3);
		
		//approximate difficulties: Easy K-5th Grade, Medium 3rd - 8th Grade, Hard ~6th Grade+
		g.drawString("Approximate grade level equivalencies", (int)(insaneDif.x + insaneDif.width*1.5), insaneDif.y + fntNormal.getSize());
		g.drawString("Easy: Kindergarten - 5th Grade", (int)(insaneDif.x + insaneDif.width*1.5), insaneDif.y + fntNormal.getSize()*2);
		g.drawString("Medium: 3rd Grade - 8th Grade", (int)(insaneDif.x + insaneDif.width*1.5), insaneDif.y + fntNormal.getSize()*3);
		g.drawString("Hard: 6th Grade+", (int)(insaneDif.x + insaneDif.width*1.5), insaneDif.y + fntNormal.getSize()*4);
		g.drawString("Insane: ???", (int)(insaneDif.x + insaneDif.width*1.5), insaneDif.y + fntNormal.getSize()*5);

		g.drawString("Number Text Field:", numQuestionsInput.getX() + numQuestionsInput.getWidth() + 5, numQuestionsInput.getY() + fntNormal.getSize());
		g.drawString("Click to activate typing.", numQuestionsInput.getX() + numQuestionsInput.getWidth() + 5, numQuestionsInput.getY() + fntNormal.getSize()*2);
		
		g.drawString("Choose an operation, difficulty,",(int)GenerateSet.x - GenerateSet.width*2, GenerateSet.y + fntNormal.getSize());
		g.drawString("and problem set length to begin.",(int)GenerateSet.x - GenerateSet.width*2, GenerateSet.y + fntNormal.getSize()*2);
		g.drawString("(Hot key: \"Enter\")",(int)GenerateSet.x - GenerateSet.width*2, GenerateSet.y + fntNormal.getSize()*3);

	}
			
	public void tick() {
		
	}

	public boolean[] getQChosen(){
		return qChosen;
	}

	public void AddChosen() {
		AddChoose.nextState();
		
		qChosen[0] = (AddChoose.getColorState() == 0) ? false : true;
	}

	public void SubChosen() {
		SubChoose.nextState();
		
		qChosen[1] = (SubChoose.getColorState() == 0) ? false : true;
		if(SubChoose.getColorState()==2) onlyPositive = true;
		else onlyPositive = false;
	}

	public void MultChosen() {
		MultChoose.nextState();

		qChosen[2] = (MultChoose.getColorState() == 0) ? false : true;
	}

	public void DivChosen() {
		DivChoose.nextState();
		
		qChosen[3] = (DivChoose.getColorState() == 0) ? false : true;
		
		if(DivChoose.getColorState()==2)perfectDivisors = true;
		else perfectDivisors = false;
	}
	
	public boolean isSetReady() {
		return (qChosen[0] || qChosen[1] || qChosen[2] || qChosen[3]) 
				&& (numQuestionsInput.retrieveNum() > 0 || endlessQuestions)
				&& (difficulty != -1);
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

}

