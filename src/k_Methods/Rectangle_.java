package k_Methods;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;


public class Rectangle_ extends Rectangle implements Shape{

	//Here are all the properties of the rectangle	
	ArrayList<String> text = new ArrayList<>(); 
	public enum textPosition {
		top,
		middle,
		left
	}
	
	textPosition textPos = textPosition.middle;
	
	Font font = new Font("Arial", Font.PLAIN, 12);
	
	//COLOR PROPERTIES
	private ArrayList<Color> borderColors = new ArrayList<Color>();
	//this will be a list of colors for a single mode
	//this is the compilation of the color modes.
	private ArrayList<ArrayList<Color>> backgroundColors = new ArrayList<ArrayList<Color>>();
	
	private Color borderDarkened; //Darkned can either be highlighted or selected. The draw will default to these variables 
	private Color[] backgroundDarkened;
	boolean highlighted = false, selected = false; //Selected has if statement priority over highlighted; look at the draw method
	
	Color fontColor;
	
	//the size of backgroundColors and borderColors should be the same, but it isn't critical
	int currentBackgroundColor = 0;
	int currentBorderColor = 0;
	int currentText
	= 0;
	/*An example for the background Color: You have a rectangle with three modes. 
	 * The first will be a gradient from red to blue
	 * Second will be solid purple
	 * Third is green to yellow to red
	 * Each of these modes will be a backgroundColor
	 * These three modes will be put together into the backgroundColors 
	*/
	
	double backgroundOpacity = 255;
	int borderThickness = 1;
	
	gradientFormat gFormat = gradientFormat.none;

    public enum gradientFormat
    {
    	none,
    	horizontal,
    	vertical    
    }
    
    boolean hasDarkenedColors = false;
	
	//the bare-bones constructor
    public Rectangle_(int x, int y, int width, int height){
        super(x, y, width, height);
        

    }
    //the maximum constructor with arraylists
    public Rectangle_(int x, int y, int width, int height, ArrayList<String> text, textPosition textPos, Font font, Color fontColor,
    		ArrayList<Color> borderColors,  ArrayList<ArrayList<Color>> backgroundColors,gradientFormat gFormat, int backgroundOpacity, int borderThickness, boolean hasDarkenedColors){
        super(x, y, width, height);
        
        if(text != null)this.text = text;
        if(textPos != null) this.textPos = textPos;
        if(font != null) this.font = font;
        if(fontColor != null) this.fontColor = fontColor;
        if(borderColors != null) this.borderColors = borderColors;
        if(backgroundColors != null) this.backgroundColors = backgroundColors;
    	if(gFormat != null) this.gFormat = gFormat;
        if(backgroundOpacity >= 0) this.backgroundOpacity = backgroundOpacity*255;
        if(borderThickness >= 0) this.borderThickness = borderThickness;
        this.hasDarkenedColors = hasDarkenedColors;

    }
    
    public Rectangle_(int x, int y, int width, int height, String[] text, textPosition textPos, Font font, Color fontColor,
    		Color[] borderColors,  Color[][] backgroundColors, gradientFormat gFormat, int backgroundOpacity, int borderThickness, boolean hasDarkenedColors){
    	super(x, y, width, height);
    	
        if(text != null)this.setText(text);
    	if(textPos != null) this.textPos = textPos;
    	if(font != null) this.font = font;
    	if(fontColor != null) this.fontColor = fontColor;
    	if(borderColors != null) setBorderColors(borderColors);
    	if(backgroundColors != null) setBackgroundColors(backgroundColors);
    	if(gFormat != null) this.gFormat = gFormat;
    	if(backgroundOpacity >= 0) this.backgroundOpacity = backgroundOpacity*255;
    	if(borderThickness >= 0) this.borderThickness = borderThickness;
        this.hasDarkenedColors = hasDarkenedColors;

    }
    
