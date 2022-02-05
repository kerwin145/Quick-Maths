import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


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

	//multiplication game modes
	public Rectangle multBy2 = new Rectangle(x1, y1, buttonWidth1, buttonHeight1);
	public Rectangle multBy3 = new Rectangle(x1 + buttonWidth1 + spacing1, y1, buttonWidth1, buttonHeight1);
	public Rectangle multBy4 = new Rectangle(x1 + buttonWidth1*2 + spacing1*2, y1, buttonWidth1, buttonHeight1);
	public Rectangle multBy5 = new Rectangle(x1 + buttonWidth1*3 + spacing1*3, y1, buttonWidth1, buttonHeight1);
	//division game modes
	public Rectangle divBy2 = new Rectangle(x1 + buttonWidth1*4 + spacing1*4, y1, buttonWidth1, buttonHeight1);
	public Rectangle divBy3 = new Rectangle(x1 + buttonWidth1*5 + spacing1*5, y1, buttonWidth1, buttonHeight1);
	public Rectangle divBy4 = new Rectangle(x1 + buttonWidth1*6 + spacing1*6, y1, buttonWidth1, buttonHeight1);
	public Rectangle divBy5 = new Rectangle(x1 + buttonWidth1*7 + spacing1*7, y1, buttonWidth1, buttonHeight1);
	
	//super special game modes	
	public Rectangle isItDivisible = new Rectangle(x1, y2, (int)(buttonWidth1*1.5), buttonHeight1);
	public Rectangle squaringNumberThatEndIn5 = new Rectangle(x1 + buttonWidth1 + spacing2, y2, (int)(buttonWidth1*1.5), buttonHeight1);
	public Rectangle multBy11 = new Rectangle(x1 + buttonWidth1*2 + spacing2*2, y2, (int)(buttonWidth1 * 1.5), buttonHeight1);

	int buttonY2 = (int)(buttonHeight1* 3 + divBy5.y), buttonWidth2 = 90, buttonHeight = 50;
	public Rectangle easyDif = new Rectangle (120, y3, buttonWidth2, buttonHeight1);
	public Rectangle medDif = new Rectangle(120 + buttonWidth2 + spacing1, y3, buttonWidth2, buttonHeight1);
	public Rectangle hardDif = new Rectangle(120 + buttonWidth2*2 + spacing1*2, y3, buttonWidth2, buttonHeight1);
	public Rectangle insaneDif = new Rectangle(120 + buttonWidth2*3 + spacing1*3, y3, buttonWidth2, buttonHeight1);
		
	public Rectangle[] specialQChosen = {multBy2, multBy3, multBy4, multBy5, 
										 divBy2, divBy3, divBy4, divBy5,
										 isItDivisible, squaringNumberThatEndIn5, multBy11};
	public Rectangle[] Difficulties = {easyDif, medDif, hardDif, insaneDif};

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
		
		g.setFont(fnt0);
		g.setColor(Color.black);
		g.drawString("*2", multBy2.x + multBy2.width/2 - fnt0.getSize()/2, multBy2.y + multBy2.height/2 + fnt0.getSize()/2);
		g.drawString("*3", multBy3.x + multBy3.width/2 - fnt0.getSize()/2, multBy3.y + multBy3.height/2 + fnt0.getSize()/2);
		g.drawString("*4", multBy4.x + multBy4.width/2 - fnt0.getSize()/2, multBy4.y + multBy4.height/2 + fnt0.getSize()/2);
		g.drawString("*5", multBy5.x + multBy5.width/2 - fnt0.getSize()/2, multBy5.y + multBy5.height/2 + fnt0.getSize()/2);
		g.drawString("/2", divBy2.x + divBy2.width/2 - fnt0.getSize()/2, divBy2.y + divBy2.height/2 + fnt0.getSize()/2);
		g.drawString("/3", divBy3.x + divBy3.width/2 - fnt0.getSize()/2, divBy3.y + divBy3.height/2 + fnt0.getSize()/2);
		g.drawString("/4", divBy4.x + divBy4.width/2 - fnt0.getSize()/2, divBy4.y + divBy4.height/2 + fnt0.getSize()/2);
		g.drawString("/5", divBy5.x + divBy5.width/2 - fnt0.getSize()/2, divBy5.y + divBy5.height/2 + fnt0.getSize()/2);
		
		g.setFont(fntHeader);
		g.drawString("Multiplication: ", multBy2.x, (int)(multBy2.y - multBy2.height/2));
		g.drawString("Division: ", divBy2.x, (int)(divBy2.y - divBy2.height/2));
		
		g.setColor(Color.black);
		g.setFont(fntHeader);
		g.drawString("Super Special Questions", isItDivisible.x, isItDivisible.y -isItDivisible.height/2);
		g.setFont(fnt2);
		g.drawString("Is it", isItDivisible.x + 2, isItDivisible.y + isItDivisible.height/2 - fnt2.getSize()/3);
		g.drawString("divisible?", isItDivisible.x + 2, isItDivisible.y + isItDivisible.height/2 + fnt2.getSize() * 2/3);
	
		for (int i = 0; i < Difficulties.length; i++) {
			if (i == difficulty) {
				g.setColor(Color.blue);
				g2d.draw(Difficulties[i]);
			}
			else {
				g.setColor(Color.gray);
				g2d.draw(Difficulties[i]);
			}
		}
		
		g.setColor(Color.black);
		g.setFont(fntHeader);
		g.drawString("Difficulty", easyDif.x, easyDif.y - easyDif.height/2);
		g.setFont(fnt2);
		g.drawString("Easy", easyDif.x + easyDif.width/3 - fnt2.getSize(), easyDif.y + easyDif.height/2 + fnt2.getSize()/2);
		g.drawString("Medium", medDif.x + medDif.width/3 - fnt2.getSize(), medDif.y + medDif.height/2 + fnt2.getSize()/2);
		g.drawString("Hard", hardDif.x + hardDif.width/3 - fnt2.getSize(), hardDif.y + hardDif.height/2 + fnt2.getSize()/2);
		g.setFont(fntSmall);
		g.drawString("INSANE: Don't tell me", insaneDif.x + fntSmall.getSize()/3, insaneDif.y + insaneDif.height/2 - 5);
		g.drawString("ur not gonna use", insaneDif.x + fntSmall.getSize()/3, insaneDif.y + insaneDif.height/2 + fntSmall.getSize() - 5);
		g.drawString("your calculator :]", insaneDif.x + fntSmall.getSize()/3, insaneDif.y + insaneDif.height/2 + fntSmall.getSize() * 2 -5);

		}

	void renderHelp(Graphics g) {
		
	}

	boolean isSetReady() {
		return (numQuestionsInput.retrieveNum() > 0 || endlessQuestions) && (specialQChosenIndex > -1);
	}
	
}
