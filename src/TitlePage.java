import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TitlePage {
	
	GUI gui;
	
	TitlePage (GUI gui){
		this.gui = gui;
	}

	public Rectangle playButton = new Rectangle(100, 150, 150, 50);
	public Rectangle playSpecialButton = new Rectangle(100, 250, 150, 50);
	public Rectangle resumeButton = new Rectangle(100, 350, 150, 50);
	public Rectangle achievementsButton = new Rectangle(100, 450, 150, 50);
	public Rectangle quitButton = new Rectangle(100, 550, 150, 50);

	
	Font fnt0 = new Font("Arial", Font.BOLD, 50);
	Font fnt1 = new Font("Arial", Font.BOLD, 25);
	
	public boolean setFinished = true, setSpecialFinished = true;
	public static enum PreviousPage{
		Normal,
		Special,
		SpecialYN;
	}
	public static PreviousPage questionPage;

	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		
		g.setFont(fnt0);
		g.setColor(Color.black);
		g.drawString("Quick Maths", 100, 100);
		
		g.setFont(fnt1);
		g.setColor(Color.orange);
		g2d.draw(playButton);
		
		g.setColor(Color.magenta);
		g2d.draw(playSpecialButton);
		
		g.setColor(Color.cyan);
		g2d.draw(achievementsButton);
		
		g.setColor(Color.red);
		g2d.draw(quitButton);
			
		g.setColor(Color.black);
		if(!setFinished) {
			g.drawString("Restart", playButton.x + 10, playButton.y + 30);
		}
		else {
			g.drawString("Play", playButton.x + 10, playButton.y + 30);
		}
		if(!setSpecialFinished) {
			g.drawString("Restart", playSpecialButton.x + 10, playSpecialButton.y + 30);
		}
		else {
			g.drawString("Special", playSpecialButton.x + 10, playSpecialButton.y + 30);
		}
		
		if(!(setFinished && setSpecialFinished)){
			g.setColor(Color.green);
		}
		else g.setColor(Color.gray);
		g.drawString("Resume", resumeButton.x + 10, resumeButton.y + 30);
		g2d.draw(resumeButton);

		
		g.setColor(Color.black);
		g.drawString("Medals", achievementsButton.x + 10, achievementsButton.y + 30);
		g.drawString("Quit", quitButton.x + 10, quitButton.y + 30);

	}
	
	public void renderHelp(Graphics g) {
		
	}

}