    public Rectangle_(int x, int y, int width, int height, String text, textPosition textPos, Font font, Color fontColor,
    		Color[] borderColors,  Color[][] backgroundColors, gradientFormat gFormat, int backgroundOpacity, int borderThickness, boolean hasDarkenedColors){
    	super(x, y, width, height);
    	
        if(text != null)this.text.add(text);
    	if(textPos != null) this.textPos = textPos;
    	if(font != null) this.font = font;
    	if(fontColor != null) this.fontColor = fontColor;
    	if(borderColors != null) setBorderColors(borderColors);
    	if(backgroundColors != null) setBackgroundColors(backgroundColors);
    	if(gFormat != null) this.gFormat = gFormat;
    	if(backgroundOpacity >= 0) this.backgroundOpacity = backgroundOpacity*255;
    	if(borderThickness >= 0) this.borderThickness = borderThickness;
        this.hasDarkenedColors = hasDarkenedColors;

    }
    //drawing 
    public void draw(Graphics2D g) {
  
    	//equalize size of background and border arrayList by adding last element until they are the same
    	int maxSize =  Math.max(borderColors.size(), backgroundColors.size());
    	if(borderColors.size() != 0 && backgroundColors.size() != 0) {
	    	while(borderColors.size() < maxSize)
	    		borderColors.add(borderColors.get(borderColors.size()-1));
	    	while(backgroundColors.size() < maxSize)
	    		backgroundColors.add(backgroundColors.get(backgroundColors.size()-1));
    	}//equalize text
    	if(text.size() != 0) {
    		while(text.size() < maxSize)
    			text.add(text.get(text.size()-1));
    	}
    	
    	//To draw, we will check if there is a backgroundColor
    	//Next, we set the mode we want from backgroundColors. 
    	//Thereby, we will draw the colors according to gradient format. 

    	//setting colors for background
    	//check if there is a backgroundColor to set.
    	if(backgroundColors.size() > 0) {
    		//the plural of bgColors refers to the color(s) within 
    		ArrayList<Color> bgColors = backgroundColors.get(currentBackgroundColor);

    		if(bgColors.size() == 1) {
    		  	
    			//darkened colors if statements appear three times in draw background, once in draw border, once in font color 
    	    	if (hasDarkenedColors && highlighted || selected) {
    	    		if (highlighted) 
    	    			g.setColor(getHighlightedColor(bgColors.get(0), backgroundOpacity));
    	    		else 
    	    			g.setColor(getSelectedColor(bgColors.get(0), backgroundOpacity));
    	    	}else //fill an ordinary rectangle
	    			g.setColor(new Color(bgColors.get(0).getRed(), bgColors.get(0).getGreen(), bgColors.get(0).getBlue(), (int)backgroundOpacity));
    	    	//draw
    			g.fillRect(x, y, width, height);

    		}
    		else if(bgColors.size() > 1){ //not just an else, since you want to make sure that mode is not empty

    			int begin, end; //the beginning and ending portion for a group of two colors.
    			int span;
    			int rRange, gRange, bRange; //the second color minus the first
    			Color darkenedColorTop, darkenedColorBottom;
    			int red, green, blue;//correspond to the values of the first color

    			if(gFormat == gradientFormat.horizontal) {
    				//creates gradients from a color and its next color, mapping it to the correct portion of the rectangle
    				for(int i = 0; i < bgColors.size()-1; i++) {
    					
    					begin = x + (int)((double)i / (bgColors.size()-1) * width);
    					end = x + (int)((double)(i+1) / (bgColors.size()-1) * width);
    					span = end-begin;
    					
    					if (hasDarkenedColors && highlighted || selected) {
    	    	    		if (highlighted) {
    	    	    			darkenedColorTop = getHighlightedColor(bgColors.get(i), backgroundOpacity);
    	    	    			darkenedColorBottom = getHighlightedColor(bgColors.get(i+1), backgroundOpacity);
    	    	    		}
    	    	    		else {
    	    	    			darkenedColorTop = getSelectedColor(bgColors.get(i), backgroundOpacity);
	    	    				darkenedColorBottom = getSelectedColor(bgColors.get(i+1), backgroundOpacity);
    	    	    		}
    	    	    		
    	    	    		red = darkenedColorTop.getRed();
	    					green = darkenedColorTop.getGreen();
	    					blue = darkenedColorTop.getBlue();
	    					rRange = darkenedColorBottom.getRed() - red;
	    					gRange = darkenedColorBottom.getGreen() - green;
	    					bRange = darkenedColorBottom.getBlue() - blue;
    	    	    	}else {
	    					red = bgColors.get(i).getRed();
	    					green = bgColors.get(i).getGreen();
	    					blue = bgColors.get(i).getBlue();
	    					rRange = bgColors.get(i+1).getRed() - red;
	    					gRange = bgColors.get(i+1).getGreen() - green;
	    					bRange = bgColors.get(i+1).getBlue() - blue;
    	    	    	}    					
    					
    					//the drawing part
    					for(int j = 0; j < span-1; j++) {
    						g.setColor(new Color((int)(red + rRange * ((double)j/span)),
    								(int)(green + gRange * ((double)j/span)),
    								(int)(blue + bRange * ((double)j/span)),
    								(int)backgroundOpacity));
    	                    g.fillRect(begin+j, y, 2, height);
    					}
    				}
    				    				
    				
    			}else if(this.gFormat == gradientFormat.vertical){

    				for(int i = 0; i < bgColors.size()-1; i++) {
    					begin = y + (int)((double)i / (bgColors.size()-1) * height);
    					end = y + (int)((double)(i+1) / (bgColors.size()-1) * height);
    					span = end-begin;
    					
    					if (hasDarkenedColors && highlighted || selected) {
    	    	    		if (highlighted) {
    	    	    			darkenedColorTop = getHighlightedColor(bgColors.get(i), backgroundOpacity);
    	    	    			darkenedColorBottom = getHighlightedColor(bgColors.get(i+1), backgroundOpacity);
    	    	    		}
    	    	    		else {
    	    	    			darkenedColorTop = getSelectedColor(bgColors.get(i), backgroundOpacity);
	    	    				darkenedColorBottom = getSelectedColor(bgColors.get(i+1), backgroundOpacity);
    	    	    		}
    	    	    		
    	    	    		red = darkenedColorTop.getRed();
	    					green = darkenedColorTop.getGreen();
	    					blue = darkenedColorTop.getBlue();
	    					rRange = darkenedColorBottom.getRed() - red;
	    					gRange = darkenedColorBottom.getGreen() - green;
	    					bRange = darkenedColorBottom.getBlue() - blue;
    	    	    	}else {

	    					red = bgColors.get(i).getRed();
	    					green = bgColors.get(i).getGreen();
	    					blue = bgColors.get(i).getBlue();
	    					rRange = bgColors.get(i+1).getRed() - red;
	    					gRange = bgColors.get(i+1).getGreen() - green;
	    					bRange = bgColors.get(i+1).getBlue() - blue;
    	    	    	}    
    					
    					//the drawing part
    					for(int j = 0; j < span-1; j++) {
    						g.setColor(new Color((int)(red + rRange * ((double)j/span)),
    								(int)(green + gRange * ((double)j/span)),
    								(int)(blue + bRange * ((double)j/span)),
    								(int)backgroundOpacity));
    	                    g.fillRect(x, begin+j, width, 2);
    					}
    				}
    			}
    			else if (gFormat == gradientFormat.none) {
    				g.setColor(new Color(bgColors.get(0).getRed(), bgColors.get(0).getGreen(), bgColors.get(0).getBlue(), (int)backgroundOpacity));
        			g.fillRect(x, y, width, height);
    			}
    		}
    	}

    	//draw border
    	if(borderColors.size() > 0) {
    		
    		if(borderColors.size() > 1) {
    			if (hasDarkenedColors && highlighted || selected) {
    	    		if (highlighted) 
    	    			g.setColor(getHighlightedColor(borderColors.get(currentBorderColor), 1));
    	    		else 
    	    			g.setColor(getSelectedColor(borderColors.get(currentBorderColor), 1));
    	    	}else 
            		g.setColor(borderColors.get(currentBorderColor));
    		}
    		else {
    			if (hasDarkenedColors && highlighted || selected) {
    	    		if (highlighted) 
    	    			g.setColor(getHighlightedColor(borderColors.get(0), 1));
    	    		else 
    	    			g.setColor(getSelectedColor(borderColors.get(0), 1));
    	    	}else 
            		g.setColor(borderColors.get(0));
    		}
    		
    		g.setStroke(new BasicStroke(borderThickness));
    		g.drawRect(x, y, width, height);
    		g.setStroke(new BasicStroke(1));

    	}


    	//draw text
    	if(font != null) { g.setFont(font);}
    	if(fontColor != null) {
    		/*if (highlighted || selected) {
	    		if (highlighted) 
	    			g.setColor(getHighlightedColor(fontColor, 1));
	    		else 
	    			g.setColor(getSelectedColor(fontColor, 1));
	    	}else 
	    	*/
	    		g.setColor(fontColor);
    	}

    	if(text.size() > 0) {
	    	if(textPos == textPosition.top) {
	            stringGraphics.drawStringFlow(text.get(currentText), this.getBounds(), textPosition.top, g);
	    	}else if(textPos == textPosition.middle) {
	            stringGraphics.drawStringFlow(text.get(currentText), this.getBounds(), textPosition.middle, g);
	    	}else if (textPos == textPosition.left) { //centers on y axis
	    		g.drawString(text.get(currentText), x, (int) (y + height));
	    	}
    	}

    }
    
