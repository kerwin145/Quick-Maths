import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/*List of Possible Achievements
 * VANILLA
 * Beginner: 
 * Addition Amateur : Easy addition set of more than 10 questions with a grade of over 60
 * Simple Subtraction : 
 * Multiplication Mountain Man : 
 * Delightful Division : E
 * 
 * Intermediate:
 * Addition Apprentice : Medium addition of more than 10 questions with a grade of over 60
 * Superb Subtraction :   
 * Mischievous Multiplication  :
 * Division 
 * 
 * Proficient:
 * Adept Addition: Hard addition set of more than 10 questions with a grade of over 60
 * Subtraction Specialist :
 * Mega Multiplication : 
 * Divine division : 
 * 
 * Challenger: 
 * Addition Afficianado : Insane addition set of more than 10 questions with a grade of over 60
 * Subtraction Senseii :
 * Multiplication Master : 
 * Division Devotee : 
 */

public class AchievementPages {
	
	GUI gui;
	UserData uData;
	
	public int pageIndex = 0;
	public int numPages = 4;
	
	public Rectangle HomePage = new Rectangle(gui.WIDTH * gui.SCALE - 140, 15, 120, 20);
	public Rectangle nextSlide = new Rectangle(gui.WIDTH * gui.SCALE - 140, gui.HEIGHT * gui.SCALE - 50, 90, 30);
	public Rectangle prevSlide = new Rectangle(40, gui.HEIGHT * gui.SCALE - 50, 90, 30);
	
	public Rectangle achDescription = new Rectangle(60, gui.HEIGHT * gui.SCALE - 60, gui.WIDTH * gui.SCALE - 240, 40);

	Font fnt0 = new Font("Arial", Font.ITALIC, gui.HEIGHT * gui.SCALE / 18);
	Font fnt1 = new Font("Garamond", Font.BOLD, HomePage.height);
	Font fntNormal = new Font("Garamond", Font.PLAIN, 30);
	Font fntSmall = new Font("Garamond", Font.PLAIN, 15);
	Font fntSmallBold = new Font("Garamond", Font.BOLD, 15);
	
	int x1 = 40, y1 = 80, y2 = gui.HEIGHT * gui.SCALE / 6;
	int yspacing = (int)(fntNormal.getSize() * 1.5), ySpacing2 = 120;
    int xspacing = 50, xSpacing2 = gui.WIDTH * gui.SCALE / 4;
	int y3 = y2 + yspacing * 6;
	int x2 = x1 + gui.WIDTH * gui.SCALE/2;
		
	//vanilla achievements
	private ArrayList<ArrayList<String>> vanillaAchievementExplList = new ArrayList<ArrayList<String>>();
	private String[][] vanillaAchievementNameList = 
			{{"Addition Amateur", "Simple Subtraction", "Multiplication Mountain Man", "Delightful Division"},
			{"Addition Apprentice", "Superb Subtraction", "Mischievous Multiplication", "Division Derby"},
			{"Adept Addition", "Subtraction Specialist", "Mega Multiplication", "Divine Division"},
			{"Addition Afficianado", "Subtraction Senseii", "Multiplication Master", "Division Devotee"}};	
	
	public ArrayList<ArrayList<Achievement>> vanillaAchievementList = new ArrayList<ArrayList<Achievement>>(); //this is the ultimate vanilla achievement list. All the lists before this is set up. 
	private Achievement achievementSelected = null; //the one selected. It will have its explanations portrayed. 
	public ArrayList<ArrayList<Rectangle>> achievementButtons = new ArrayList<ArrayList<Rectangle>>(); //later, when icons are made, set up a list of corresponding icons 
	private int achStage; //used only for the render loop.

