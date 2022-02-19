import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.awt.Component;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.io.File;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.util.Random;
import java.io.Serializable;
import java.awt.Canvas;


public class GUI extends Canvas implements Runnable, Serializable
{
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 333;
    public static final int SCALE = 2;
    public final String TITLE = "Quick Maths";
    private boolean running;
    private Thread thread;
    private TitlePage titlePage;
    private QuestionPageNumber questionPageNumber;
    private QuestionPageYesNo questionPageYN;
    private LevelSelect levSelectPage;
    private LevelSelectSpecial levSelectPageSpecial;
    private ResultsPage resultsPage;
    static UserData uData;
    private AchievementPages achPages;
    private dataUpdater dataUpdater;
    private KeyInput key;
    private MouseInput mouse;
    private Question question;
    private Random r;
    public static STATE State;
    
    static {
        GUI.uData = new UserData();
        GUI.State = STATE.TITLE;
    }
    
    public GUI() {
        this.running = false;
        this.r = new Random();
    }
    
    public void init() {
        this.requestFocus();
        this.titlePage = new TitlePage(this);
        this.questionPageNumber = new QuestionPageNumber(this);
        this.questionPageYN = new QuestionPageYesNo(this);
        this.levSelectPage = new LevelSelect(this);
        this.levSelectPageSpecial = new LevelSelectSpecial(this);
        this.resultsPage = new ResultsPage(this);
        this.achPages = new AchievementPages(this);
        this.dataUpdater = new dataUpdater(GUI.uData);
        this.addKeyListener(this.key = new KeyInput(this));
        this.addMouseListener(this.mouse = new MouseInput(this));
    }
    
    private synchronized void start() {
        if (this.running) {
            return;
        }
        this.running = true;
        (this.thread = new Thread(this)).start();
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
    
    @Override
    public void run() {
        this.init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        final double ns = 1.6666666666666666E7;
        double delta = 0.0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while (this.running) {
            final long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1.0) {
                this.tick();
                ++updates;
                ++frames;
                this.render();
                --delta;
            }
            if (System.currentTimeMillis() - timer > 1000L) {
                timer += 1000L;
                updates = 0;
                frames = 0;
            }
        }
        this.stop();
    }
    
    private void tick() {
        if (GUI.State != STATE.TITLE) {
            if (GUI.State == STATE.QUESTIONROUNDYESNO) {
                this.questionPageYN.tick();
            }
            else if (GUI.State == STATE.QUESTIONROUNDNUMBER) {
                this.questionPageNumber.tick();
            }
        }
        try {
            writeObjectToDisk(GUI.uData, "userData.ser");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    private void render() {
        final BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        final Graphics g = bs.getDrawGraphics();
        for (int x = 0; x < 1200; x += 100) {
            g.drawString(new StringBuilder().append(x).toString(), x, 656);
        }
        for (int y = 0; y < 666; y += 100) {
            g.drawString(new StringBuilder().append(y).toString(), 5, y);
        }
        g.clearRect(0, 0, 1200, 666);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        if (GUI.State == STATE.TITLE) {
            this.titlePage.render(g);
        }
        else if (GUI.State == STATE.LEVELSELECT) {
            this.levSelectPage.render(g);
        }
        else if (GUI.State == STATE.QUESTIONROUNDNUMBER) {
            this.questionPageNumber.render(g);
        }
        else if (GUI.State == STATE.QUESTIONROUNDYESNO) {
            this.questionPageYN.render(g);
        }
        else if (GUI.State == STATE.RESULTS) {
            this.resultsPage.render(g);
        }
        else if (GUI.State == STATE.ACHIEVEMENTS) {
            if (this.achPages.getPageIndex() == 0) {
                this.achPages.renderPage1(g);
            }
            else if (this.achPages.getPageIndex() == 1) {
                this.achPages.renderPage2(g);
            }
            else if (this.achPages.getPageIndex() == 2) {
                this.achPages.renderPage3(g);
            }
            else if (this.achPages.getPageIndex() == 3) {
                this.achPages.renderStats(g);
            }
        }
        else if (GUI.State == STATE.LEVELSELECTSPECIAL) {
            this.levSelectPageSpecial.render(g);
        }
        g.dispose();
        bs.show();
    }
    
    public static void main(final String[] args) {
        System.out.println("It's a great day to debug");
        final GUI gui = new GUI();
        try {
            GUI.uData = (UserData)objectLoader("userData.ser");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            final File file = new File("userData.ser");
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        gui.setPreferredSize(new Dimension(1200, 666));
        gui.setMaximumSize(new Dimension(1200, 666));
        gui.setMinimumSize(new Dimension(1200, 666));
        gui.getClass();
        final JFrame frame = new JFrame("Quick Maths");
        frame.add(gui);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        gui.start();
    }
    
    public LevelSelect getLevSelect() {
        return this.levSelectPage;
    }
    
    public TitlePage getTitlePage() {
        return this.titlePage;
    }
    
    public QuestionPageNumber getQuestionPage() {
        return this.questionPageNumber;
    }
    
    public QuestionPageYesNo getQuestionPageYesNo() {
        return this.questionPageYN;
    }
    
    public ResultsPage getResultsPage() {
        return this.resultsPage;
    }
    
    public UserData getUserData() {
        return GUI.uData;
    }
    
    public void setUserData(final UserData uData) {
        GUI.uData = uData;
    }
    
    public AchievementPages getAchPage() {
        return this.achPages;
    }
    
    public LevelSelectSpecial getLevelSelectSpecial() {
        return this.levSelectPageSpecial;
    }
    
    public static void writeObjectToDisk(final Object obj, final String name) throws IOException {
        final FileOutputStream fileOutStr = new FileOutputStream(name);
        final ObjectOutputStream objOutStr = new ObjectOutputStream(fileOutStr);
        objOutStr.writeObject(obj);
        objOutStr.close();
        fileOutStr.close();
    }
    
    public static Object objectLoader(final String filename) throws IOException, ClassNotFoundException {
        final FileInputStream fileInStr = new FileInputStream(filename);
        final ObjectInputStream objInStr = new ObjectInputStream(fileInStr);
        final Object obj = objInStr.readObject();
        objInStr.close();
        fileInStr.close();
        return obj;
    }
    
    public UserData getUdata() {
        return GUI.uData;
    }
    
    public enum STATE
    {
        TITLE("TITLE", 0), 
        LEVELSELECT("LEVELSELECT", 1), 
        QUESTIONROUNDNUMBER("QUESTIONROUNDNUMBER", 2), 
        QUESTIONROUNDYESNO("QUESTIONROUNDYESNO", 3), 
        RESULTS("RESULTS", 4), 
        ACHIEVEMENTS("ACHIEVEMENTS", 5), 
        LEVELSELECTSPECIAL("LEVELSELECTSPECIAL", 6);
        
        private STATE(final String name, final int ordinal) {
        }
    }
}