    public void setHasDarkenedColors(boolean hasDarkenedColors) {
		this.hasDarkenedColors = hasDarkenedColors;
	}
    
	private Color getHighlightedColor(Color input, double opacity) {
    	double red, blue, green;
    	if(input.getRed() > 128)
    		red = input.getRed() - 20;
    	else
    		red = input.getRed() + 20;
     	if(input.getGreen() > 128)
    		green = input.getGreen() - 20;
    	else
    		green = input.getGreen() + 20;
     	if(input.getBlue() > 128)
     		blue = input.getBlue() - 20;
     	else
     		blue = input.getBlue() + 20;
     	return new Color((int)red, (int) green, (int) blue, (opacity >= 0 && opacity <= 1) ? (int)(opacity * 255) : input.getAlpha());
    }
    
    //same as highlighted, but we'll change the coefficients. Keeping them their own to avoid overhead within the loop
    private Color getSelectedColor(Color input, double opacity) {
    	double red, blue, green;
    	if(input.getRed() > 128)
    		red = input.getRed() * .75;
    	else
    		red = input.getRed() * 1.33;
     	if(input.getGreen() > 128)
    		green = input.getGreen() * .75;
    	else
    		green = input.getGreen() * 1.33;
     	if(input.getBlue() > 128)
     		blue = input.getBlue() * .75;
     	else
     		blue = input.getBlue() * 1.33;
     	return new Color((int)red, (int) green, (int) blue, (opacity >= 0 && opacity <= 1) ? (int)(opacity * 255) : input.getAlpha());
    }

    
    //attribute editors--------------------------
    public void setText(int pos, String t) {
    	if(pos < this.text.size()) {
        	text.set(pos, t);
    	}else
    		text.add(t);
    }
    