	public AchievementPages(GUI gui){
		this.gui = gui;
		uData = gui.getUdata();
		
		//create an array list of array list of explanations.  
		for(int type = 0; type < 4; type++) {
			for(int operation = 0; operation < 4; operation++) {
				vanillaAchievementExplList.add(new ArrayList<String>());
			}
		}
	
		String operationText = "", difficultyText = "", lengthText = "", scoreText = "";
		String result;
		//create explanations for all 16 explanation lists (total of 48 explanations)
			for(int i = 0; i < vanillaAchievementExplList.size(); i++){
					for(int j = 0; j < 3; j++) {//levels 
						lengthText = " " + gui.getResultsPage().getAchCheck().vanillaSetLengthReq[j] + " ";
						scoreText = " " + (int)(gui.getResultsPage().getAchCheck().vanillaSetScoreReq[j]*100) + ".";
						
						if(i / 4 == 0) //beginner explanations
							difficultyText = " EASY ";
						if(i / 4 == 1) 
							difficultyText = " MEDIUM ";
						if(i / 4 == 2) 
							difficultyText = " HARD ";
						if(i / 4 == 3) 
							difficultyText = " INSANE ";
						
						if(i % 4 == 0) //addition explanations
							operationText = " an ADDITION ";
						else if(i % 4 == 1)
							operationText = " a SUBTRACTION ";
						else if(i % 4 == 2)
							operationText = " a MULTIPLICATION ";
						else if(i % 4 == 3)
							operationText = " a DIVISION ";
 
						
						result = "Complete" + operationText + "set at" + difficultyText + "difficulty with at least" + lengthText
								+ "problems and a score of at least" + scoreText;
						vanillaAchievementExplList.get(i).add(result);
						//System.out.println(result);	
			}
		}
		
		ArrayList<Achievement> tempAchievementList;
		for(int type = 0; type < 4; type++) {
			tempAchievementList = new ArrayList<Achievement>();
			for(int operation = 0; operation < 4; operation++) {
				tempAchievementList.add(new Achievement(vanillaAchievementNameList[type][operation], 
						vanillaAchievementExplList.get(type * 4 + operation), 3));
			}
			vanillaAchievementList.add(tempAchievementList); 
		}
		
		//set up achievement button list and sync unlock of achievement list with data file
		
		ArrayList<Rectangle> tempButtonList;
		for(int type = 0; type < vanillaAchievementList.size(); type++) {
			tempButtonList = new ArrayList<Rectangle>();
			for(int operation = 0; operation < vanillaAchievementList.get(type).size(); operation++) {
				tempButtonList.add(new Rectangle(x1 + xSpacing2 * type, y2 + ySpacing2 * operation, 50, 50));
				vanillaAchievementList.get(type).get(operation).setCurrentStage(uData.vanillaAchLevel[type][operation]);
			}
			achievementButtons.add(tempButtonList);

		}
	}
	
	public void renderPage1(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		
		g.setColor(Color.black);
		g.setFont(fnt0);
		g.drawString("Medals: Vanilla ", x1, y1);
		
		/* loop through the list of achievements.
		 *Draw a rectangle with color gray that corresponds to if that achievement has been obtained
		 * 		In the future, it will be replaced with a picture (like an icon)
		 * Draw the achievement name next to it. 
		 * When the row is done, go across by xspacing2 amount
		 */
		//render the vanilla achievements:
		g.setFont(fntSmall);
		for(int type = 0; type < vanillaAchievementList.size(); type++) {
			for(int operation = 0; operation < vanillaAchievementList.get(type).size(); operation++) {
				
				achStage = vanillaAchievementList.get(type).get(operation).getCurrentStage();
				
				if(achStage == 0) g.setColor(Color.gray);
				if(achStage == 1) g.setColor(Color.green);
				if(achStage == 2) g.setColor(Color.blue);
				if(achStage == 3) g.setColor(Color.magenta);
				
				g2d.draw(achievementButtons.get(type).get(operation));
				g.setColor(Color.black);
				g.drawString(vanillaAchievementList.get(type).get(operation).getDisplayText(), 
						achievementButtons.get(type).get(operation).x + achievementButtons.get(type).get(operation).width + 5, 
						achievementButtons.get(type).get(operation).y + achievementButtons.get(type).get(operation).height/2);

			}
		}
		
		g2d.draw(achDescription);
		if(achievementSelected != null)
		g.drawString(achievementSelected.getDisplayText() + ": " + achievementSelected.getExplanation(), achDescription.x + 5, achDescription.y + fntSmall.getSize());
		else g.drawString("Click on the achievement icon to view its description!", achDescription.x + 5, achDescription.y + fntSmall.getSize());
		
		g.setColor(Color.black);
		g2d.draw(HomePage);
		g2d.draw(nextSlide);
		
		g2d.setFont(fnt1);
		g.drawString("Home", HomePage.x + HomePage.width/4, (int)(HomePage.y + HomePage.height * 0.85));
		g.drawString("Next =>", nextSlide.x + 6, (int)(nextSlide.y + fnt1.getSize()*0.87));
	
	}
	
