package src.main.Notifications;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import k_Methods.MoColors;
import k_Methods.Rectangle_;
import src.main.GUI;

public class Notification {
	String text;
	public Rectangle_ box;
	
	public static final int height = (int) (GUI.HEIGHT * GUI.SCALE * .06);
	
	int timeMax = 5 * 60, time = 0;
	boolean timeOver = false;

	Font fnt = new Font("Garamond", Font.BOLD, 14);
	Random rand = new Random();
	

	//we will use hues of red, green, blue, or purple
	int colorInt = rand.nextInt(4);
	ArrayList<Color> hueSet = new ArrayList<Color>();
	Color borderColor;
	Color bgColor = new Color(0,0,0);
	Color textColor;
	
	public Notification(int y, int width, String text) {
		switch(colorInt) {
		case 0:
			hueSet = MoColors.redColors;
			break;
		case 1:
			hueSet = MoColors.greenColors;
			break;
		case 2: 
			hueSet = MoColors.blueColors;
			break;
		case 3:
			hueSet = MoColors.purpleColors;
			break;
		}
		
		borderColor = hueSet.get(rand.nextInt(hueSet.size()));
		do{
			bgColor = hueSet.get(rand.nextInt(hueSet.size()));
		}while (bgColor == borderColor );
		
		//bgColorInverseAverage
		int bgColorInverseAverage = ((bgColor.getRed()+128)%256 + (bgColor.getGreen()+128)%256 + (bgColor.getBlue()+128)%256)/3;
		bgColorInverseAverage += (int)((256-bgColorInverseAverage) * .75);
		Color textColor = new Color(bgColorInverseAverage, bgColorInverseAverage, bgColorInverseAverage);
		
		box = new Rectangle_(GUI.WIDTH * GUI.SCALE, y, width, height);
		box.setBackgroundOpacity(.6);
		box.forceBackgroundColor(bgColor);
		box.setBorderColor(borderColor);
		box.setFont(fnt);
		box.setFontColor(textColor);
		box.setText(text);
		box.setBorderThickness(2);
		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		box.draw(g2d);
	}
	
	public void tick()	{
		//System.out.println("X: " + box.x + ",Y: " + box.y + ", Time; " + time);

		time++;
		if(time == timeMax)
			timeOver = true;
		//destroying will be done by notificationManager class
	}

	public boolean getTimeOver() {return timeOver;}


	public int getHeight() {
		return height;
	}
	
	public Rectangle_ getBox() {return box;}

	
}
