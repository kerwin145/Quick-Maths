import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class NumberTextField {
	
	/*wierd form of textfield.
	 * It will only register numbers, and will take inputs from the main class. 
	 * In focus and out of focus will also be determined by main class
	 */


	private int x, y, width, height;
	private String text;
	private Rectangle borders;
	boolean allowNegatives;

	private boolean inFocus = false;

	NumberTextField(int x, int y, int width, int height, String text, boolean allowNegatives){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.allowNegatives = allowNegatives;
		borders = new Rectangle(x, y, width, height);
	}

	public void render(Graphics g){
		Font fnt0 = new Font("Garamond", Font.PLAIN, height);

		Graphics2D g2d = (Graphics2D)g;
		
		if (inFocus) g.setColor(Color.green);
		else g.setColor(Color.gray);
		
		g2d.draw(borders);
		g.setColor(Color.black);
		g.setFont(fnt0);
		g.drawString(text, x + 2, (int)(y + height * 0.87));
	}
	
	public void updateText(int keyCode) {
		if (inFocus) {
			switch(keyCode) {
			case 48: text += "0";
				break;
			case 49: text += "1";
				break;
			case 50: text += "2";
				break;
			case 51: text += "3";
				break;
			case 52: text += "4";
				break;
			case 53: text += "5";
				break;
			case 54: text += "6";
				break;
			case 55: text += "7";
				break;
			case 56: text += "8";
				break;
			case 57: text += "9";
				break;
			case 8: if(text.length() > 0) text = text.substring(0, text.length() - 1); //backspace
				break;
			case 45: //"-"
				if(allowNegatives) {
					if (text.length() == 0) text = "-"; 
					else if(text.substring(0, 1).equals("-")) text = text.substring(1, text.length()); //if number is negative, make the number positive
					else text = "-" + text; //add negative to the beginning
				}
				break;
			case 96: text += "0";
				break;
			case 97: text += "1";
				break;
			case 98: text += "2";
				break;
			case 99: text += "3";
				break;
			case 100: text += "4";
				break;
			case 101: text += "5";
			break;
			case 102: text += "6";
				break;
			case 103: text += "7";
				break;
			case 104: text += "8";
				break;
			case 105: text += "9";
				break;
			case 110: if(text.length() > 0) text = text.substring(0, text.length() - 1); //backspace
				break;
			case 109: //"-"
				if(allowNegatives) {
					if (text.length() == 0) text = "-"; 
					else if(text.substring(0, 1).equals("-")) text = text.substring(1, text.length()); //if number is negative, make the number positive
					else text = "-" + text; //add negative to the beginning
				}
				break;
			}//switch
			
			//delete the zero in front if the number is greater than 0
			if((text.length() != 0)) {
				if(text.substring(0,1).equals("0") && (Integer.parseInt(text) != 0)) {
					for(int i = 0; i < text.length(); i++) {
						if(!text.substring(i, i+1).equals("0")) {//if the number at i is not 0, then we know that that is where the real number starts. 
							text = text.substring(i, text.length());//delete everything before
							i = text.length();//exit
						}
					}//for
				}//if starts with 0
				if(text.substring(0,1).equals("-")) {
					for(int i = 1; i < text.length(); i++) {
						if(!text.substring(i, i+1).equals("0")) {//if the number at i is not 0, then we know that that is where the real number starts. 
							text = "-" + text.substring(i, text.length());//delete everything before
							i = text.length();//exit
						}
					}//for
				}//if starts with -
			}
	
		}//infocus
	}
	
	public void attemptFocus(int x, int y) {
		if ((x > this.x && x < this.x + width) && (y > this.y && y < this.y + height)) {
			inFocus = true;
			//System.out.println("FOCUSED");
		}
		else 
			inFocus = false;
		
		//System.out.println("INPUTS: (" + x + ", " + y + ") Bounds X: " + this.x + " WIDTH: " + width + " Y: " + this.y + " HEIGHT: " + height + inFocus);
	}
	
	public boolean getFocus() {
		return inFocus;
	}
	
	public int retrieveNum() {
		try {return Integer.parseInt(text);}
		catch (NumberFormatException nfe){return 0;}
		finally {}
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
