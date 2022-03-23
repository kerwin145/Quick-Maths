package src.main.Achievements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import k_Methods.MoColors;
import k_Methods.Rectangle_;
import k_Methods.Rectangle_.gradientFormat;
import src.main.GUI;

public class AchievementPage {
	
	String header;
    Font fntBoxSmall = new Font("Lucida Bright", Font.PLAIN, 15);
    Font fntInfo = new Font("Eras Medium ITC", Font.BOLD, 15);
    Font fntHeader = new Font("COPPERPLATE GOTHIC LIGHT", Font.ITALIC, 50);
	static final int margin = 55;
	
	Image background; 
	
	Rectangle_ back;
	Rectangle_ infoBox;
	
	String infoBoxDefaultText = "Click on an acheivement to view its requirements";
	
	public AchievementPage(String header, Image background) {
		this.header = header;
		
		back = new Rectangle_(GUI.WIDTH * GUI.SCALE - 140, 15, 120, 20);
		back.addBackgroundColor(new Color[]{MoColors.royalBlue, MoColors.deepSkyBlue});
		back.setGradientFormat(gradientFormat.vertical);
		back.setBorderColor(MoColors.navy);
		back.setFont(fntBoxSmall);
		back.setFontColor(Color.white);
		back.setText("Back");
		back.setHasDarkenedColors(true);
		
		//width is 14 72 14
		infoBox = new Rectangle_((int)(GUI.WIDTH * GUI.SCALE *.14), (int)(GUI.HEIGHT * GUI.SCALE * .9), (int)(GUI.WIDTH * GUI.SCALE *.72), (int)(fntInfo.getSize() * 1.5) + 10);
		infoBox.setBorderColor(MoColors.darkGray);
		infoBox.setBorderThickness(2);
		infoBox.setBackgroundOpacity(.5);
		infoBox.addBackgroundColor(MoColors.silver);
		infoBox.setText(infoBoxDefaultText);
		infoBox.setFont(fntInfo);
		infoBox.setFontColor(Color.white);
		
		this.background = background;
;
	}

	public void renderBase(Graphics g){
		
		g.drawImage(background, 0, 0, null);
		
		back.draw((Graphics2D)g);
		
		g.setFont(fntHeader);
		g.drawString(header, 55, 70);
	}
	
	public Rectangle_ getBack() {return back;}
	public void setInfoBoxText(String text) {infoBox.setText(text);}
    public Rectangle_ getInfoBox() {return infoBox;}
    //gets called when you switch to this page
    public void reInit() {
    	infoBox.setText(infoBoxDefaultText);
    }

}
