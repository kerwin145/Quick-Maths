

package k_Methods;

import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.font.GlyphVector;
import java.awt.font.FontRenderContext;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Shape;
import java.awt.SystemColor;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.util.Random;

import k_Methods.Rectangle_.textPosition;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;

public class stringGraphics extends Canvas
{
    private static  long serialVersionUID = 1L;
    public static  int WIDTH = 560;
    public static  int HEIGHT = 350;
    public static  int SCALE = 2;
    public  String TITLE = "TITLE";
    private boolean running;
    private Thread thread;
    private Random r;
    
    public stringGraphics() {
        this.running = false;
        this.r = new Random();
    }
    
    public void init() {
        this.requestFocus();
    }
    
    private synchronized void start() {
        if (this.running) {
            return;
        }
        this.running = true;
    }
    
    private synchronized void stop() {
        if (!this.running) {
            return;
        }
        this.running = false;
        try {
            this.thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void tick() {
    }
    
    private void render() {
         BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
         Graphics g = bs.getDrawGraphics();
         Graphics2D g2d = (Graphics2D)g;
         Rectangle rectangle = new Rectangle(20, 20, 100, 100);
        g.setColor(SystemColor.ORANGE);
        g2d.draw(rectangle);
        g.setColor(SystemColor.black);
        drawStringCentered("HELLO", rectangle, g);
        g.dispose();
        bs.show();
    }
    
    
    //ONE LINE STRINGS
    public static void drawStringCentered( String input,  Rectangle rect,  Graphics g) {
         Graphics2D g2d = (Graphics2D)g;
         FontRenderContext frc = g2d.getFontRenderContext();
         GlyphVector gv = g.getFont().createGlyphVector(frc, input);
         Rectangle2D bounds = gv.getVisualBounds();
        g.drawString(input, (int)(rect.x + (rect.width - bounds.getWidth()) / 2.0), (int)(rect.y + bounds.getHeight() + (rect.height - bounds.getHeight()) / 2.0));
    }
    public static void drawStringCentered( String input,  Rectangle rect, textPosition tPosition, Graphics g) {
    	Graphics2D g2d = (Graphics2D)g;
    	FontRenderContext frc = g2d.getFontRenderContext();
        double spacing = g.getFontMetrics().getHeight() / 5;

    	GlyphVector gv = g.getFont().createGlyphVector(frc, input);
    	Rectangle2D bounds = gv.getVisualBounds();
    	if(tPosition == textPosition.middle)
    		g.drawString(input, (int)(rect.x + (rect.width - bounds.getWidth()) / 2.0), (int)(rect.y + bounds.getHeight() + (rect.height - bounds.getHeight()) / 2.0));
    	else g.drawString(input, (int) (rect.x + spacing), (int)(rect.y + bounds.getHeight() + (rect.height - bounds.getHeight()) / 2.0));
    }
    
    
    
    public static void drawStringCentered( String[] input,  Rectangle rect,  Graphics g) {
         Graphics2D g2d = (Graphics2D)g;
         FontRenderContext frc = g2d.getFontRenderContext();
         double spacing = g.getFontMetrics().getHeight() / 2.5;
        double totalHeight = 0.0;
        double currentHeight = 0.0;
         ArrayList<GlyphVector> glyphVectors = new ArrayList<GlyphVector>();
         ArrayList<Rectangle2D> bounds = new ArrayList<Rectangle2D>();
        for (int i = 0; i < input.length; i++) {
            glyphVectors.add(g.getFont().createGlyphVector(frc, input[i]));
            bounds.add(glyphVectors.get(i).getVisualBounds());
            totalHeight += glyphVectors.get(i).getVisualBounds().getHeight();
        }
        totalHeight += spacing * (input.length - 1);
        for (int i = 0; i < input.length; i++) {
            g.drawString(input[i], (int)(rect.x + (rect.width - bounds.get(i).getWidth()) / 2.0), (int)(rect.y + bounds.get(0).getHeight() + currentHeight + (rect.height - totalHeight) / 2.0));
            currentHeight += spacing + bounds.get(i).getHeight();
        }
    }
    
    public static void drawStringFlow( String input,  Rectangle rect, textPosition tPos,  Graphics g) {
         Graphics2D g2d = (Graphics2D)g;
         FontRenderContext frc = g2d.getFontRenderContext();

         double totalWidth = g.getFontMetrics().stringWidth(input);
         

         String[] words = input.split(" "); //separate words
         for(int i = 0; i < words.length - 1; i++) {
         	words[i]+= " ";
         } //puts a space at the end of the last word, except for the last
         
       //  int numRows = (int)(Math.ceil(totalWidth/rect.width)); //minimum number of rows needed to fit text
        // double rowWidth = Math.ceil(totalWidth/numRows); //makes each row have similar width in text
         
         ArrayList<String> rows = new ArrayList<String>();
         String currentRow = "";
         double currentRowLength = 0;
         
         for(int i = 0; i < words.length; i++) {
 			currentRowLength += g.getFontMetrics().stringWidth(words[i]);
         	if(currentRowLength > rect.width) {
         		rows.add(currentRow);
         		
         		currentRowLength = 0;
         		currentRow = "";
         	}
         	currentRow += words[i];

         }
        rows.add(currentRow);//one last time for the last row
        
        if(tPos == textPosition.middle)
        	drawStringCentered(rows, rect, g);
        else if(tPos == textPosition.top)
        	drawStringCenteredTop(rows, rect, g);
    }
   
    
    public static void drawStringCenteredTop( ArrayList<String> input,  Rectangle rect,  Graphics g) {
    	  Graphics2D g2d = (Graphics2D)g;
          FontRenderContext frc = g2d.getFontRenderContext();
          double spacing = g.getFontMetrics().getHeight() / 2.5;
         double currentHeight = 0.0;
          ArrayList<GlyphVector> glyphVectors = new ArrayList<GlyphVector>();
          ArrayList<Rectangle2D> bounds = new ArrayList<Rectangle2D>();
         for (int i = 0; i < input.size(); i++) {
             glyphVectors.add(g.getFont().createGlyphVector(frc, input.get(i)));
             bounds.add(glyphVectors.get(i).getVisualBounds());
         }
         
         for (int i = 0; i < input.size(); i++) {
             g.drawString(input.get(i), (int)(rect.x + (rect.width - bounds.get(i).getWidth()) / 2.0), (int)(rect.y + bounds.get(0).getHeight() + currentHeight + spacing / 2.0));
             currentHeight += spacing + bounds.get(i).getHeight();
         }
    }

    
    public static void drawStringCentered( ArrayList<String> input,  Rectangle rect,  Graphics g) {
    	/*
         Graphics2D g2d = (Graphics2D)g;
         Font font = g.getFont();
         FontMetrics metrics = g.getFontMetrics(font);
         
         double spacing = g.getFontMetrics().getHeight() / 2.5;
        double totalHeight = 0.0;
        
        ArrayList<Integer> widths = new ArrayList<Integer>();
        ArrayList<Integer> heights = new ArrayList<Integer>();
        for (int i = 0; i < input.size(); i++) {
        	heights.add((int)(metrics.getLineMetrics(input.get(i), g2d).getAscent()));
        	widths.add((int)(metrics.stringWidth(input.get(i))));

            totalHeight += heights.get(i);

        }
        
        totalHeight += spacing * (input.size() - 1);
        
        double currentHeight = (rect.height - totalHeight)/2;

        for (int i = 0; i < input.size(); i++) {
            //g.drawRect((int)(rect.x + (rect.width - widths.get(i)) / 2.0), (int)(rect.y + currentHeight ),(int)(widths.get(i)), (int)(heights.get(i)));

            g.drawString(input.get(i), (int)(rect.x + (rect.width - widths.get(i)) / 2.0), (int)(rect.y + currentHeight + heights.get(i)));
            currentHeight += spacing + heights.get(i);
        }
        */
        
        Graphics2D g2d = (Graphics2D)g;
        FontRenderContext frc = g2d.getFontRenderContext();
        double spacing = g.getFontMetrics().getHeight() / 2.5;
       double totalHeight = 0.0;
       double currentHeight = 0.0;
        ArrayList<GlyphVector> glyphVectors = new ArrayList<GlyphVector>();
        ArrayList<Rectangle2D> bounds = new ArrayList<Rectangle2D>();
       for (int i = 0; i < input.size(); i++) {
           glyphVectors.add(g.getFont().createGlyphVector(frc, input.get(i)));
           bounds.add(glyphVectors.get(i).getVisualBounds());
           totalHeight += glyphVectors.get(i).getVisualBounds().getHeight();
       }
       totalHeight += spacing * (input.size() - 1);
       for (int i = 0; i < input.size(); i++) {
           g.drawString(input.get(i), (int)(rect.x + (rect.width - bounds.get(i).getWidth()) / 2.0), (int)(rect.y + bounds.get(0).getHeight() + currentHeight + (rect.height - totalHeight) / 2.0));
           currentHeight += spacing + bounds.get(i).getHeight();
       }
    
    }
}
