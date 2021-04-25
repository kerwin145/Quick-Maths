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
	
	public Rectangle HomePage = new Rectangle(gui.WIDTH * gui.SCALE - 140, 15, 120, 20);
	public Rectangle nextSlide = new Rectangle(gui.WIDTH * gui.SCALE - 140, gui.HEIGHT * gui.SCALE - 50, 90, 30);
	public Rectangle prevSlide = new Rectangle(50, gui.HEIGHT * gui.SCALE - 50, 90, 30);
	
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
	
	//vanilla achievements
	Achievement beginnerAdd = new Achievement("Addition Amateur", "Complete an ADDITION set at EASY difficulty with at least 10 problems and finish with a score of at least 60.", false);
	Achievement beginnerSub = new Achievement("Simple Subtraction", "Complete a SUBTRACTION set at EASY difficulty with at least 10 problems and finish with a score of at least 60.", false);
	Achievement beginnerMult = new Achievement("Multiplication Mountain Man", "Complete a MULTIPLICATION set at EASY difficulty with at least 10 problems and finish with a score of at least 60.", false);
	Achievement beginnerDiv = new Achievement("Delightful Division", "Complete a DIVISION set at EASY difficulty with at least 10 problems and finish with a score of at least 60.", false);
		
	Achievement intermediateAdd = new Achievement("Addition Apprentice", "Complete an ADDITION set at MEDIUM difficulty with at least 10 problems and finish with a score of at least 60.", false);
	Achievement intermediateSub = new Achievement("Superb Subtraction", "Complete a SUBTRACTION set at MEDIUM difficulty with at least 10 problems and finish with a score of at least 60.", false);
	Achievement intermediateMult = new Achievement("Mischievous Multiplication", "Complete a MULTIPLICATION set at MEDIUM difficulty with at least 10 problems and finish with a score of at least 60.", false);
	Achievement intermediateDiv = new Achievement("Division ?", "Complete a DIVISION set at MEDIUM difficulty with at least 10 problems and finish with a score of at least 60.", false);

	Achievement proficientAdd = new Achievement("Adept Addition", "Complete an ADDITION set at HARD difficulty with at least 10 problems and finish with a score of at least 60.", false);
	Achievement proficientSub = new Achievement("Subtraction Specialist", "Complete a SUBTRACTION set at HARD difficulty with at least 10 problems and finish with a score of at least 60.", false);
	Achievement proficientMult= new Achievement("Mega Multiplication", "Complete a MULTIPLICATION set at HARD difficulty with at least 10 problems and finish with a score of at least 60.", false);
	Achievement proficientDiv = new Achievement("Divine Division", "Complete a DIVISION set at HARD difficulty with at least 10 problems and finish with a score of at least 60.", false);

	Achievement challengerAdd = new Achievement("Addition Afficianado", "Complete an ADDITION set at INSANE difficulty with at least 10 problems and finish with a score of at least 60.", false);
	Achievement challengerSub = new Achievement("Subtraction Senseii", "Complete a SUBTRACTION set at INSANE difficulty with at least 10 problems and finish with a score of at least 60.", false);
	Achievement challengerMult = new Achievement("Multiplication Master", "Complete a MULTIPLICATION set at INSANE difficulty with at least 10 problems and finish with a score of at least 60.", false);
	Achievement challengerDiv =  new Achievement("Division Devotee", "Complete a DIVISION set at INSANE difficulty with at least 10 problems and finish with a score of at least 60.", false);

	//arangement: outside is achievemt type, inside is specific operation
	public Achievement[][] achievementList = {{beginnerAdd, beginnerSub, beginnerMult, beginnerDiv},
											  {intermediateAdd, intermediateSub, intermediateMult, intermediateDiv},
											  {proficientAdd, proficientSub, proficientMult, proficientDiv},
											  {challengerAdd, challengerSub, challengerMult, challengerDiv}
											 };
	private Achievement achievementSelected = null; //the one selected. It will have its explanations portrayed. 
	
	public ArrayList<Rectangle> achievementButtons = new ArrayList<Rectangle>(); //later, when icons are made, set up a list of corresponding icons 

	public AchievementPages(GUI gui){
		this.gui = gui;
		uData = gui.getUdata();
		
		for(int group = 0; group < achievementList.length; group++) {
			for(int operation = 0; operation < achievementList[group].length; operation++) {
				achievementButtons.add(new Rectangle(x1 + xSpacing2 * group, y2 + ySpacing2 * operation, 50, 50));
				achievementList[group][operation].setUnlocked(uData.vanillaAchObtained[group][operation]);
			}
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
		for(int group = 0; group < achievementList.length; group++) {
			for(int operation = 0; operation < achievementList[group].length; operation++) {
				if(achievementList[group][operation].isUnlocked()) g.setColor(Color.green);
				else g.setColor(Color.gray);
				//to convert index of the 2d achievement list to the 1d arraylist, we will tkae the group * achievementList.length + operation
				g2d.draw(achievementButtons.get(group * achievementList.length + operation));
				g.setColor(Color.black);
				g.drawString(achievementList[group][operation].getDisplayText(), 
						achievementButtons.get(group * achievementList.length + operation).x + achievementButtons.get(group * achievementList.length + operation).width + 5, 
						achievementButtons.get(group * achievementList.length + operation).y + achievementButtons.get(group * achievementList.length + operation).height/2);
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
	
		//have a box at the bottom to display the information of the achievement selected
	}
	
	public void renderPage2(Graphics g) {
		
	}
	
	public void renderStats(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		
		g.setFont(fnt0);
		g.drawString("Stats: ", x1, y1);

		g.setFont(fntSmallBold);
		g.drawString("Question Completed", x1, y2);
		g.drawString("Question Correct", x1, y3);
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
		g.setColor(Color.black);
		g2d.draw(HomePage);
		g2d.draw(prevSlide);
		g2d.setFont(fnt1);
		
		g.drawString("Home", HomePage.x + HomePage.width/4, (int)(HomePage.y + HomePage.height * 0.85));
		g.drawString("<= Prev", prevSlide.x + 6, (int)(prevSlide.y + fnt1.getSize()*0.87));
		
	}
	
	public int getPageIndex(){return pageIndex;}
	public void setPageIndex(int hi){pageIndex = hi;}
	
	public void setSelectedAchievement(Achievement ach) {achievementSelected = ach;}
	public void updateAchievementsWithUserData(){
		for(int group = 0; group < achievementList.length; group++) {
			for(int operation = 0; operation < achievementList[group].length; operation++) {
				achievementList[group][operation].setUnlocked(uData.vanillaAchObtained[group][operation]); 
			}
		}
	}

	
}
