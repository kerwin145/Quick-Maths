

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class LevelSelect extends QuestionSelectPage {
	
	public boolean addChosen = false, subChosen = false, multChosen = false, divChosen = false;
	public boolean onlyPositive = false, perfectDivisors = false;
	
	LevelSelect (GUI gui){
		super(gui);
	}
	
	int spacing1 = 80;
	int buttonWidth1 = 60, buttonHeight1 = 60;
	public Rectangle AddChoose = new Rectangle(120, 150, buttonWidth1, buttonHeight1);
	public Rectangle SubChoose = new Rectangle(120 + buttonWidth1 + spacing1, 150, buttonWidth1, buttonHeight1);
	public Rectangle MultChoose = new Rectangle(120 + buttonWidth1*2 + spacing1*2, 150, buttonWidth1, buttonHeight1);
	public Rectangle DivChoose = new Rectangle(120 + buttonWidth1*3 + spacing1*3, 150, buttonWidth1, buttonHeight1);
	
	private Rectangle[] qTypes = {AddChoose, SubChoose, MultChoose, DivChoose};
	private boolean[] qChosen = {addChosen, subChosen, multChosen, divChosen};
	
	//these will generate small rectangles under
	//public Rectangle OnlyPositive = new Rectangle((int)(120 + buttonWidth1 + spacing1 - buttonWidth1 * 0.25), (int)(150 + buttonHeight1 * 1.25), (int)(buttonWidth1 * 1.5), buttonHeight1/2);
	//public Rectangle PerfectDivisors = new Rectangle((int)(120 + buttonWidth1*3 + spacing1*3 - buttonWidth1 * 0.25), (int)(150 + buttonHeight1 * 1.25), (int)(buttonWidth1 * 1.5), buttonHeight1/2);
	
	public Rectangle OnlyPositive = new Rectangle((int)(120 + buttonWidth1 + spacing1), (int)(150 + buttonHeight1 * 1.25), (int)(buttonWidth1), buttonHeight1);
	public Rectangle PerfectDivisors = new Rectangle((int)(120 + buttonWidth1*3 + spacing1*3), (int)(150 + buttonHeight1 * 1.25), (int)(buttonWidth1), buttonHeight1);
		
	int buttonY2 = (int)(AddChoose.height * 3 + AddChoose.y), buttonWidth2 = 90, buttonHeight = 50;
	public Rectangle easyDif = new Rectangle (120, buttonY2, buttonWidth2, buttonHeight1);
	public Rectangle medDif = new Rectangle(120 + buttonWidth2 + spacing1, buttonY2, buttonWidth2, buttonHeight1);
	public Rectangle hardDif = new Rectangle(120 + buttonWidth2*2 + spacing1*2, buttonY2, buttonWidth2, buttonHeight1);
	public Rectangle insaneDif = new Rectangle(120 + buttonWidth2*3 + spacing1*3, buttonY2, buttonWidth2, buttonHeight1);
	
	public Rectangle[] Difficulties = {easyDif, medDif, hardDif, insaneDif};
	
	Font fnt0 = new Font("Times", Font.ITALIC, (int)(AddChoose.height/1.5));
	Font fnt1 = new Font("Garamond", Font.BOLD, HomePage.height);
	Font fnt2 = new Font("Garamond", Font.PLAIN, (int)(AddChoose.height/2.5));
	Font fnt3 = new Font("Georgia", Font.PLAIN, (int)(InfoBox.width/1.5));
	Font fntHeader = new Font("Times", Font.BOLD, (int)(AddChoose.height/2));
	Font fntNormal = new Font("Garamond", Font.PLAIN, (int)(AddChoose.height / 4));
	Font fntNormal2 = new Font("Garamond", Font.PLAIN, (int)(AddChoose.height/3.5));
	
	public void render(Graphics g){
		super.render(g);
		Graphics2D g2d = (Graphics2D)g;
		
		//render the current chosen question type box to be green and the others gray
		//also has the benefit of still rendering when quesitontype is null
		for (int i = 0; i < qTypes.length; i++) {
			if (qChosen[i] == true) {
				g.setColor(Color.orange);
				g2d.draw(qTypes[i]);
			}
			else {
				g.setColor(Color.gray);
				g2d.draw(qTypes[i]);
			}
		}
		
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

		g.setFont(fntHeader);
		g.setColor(Color.black);
		g.drawString("Operation", AddChoose.x, AddChoose.y - AddChoose.height/2);

		g.setFont(fnt0);
		g.setColor(Color.black);
		g.drawString(" + ", AddChoose.x + AddChoose.width/2 - fnt0.getSize()/2, AddChoose.y + AddChoose.height/2 + fnt0.getSize()/2);
		g.drawString(" - ", SubChoose.x + SubChoose.width/2 - fnt0.getSize()/2, SubChoose.y + SubChoose.height/2 + fnt0.getSize()/2);
		g.drawString(" * ", MultChoose.x + MultChoose.width/2 - fnt0.getSize()/2, MultChoose.y + MultChoose.height/2 + fnt0.getSize()/2);
		g.drawString(" / ", DivChoose.x + DivChoose.width/2 - fnt0.getSize()/2, DivChoose.y + DivChoose.height/2 + fnt0.getSize()/2);

		g.setFont(fntNormal2);
		if(qChosen[1]){
			g.setColor(Color.black);
			drawStringMultiLine(g2d, "Only Positive", OnlyPositive.width, OnlyPositive.x + 2, OnlyPositive.y + OnlyPositive.height/3);
			if(onlyPositive)g.setColor(Color.orange);
			else g.setColor(Color.gray);
			g2d.draw(OnlyPositive);
		}
		if(qChosen[3]){
			g.setColor(Color.black);
			drawStringMultiLine(g2d, "Perfect Divisors", PerfectDivisors.width, PerfectDivisors.x + 2, PerfectDivisors.y + PerfectDivisors.height/3);
			if(perfectDivisors)g.setColor(Color.orange);
			else g.setColor(Color.gray);
			g2d.draw(PerfectDivisors);
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
	
	public void renderHelp(Graphics g) {
		g.setFont(fntNormal);
		g.setColor(Color.gray);
		g.drawString("Click to Select an operation", (int)(DivChoose.x + DivChoose.width + 5), AddChoose.y + fntNormal.getSize());
		g.drawString("You can also select more than one", (int)(DivChoose.x + DivChoose.width + 5), AddChoose.y + fntNormal.getSize()*2); 
		g.drawString("operation for your problem set. ", (int)(DivChoose.x + DivChoose.width + 5), AddChoose.y + fntNormal.getSize()*3);
		
		if(qChosen[1]) {
			g.drawString("If selected, all questions", (int)(OnlyPositive.x + OnlyPositive.width + 5), OnlyPositive.y + fntNormal.getSize());
			g.drawString("will have answers.",(int)(OnlyPositive.x + OnlyPositive.width + 5), OnlyPositive.y + fntNormal.getSize()*2);
			g.drawString("greater than or equal to 0.",(int)(OnlyPositive.x + OnlyPositive.width + 5), OnlyPositive.y + fntNormal.getSize()*3);
		}
		if(qChosen[3]) {
			g.drawString("If selected, all questions", (int)(PerfectDivisors.x + PerfectDivisors.width + 5), PerfectDivisors.y + fntNormal.getSize());
			g.drawString("will divide perfectly.",(int)(PerfectDivisors.x + PerfectDivisors.width + 5), PerfectDivisors.y + fntNormal.getSize()*2);
			g.drawString("If unselected, answers", (int)(PerfectDivisors.x + PerfectDivisors.width + 5), PerfectDivisors.y + fntNormal.getSize()*3);
			g.drawString("will be rounded down.", (int)(PerfectDivisors.x + PerfectDivisors.width + 5), PerfectDivisors.y + fntNormal.getSize()*4);
		}
		
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
		qChosen[0] = !qChosen[0];
	}

	public void SubChosen() {
		qChosen[1] = !qChosen[1];
	}

	public void MultChosen() {
		qChosen[2] = !qChosen[2];
	}

	public void DivChosen() {
		qChosen[3] = !qChosen[3];
	}
	
	public boolean isSetReady() {
		return (qChosen[0] || qChosen[1] || qChosen[2] || qChosen[3]) 
				&& (numQuestionsInput.retrieveNum() > 0);
	}
	
//thanks stack overflow
//https://stackoverflow.com/questions/4413132/problems-with-newline-in-graphics2d-drawstring
	public static void drawStringMultiLine(Graphics2D g, String text, int lineWidth, int x, int y) {
	    FontMetrics m = g.getFontMetrics();
	    if(m.stringWidth(text) < lineWidth) {
	        g.drawString(text, x, y);
	    } else {
	        String[] words = text.split(" ");
	        String currentLine = words[0];
	        for(int i = 1; i < words.length; i++) {
	            if(m.stringWidth(currentLine+words[i]) < lineWidth) {
	                currentLine += " "+words[i];
	            } else {
	                g.drawString(currentLine, x, y);
	                y += m.getHeight();
	                currentLine = words[i];
	            }
	        }
	        if(currentLine.trim().length() > 0) {
	            g.drawString(currentLine, x, y);
	        }
	    }
	}
}

