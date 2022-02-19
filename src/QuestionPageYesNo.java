import java.awt.Graphics2D;
import java.awt.Graphics;
import k_Methods.MoColors;
import k_Methods.RectanglePlus;
import java.awt.Color;


public class QuestionPageYesNo extends QuestionPageNumber
{
    Color[] yesNoColors1;
    Color[] yesNoColors2;
    Color[] yesNoBorderColors;
    Color[] yesNoFontColors;
    public RectanglePlus yes;
    public RectanglePlus no;
    public int yesNo;
    public boolean autoConfirm;
    Color[] autoConfirmColor1;
    Color[] autoConfirmColor2;
    Color[] autoConfirmBorderColor;
    Color[] autoConfirmTextColor;
    public RectanglePlus AutoConfirm;
    boolean displayingAnswer;
    
    public QuestionPageYesNo(final GUI gui) {
        super(gui);
        this.yesNoColors1 = new Color[] { MoColors.deepSkyBlue, MoColors.greenYellow, MoColors.greenYellow, MoColors.orangeRed };
        this.yesNoColors2 = new Color[] { MoColors.cadetBlue, MoColors.aqua, MoColors.limeGreen, MoColors.gray };
        this.yesNoBorderColors = new Color[] { MoColors.white, MoColors.chartreuse, MoColors.greenYellow, MoColors.fireBrick };
        this.yesNoFontColors = new Color[] { MoColors.white };
        this.yes = new RectanglePlus(this.fnt0.getSize(), 333 * 2 / 2, 120, 50, this.yesNoColors1, this.yesNoColors2, true, RectanglePlus.gradientFormat.horizontal, this.yesNoBorderColors, this.fntSplash, this.yesNoFontColors, "Yes");
        this.no = new RectanglePlus(this.fnt0.getSize() * 2 + this.yes.width, 333 * 2 / 2, 120, 50, this.yesNoColors1, this.yesNoColors2, true, RectanglePlus.gradientFormat.horizontal, this.yesNoBorderColors, this.fntSplash, this.yesNoFontColors, "No");
        this.yesNo = -1;
        this.autoConfirm = false;
        this.autoConfirmColor1 = new Color[] { MoColors.deepSkyBlue, MoColors.dodgerBlue };
        this.autoConfirmColor2 = new Color[] { MoColors.lightBlue, MoColors.aqua };
        this.autoConfirmBorderColor = new Color[] { Color.gray, MoColors.chartreuse };
        this.autoConfirmTextColor = new Color[] { Color.white, Color.white };
        this.AutoConfirm = new RectanglePlus((int)(this.InfoBox.x + this.InfoBox.width * 1.2), this.InfoBox.y, 200, this.InfoBox.height, this.autoConfirmColor1, this.autoConfirmColor2, true, RectanglePlus.gradientFormat.horizontal, this.autoConfirmBorderColor, this.fnt3, this.autoConfirmTextColor, "Auto Confirm");
        this.displayingAnswer = false;
    }
    
    @Override
    public void render(final Graphics g) {
        final Graphics2D g2d = (Graphics2D)g;
        this.renderNotQuestion(g);
        if (this.question != null) {
            g.setColor(Color.white);
            g.setFont(this.fnt0);
            g.drawString(this.question.getQuestionText(), this.fnt0.getSize(), 333 * 2 / 2 - this.fnt0.getSize());
            if (!this.displayingAnswer) {
                if (this.yesNo == 1) {
                    this.yes.setColorState(1);
                    this.no.setColorState(0);
                }
                else if (this.yesNo == 0) {
                    this.yes.setColorState(0);
                    this.no.setColorState(1);
                }
                else {
                    this.yes.setColorState(0);
                    this.no.setColorState(0);
                }
            }
            this.yes.draw(g2d);
            this.no.draw(g2d);
        }
        if (this.autoConfirm) {
            this.AutoConfirm.setColorState(1);
        }
        else {
            this.AutoConfirm.setColorState(0);
        }
        this.AutoConfirm.draw(g2d);
        if (this.renderHelp) {
            this.renderHelp(g);
        }
    }
    
    @Override
    public void tick() {
        if (this.autoConfirm && this.yesNo != -1 && (!this.askForConfirm || this.warned)) {
            this.submitAnswer();
        }
        this.baseTick();
    }
    