    public void setText(String[] t) {
    	ArrayList<String> newText = new ArrayList<String>();
    	for(String s: t) {
    		newText.add(s);
    	}
    	this.text = newText;
    }
    
    public void setText(ArrayList<String> t) {
    	this.text = t;
    }
    
    public void setText(String t) {
    	text.clear();
    	text.add(t);
    }
    
    public void addText(String t) {
    	text.add(t);
    }
    
    public void appendText(ArrayList<String> t) {	
    	for(String s:t) {
    		text.add(s);
    	}
    }
    public void appendText(String[] t) {
    	for(String s:t) {
    		text.add(s);
    	}
    }
    
    public void setTextPosition(textPosition pos) {
    	textPos = pos;
    }
    
    public void setFont(Font font) {
    	this.font = font;
    }
    
    public void setFontColor(Color color) {
    	fontColor = color;
    }

    public Font getFont() {
    	return font;
    }
    
    //borderColors setters
    public void setBorderColor(Color color) {
		borderColors.clear();
		borderColors.add(color);
    }
    public void setBorderColors(ArrayList<Color> colors) {
    	borderColors = colors;
    }
    public void setBorderColors(Color[] colors) {
    	borderColors.clear();
    	for(Color i: colors) 
    		borderColors.add(i);
    }
    public void addBorderColor(Color color) {
    	borderColors.add(color);
    }
    
