package src.main.Achievements;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import k_Methods.DrawFormat;
import k_Methods.MoColors;
import k_Methods.Rectangle_;
import k_Methods.Rectangle_.gradientFormat;
import k_Methods.Rectangle_.textPosition;
import src.main.GUI;
import src.main.UserData;

//this class will initialize all the achievements
public class AllAchievements {
	
	GUI gui;
	UserData uData;
	
	public static int numAchTotal = 0;
	public static int numAchVanilla = 0;
	
	Font iconFont = new Font("Sitka Text", Font.PLAIN, 28);
	

    //these are the actual achievement components.
    public static ArrayList<ArrayList<Achievement>> vanillaAchievements = new ArrayList<ArrayList<Achievement>>();
    public static ArrayList<ArrayList<Achievement>> myriadAchievements = new ArrayList<ArrayList<Achievement>>();
  //Myriad achievements: Complete total of x easy questions, x normal questions, x hard, x insane
  	 //Levels: 100, 500, 1000, 5000, 10000
  //x addition, x subtraction, x multiplication (special included), x division (special included)
     //Levels : same as prior
  //x is it divisible, x total questions of any type, 
	
    public AllAchievements(GUI gui) {
    	this.gui = gui;
    	uData = gui.getUserData();
    	createAchievementsVanilla();
    	createAchievementsMyriad();
    }
 
    private Rectangle_[][] create4x4AchBoxes() {
      	Color[] achBorder = {
        		MoColors.darkGray, MoColors.forestGreen, MoColors.navajoWhite, MoColors.dodgerBlue,MoColors.mediumOrchid, MoColors.thistle
        };
      	
    	Color[][] achBackground = {
    		{MoColors.gainsboro, MoColors.silver},
    		{MoColors.mediumSeaGreen, MoColors.mediumAquamarine,  MoColors.lightGreen},
    		{MoColors.goldenrod, MoColors.gold, MoColors.yellow},
    		{MoColors.steelBlue, MoColors.cornflowerBlue, MoColors.skyBlue},
    		{MoColors.paleVioletRed, MoColors.plum, MoColors.thistle},
    		{MoColors.sandyBrown, MoColors.khaki, MoColors.lightGreen, MoColors.skyBlue}
    	};
    	
        int iconDimension = (int)(GUI.WIDTH * GUI.SCALE / 24);
    	 Rectangle_[][] achBoxes = new Rectangle_[4][4];
    	 for(int i = 0; i < achBoxes.length; i++) {
 	    	for(int j = 0; j < achBoxes[i].length; j++) {
 	    		achBoxes[i][j] = new Rectangle_(0,0, iconDimension, iconDimension, "",
 	    				textPosition.middle, iconFont, Color.white, achBorder, achBackground, gradientFormat.vertical, 1, 2, true);
 	    	}
 	    }
    	 
 	    DrawFormat.setCentered_xy_spacing((int)(GUI.HEIGHT * GUI.SCALE * .125), GUI.WIDTH * GUI.SCALE, (int)(GUI.HEIGHT * GUI.SCALE * .9), 0,
 	   	    (int)(iconDimension * 4.5), (int)(iconDimension * 1.6), achBoxes);
 	    
 	    return achBoxes;
    	
    }

  
    private void createAchievementsVanilla() {
    	String[][] vanillaAchievementNameList = { 
        	{ "Addition Amateur", "Simple Subtraction", "Multiplication Mountain Man", "Delightful Division" }, 
        	{ "Addition Apprentice", "Superb Subtraction", "Mischievous Multiplication", "Division Derby" }, 
        	{ "Adept Addition", "Subtraction Specialist", "Mega Multiplication", "Divine Division" }, 
        	{ "Addition Afficianado", "Subtraction Senseii", "Multiplication Master", "Division Devotee" } };

    	Rectangle_[][] vanillaAchBoxes = create4x4AchBoxes();
	    
	    //setting descriptions
		int numStages = 5;
	    String lengthText = "";
	    String scoreText = "";
	    String[] difficulties = { " EASY ", " MEDIUM ", " HARD ", " INSANE "};
	    String[] operations = {" an ADDITION ", " a SUBTRACTION ", " a MULTIPLICATION ", " a DIVISION "};
	    
	    
        for (int type = 0; type < 4; type++) {
        	ArrayList<Achievement> tempAchievementList = new ArrayList<Achievement>();
            for (int operation = 0; operation < 4; operation++) {
            	ArrayList<String> stageExpl = new ArrayList<String>();
            	for(int k = 0; k < numStages; k++) {
		            lengthText = " " + gui.getAchCheck().vanillaSetLengthReq[k] + " ";
		            scoreText = " " + (int)(gui.getAchCheck().vanillaSetScoreReq[k] * 100.0) + ".";
	
		            String expl = "Complete" + operations[operation] + "set at" + difficulties[type] + "difficulty with at least" + lengthText + "problems and a score of at least" + scoreText;
		            stageExpl.add(expl);
            	}
            	Achievement ach = new Achievement(vanillaAchievementNameList[type][operation], 
                			stageExpl, 
                			numStages, 
                			vanillaAchBoxes[type][operation]);
                tempAchievementList.add(ach);
            }
            vanillaAchievements.add(tempAchievementList);
        }

    }

    private void createAchievementsMyriad() {
    	String[][] myriadAchievemnetNameList = { 
            	{ "Easy Peasy", "Medium Marathon", "Hard-Wired", "Insanity" }, 
            	{ "Additive Addiction", "Senior Subtractor", "Multiplicative Multitude", "Delerious Dividee" }, 
            	{ "", "", "", "" }, 
            	{ "", "Sharp Shooter", "30 minutes later...", "Really?" } };
    	//Sharp shooter: 

        	Rectangle_[][] vanillaAchBoxes = create4x4AchBoxes();
    	    
    	    //setting descriptions
    		int numStages = 5;
    	    String lengthText = "";
    	    String scoreText = "";
    	    String[] difficulties = { " EASY ", " MEDIUM ", " HARD ", " INSANE "};
    	    String[] operations = {" an ADDITION ", " a SUBTRACTION ", " a MULTIPLICATION ", " a DIVISION "};
    	    
    	    
            for (int type = 0; type < 4; type++) {
            	ArrayList<Achievement> tempAchievementList = new ArrayList<Achievement>();
                for (int operation = 0; operation < 4; operation++) {
                	ArrayList<String> stageExpl = new ArrayList<String>();
                	for(int k = 0; k < numStages; k++) {
    		            lengthText = " " + gui.getAchCheck().vanillaSetLengthReq[k] + " ";
    		            scoreText = " " + (int)(gui.getAchCheck().vanillaSetScoreReq[k] * 100.0) + ".";
    	
    		            String expl = "Complete" + operations[operation] + "set at" + difficulties[type] + "difficulty with at least" + lengthText + "problems and a score of at least" + scoreText;
    		            stageExpl.add(expl);
                	}
                	
                    /*tempAchievementList.add(new Achievement(vanillaAchievementNameList[type][operation], 
                    			stageExpl, 
                    			numStages, 
//                    			vanillaAchBoxes[type][operation])); */
                }
                vanillaAchievements.add(tempAchievementList);
                
            }
    }
}
