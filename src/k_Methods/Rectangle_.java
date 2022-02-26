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
	
	
	//COLOR PROPERTIES
	private ArrayList<Color> borderColors = new ArrayList<Color>();
	//this will be a list of colors for a single mode
	//this is the compilation of the color modes.
	private ArrayList<ArrayList<Color>> backgroundColors = new ArrayList<ArrayList<Color>>();
	Color fontColor;
	
	//the size of backgroundColors and borderColors should be the same, but it isn't critical
	int currentBackgroundColor = 0;
	int currentBorderColor = 0;
	/*An example for the background Color: You have a rectangle with three modes. 
	 * The first will be a gradient from red to blue
	 * Second will be solid purple
	 * Third is green to yellow to red
	 * Each of these modes will be a backgroundColor
	 * These three modes will be put together into the backgroundColors 
	*/
	
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
    	
    	//equalize size of background and border arrayList by adding last element until they are the same
    	int maxSize =  Math.max(borderColors.size(), backgroundColors.size());
    	if(borderColors.size() != 0 && backgroundColors.size() != 0) {
	    	while(borderColors.size() < maxSize)
	    		borderColors.add(borderColors.get(borderColors.size()-1));
	    	while(backgroundColors.size() < maxSize)
	    		backgroundColors.add(backgroundColors.get(backgroundColors.size()-1));
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
    			
    			//draw an ordinary rectangle
    			g.setColor(new Color(bgColors.get(0).getRed(), bgColors.get(0).getGreen(), bgColors.get(0).getBlue(), (int)(backgroundOpacity*255)));
    			g.fillRect(x, y, width, height);
    		}
    		else if(bgColors.size() > 1){ //not just an else, since you want to make sure that mode is not empty

    			int begin, end; //the beginning and ending portion for a group of two colors.
    			int span;
    			int rRange, gRange, bRange; //the second color minus the first
    			int red, green, blue;//correspond to the values of the first color
    			
    			if(gFormat == gradientFormat.horizontal) {
    				//creates gradients from a color and its next color, mapping it to the correct portion of the rectangle
    				for(int i = 0; i < bgColors.size()-1; i++) {
    					begin = x + (int)((double)i / (bgColors.size()-1) * width);
    					end = x + (int)((double)(i+1) / (bgColors.size()-1) * width);
    					span = end-begin;
    					
    					red = bgColors.get(i).getRed();
    					green = bgColors.get(i).getGreen();
    					blue = bgColors.get(i).getBlue();
    					rRange = bgColors.get(i+1).getRed() - red;
    					gRange = bgColors.get(i+1).getGreen() - green;
    					bRange = bgColors.get(i+1).getBlue() - blue;
    					
    					//the drawing part
    					for(int j = 0; j < span-1; j++) {
    						g.setColor(new Color((int)(red + rRange * ((double)j/end)),
    								(int)(green + gRange * ((double)j/end)),
    								(int)(blue + bRange * ((double)j/end)),
    								(int)(backgroundOpacity*255)));
    	                    g.fillRect(begin+j, y, 2, height);
    					}
    				}
    				    				
    				
    			}else if(this.gFormat == gradientFormat.vertical){

    				for(int i = 0; i < bgColors.size()-1; i++) {
    					begin = y + (int)((double)i / (bgColors.size()-1) * height);
    					end = y + (int)((double)(i+1) / (bgColors.size()-1) * height);
    					span = end-begin;
    					
    					red = bgColors.get(i).getRed();
    					green = bgColors.get(i).getGreen();
    					blue = bgColors.get(i).getBlue();
    					rRange = bgColors.get(i+1).getRed() - red;
    					gRange = bgColors.get(i+1).getGreen() - green;
    					bRange = bgColors.get(i+1).getBlue() - blue;
    					
    					//the drawing part
    					for(int j = 0; j < span-1; j++) {
    						g.setColor(new Color((int)(red + rRange * ((double)j/span)),
    								(int)(green + gRange * ((double)j/span)),
    								(int)(blue + bRange * ((double)j/span)),
    								(int)(backgroundOpacity*255)));
    	                    g.fillRect(x, begin+j, width, 2);
    					}
    				}
    			}
    			else if (gFormat == gradientFormat.none) {
    				g.setColor(new Color(bgColors.get(0).getRed(), bgColors.get(0).getGreen(), bgColors.get(0).getBlue(), (int)(backgroundOpacity*255)));
        			g.fillRect(x, y, width, height);
    			}
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


    		
    	//draw text
    	if(font != null) { g.setFont(font);}
    	if(fontColor != null) g.setColor(fontColor);

    	if(textPos == textPosition.top) {
            stringGraphics.drawStringFlow(text, this.getBounds(), textPosition.top, g);
    	}else {
            stringGraphics.drawStringFlow(text, this.getBounds(), textPosition.middle, g);
    	}
    	
    	
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
    	fontColor = color;
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
    	if(i < backgroundColors.size()) {
        	ArrayList<Color> backgroundColor = new ArrayList<Color>();	
        	backgroundColor.add(color);
    		backgroundColors.set(i, backgroundColor);
    	}
    }
    public void setBackgroundColor(int i, ArrayList<Color> colors) {
    	if(i < backgroundColors.size()) 
    		backgroundColors.set(i, colors);
    }

    public void setBackgroundColor(int i, Color[] Color) {
    	if(i < backgroundColors.size()) {
        	ArrayList<Color> backgroundColor = new ArrayList<Color>();	
        	for(Color j: Color)
        		backgroundColor.add(j);
        	backgroundColors.set(i, backgroundColor);
        		
    	}

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
    	
    	if(index >= backgroundColors.size())
    		currentBackgroundColor = backgroundColors.size()-1;
    	
    	if(index >= borderColors.size())
        	currentBorderColor = borderColors.size()-1;
    	
    	if(index < 0) {
    		currentBackgroundColor = 0;
    		currentBorderColor = 0;
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

}
