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
	
	static int numAchTotal = 0;
	static int numAchVanilla = 0;
	
	Font iconFont = new Font("Sitka Text", Font.PLAIN, 28);
	

    //these are the actual achievement components.
    public static ArrayList<ArrayList<Achievement>> vanillaAchievements = new ArrayList<ArrayList<Achievement>>();
    //public static ArrayList<ArrayList<Rectangle_>> vanillaAchievementsButtons = new ArrayList<ArrayList<Rectangle_>>();
    
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
    
    private void createAchievementsVanilla() {
    	String[][] vanillaAchievementNameList = new String[][] { 
        	{ "Addition Amateur", "Simple Subtraction", "Multiplication Mountain Man", "Delightful Division" }, 
        	{ "Addition Apprentice", "Superb Subtraction", "Mischievous Multiplication", "Division Derby" }, 
        	{ "Adept Addition", "Subtraction Specialist", "Mega Multiplication", "Divine Division" }, 
        	{ "Addition Afficianado", "Subtraction Senseii", "Multiplication Master", "Division Devotee" } };
        ArrayList<ArrayList<ArrayList<String>>> vanillaAchievementExplList = new ArrayList<ArrayList<ArrayList<String>>>();;
        //first layer will be the types, aka the difficulty
        //second layer will be the operation
        //last layer is the list of explanations for that specific achievement
        
        for (int type = 0; type < 4; ++type) {
        	vanillaAchievementExplList.add(new ArrayList<ArrayList<String>>());
            for (int operation = 0; operation < 4; ++operation) {
                vanillaAchievementExplList.get(type).add(new ArrayList<String>());
            }
        }

	    //setting descriptions
	    String lengthText = "";
	    String scoreText = "";
	    
	    int numTypes = vanillaAchievementExplList.size(), 
    		numOperations = vanillaAchievementExplList.get(0).size(), 
    		numStages = 5;
	    String[] difficulties = { " EASY ", " MEDIUM ", " HARD ", " INSANE "};
	    String[] operations = {" an ADDITION ", " a SUBTRACTION ", " a MULTIPLICATION ", " a DIVISION "};
	    
	    for (int i = 0; i < numTypes; i++) {
	        for (int j = 0; j < numOperations; j++) {
	        	for(int k = 0; k < numStages; k++) {
		            lengthText = " " + gui.getAchCheck().vanillaSetLengthReq[k] + " ";
		            scoreText = " " + (int)(gui.getAchCheck().vanillaSetScoreReq[k] * 100.0) + ".";
	
		            String expl = "Complete" + operations[j] + "set at" + difficulties[i] + "difficulty with at least" + lengthText + "problems and a score of at least" + scoreText;
		            vanillaAchievementExplList.get(i).get(j).add(expl);
	        	}
	        }
	    }
	    
	    
	    Color[] vanillaAchBorder = {
	    		MoColors.darkGray, MoColors.forestGreen, MoColors.navajoWhite, MoColors.dodgerBlue,MoColors.mediumOrchid, MoColors.thistle
	    };
	    
	    Color[][] vanillaAchBackground = {
	    		{MoColors.gainsboro, MoColors.silver},
	    		{MoColors.mediumSeaGreen, MoColors.mediumAquamarine,  MoColors.lightGreen},
	    		{MoColors.goldenrod, MoColors.gold, MoColors.yellow},
	    		{MoColors.steelBlue, MoColors.cornflowerBlue, MoColors.skyBlue},
	    		{MoColors.paleVioletRed, MoColors.plum, MoColors.thistle},
	    		{MoColors.sandyBrown, MoColors.khaki, MoColors.lightGreen, MoColors.skyBlue}
	    };
	    
	    int iconDimension = (int)(GUI.WIDTH * GUI.SCALE / 24);
	    
	    Rectangle_[][] vanillaBoxes = new Rectangle_[4][4];
	    //fill that with rectangles
	    for(int i = 0; i < vanillaBoxes.length; i++) {
	    	for(int j = 0; j < vanillaBoxes[i].length; j++) {
	    		vanillaBoxes[i][j] = new Rectangle_(0,0, iconDimension, iconDimension, null,
	    				textPosition.middle, iconFont, Color.white, vanillaAchBorder, vanillaAchBackground, gradientFormat.vertical, 1, 2, true);
	    	}
	    }

	    
	    //set locations for them
	    DrawFormat.setCentered_xy_spacing((int)(GUI.HEIGHT * GUI.SCALE * .125), GUI.WIDTH * GUI.SCALE, (int)(GUI.HEIGHT * GUI.SCALE * .9), 0,
	    (int)(iconDimension * 4.5), (int)(iconDimension * 1.6), vanillaBoxes);
	    
        for (int type = 0; type < 4; type++) {
        	ArrayList<Achievement> tempAchievementList = new ArrayList<Achievement>();
            for (int operation = 0; operation < 4; operation++) {
                tempAchievementList.add(new Achievement(vanillaAchievementNameList[type][operation], 
                			vanillaAchievementExplList.get(type).get(operation), 
                			numStages, 
                			vanillaBoxes[type][operation]));
            }
            vanillaAchievements.add(tempAchievementList);
        }

    }

    private void createAchievementsMyriad() {
    	
    }
}
