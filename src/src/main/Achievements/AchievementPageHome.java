package src.main.Achievements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import k_Methods.DrawFormat;
import k_Methods.MoColors;
import k_Methods.Rectangle_;
import k_Methods.Rectangle_.gradientFormat;
import src.main.GUI;

public class AchievementPageHome extends AchievementPage{
	
	Font header2 =  new Font("Linux Libertine Display G", Font.BOLD, 34);
	Font header3 = new Font("COPPERPLATE GOTHIC BOLD", Font.PLAIN, 23);
	Font overviewTextFont = new Font("COPPERPLATE GOTHIC LIGHT", Font.PLAIN | Font.ITALIC , 22);
	Font overviewHeader= new Font("COPPERPLATE GOTHIC LIGHT", Font.BOLD, 26);
	
	final int yOverview = 140;
	
	String[] overviewText = {
		"Vanilla: 0",
		"Challenge: 0",
		"Myriad: 0",
		"Potpourri: 0"
	};
	
	Rectangle_ overview = new Rectangle_(margin, yOverview + header2.getSize(), (int)(GUI.WIDTH * GUI.SCALE * .25), (int)(GUI.HEIGHT * GUI.SCALE * .65));
	
	final int buttonWidth = 235, buttonHeight = 110, borderThickness = 2;
	public Rectangle_ vanillaBtn = new Rectangle_(0, 0, buttonWidth,buttonHeight);
	public Rectangle_ challengeBtn = new Rectangle_(0, 0, buttonWidth,buttonHeight);
	public Rectangle_ myriadBtn = new Rectangle_(0, 0, buttonWidth,buttonHeight);
	public Rectangle_ potpourriBtn = new Rectangle_(0, 0, buttonWidth,buttonHeight);
	
	Rectangle_[][] buttons;
	
	public AchievementPageHome(String header, Image background) {

		super(header, background);
		
		vanillaBtn.setBorderThickness(borderThickness);
		vanillaBtn.setGradientFormat(gradientFormat.vertical);
		vanillaBtn.addBackgroundColor(new Color[] { MoColors.mediumAquamarine, MoColors.mediumSeaGreen});
		vanillaBtn.addBorderColor(MoColors.paleGreen);
		vanillaBtn.setBorderThickness(2);
		vanillaBtn.setFontColor(Color.white);
		vanillaBtn.setFont(header3);
		vanillaBtn.setText("Vanilla");
		vanillaBtn.setHasDarkenedColors(true);
		
		challengeBtn.setBorderThickness(borderThickness);
		challengeBtn.setGradientFormat(gradientFormat.vertical);
		challengeBtn.addBackgroundColor(new Color[] {MoColors.mediumOrchid, MoColors.steelBlue});
		challengeBtn.addBorderColor(MoColors.violet);
		vanillaBtn.setBorderThickness(2);
		challengeBtn.setFontColor(Color.white);
		challengeBtn.setFont(header3);
		challengeBtn.setText("Challenge");
		challengeBtn.setHasDarkenedColors(true);
		
		myriadBtn.setBorderThickness(borderThickness);
		myriadBtn.setGradientFormat(gradientFormat.vertical);
		myriadBtn.addBackgroundColor(new Color[] {MoColors.skyBlue, MoColors.mediumAquamarine, MoColors.lightSeaGreen});
		myriadBtn.addBorderColor(MoColors.dodgerBlue);
		myriadBtn.setBorderThickness(2);
		myriadBtn.setFontColor(Color.white);
		myriadBtn.setFont(header3);
		myriadBtn.setText("Myriad");
		myriadBtn.setHasDarkenedColors(true);
		
		potpourriBtn.setBorderThickness(borderThickness);
		potpourriBtn.setGradientFormat(gradientFormat.vertical);
		//pearl pink, grey olive, traffic green. Used: a * 	(255-a) * .5 to lighten the colors
		potpourriBtn.addBackgroundColor(new Color[] {MoColors.coral, MoColors.paleGreen, MoColors.cornflowerBlue});
		potpourriBtn.addBorderColor(MoColors.navajoWhite);
		potpourriBtn.setBorderThickness(2);
		potpourriBtn.setFontColor(Color.white);
		potpourriBtn.setFont(header3);
		potpourriBtn.setText("Potpourri");
		potpourriBtn.setHasDarkenedColors(true);

		overview.setBorderColor(MoColors.silver);
		overview.setBorderThickness(borderThickness);
		overview.setFontColor(Color.blue);		
		
		buttons = new Rectangle_[][] {new Rectangle_[] {vanillaBtn, challengeBtn}, 
			  new Rectangle_[] {myriadBtn, potpourriBtn}};
		
		//DrawFormat.setCentered_xy_((int)(GUI.HEIGHT * GUI.SCALE*.4), (int)(GUI.WIDTH * GUI.SCALE * .75), (int)(GUI.HEIGHT * GUI.SCALE*.75), (int)(GUI.WIDTH * GUI.SCALE * .3), buttons);
		DrawFormat.setCentered_xy_spacing(overview.y, GUI.WIDTH * GUI.SCALE, overview.y + overview.height, overview.x + overview.width, 40, 40, buttons);
	
	}
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		renderBase(g);
		
		g.setFont(header2);
		
		g.drawString("Achievement Overview", margin, yOverview);
		renderOverviewPanel(g);

		overview.draw(g2d);
		
		for(Rectangle_[] row: buttons) {
			for(Rectangle_ button: row) {
				button.draw(g2d);
			}
		}
		
	}
	
	private void renderOverviewPanel(Graphics g) {
		int overviewMargin = 15;
		int spacingSmall = 5;
		int spacing = 8;
		g.setFont(overviewHeader);
		g.drawString("Achievments", overview.x + overviewMargin, overview.y + overviewMargin + g.getFont().getSize());
		g.drawString("Acquired: " + AllAchievements.numAchTotal, overview.x + overviewMargin, overview.y + overviewMargin + g.getFont().getSize()*2 + spacingSmall);
		
		//let's hope this isn't too time consuming
		overviewText = new String[]{
				"Vanilla: " + AllAchievements.numAchVanilla,
				"Challenge: 0",
				"Myriad: 0",
				"Potpourri: 0"
			};
		
		g.setFont(overviewTextFont);
		for(int i = 0; i < overviewText.length; i++) {
			g.drawString(overviewText[i], overview.x + overviewMargin, overview.y + overviewMargin + spacing * (i+4) + g.getFont().getSize()*(i+3) );
		}
	}

}
