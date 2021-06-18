import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

import javax.swing.JFrame;

//TODO make help page for question select special 
//TODO make the initialize methods belong to the level select not the question pages

public class GUI extends Canvas implements Runnable, Serializable {
	
	//panel settings
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 560;
	public static final int HEIGHT = WIDTH / 8 * 5;
	public static final int SCALE = 2;
	public  final String TITLE = "Quick Maths";
	
	private boolean running = false;
	private Thread thread;
	
	//perhaps these could be public oop
	private TitlePage titlePage;
	private QuestionPageNumber questionPageNumber;
	private QuestionPageYesNo questionPageYN;
	private LevelSelect levSelectPage;
	private LevelSelectSpecial levSelectPageSpecial;
	private ResultsPage resultsPage;
	static UserData uData = new UserData();
	private AchievementPages achPages;
	private dataUpdater dataUpdater;

	private KeyInput key;
	private MouseInput mouse;
	
	//create game objects
	private Question question;
	private Random r = new Random();

	
	public static enum STATE{
		TITLE, 
		LEVELSELECT,
		QUESTIONROUNDNUMBER,
		QUESTIONROUNDYESNO,
		RESULTS,
		ACHIEVEMENTS,
		LEVELSELECTSPECIAL;
	}
	public static STATE State = STATE.TITLE;
	
	public void init(){
		requestFocus();
		titlePage = new TitlePage(this);
		questionPageNumber = new QuestionPageNumber(this);
		questionPageYN = new QuestionPageYesNo(this);
		levSelectPage = new LevelSelect(this);
		levSelectPageSpecial = new LevelSelectSpecial(this);
		resultsPage = new ResultsPage(this);	
		achPages = new AchievementPages(this);
		dataUpdater = new dataUpdater(uData);
		
		dataUpdater.resetData();

		key = new KeyInput(this);
		this.addKeyListener(key);
		
		mouse = new MouseInput(this); 
		this.addMouseListener(mouse);
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
		if(State == STATE.TITLE){
			//something.tick
		}
		else if (State == STATE.QUESTIONROUNDYESNO){
			questionPageYN.tick();
		}
		else if (State == STATE.QUESTIONROUNDNUMBER){
			questionPageNumber.tick();
		}
		try{writeObjectToDisk((Object)uData, "userData.ser");}
		catch(IOException ioe) {ioe.printStackTrace();}
	
	}
	
	private void render(){
		//create a graphics object :0
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) { //create bufferstrategy once
			createBufferStrategy(3); //loads three images at a time.
			return;
		}
		//****************************---------****************************//
		//actual rendering
		Graphics g = bs.getDrawGraphics(); //creates graphics context (draws out the buffers)
		//create reference lines
		
		for(int x = 0; x < WIDTH*SCALE; x+=100)
			g.drawString(""+x, x, HEIGHT * SCALE-10);
		for(int y = 0; y < HEIGHT*SCALE; y+=100)
			g.drawString(""+y, 5, y);
		
		g.clearRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);//some reason menu pages render is not going away
		
		if(State == STATE.TITLE){
			titlePage.render(g);
		}
		else if(State == STATE.LEVELSELECT){
			levSelectPage.render(g);
		}	
		else if(State == STATE.QUESTIONROUNDNUMBER) {
			questionPageNumber.render(g);
		}
		else if(State == STATE.QUESTIONROUNDYESNO){
			questionPageYN.render(g);
		}
		else if(State == STATE.RESULTS){
			resultsPage.render(g);
		}
		else if(State == STATE.ACHIEVEMENTS){
			if(achPages.getPageIndex() == 0)
				achPages.renderPage1(g);
			else if (achPages.getPageIndex() == 1)
				achPages.renderPage2(g);
			else if (achPages.getPageIndex() == 2)
				achPages.renderPage3(g);
			else if (achPages.getPageIndex() == 3)
				achPages.renderStats(g);
		}
		else if(State == STATE.LEVELSELECTSPECIAL){
			levSelectPageSpecial.render(g);
		}
		//****************************---------****************************//
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		System.out.println("It's a great day to debug");
				
		GUI gui = new GUI();
			
		try {uData = (UserData) objectLoader("userData.ser");}
		   catch(IOException ioe){
		        ioe.printStackTrace();
				File userDataSave = new File("userData.ser");
	    }
		    catch(ClassNotFoundException cnfe){
		        cnfe.printStackTrace();
	    }		
				
					
		gui.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		gui.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		gui.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(gui.TITLE);
		frame.add(gui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack(); 
		frame.setLocationRelativeTo(null); //needs to be after pack
		frame.setVisible(true);
		
		gui.start(); 
		
		/*
		public class SaveData implements java.io.Serializable{

			private static final long serialVersionUID = 1L;
			public int[][] questionsCompleted = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}; //groups are the question types, inner brackets are the question difficulties
			public int[][] questionsCorrect = {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}; //groups are the question types, inner brackets are the question difficulties

		}
		*/
	}

	public LevelSelect getLevSelect() {return levSelectPage;}
	public TitlePage getTitlePage() {return titlePage;}
	public QuestionPageNumber getQuestionPage() {return questionPageNumber;}
	public QuestionPageYesNo getQuestionPageYesNo() {return questionPageYN;}
	public ResultsPage getResultsPage() {return resultsPage;}
	public UserData getUserData() {return uData;}
	public void setUserData(UserData uData) {this.uData = uData;}
	public AchievementPages getAchPage(){return achPages;}
	public LevelSelectSpecial getLevelSelectSpecial(){return  levSelectPageSpecial;}
	
	public static void writeObjectToDisk(Object obj, String name) throws IOException {
        //Create file output stream
        FileOutputStream fileOutStr = new FileOutputStream(name);
        //Create object output stream and write object
        ObjectOutputStream objOutStr = new ObjectOutputStream(fileOutStr);
        objOutStr.writeObject(obj);
       
        //Close all streams
        objOutStr.close();
        fileOutStr.close();
        //System.out.println("Serialized data is saved in a file  - "+name);
    }

    public static Object objectLoader(String filename) throws IOException,
                                                      ClassNotFoundException{
        FileInputStream fileInStr = new FileInputStream(filename);
        ObjectInputStream objInStr = new ObjectInputStream(fileInStr);
        Object obj = (Object) objInStr.readObject();
        objInStr.close();
        fileInStr.close();

        return obj;
    }
    
    public UserData getUdata() {
    	return uData;
    }

	
}
