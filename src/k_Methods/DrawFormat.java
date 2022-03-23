package k_Methods;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawFormat {
	//bruh i should just port this to online, so i can have CSS. >:(

	//preconditions all rectangles have same width and same height
	public static void setCentered_xy(int top, int right, int bottom, int left, Rectangle_[][] rectangles) {
		//the first four parameters are like a bounding box
		//inset is like padding if you're familiar with CSS
		//rectangle: rows and columns as normal
		
		if(rectangles.length < 1)
			return;
		if(rectangles[0].length < 1)
			return;
		
		int numRows = rectangles.length;
		int numColumns = rectangles[0].length;
		
		int width = rectangles[0][0].width;
		int height = rectangles[0][0].height;
		
		int totalWidth = width * numColumns;//get width of a rectangle, and multiply by number of columns
		int totalHeight = height * numRows;//get height of rectangle and multiply by number of rows
		

		int spacingX = 0, spacingY = 0;
		if (numColumns > 1)
			spacingX = (right-left - totalWidth)/(numColumns - 1); // right-left gives amount of room your working with. Inset*2 takes away from both right and left sides. Totalwidth gives remaining space. numColumns-1 divides that space up to evenly append (n-1 spaces for n columns)
		if (numRows > 1)
			spacingY = (bottom-top - totalHeight)/(numRows - 1);
			
		for(int i = 0; i < rectangles.length; i++) {
			for(int j = 0; j < rectangles[0].length; j++) {
				rectangles[i][j].x = left + spacingX*j + width*j;
				rectangles[i][j].y = top + spacingY *i + height*i;
				//System.out.println("i: " + i + ", j: " + j + ", x: " + (left + insetX + (spacingX * j)) + ", y:" + (top + insetY + (spacingY * i)));
			}
		}

		
	}
	
	
	public static void setCentered_xy_spacing(int top, int right, int bottom, int left, int spacingX, int spacingY, Rectangle_[][] rectangles){

		//here the spacing and rectangles are all calculated as a single entity which is then centered
		
		//am i initializing too many variables?
		if(rectangles.length < 1)
			return;
		if(rectangles[0].length < 1)
			return;
		
		int spanX = right - left;
		int spanY = bottom - top;
		
		int width= rectangles[0][0].width, numElementsX = rectangles[0].length;
		int height = rectangles[0][0].height, numElementsY = rectangles.length;
		
		//not the same as totalwidth from the prior method
		int totalWidth = numElementsX * width + (numElementsX-1) * spacingX;
		int totalHeight = numElementsY * height + (numElementsY-1) * spacingY;
		
		if(totalHeight > spanY) {
			System.out.println("The total space on y axis will exceed screen height by " + (totalHeight - spanY));
			return;
		}
		if(totalWidth > spanX) {
			System.out.println("The total space on x axis will exceed screen width");
			return;
		}
				
		for(int i = 0; i < rectangles.length; i++) {
			for(int j = 0; j < rectangles[0].length; j++) {
				rectangles[i][j].x = left + (spanX - totalWidth)/2 + width*j + spacingX*j; //spanX - totalWidth gives the starting point
				rectangles[i][j].y = top + (spanY - totalHeight)/2 + height*i + spacingY*i;
				//System.out.println("i: " + i + ", j: " + j + ", x: " + (left + insetX + (spacingX * j)) + ", y:" + (top + insetY + (spacingY * i)));
			}
		}

	}
}
