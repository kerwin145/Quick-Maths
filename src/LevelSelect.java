

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class LevelSelect {
	
	GUI gui;
	
	private int questionDifficulty = 0;
	private boolean addChosen = false, subChosen = false, multChosen = false, divChosen = false;
	
	LevelSelect (GUI gui){
		this.gui = gui;
	}

	int spacing1 = 80;
	int buttonWidth1 = 60, buttonHeight1 = 60;
	public Rectangle AddChoose = new Rectangle(120, 150, buttonWidth1, buttonHeight1);
	public Rectangle SubChoose = new Rectangle(120 + buttonWidth1 + spacing1, 150, buttonWidth1, buttonHeight1);
	public Rectangle MultChoose = new Rectangle(120 + buttonWidth1*2 + spacing1*2, 150, buttonWidth1, buttonHeight1);
	public Rectangle DivChoose = new Rectangle(120 + buttonWidth1*3 + spacing1*3, 150, buttonWidth1, buttonHeight1);
	
	private Rectangle[] qTypes = {AddChoose, SubChoose, MultChoose, DivChoose};
	private boolean[] qChosen = {addChosen, subChosen, multChosen, divChosen};
	public boolean renderHelp = false;
	
	int buttonY2 = (int)(AddChoose.height * 3 + AddChoose.y), buttonWidth2 = 90, buttonHeight = 50;
	public Rectangle easyDif = new Rectangle (120, buttonY2, buttonWidth2, buttonHeight1);
	public Rectangle medDif = new Rectangle(120 + buttonWidth2 + spacing1, buttonY2, buttonWidth2, buttonHeight1);
	public Rectangle hardDif = new Rectangle(120 + buttonWidth2*2 + spacing1*2, buttonY2, buttonWidth2, buttonHeight1);
	public Rectangle insaneDif = new Rectangle(120 + buttonWidth2*3 + spacing1*3, buttonY2, buttonWidth2, buttonHeight1);
	
	public Rectangle[] Difficulties = {easyDif, medDif, hardDif, insaneDif};
	
	public NumberTextField numQuestionsInput = new NumberTextField(120, (int)(gui.HEIGHT *gui.SCALE * .7), 200, 35, "0", false);
	
	public Rectangle HomePage = new Rectangle(gui.WIDTH * gui.SCALE - 140, 15, 120, 20);
	public Rectangle GenerateSet = new Rectangle(HomePage.x + HomePage.width - buttonWidth1*2, (int)((gui.HEIGHT * gui.SCALE) * .85), (buttonWidth1 * 2), buttonHeight1);
	public Rectangle InfoBox = new Rectangle(20, 20, 40, 40);

	Font fnt0 = new Font("Times", Font.ITALIC, (int)(AddChoose.height/1.5));
	Font fnt1 = new Font("Garamond", Font.BOLD, HomePage.height);
	Font fnt2 = new Font("Garamond", Font.PLAIN, (int)(AddChoose.height/2.5));
	Font fnt3 = new Font("Georgia", Font.PLAIN, (int)(InfoBox.width/1.5));
	Font fntHeader = new Font("Times", Font.BOLD, (int)(AddChoose.height/2));
	Font fntSmall = new Font("Times", Font.PLAIN, (int)(insaneDif.height/6));
	Font fntNormal = new Font("Garamond", Font.PLAIN, (int)(AddChoose.height / 4));
	
	String beginText;

	public void render(Graphics g){
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
			if (i == questionDifficulty) {
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

		
		g.setFont(fnt1);
		g2d.draw(HomePage);
		g.drawString("Home", HomePage.x + HomePage.width/4, (int)(HomePage.y + HomePage.height * 0.85));
		
		g.drawString("Number of Questions", numQuestionsInput.getX(), numQuestionsInput.getY() - numQuestionsInput.getHeight()/2);
		numQuestionsInput.render(g);
		
		g.setFont(fnt3);
		if (isSetReady() && numQuestionsInput.retrieveNum() > 0)
			g.setColor(Color.blue);
		else g.setColor(Color.gray);
		
		if(numQuestionsInput.retrieveNum()>10000)
			beginText = "X_X";
		else if(numQuestionsInput.retrieveNum()>1000)
			beginText = "Umm....";
		else if(numQuestionsInput.retrieveNum()>100) {
			g.setFont(fntNormal);
			beginText = "Really?";
		}
		else if (numQuestionsInput.retrieveNum() >= 0 && numQuestionsInput.retrieveNum() <= 100)
			beginText = "Begin!";
		else
			beginText = "STahhp";

		g.drawString(beginText, (int)GenerateSet.getX() + fnt3.getSize()/4, (int)GenerateSet.getY() + (int)(GenerateSet.getHeight()/2) + 10);

		g2d.draw(GenerateSet);
		
		g.setColor(Color.gray);
		g2d.draw(InfoBox);
		g.setFont(fnt3);
		g.drawString("i", (int)(InfoBox.x + fnt3.getSize() * 0.6), (int)(InfoBox.y  + fnt3.getSize()));
		
		if(renderHelp) renderHelp(g);
		

	}
	
	public void renderHelp(Graphics g) {
		g.setFont(fntNormal);
		g.setColor(Color.gray);
		g.drawString("Click to Select an operation", (int)(DivChoose.x + DivChoose.width*1.5), AddChoose.y + fntNormal.getSize());
		g.drawString("You can also select more than one", (int)(DivChoose.x + DivChoose.width*1.5), AddChoose.y + fntNormal.getSize()*2); 
		g.drawString("operation for your problem set. ", (int)(DivChoose.x + DivChoose.width*1.5), AddChoose.y + fntNormal.getSize()*3);
		
		if(qChosen[3]) {
			g.drawString("For division, round answers down", (int)(DivChoose.x + DivChoose.width*1.5), AddChoose.y + fntNormal.getSize()*4);
			g.drawString("to the nearest whole number.", (int)(DivChoose.x + DivChoose.width*1.5), AddChoose.y + fntNormal.getSize()*5);
		}
		
		//approximate difficulties: Easy K-5th Grade, Medium 3rd - 8th Grade, Hard ~6th Grade+
		g.drawString("Approximate grade level equivalencies", (int)(insaneDif.x + insaneDif.width * 1.5), insaneDif.y + fntNormal.getSize());
		g.drawString("Easy: Kindergarten - 5th Grade", (int)(insaneDif.x + insaneDif.width * 1.5), insaneDif.y + fntNormal.getSize()*2);
		g.drawString("Medium: 3rd Grade - 8th Grade", (int)(insaneDif.x + insaneDif.width * 1.5), insaneDif.y + fntNormal.getSize()*3);
		g.drawString("Hard: 6th Grade+", (int)(insaneDif.x + insaneDif.width * 1.5), insaneDif.y + fntNormal.getSize()*4);
		g.drawString("Insane: ???", (int)(insaneDif.x + insaneDif.width * 1.5), insaneDif.y + fntNormal.getSize()*5);

		g.drawString("Number Text Field:", numQuestionsInput.getX() + numQuestionsInput.getWidth(), numQuestionsInput.getY() + fntNormal.getSize());
		g.drawString("Click to activate typing.", numQuestionsInput.getX() + numQuestionsInput.getWidth(), numQuestionsInput.getY() + fntNormal.getSize()*2);
		
		g.drawString("Choose an operation, difficulty,",(int)GenerateSet.x - GenerateSet.width*2, GenerateSet.y + fntNormal.getSize());
		g.drawString("and problem set length to begin.",(int)GenerateSet.x - GenerateSet.width*2, GenerateSet.y + fntNormal.getSize()*2);
		g.drawString("(Hot key: \"Enter\")",(int)GenerateSet.x - GenerateSet.width*2, GenerateSet.y + fntNormal.getSize()*3);

	}
			
	public void tick() {
		
	}

	public int getQuestionDifficulty() {return questionDifficulty;}

	public void setQuestionDifficulty(int questionDifficulty) {this.questionDifficulty = questionDifficulty;}

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

}