    //opacity
    public void setBackgroundOpacity(double opacity) {
    	if(opacity > 1) opacity = 255;
    	else if(opacity < 0)opacity = 0;
    	else backgroundOpacity = opacity * 255;
    }
    //border thickness
    public void setBorderThickness(int thickness) {
    	borderThickness = thickness;
    }
    
    //gradient format 
    public void setGradientFormat(gradientFormat format) {
		gFormat = format;
    }
    
    //set background Colors
    //---
    public void addBackgroundColor(Color color) {
    	ArrayList<Color> backgroundColor = new ArrayList<Color>();	
    	backgroundColor.add(color);
    	backgroundColors.add(backgroundColor);
    }
    public void addBackgroundColor(ArrayList<Color> colors) {
    	backgroundColors.add(colors);
    }
   
    public void addBackgroundColor(Color[] colors) {
    	ArrayList<Color> backgroundColor = new ArrayList<Color>();	

    	for(Color i: colors) {
    		backgroundColor.add(i);
    	}
    	
    	backgroundColors.add(backgroundColor);		
    }
    //---
    //force the colors to have only one background color mode of the parameter 
    public void forceBackgroundColor(Color color) {
    	backgroundColors.clear();
    	addBackgroundColor(color);
    }
    public void forceBackgroundColor(ArrayList<Color> colors) {
    	backgroundColors.clear();
    	addBackgroundColor(colors);
    }
    
    public void forceBackgroundColor(Color[] colors) {
    	backgroundColors.clear();
    	addBackgroundColor(colors);
    }
    //--
    public void setBackgroundColor(int i, Color color) {
    	ArrayList<Color> backgroundColor = new ArrayList<Color>();	
    	if(i < backgroundColors.size()) {
        	backgroundColor.add(color);
    		backgroundColors.set(i, backgroundColor);
    	}else
    		backgroundColors.add(backgroundColor);
    }
    public void setBackgroundColor(int i, ArrayList<Color> colors) {
    	if(i < backgroundColors.size()) 
    		backgroundColors.set(i, colors);
    	else
    	    backgroundColors.add(colors);
    }


    public void setBackgroundColor(int i, Color[] Color) {
    	ArrayList<Color> backgroundColor = new ArrayList<Color>();	
    	if(i < backgroundColors.size()) {
        	for(Color j: Color)
        		backgroundColor.add(j);
        	backgroundColors.set(i, backgroundColor);	
    	}else
    		backgroundColors.add(backgroundColor);

    	

    }
    //--
    public void setBackgroundColors(ArrayList<ArrayList<Color>> colors) {
    	backgroundColors = colors;
    }
    public void setBackgroundColors(Color[][] colors) {
    	for(int i = 0; i<colors.length;i++) {
        	ArrayList<Color> backgroundMode = new ArrayList<Color>();

    		for(int j = 0; j < colors[i].length;j++) {
    			backgroundMode.add(colors[i][j]);
    		}
    		backgroundColors.add(backgroundMode);
    	}
    	//System.out.println(backgroundColors.toString());
    }
    
    //end backgroundcolor setting
    
    public void nextBorderColor() {
    	if(borderColors.size() > 1) {
        	currentBorderColor++;

        	if(currentBorderColor >= borderColors.size())
        		currentBorderColor = 0;
        	
    	}
    }
    
