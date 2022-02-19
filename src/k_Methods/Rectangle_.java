package k_Methods;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.io.Serializable;
import java.util.ArrayList;

import k_Methods.RectanglePlus.gradientFormat;
import k_Methods.Rectangle_.textPosition;

public class Rectangle_ extends Rectangle implements Shape, Serializable{
	
	//the bare-bones constructor
    public Rectangle_(int x, int y, int width, int height){
        super(x, y, width, height);

    }
	
	//Here are all the properties of the rectangle	
	String text = ""; 
	public enum textPosition {
		top,
		middle,
		left
	}
	
	textPosition textPos = textPosition.middle;
	
	Font font = null;
	ArrayList<Color> fontColors = new ArrayList<Color>();
	int currentFontColor = 0;
	
	//list of border and bg colors. Check its setter methods
	/*Requirement if the backgroundColors2 needs backgroundColors1 to not be null
	 * 
	 */
	ArrayList<Color> borderColors = new ArrayList<Color>();
	ArrayList<Color> backgroundColors = new ArrayList<Color>();
	ArrayList<Color> backgroundColors2 = new ArrayList<Color>();//for gradients (only 2 color gradients)
	int currentBackgroundColor = 0;
	int currentBorderColor = 0;
	
	double backgroundOpacity = 1;
	
	int borderThickness = 1;
	
	gradientFormat gFormat = gradientFormat.none;

    public enum gradientFormat
    {
    	none,
    	horizontal,
    	vertical    
    }
    
    boolean hasDarkenedColors = false;
    
    
    //drawing 
    public void draw(Graphics2D g) {
    	
    	//setting colors for background
    	Color c1 = new Color(0,0,0,0), c2 = new Color(0,0,0,0), c_temp; 
    	int red, green, blue;
    	
    	if(backgroundColors.size() > 0) {
			c_temp = backgroundColors.get(currentBackgroundColor);
			red = c_temp.getRed();
			green = c_temp.getGreen();
			blue = c_temp.getBlue();
			c1 = new Color(red,green,blue, (int)(backgroundOpacity*255));  
			
    		//attempt to get the second color
    		if(backgroundColors2.size() > 0){
    			//check if second color exists
    			if(currentBackgroundColor-1 > backgroundColors2.size())
    				c2 = new Color(0,0,0,0);
    			else {
    				c_temp = backgroundColors2.get(currentBackgroundColor);
    				red = c_temp.getRed();
    				green = c_temp.getGreen();
    				blue = c_temp.getBlue();
    				c2 = new Color(red,green,blue, (int)(backgroundOpacity*255));  
    			}
    		}
    		else c2 = new Color(0,0,0,0);
    	}
	    	
    	if(gFormat != gradientFormat.none) {
    		
            int rRange = c1.getRed() - c2.getRed();
            int gRange = c1.getGreen() - c2.getGreen();
            int bRange = c1.getBlue() - c2.getBlue();
            double i = 0.0;
            if (this.gFormat == gradientFormat.vertical) {
                for (double range = this.height - 1; i <= range; ++i) {
                    g.setColor(new Color((int)(c1.getRed() - rRange * (i / range)), (int)(c1.getGreen() - gRange * (i / range)), (int)(c1.getBlue() - bRange * (i / range))));
                    g.fillRect(this.x, this.y + (int)i, this.width, 1);
                }
            }
            else if (this.gFormat == gradientFormat.horizontal) {
                for (double range = this.width; i <= range - 1.0; ++i) {
                    g.setColor(new Color((int)(c1.getRed() - rRange * (i / range)), (int)(c1.getGreen() - gRange * (i / range)), (int)(c1.getBlue() - bRange * (i / range))));
                    g.fillRect(this.x + (int)i, this.y, 1, this.height);
                }
            }
    	}else {
    		g.setColor(c1);
			g.fillRect(x, y, width, height);
    	}
      	
    	//draw border
    	if(borderColors.size() > 0) {
    		if(borderColors.size() > 1)
        		g.setColor(borderColors.get(currentBorderColor));
    		else
        		g.setColor(borderColors.get(0));
    		
    		g.setStroke(new BasicStroke(borderThickness));
    		g.drawRect(x, y, width, height);
    		g.setStroke(new BasicStroke(1));

    	}

    	
    	

    		/*
    		g.setColor(borderColors.get(currentBorderColor));
    		if(borderThickness == 1) {
    			g.drawRect(x, y, width, height);

    		}else {
    			g.fillRect(x, y, width, borderThickness);//top 
    			g.fillRect(x, y, borderThickness, height);//left
    			g.fillRect(x, y+height-borderThickness, width, borderThickness);//bottom
    			g.fillRect(x+width-borderThickness, y, borderThickness, height);//right
    		}
    		*/
    	
    		
    	//draw text
    	if(font != null) {
    		g.setFont(font);
    	}
    	if(fontColors.size() > 0) {
    		g.setColor(fontColors.get(currentFontColor));
    	}
    	
    	if(textPos == textPosition.top) {
            stringGraphics.drawStringFlow(text, this.getBounds(), textPosition.top, g);
    	}else {
            stringGraphics.drawStringFlow(text, this.getBounds(), textPosition.middle, g);

    	}
    }
    
    

    
    //attribute editors--------------------------
    public void setText(String text) {
    	this.text = text;
    }
    
