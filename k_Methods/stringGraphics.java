package k_Methods;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Random;

public class stringGraphics extends Canvas implements Runnable {

    //panel settings
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 560;
    public static final int HEIGHT = WIDTH / 8 * 5;
    public static final int SCALE = 2;
    public  final String TITLE = "TITLE";

    private boolean running = false;
    private Thread thread;

    private Random r = new Random();

    public void init(){
        requestFocus();
    }

    private synchronized void start() {
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();

    }

    private synchronized void stop() {
        if (!running)
            return;

        running = false;
        try {

            thread.join();
        }
        catch (InterruptedException e)
        {e.printStackTrace();}

        //System.exit(1);
    }

    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0; //calculates time passed to catch up
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) { //if one sixtieth of a second has passed
                tick();
                updates++;
                frames++;
                render(); //we shall render every tick. No need for such high frames
                delta--;
            }


            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }

        }//end running

        stop();
    }//end run

    private void tick(){


    }

    private void render(){
        //create a graphics object :0
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) { //create bufferstrategy once
            createBufferStrategy(3); //loads three images at a time.
            return;
        }
        Graphics g = bs.getDrawGraphics(); //creates graphics context (draws out the buffers)
        //****************************---------****************************//
        //actual rendering
        Graphics2D g2d = (Graphics2D)g;
        Rectangle rectangle = new Rectangle(20, 20, 100, 100);

        g.setColor(SystemColor.ORANGE);
        g2d.draw(rectangle);
        g.setColor(SystemColor.black);
        drawStringCentered("HELLO", rectangle, g);
        //****************************---------****************************//
        g.dispose();
        bs.show();
    }

    public static void main(String args[]) {
        System.out.println("It's a great day to debug");

        stringGraphics SMT = new stringGraphics();

        SMT.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        SMT.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        SMT.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(SMT.TITLE);
        frame.add(SMT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null); //needs to be after pack
        frame.setVisible(true);

        SMT.start();
    }

    public static void drawStringCentered(String input, Rectangle rect, Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        FontRenderContext frc = g2d.getFontRenderContext();
        GlyphVector gv = g.getFont().createGlyphVector(frc, input);

        //Rectangle bounds =  gv.getPixelBounds(null, rect.x, rect.y);
        Rectangle2D bounds =  gv.getVisualBounds();
        //g.drawString(input, rect.x, (int)(rect.y + bounds.getHeight()));
        // g2d.draw(new Rectangle(rect.x, rect.y, (int)bounds.getWidth(), (int)bounds.getHeight()));

        g.drawString(input, (int)(rect.x + (rect.width-bounds.getWidth())/2), (int)(rect.y + bounds.getHeight() + (rect.height-bounds.getHeight())/2));

    }

    public static void drawStringCentered(String[] input, Rectangle rect, Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        FontRenderContext frc = g2d.getFontRenderContext();
        double spacing = g.getFontMetrics().getHeight()/2.5;
        double totalHeight = 0;
        double currentHeight = 0;

        ArrayList<GlyphVector> glyphVectors = new ArrayList<GlyphVector>();
        ArrayList<Rectangle2D> bounds = new ArrayList<Rectangle2D>();
        for(int i = 0; i < input.length; i++){
            glyphVectors.add(g.getFont().createGlyphVector(frc, input[i]));
            bounds.add(glyphVectors.get(i).getVisualBounds());

            totalHeight += glyphVectors.get(i).getVisualBounds().getHeight();
        }

        totalHeight += spacing * (input.length-1);

        for(int i = 0; i < input.length; i++) {
            g.drawString(input[i], (int)(rect.x + (rect.width-bounds.get(i).getWidth())/2),
                    (int)(rect.y + bounds.get(0).getHeight() + currentHeight + (rect.height-totalHeight)/2));
            currentHeight+= spacing + bounds.get(i).getHeight();
        }


        //Rectangle bounds =  gv.getPixelBounds(null, rect.x, rect.y);
        //g.drawString(input, rect.x, (int)(rect.y + bounds.getHeight()));
        // g2d.draw(new Rectangle(rect.x, rect.y, (int)bounds.getWidth(), (int)bounds.getHeight()));


    }


}
