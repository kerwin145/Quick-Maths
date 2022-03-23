package src.main.Achievements;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class AchievementPageVanilla extends AchievementPage {

	public AchievementPageVanilla(String header,Image background) {
		super(header, background);
	}
		
	Achievement tempAch;
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		renderBase(g);
		
		//render out the achievements
		for(int i = 0; i < AllAchievements.vanillaAchievements.size(); i++) {
			for(int j = 0; j < AllAchievements.vanillaAchievements.get(i).size(); j++) {
				AllAchievements.vanillaAchievements.get(i).get(j).render(g);

			}
		}
		
		infoBox.draw(g2d);
	}

}
