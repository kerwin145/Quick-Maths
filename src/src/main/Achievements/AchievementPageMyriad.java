package src.main.Achievements;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class AchievementPageMyriad extends AchievementPage{
	
	public AchievementPageMyriad(String header,Image background) {
		super(header, background);
	}
	
	Achievement tempAch;
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		renderBase(g);
		
		//render out the achievements
	
		
		infoBox.draw(g2d);
	}

}
