package src.main;
import k_Methods.MoColors;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Graphics;
import k_Methods.RectanglePlus;
import k_Methods.Rectangle_;
import k_Methods.Rectangle_.textPosition;
import k_Methods.stringGraphics;

public class NumberTextField
{
    private int x;
    private int y;
    private int width;
    private int height;
    private String text;
    private Rectangle_ box;
    boolean allowNegatives;
    private boolean inFocus;
    
    NumberTextField(final int x, final int y, final int width, final int height, final String text, final boolean allowNegatives) {
        this.inFocus = false;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.allowNegatives = allowNegatives;
        this.box = new Rectangle_(x, y, width, height);
        box.setBorderThickness(2);
        box.addBackgroundColor(MoColors.white);
        box.setBackgroundOpacity(.2);
    }
    
    public void render(final Graphics g, final boolean isOn) {
        final Font fnt0 = new Font("Garamond", 0, this.height);
        final Graphics2D g2d = (Graphics2D)g;
        g.setFont(fnt0);
        if (isOn) {
        	box.setBorderColor(Color.white);
            //g.setColor(Color.white);
        }
        else {
        	box.setBorderColor(MoColors.silver);
            //g.setColor(MoColors.silver);
        }
        stringGraphics.drawStringCentered(this.text, box, textPosition.left, g);
        
        if (inFocus) {
        	box.setBorderColor(MoColors.lightGreen);
        	//g.setColor(Color.green);
        }
        else {
        	box.setBorderColor(MoColors.silver);
            //g.setColor(MoColors.silver);
        }
        
        box.draw(g2d);
    }
    
    public void updateText(final int keyCode) {
        if (this.inFocus) {
            switch (keyCode) {
                case 48: {
                	appendNum(0);
                    break;
                }
                case 49: {
                	appendNum(1);
                    break;
                }
                case 50: {
                	appendNum(2);
                    break;
                }
                case 51: {
                	appendNum(3);
                    break;
                }
                case 52: {
                	appendNum(4);
                    break;
                }
                case 53: {
                	appendNum(5);
                    break;
                }
                case 54: {
                	appendNum(6);
                    break;
                }
                case 55: {
                	appendNum(7);
                    break;
                }
                case 56: {
                	appendNum(8);
                    break;
                }
                case 57: {
                	appendNum(9);
                    break;
                }
                case 8: {
                    if (this.text.length() > 0) {
                        this.text = this.text.substring(0, this.text.length() - 1);
                        break;
                    }
                    break;
                }
                case 45: {
                    if (!this.allowNegatives) {
                        break;
                    }
                    if (this.text.length() == 0) {
                        this.text = "-";
                        break;
                    }
                    if (this.text.substring(0, 1).equals("-")) {
                        this.text = this.text.substring(1, this.text.length());
                        break;
                    }
                    this.text = "-" + this.text;
                    break;
                }
                case 96: {
                	appendNum(0);
                    break;
                }
                case 97: {
                	appendNum(1);
                    break;
                }
                case 98: {
                	appendNum(2);
                    break;
                }
                case 99: {
                	appendNum(3);
                    break;
                }
                case 100: {
                	appendNum(4);
                    break;
                }
                case 101: {
                	appendNum(5);
                    break;
                }
                case 102: {
                	appendNum(6);
                    break;
                }
                case 103: {
                	appendNum(7);
                	break;
                }
                case 104: {
                	appendNum(8);
                	break;
                }
                case 105: {
                	appendNum(9);
                    break;
                }
                case 110: {
                    if (this.text.length() > 0) {
                        this.text = this.text.substring(0, this.text.length() - 1);
                        break;
                    }
                    break;
                }
                case 109: {
                    if (!this.allowNegatives) {
                        break;
                    }
                    if (this.text.length() == 0) {
                        this.text = "-";
                        break;
                    }
                    if (this.text.substring(0, 1).equals("-")) {
                        this.text = this.text.substring(1, this.text.length());
                        break;
                    }
                    this.text = "-" + this.text;
                    break;
                }
            }
            if (this.text.length() != 0) {
                if (this.text.substring(0, 1).equals("0") && Integer.parseInt(this.text) != 0) {
                    for (int i = 0; i < this.text.length(); ++i) {
                        if (!this.text.substring(i, i + 1).equals("0")) {
                            this.text = this.text.substring(i, this.text.length());
                            i = this.text.length();
                        }
                    }
                }
                if (this.text.substring(0, 1).equals("-")) {
                    for (int i = 1; i < this.text.length(); ++i) {
                        if (!this.text.substring(i, i + 1).equals("0")) {
                            this.text = "-" + this.text.substring(i, this.text.length());
                            i = this.text.length();
                        }
                    }
                }
            }
        }
    }
    
    private void appendNum(int num) {
    	try{
    		if(Math.abs(Integer.parseInt(text + ""  + num)) <= Integer.MAX_VALUE)
        		this.text += num;
    	}catch(Exception e){}
    }
    public void attemptFocus(final int x, final int y) {
        if (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height) {
            this.inFocus = true;
        }
        else {
            this.inFocus = false;
        }
    }
    
    public boolean getFocus() {
        return this.inFocus;
    }
    
    public int retrieveNum() {
        try {
            return Integer.parseInt(this.text);
        }
        catch (NumberFormatException nfe) {
            return 0;
        }
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
}