    public void setBackgroundColor(int i) {
    	currentBackgroundColor = i;
    	if(i >= backgroundColors.size())
    		currentBackgroundColor = backgroundColors.size()-1;
    	if(i < 0)
    		currentBackgroundColor = 0;	
    }
    
    public void setBorderColor(int i) {
    	currentBorderColor = i;
    	if(i >= borderColors.size())
    		currentBorderColor = borderColors.size()-1;
    	if(i < 0)
    		currentBorderColor = 0;	
    }
    
    public void nextColors() {
    	if(borderColors.size() > 1 && backgroundColors.size() > 1) {
    		int largerSize = Math.max(borderColors.size(), backgroundColors.size());
    		
    		currentBorderColor++;
    		currentBackgroundColor++;
    		if(currentBackgroundColor >= largerSize)
        		currentBackgroundColor = 0;
    		if(currentBorderColor >= largerSize)
        		currentBorderColor = 0;
    	}
    }
    
    public void setColors(int index) {
    	currentBorderColor = index;
    	currentBackgroundColor = index;
    	currentText = index;
    	
    	if(index >= backgroundColors.size())
    		currentBackgroundColor = backgroundColors.size()-1;
    	
    	if(index >= borderColors.size())
        	currentBorderColor = borderColors.size()-1;
    	
    	if(index >= text.size())
    		currentText = text.size()-1;
    	
    	if(index < 0) {
    		currentBackgroundColor = 0;
    		currentBorderColor = 0;
    		currentText = 0;
    	}

    }
    

    /*
    //Set darkened colors for a given base color (bg colors size has to be 1)
    public void giveDarkenedColor(double darkenedAmount) {
    	if(backgroundColors.size() == 1 && darkenedAmount <= 1) {
        	darkenedAmount = 1 - darkenedAmount;

    		Color baseColor = backgroundColors.get(0);
    		int r = baseColor.getRed();
    		int g = baseColor.getGreen();
    		int b = baseColor.getBlue();
    		
    		backgroundColors.add(new Color((int)(r * darkenedAmount), (int)(g * darkenedAmount), (int)(b * darkenedAmount)));

    		//try the rest of the colors
    		if(backgroundColors2.size() == 1) {
    			baseColor = backgroundColors2.get(0);
        		r = baseColor.getRed();
        		g = baseColor.getGreen();
        		b = baseColor.getBlue();
        		
        		backgroundColors.add(new Color((int)(r * darkenedAmount), (int)(g * darkenedAmount), (int)(b * darkenedAmount)));
    		}
    		if(borderColors.size() == 1) {
    			baseColor = borderColors.get(0);
        		r = baseColor.getRed();
        		g = baseColor.getGreen();
        		b = baseColor.getBlue();
        		
        		borderColors.add(new Color((int)(r * darkenedAmount), (int)(g * darkenedAmount), (int)(b * darkenedAmount)));
   
    		}
    		
    		hasDarkenedColors = true;
    	}
    	
    }
    
    public void removeDarkenedColor() {
    	if(hasDarkenedColors) {
    		//this still works if you end up having more colors after adding
    		//additional colors. The darkened one will still be removed
    		 backgroundColors.remove(1);//remove 2nd item
    		 
    		 if(backgroundColors.size() >= 2){
    			 backgroundColors2.remove(1);
    		 }
    		 if(borderColors.size() >= 2){
    			 borderColors.remove(1);
    		 }
    	}
    	
    }
    */
    //getters
    public int getCurrentBorderColorIndex() {
    	return currentBorderColor;
    }
	public boolean isHighlighted() {
		return highlighted;
	}
	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}
    
	public void setWidthMatchText() {
		if(text.size() > 0) {
			FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
			this.width = (int)(font.getStringBounds(text.get(0), frc).getWidth());
		}
	}
	
	public void setXCenteredTo(Rectangle_ r) {
		x = (r.width-width)/2 + r.x;
	}
    


}