    public void setTextPosition(textPosition pos) {
    	textPos = pos;
    }
    
    public void setFont(Font font) {
    	this.font = font;
    }
    
    public void setFontColor(Color color) {
    	fontColors.clear();
    	fontColors.add(color);
    }
    public void setFontColors(ArrayList<Color> colors) {
    	fontColors = colors;
    }
    public void setFontColors(Color[] colors) {
    	fontColors.clear();
    	for(Color i: colors) 
    		fontColors.add(i);
    }
    public void addFontColor(Color color) {
    	fontColors.add(color);
    }
    
    public void nextFontColor() {
    	if(fontColors.size() > 1) {
        	currentFontColor++;

        	if(currentFontColor >= fontColors.size())
        		currentFontColor = 0;
        	
    	}
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
    	if(opacity > 1) opacity = 1;
    	else if(opacity < 0)opacity = 0;
    	backgroundOpacity = opacity;
    }
    //border thickness
    public void setBorderThickness(int thickness) {
    	borderThickness = thickness;
    }
    
    //backgroundColors setters
    public void setBackgroundColor(Color color) {
    	backgroundColors.clear();
    	backgroundColors.add(color);
    }    
    public void setBackgroundColors(ArrayList<Color> colors) {
    	backgroundColors = colors;
    }
    public void setBackgroundColors(Color[] colors) {
    	backgroundColors.clear();
    	for(Color i: colors) 
    		backgroundColors.add(i);
    }
    public void addBackgroundColor(Color color) {
    	backgroundColors.add(color);
    }
    
    //gradient format 
    public void setGradientFormat(gradientFormat format) {
		gFormat = format;
    }
    //backgroundColors2 setters
    public void setBackgroundColor2(Color color) {
    	if(gFormat != gradientFormat.none) {
    		backgroundColors2.clear();
    		backgroundColors2.add(color);
    	}
    }    
    public void setBackgroundColors2(ArrayList<Color> colors) {
    	if(gFormat != gradientFormat.none) 
    		backgroundColors = colors;
    }
    public void setBackgroundColors2(Color[] colors) {
    	if(gFormat != gradientFormat.none) {
    		backgroundColors2.clear();
	    	for(Color i: colors) 
	    		backgroundColors2.add(i);
    	}
    }
    public void addBackgroundColor2(Color color) {
    	if(gFormat != gradientFormat.none) 
    		backgroundColors.add(color);
    }
    
    public void nextBackgroundColor() {
    	if(backgroundColors.size() > 1) {
        	currentBackgroundColor++;

        	if(currentBackgroundColor >= backgroundColors.size())
        		currentBackgroundColor = 0;
        	
    	}
    }
    
    public void nextBorderColor() {
    	if(borderColors.size() > 1) {
        	currentBorderColor++;

        	if(currentBorderColor >= borderColors.size())
        		currentBorderColor = 0;
        	
    	}
    }
    
    public void nextColors() {
    	if(borderColors.size() > 1 && backgroundColors.size() > 1) {
    		currentBorderColor++;
    		currentBackgroundColor++;
    		if(currentBackgroundColor >= backgroundColors.size())
        		currentBackgroundColor = 0;
    		if(currentBorderColor >= borderColors.size())
        		currentBorderColor = 0;
    	}
    	if(fontColors.size() > 1) {
    		currentFontColor++;
    		if(currentFontColor >= fontColors.size())
        		currentFontColor = 0;
    	}

    }
    
    public void setColors(int index) {
    	currentBorderColor = index;
    	currentBackgroundColor = index;
    	currentFontColor = index;
    	
    	if(index >= backgroundColors.size())
    		currentBackgroundColor = backgroundColors.size()-1;
    	
    	if(index >= borderColors.size())
        	currentBorderColor = borderColors.size()-1;
    	
    	if(index >= fontColors.size())
    		currentFontColor = fontColors.size()-1;

    }

    
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
    
    //getters
    public int getCurrentBorderColorIndex() {
    	return currentBorderColor;
    }

}
