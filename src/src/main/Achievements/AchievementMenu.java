package src.main.Achievements;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import src.main.GUI;

public class AchievementMenu {
	
	GUI gui;
	
	AllAchievements allAch;
	
	AchievementPageHome homePage;
	AchievementPageVanilla vanillaPage;
	AchievementPageMyriad myriadPage;
	
	/*
	 * To make a new page: Make the class, extends achievementpage
	 * Create render method (import graphics)
	 * Create a page for that in AchPage
	 * Create object here
	 * Add to mouse input and key input
	 */

	public enum AchPage {
		home,
		vanilla,
		myriad,
		stats
	}
	
	//change this when you want to test a certain ach page directly
	public AchPage achPage = AchPage.home;
	
	public AchievementMenu(GUI gui) {
		this.gui = gui;
		allAch = new AllAchievements(gui);
		homePage = new AchievementPageHome("Achievements", new ImageIcon("images/AcheivementBackground2.jpg").getImage().getScaledInstance(600 * 2, 333 * 2, 1));
		vanillaPage = new AchievementPageVanilla("Vanilla", new ImageIcon("images/AcheivementBackground3.jpg").getImage().getScaledInstance(600 * 2, 333 * 2, 1));
		myriadPage = new AchievementPageMyriad("Mryiad", new ImageIcon("images/AcheivementBackground3.jpg").getImage().getScaledInstance(600 * 2, 333 * 2, 1));
	}
	
	
	public void render(Graphics g) {
		if(achPage == AchPage.home) 
			homePage.render(g);
		else if(achPage == AchPage.vanilla) 
			vanillaPage.render(g);
		else if (achPage == AchPage.myriad)
			myriadPage.render(g);
		else if(achPage == AchPage.stats) {
			
		}
			
		
	}
	
	public AllAchievements getAllAchievements() {
		return allAch;
	}
	public AchievementPageHome getHomePage() {
		return homePage;
	}


	public AchievementPageVanilla getVanillaPage() {
		return vanillaPage;
	}


	public AchievementPageMyriad getMyriadPage() {
		return myriadPage;
	}
}