    @Override
    public void renderHelp(final Graphics g) {
        g.setFont(this.fntNormal);
        g.setColor(Color.gray);
        g.drawString("If turned on, your answers will", this.AutoConfirm.x, this.AutoConfirm.y + this.AutoConfirm.height + this.fntNormal.getSize());
        g.drawString("automatically submit upon", this.AutoConfirm.x, this.AutoConfirm.y + this.AutoConfirm.height + this.fntNormal.getSize() * 2);
        g.drawString("choosing \"Yes\" or \"no\".", this.AutoConfirm.x, this.AutoConfirm.y + this.AutoConfirm.height + this.fntNormal.getSize() * 3);
        g.drawString("Hotkey: Up Arrow \u2191", this.AutoConfirm.x, this.AutoConfirm.y + this.AutoConfirm.height + this.fntNormal.getSize() * 4);
        g.drawString("Hotkey: Left arrow \u2190", this.yes.x, this.yes.y + this.yes.height + this.fntNormal.getSize());
        g.drawString("Hotkey: Right arrow \u2192", this.no.x, this.no.y + this.no.height + this.fntNormal.getSize());
        g.drawString("Submit: Submit and check your answer", (int)(this.submitAnswer.x + this.submitAnswer.width * 1.5), this.submitAnswer.y + this.fntNormal.getSize());
        g.drawString("(Hot key: \"Space\")", (int)(this.submitAnswer.x + this.submitAnswer.width * 1.5), this.submitAnswer.y + this.fntNormal.getSize() * 2);
        if (!this.timerHidden) {
            g.drawString("Timer: Click to hide.", (int)(600 * 2 - this.HomePage.width * 1.8), this.HomePage.y + this.HomePage.height * 5);
        }
        else {
            g.drawString("Timer: Click to show.", (int)(600 * 2 - this.HomePage.width * 1.8), this.HomePage.y + this.HomePage.height * 5);
        }
    }
    
    @Override
    public void submitAnswer() {
        this.renderHelp = false;
        if (this.askForConfirm) {
            if (this.warned) {
                this.askForConfirm = false;
                this.checkAnswer();
            }
            else if (this.currentQuestion <= this.numQuestions || this.endlessQuestions) {
                this.genQuestion();
                this.timerUnPause();
                this.submitColor = Color.black;
                this.askForConfirm = false;
                this.yesNo = -1;
                this.splashText = "";
            }
        }
        else {
            this.checkAnswer();
        }
    }
    
    @Override
    public void checkAnswer() {
        if (this.yesNo == this.question.getAnswer()) {
            this.correct();
        }
        else if (this.yesNo == -1 && !this.warned) {
            this.warn();
        }
        else {
            this.incorrect();
        }
    }
    
    @Override
    public void correct() {
        ++this.numCorrect;
        this.warned = false;
        this.yesNo = -1;
        this.splashText = this.randFromArray(this.correct);
        this.genQuestion();
        this.submitAnswer.setColors(0);
    }
    
    @Override
    public void incorrect() {
        this.warned = false;
        this.yesNo = -1;
        this.displayingAnswer = true;
        if (this.question.getAnswer() == 0) {
            this.yes.setColorState(3);
            this.no.setColorState(2);
            this.splashText = String.valueOf(this.randFromArray(this.incorrect)) + " (It wasn't divisisble.)";
        }
        else if (this.question.getAnswer() == 1) {
            this.yes.setColorState(2);
            this.no.setColorState(3);
            this.splashText = String.valueOf(this.randFromArray(this.incorrect)) + " (It was divisible.)";
        }
        this.submitColor = Color.orange;
        this.timerPause();
        this.askForConfirm = true;
        this.submitAnswer.setColors(1);
    }
    
    public void genQuestion() {
        if (this.currentQuestion == this.numQuestions && !this.endlessQuestions) {
            this.switchToResults();
        }
        this.question = new Question(this.difficulty);
        ++this.currentQuestion;
        this.displayingAnswer = false;
        this.submitAnswer.setColors(0);
    }
    
	public void initializeRound(){
		baseInit(gui.getLevelSelectSpecial());
		gui.getTitlePage().questionPage = gui.getTitlePage().questionPage.SpecialYN;
		endlessQuestions = gui.getLevelSelectSpecial().endlessQuestions;

		genQuestion();

	}

	public void switchToResults(){
		gui.getTitlePage().setSpecialFinished = true;
		gui.getTitlePage().setFinished = true;
		gui.getResultsPage().initializeYN();
		System.out.println("Switching from qPageYN to results");
		gui.State = gui.State.RESULTS;
	}



}
