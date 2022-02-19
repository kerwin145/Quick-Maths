import java.util.ArrayList;

// 
// Decompiled by Procyon v0.5.36
// 

public class Achievement
{
    private static final long serialVersionUID = 1L;
    String displayText;
    ArrayList<String> explanation;
    int stages;
    int currentStage;
    String[] romanNumerals;
    
    public Achievement(final String displayText, final String explanation) {
        this.explanation = new ArrayList<String>();
        this.currentStage = 0;
        this.romanNumerals = new String[] { "I", "II", "III", "IV", "V" };
        this.displayText = displayText;
        this.explanation.add(explanation);
        this.stages = 0;
    }
    
    public Achievement(final String displayText, final ArrayList<String> explanation, final int stages) {
        this.explanation = new ArrayList<String>();
        this.currentStage = 0;
        this.romanNumerals = new String[] { "I", "II", "III", "IV", "V" };
        this.displayText = displayText;
        this.explanation = explanation;
        this.stages = stages;
    }
    
    public int getCurrentStage() {
        return this.currentStage;
    }
    
    public void setCurrentStage(final int cStage) {
        this.currentStage = cStage;
    }
    
    public void updateCurrentStage() {
        ++this.currentStage;
    }
    
    public int getStages() {
        return this.stages;
    }
    
    public String getDisplayText() {
        if (this.currentStage < this.stages) {
            return String.valueOf(this.displayText) + " " + this.romanNumerals[this.currentStage];
        }
        return "**" + this.displayText + "**";
    }
    
    public void setDisplayText(final String displayText) {
        this.displayText = displayText;
    }
    
    public void setExplanation(final ArrayList<String> explanation) {
        this.explanation = explanation;
    }
    
    public String getExplanation() {
        if (this.currentStage < this.stages) {
            return this.explanation.get(this.currentStage);
        }
        return "COMPLETE!";
    }
    
    public void setExplanation(final String explanation) {
        this.explanation.set(0, explanation);
    }
}
