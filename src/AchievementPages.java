import k_Methods.stringGraphics;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.36
// 

public class AchievementPages
{
    GUI gui;
    UserData uData;
    public int pageIndex;
    public int numPages;
    public Rectangle HomePage;
    public Rectangle nextSlide;
    public Rectangle prevSlide;
    public Rectangle achDescription;
    Font fnt0;
    Font fnt1;
    Font fntNormal;
    Font fntSmall;
    Font fntSmallBold;
    int x1;
    int y1;
    int y2;
    int yspacing;
    int ySpacing2;
    int xspacing;
    int xSpacing2;
    int y3;
    int x2;
    private ArrayList<ArrayList<String>> vanillaAchievementExplList;
    private String[][] vanillaAchievementNameList;
    public ArrayList<ArrayList<Achievement>> vanillaAchievementList;
    private Achievement achievementSelected;
    public ArrayList<ArrayList<Rectangle>> achievementButtons;
    private int achStage;
    
    public AchievementPages(final GUI gui) {
        this.pageIndex = 0;
        this.numPages = 4;
        this.HomePage = new Rectangle(600 * 2 - 140, 15, 120, 20);
        this.nextSlide = new Rectangle(600 * 2 - 140, 333 * 2 - 50, 90, 30);
        this.prevSlide = new Rectangle(40, 333 * 2 - 50, 90, 30);
        this.achDescription = new Rectangle(60, 333 * 2 - 60, 600 * 2 - 240, 40);
        this.fnt0 = new Font("Arial", 2, 333 * 2 / 18);
        this.fnt1 = new Font("Garamond", 1, this.HomePage.height);
        this.fntNormal = new Font("Garamond", 0, 30);
        this.fntSmall = new Font("Garamond", 0, 15);
        this.fntSmallBold = new Font("Garamond", 1, 15);
        this.x1 = 40;
        this.y1 = 80;
        this.y2 = 333 * 2 / 6;
        this.yspacing = (int)(this.fntNormal.getSize() * 1.5);
        this.ySpacing2 = 120;
        this.xspacing = 50;
        this.xSpacing2 = 600 * 2 / 4;
        this.y3 = this.y2 + this.yspacing * 6;
        this.x2 = this.x1 + 600 * 2 / 2;
        this.vanillaAchievementExplList = new ArrayList<ArrayList<String>>();
        this.vanillaAchievementNameList = new String[][] { { "Addition Amateur", "Simple Subtraction", "Multiplication Mountain Man", "Delightful Division" }, { "Addition Apprentice", "Superb Subtraction", "Mischievous Multiplication", "Division Derby" }, { "Adept Addition", "Subtraction Specialist", "Mega Multiplication", "Divine Division" }, { "Addition Afficianado", "Subtraction Senseii", "Multiplication Master", "Division Devotee" } };
        this.vanillaAchievementList = new ArrayList<ArrayList<Achievement>>();
        this.achievementSelected = null;
        this.achievementButtons = new ArrayList<ArrayList<Rectangle>>();
        this.gui = gui;
        this.uData = gui.getUdata();
        for (int type = 0; type < 4; ++type) {
            for (int operation = 0; operation < 4; ++operation) {
                this.vanillaAchievementExplList.add(new ArrayList<String>());
            }
        }
        String operationText = "";
        String difficultyText = "";
        String lengthText = "";
        String scoreText = "";
        for (int i = 0; i < this.vanillaAchievementExplList.size(); ++i) {
            for (int j = 0; j < 3; ++j) {
                lengthText = " " + gui.getResultsPage().getAchCheck().vanillaSetLengthReq[j] + " ";
                scoreText = " " + (int)(gui.getResultsPage().getAchCheck().vanillaSetScoreReq[j] * 100.0) + ".";
                if (i / 4 == 0) {
                    difficultyText = " EASY ";
                }
                if (i / 4 == 1) {
                    difficultyText = " MEDIUM ";
                }
                if (i / 4 == 2) {
                    difficultyText = " HARD ";
                }
                if (i / 4 == 3) {
                    difficultyText = " INSANE ";
                }
                if (i % 4 == 0) {
                    operationText = " an ADDITION ";
                }
                else if (i % 4 == 1) {
                    operationText = " a SUBTRACTION ";
                }
                else if (i % 4 == 2) {
                    operationText = " a MULTIPLICATION ";
                }
                else if (i % 4 == 3) {
                    operationText = " a DIVISION ";
                }
                final String result = "Complete" + operationText + "set at" + difficultyText + "difficulty with at least" + lengthText + "problems and a score of at least" + scoreText;
                this.vanillaAchievementExplList.get(i).add(result);
            }
        }
        for (int type2 = 0; type2 < 4; ++type2) {
            final ArrayList<Achievement> tempAchievementList = new ArrayList<Achievement>();
            for (int operation2 = 0; operation2 < 4; ++operation2) {
                tempAchievementList.add(new Achievement(this.vanillaAchievementNameList[type2][operation2], this.vanillaAchievementExplList.get(type2 * 4 + operation2), 3));
            }
            this.vanillaAchievementList.add(tempAchievementList);
        }
        for (int type3 = 0; type3 < this.vanillaAchievementList.size(); ++type3) {
            final ArrayList<Rectangle> tempButtonList = new ArrayList<Rectangle>();
            for (int operation3 = 0; operation3 < this.vanillaAchievementList.get(type3).size(); ++operation3) {
                tempButtonList.add(new Rectangle(this.x1 + this.xSpacing2 * type3, this.y2 + this.ySpacing2 * operation3, 50, 50));
                this.vanillaAchievementList.get(type3).get(operation3).setCurrentStage(this.uData.vanillaAchLevel[type3][operation3]);
            }
            this.achievementButtons.add(tempButtonList);
        }
    }
    