	public void renderPage2(Graphics g) {
		g.setFont(fnt0);
		g.drawString("Medals: Special", x1, y1);

		renderFrame(g);

	}
	
	public void renderPage3(Graphics g) {
		g.setFont(fnt0);
		g.drawString("Medals: Potpourri", x1, y1);
		
		renderFrame(g);
	}
	
		
	public void renderStats(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		
		g.setFont(fnt0);
		g.drawString("Stats I: ", x1, y1);

		g.setFont(fntSmallBold);
		g.drawString("Question Completed", x1, y2);
		g.drawString("Question Correct", x1, y3);
		g.drawString("Average Times", x2, y2);
		g.drawString("Best Question Times", x2, y3);
		g.setFont(fntSmall);
		g.setColor(new Color(0, 115, 168));
		//rows organized by question type, columns is difficulty. i.e. Addition: Easy, Medium,  Hard, Insane
		for(int type = 0; type < uData.questionsCompleted.length; type++){
			g.drawString(uData.typeText[type] + ": ", x1, y2 + (type + 1)*yspacing);
			for(int difficulty = 0; difficulty < uData.questionsCompleted[type].length; difficulty++){
				g.drawString(""+uData.questionsCompleted[type][difficulty], x1 + (difficulty + 1)*xspacing + fnt1.getSize() * 5, y2 + (type + 1)*yspacing);
				if(type == uData.questionsCompleted.length - 1){//when on the last iteration, print the headers and the vertical lines
					g.drawString(uData.difficultyText[difficulty], x1 + (difficulty + 1)*xspacing + fnt1.getSize() * 5, y2); 
					//g.drawLine((int)(x1 + (xspacing + 1) * difficulty  + fnt1.getSize() * 5.5), y2 + yspacing, 
						//	(int)(x1 + (xspacing + 1) * difficulty  + fnt1.getSize() * 5.5), y2 + yspacing * uData.questionsCompleted.length);
				}
			}
		}
		g.setColor(new Color(0, 168, 56));
		for(int type = 0; type < uData.questionsCorrect.length; type++){
			g.drawString(uData.typeText[type] + ": ", x1, y3 + (type + 1)*yspacing);
			for(int difficulty = 0; difficulty < uData.questionsCorrect[type].length; difficulty++){
				g.drawString(""+uData.questionsCorrect[type][difficulty], x1 + (difficulty + 1)*xspacing + fnt1.getSize() * 5, y3 + (type + 1)*yspacing);
				if(type == uData.questionsCorrect.length - 1){//when on the last iteration, print the headers and the vertical lines
					g.drawString(uData.difficultyText[difficulty], x1 + (difficulty + 1)*xspacing + fnt1.getSize() * 5, y3); 
					//g.drawLine((int)(x1 + (xspacing + 1) * difficulty  + fnt1.getSize() * 5.5), y2 + yspacing, 
						//	(int)(x1 + (xspacing + 1) * difficulty  + fnt1.getSize() * 5.5), y2 + yspacing * uData.questionsCompleted.length);
				}
			}
		}
		
		g.setColor(new Color(26, 184, 160));
		for(int type = 0; type < uData.timeAverageSum.length; type++){
			g.drawString(uData.typeText[type] + ": ", x2, y2 + (type + 1)*yspacing);
			for(int difficulty = 0; difficulty < uData.timeAverageSum[type].length; difficulty++){
				try{
					double time = Math.round(uData.timeAverageSum[type][difficulty]/1000/uData.timeAverageCount[type][difficulty] * 1000)/1000.0;
				g.drawString(""+  time + "s", x2 + (difficulty + 1)*xspacing + fnt1.getSize() * 5, y2 + (type + 1)*yspacing);
				}catch(ArithmeticException e){
					g.drawString(""+  0 + "s", x2 + (difficulty + 1)*xspacing + fnt1.getSize() * 5, y2 + (type + 1)*yspacing);
				}
				if(type == uData.questionsCompleted.length - 1){//when on the last iteration, print the headers and the vertical lines
					g.drawString(uData.difficultyText[difficulty], x2 + (difficulty + 1)*xspacing + fnt1.getSize() * 5, y2); //draw dif
					//g.drawLine((int)(x1 + (xspacing + 1) * difficulty  + fnt1.getSize() * 5.5), y2 + yspacing, 
						//	(int)(x1 + (xspacing + 1) * difficulty  + fnt1.getSize() * 5.5), y2 + yspacing * uData.questionsCompleted.length);
				}
			}
		}
		
		g.setColor(new Color(168, 50, 107));
		for(int type = 0; type < uData.recordTimeIndividual.length; type++){
			g.drawString(uData.typeText[type] + ": ", x2, y3 + (type + 1)*yspacing);
			for(int difficulty = 0; difficulty < uData.recordTimeIndividual[type].length; difficulty++){
				double time = uData.recordTimeIndividual[type][difficulty]/1000.0;
				if(time == 0)
					g.drawString("--" + "s", x2 + (difficulty + 1)*xspacing + fnt1.getSize() * 5, y3 + (type + 1)*yspacing);
				else
					g.drawString(time + "s", x2 + (difficulty + 1)*xspacing + fnt1.getSize() * 5, y3 + (type + 1)*yspacing);
				if(type == uData.questionsCompleted.length - 1){//when on the last iteration, print the headers and the vertical lines
					g.drawString(uData.difficultyText[difficulty], x2 + (difficulty + 1)*xspacing + fnt1.getSize() * 5, y3); //draw dif
					//g.drawLine((int)(x1 + (xspacing + 1) * difficulty  + fnt1.getSize() * 5.5), y2 + yspacing, 
						//	(int)(x1 + (xspacing + 1) * difficulty  + fnt1.getSize() * 5.5), y2 + yspacing * uData.questionsCompleted.length);
				}
		
			}
		}
		
		
		
		g.setColor(Color.black);
		g2d.draw(HomePage);
		g2d.draw(prevSlide);
		
		g2d.setFont(fnt1);
		g.drawString("Home", HomePage.x + HomePage.width/4, (int)(HomePage.y + HomePage.height * 0.85));
		g.drawString("Last <=", prevSlide.x + 6, (int)(prevSlide.y + fnt1.getSize()*0.87));
	}
	
