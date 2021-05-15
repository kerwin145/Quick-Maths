import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class QuestionSelectPage {
	
	GUI gui;
	
	private String beginText;
	int buttonWidth1 = 60, buttonHeight1 = 60;

	boolean renderHelp;
	public QuestionSelectPage(GUI gui){
		this.gui = gui;
	}

	public NumberTextField numQuestionsInput = new NumberTextField(120, (int)(gui.HEIGHT *gui.SCALE * .85), 200, 35, "0", false);

	public Rectangle HomePage = new Rectangle(gui.WIDTH * gui.SCALE - 140, 15, 120, 20);
	public Rectangle GenerateSet = new Rectangle(HomePage.x + HomePage.width - buttonWidth1*2, (int)((gui.HEIGHT * gui.SCALE) * .85), (buttonWidth1 * 2), buttonHeight1);
	public Rectangle InfoBox = new Rectangle(20, 20, 40, 40);

	Font fnt0 = new Font("Times", Font.ITALIC, (int)(buttonHeight1/1.5));
	Font fnt1 = new Font("Garamond", Font.BOLD, HomePage.height);
	Font fnt2 = new Font("Garamond", Font.PLAIN, (int)(buttonHeight1/2.5));
	Font fnt3 = new Font("Georgia", Font.PLAIN, (int)(InfoBox.width/1.5));
	Font fntHeader = new Font("Times", Font.BOLD, (int)(buttonHeight1/2));
	Font fntNormal = new Font("Garamond", Font.PLAIN, (int)(buttonHeight1/4));
	Font fntSmall = new Font("Times", Font.PLAIN, (int)(buttonHeight1/6));


	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;

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
	
	abstract void renderHelp(Graphics g);
	abstract boolean isSetReady();
}