    public void renderPage1(final Graphics g) {
        final Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.black);
        g.setFont(this.fnt0);
        g.drawString("Medals: Vanilla ", this.x1, this.y1);
        g.setFont(this.fntSmall);
        for (int type = 0; type < this.vanillaAchievementList.size(); ++type) {
            for (int operation = 0; operation < this.vanillaAchievementList.get(type).size(); ++operation) {
                this.achStage = this.vanillaAchievementList.get(type).get(operation).getCurrentStage();
                if (this.achStage == 0) {
                    g.setColor(Color.gray);
                }
                if (this.achStage == 1) {
                    g.setColor(Color.green);
                }
                if (this.achStage == 2) {
                    g.setColor(Color.blue);
                }
                if (this.achStage == 3) {
                    g.setColor(Color.magenta);
                }
                g2d.draw(this.achievementButtons.get(type).get(operation));
                g.setColor(Color.black);
                g.drawString(this.vanillaAchievementList.get(type).get(operation).getDisplayText(), this.achievementButtons.get(type).get(operation).x + this.achievementButtons.get(type).get(operation).width + 5, this.achievementButtons.get(type).get(operation).y + this.achievementButtons.get(type).get(operation).height / 2);
            }
        }
        g2d.draw(this.achDescription);
        if (this.achievementSelected != null) {
            stringGraphics.drawStringCentered(String.valueOf(this.achievementSelected.getDisplayText()) + ": " + this.achievementSelected.getExplanation(), this.achDescription, g);
        }
        else {
            stringGraphics.drawStringCentered("Click on the achievement icon to view its description!", this.achDescription, g);
        }
        g.setColor(Color.black);
        g2d.draw(this.HomePage);
        g2d.draw(this.nextSlide);
        g2d.setFont(this.fnt1);
        stringGraphics.drawStringCentered("Home", this.HomePage, g);
        stringGraphics.drawStringCentered("Next =>", this.nextSlide, g);
    }
    
    public void renderPage2(final Graphics g) {
        g.setFont(this.fnt0);
        g.drawString("Medals: Special", this.x1, this.y1);
        this.renderFrame(g);
    }
    
    public void renderPage3(final Graphics g) {
        g.setFont(this.fnt0);
        g.drawString("Medals: Potpourri", this.x1, this.y1);
        this.renderFrame(g);
    }
    
    public void renderStats(final Graphics g) {
        final Graphics2D g2d = (Graphics2D)g;
        g.setFont(this.fnt0);
        g.drawString("Stats I: ", this.x1, this.y1);
        g.setFont(this.fntSmallBold);
        g.drawString("Question Completed", this.x1, this.y2);
        g.drawString("Question Correct", this.x1, this.y3);
        g.drawString("Average Times", this.x2, this.y2);
        g.drawString("Best Question Times", this.x2, this.y3);
        g.setFont(this.fntSmall);
        g.setColor(new Color(0, 115, 168));
        for (int type = 0; type < this.uData.questionsCompleted.length; ++type) {
            g.drawString(String.valueOf(this.uData.typeText[type]) + ": ", this.x1, this.y2 + (type + 1) * this.yspacing);
            for (int difficulty = 0; difficulty < this.uData.questionsCompleted[type].length; ++difficulty) {
                g.drawString(new StringBuilder().append(this.uData.questionsCompleted[type][difficulty]).toString(), this.x1 + (difficulty + 1) * this.xspacing + this.fnt1.getSize() * 5, this.y2 + (type + 1) * this.yspacing);
                if (type == this.uData.questionsCompleted.length - 1) {
                    g.drawString(this.uData.difficultyText[difficulty], this.x1 + (difficulty + 1) * this.xspacing + this.fnt1.getSize() * 5, this.y2);
                }
            }
        }
        g.setColor(new Color(0, 168, 56));
        for (int type = 0; type < this.uData.questionsCorrect.length; ++type) {
            g.drawString(String.valueOf(this.uData.typeText[type]) + ": ", this.x1, this.y3 + (type + 1) * this.yspacing);
            for (int difficulty = 0; difficulty < this.uData.questionsCorrect[type].length; ++difficulty) {
                g.drawString(new StringBuilder().append(this.uData.questionsCorrect[type][difficulty]).toString(), this.x1 + (difficulty + 1) * this.xspacing + this.fnt1.getSize() * 5, this.y3 + (type + 1) * this.yspacing);
                if (type == this.uData.questionsCorrect.length - 1) {
                    g.drawString(this.uData.difficultyText[difficulty], this.x1 + (difficulty + 1) * this.xspacing + this.fnt1.getSize() * 5, this.y3);
                }
            }
        }
        g.setColor(new Color(26, 184, 160));
        for (int type = 0; type < this.uData.timeAverageSum.length; ++type) {
            g.drawString(String.valueOf(this.uData.typeText[type]) + ": ", this.x2, this.y2 + (type + 1) * this.yspacing);
            for (int difficulty = 0; difficulty < this.uData.timeAverageSum[type].length; ++difficulty) {
                try {
                    final double time = Math.round((float)(this.uData.timeAverageSum[type][difficulty] / this.uData.timeAverageCount[type][difficulty])) / 1000.0;
                    g.drawString(time + "s", this.x2 + (difficulty + 1) * this.xspacing + this.fnt1.getSize() * 5, this.y2 + (type + 1) * this.yspacing);
                }
                catch (ArithmeticException e) {
                    g.drawString("0s", this.x2 + (difficulty + 1) * this.xspacing + this.fnt1.getSize() * 5, this.y2 + (type + 1) * this.yspacing);
                }
                if (type == this.uData.questionsCompleted.length - 1) {
                    g.drawString(this.uData.difficultyText[difficulty], this.x2 + (difficulty + 1) * this.xspacing + this.fnt1.getSize() * 5, this.y2);
                }
            }
        }
        g.setColor(new Color(168, 50, 107));
        for (int type = 0; type < this.uData.recordTimeIndividual.length; ++type) {
            g.drawString(String.valueOf(this.uData.typeText[type]) + ": ", this.x2, this.y3 + (type + 1) * this.yspacing);
            for (int difficulty = 0; difficulty < this.uData.recordTimeIndividual[type].length; ++difficulty) {
                final double time = this.uData.recordTimeIndividual[type][difficulty] / 1000.0;
                if (time == 0.0) {
                    g.drawString("--s", this.x2 + (difficulty + 1) * this.xspacing + this.fnt1.getSize() * 5, this.y3 + (type + 1) * this.yspacing);
                }
                else {
                    g.drawString(String.valueOf(time) + "s", this.x2 + (difficulty + 1) * this.xspacing + this.fnt1.getSize() * 5, this.y3 + (type + 1) * this.yspacing);
                }
                if (type == this.uData.questionsCompleted.length - 1) {
                    g.drawString(this.uData.difficultyText[difficulty], this.x2 + (difficulty + 1) * this.xspacing + this.fnt1.getSize() * 5, this.y3);
                }
            }
        }
        g.setColor(Color.black);
        g2d.draw(this.HomePage);
        g2d.draw(this.prevSlide);
        g2d.setFont(this.fnt1);
        stringGraphics.drawStringCentered("Home", this.HomePage, g);
        stringGraphics.drawStringCentered("Last <=", this.prevSlide, g);
    }
    
    public void renderFrame(final Graphics g) {
        final Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.black);
        g2d.draw(this.HomePage);
        g2d.draw(this.nextSlide);
        g2d.draw(this.prevSlide);
        g2d.setFont(this.fnt1);
        stringGraphics.drawStringCentered("Home", this.HomePage, g);
        stringGraphics.drawStringCentered("Next =>", this.nextSlide, g);
        stringGraphics.drawStringCentered("Last <=", this.prevSlide, g);
    }
    
    public int getPageIndex() {
        return this.pageIndex;
    }
    
    public void setPageIndex(final int hi) {
        this.pageIndex = hi;
    }
    
    public void setSelectedAchievement(final Achievement ach) {
        this.achievementSelected = ach;
    }
    
    public ArrayList<ArrayList<Achievement>> getVanillaAchievementList() {
        return this.vanillaAchievementList;
    }
    
    public void updateAchievementsWithUserData() {
        for (int type = 0; type < this.vanillaAchievementList.size(); ++type) {
            for (int operation = 0; operation < this.vanillaAchievementList.get(type).size(); ++operation) {
                this.vanillaAchievementList.get(type).get(operation).setCurrentStage(this.uData.vanillaAchLevel[type][operation]);
            }
        }
    }
}