	public void renderFrame(Graphics g){
		//on intermediate pages
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(Color.black);
		g2d.draw(HomePage);
		g2d.draw(nextSlide);
		g2d.draw(prevSlide);
		
		g2d.setFont(fnt1);
		g.drawString("Home", HomePage.x + HomePage.width/4, (int)(HomePage.y + HomePage.height * 0.85));
		g.drawString("Next =>", nextSlide.x + 6, (int)(nextSlide.y + fnt1.getSize()*0.87));
		g.drawString("Last <=", prevSlide.x + 6, (int)(prevSlide.y + fnt1.getSize()*0.87));
	}

	public int getPageIndex(){return pageIndex;}
	public void setPageIndex(int hi){pageIndex = hi;}
	
	public void setSelectedAchievement(Achievement ach) {achievementSelected = ach;}
	public ArrayList<ArrayList<Achievement>> getVanillaAchievementList(){return vanillaAchievementList;}
	
	public void updateAchievementsWithUserData(){
		for(int type = 0; type < vanillaAchievementList.size(); type++) {
			for(int operation = 0; operation < vanillaAchievementList.get(type).size(); operation++) {
				vanillaAchievementList.get(type).get(operation).setCurrentStage(uData.vanillaAchLevel[type][operation]);
			}
		}
	}

}
