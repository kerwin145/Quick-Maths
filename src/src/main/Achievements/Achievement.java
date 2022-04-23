package src.main.Achievements;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import k_Methods.Rectangle_;
import k_Methods.stringGraphics;

public class Achievement {
    String name;
    ArrayList<String> explanations = new ArrayList<String>();
    Rectangle_ box;
    int numStages;
    int stage = 0;
    
    int displayText_X, displayText_Y;
    private Rectangle_ displayTextBox;
    private Font displayFont = new Font("Eras Medium ITC", Font.BOLD, 16);
    
    //utility
    String[] romanNumerals = new String[] {"I", "I", "II", "III", "IV", "V" };
    
    public Achievement(String name, String explanation, Rectangle_ box) {
        this.name = name;
        this.box = (Rectangle_)box.clone();
        this.box.setText(romanNumerals[stage]);
        this.box.setColors(stage);//for testing purposes
        explanations.add(explanation);
        numStages = 1;
        displayTextBox = new Rectangle_(box.x+box.width/2, box.y - (int)(displayFont.getSize()*1.65), 0, displayFont.getSize());
    }
    
    public Achievement(String name, ArrayList<String> explanations, int numStages, Rectangle_ box) {
        this.name = name;
        this.box = (Rectangle_)box.clone();
        this.box.setText(romanNumerals[stage]);
        this.box.setColors(stage);//for testing purposes
        this.explanations = explanations;
        this.numStages = numStages;
        displayTextBox = new Rectangle_(box.x+box.width/2, box.y -(int)(displayFont.getSize()*1.65), 0, displayFont.getSize());
    }
    
    public void render(Graphics g) {
    	Graphics2D g2d = (Graphics2D)g;
    	
    	box.draw(g2d);
    	
    	g.setFont(displayFont);
    	stringGraphics.drawStringCentered(getDisplayText(), displayTextBox, g);
    	
    }
    
    public int getStage() {
    	return stage;
    }
    
    public void setStage(int stage) {
    	this.stage = stage;
		box.setText(romanNumerals[stage]);
		box.setColors(stage);
    }
    
    public boolean stageUp() {
    	if(stage < numStages) {
    		stage++;
    		box.setText(romanNumerals[stage]);
    		box.setColors(stage);
            //.setText(explanations.get(stage));
    		
       		return true;
    	}else {
    		return false;
    	}
    }
    
    public String getFullName() {
     	if(numStages > 1) {//multistage
    		if (stage < numStages) 
    			return name + " " + romanNumerals[stage];
    		else  //stage = numStages
    			return "** " + name + this.romanNumerals[stage] + " **";
    	}
    	else {//single stage
    		if(stage == 0)
    			return name;
    		else
    			return "**" + name + "**";
    	}   
 	}
    public String getDisplayText() {
    	if(numStages > 1) {//multistage
    		if (stage < numStages) 
    			return name + " ";// + this.romanNumerals[stage];
    		else  //stage = numStages
    			return "**" + name + "**";// + " " +this.romanNumerals[stage] + "**";
    	}
    	else {//single stage
    		if(stage == 0)
    			return name;
    		else
    			return "**" + name + "**";
    	}
    }

    public String setIconText() {
    	return romanNumerals[stage];
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getExplanation() {return (stage == numStages ? name + " Complete!" : explanations.get(stage));}
    public Rectangle_ getBox() {return box;}
    
    
}
