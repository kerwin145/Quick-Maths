

package k_Methods;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

import k_Methods.Rectangle_.textPosition;

import java.io.Serializable;
import java.awt.Shape;
import java.awt.Rectangle;

public class RectanglePlus extends Rectangle implements Shape, Serializable
{
    private static final long serialVersionUID = 1L;
    private final double darkFactor = 0.9;
    gradientFormat gFormat;
    ArrayList<Color> color1;
    ArrayList<Color> color2;
    ArrayList<Color> border;
    ArrayList<Color> color1a;
    ArrayList<Color> color2a;
    ArrayList<Color> fontColor;
    int colorState;
    int colorStateMax;
    boolean hoveredOver;
    boolean doDarken;
    String text;
    Font font;
    
    public RectanglePlus(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.color1 = new ArrayList<Color>();
        this.color2 = new ArrayList<Color>();
        this.border = new ArrayList<Color>();
        this.color1a = new ArrayList<Color>();
        this.color2a = new ArrayList<Color>();
        this.fontColor = new ArrayList<Color>();
        this.colorState = 0;
        this.colorStateMax = 0;
        this.hoveredOver = false;
        this.doDarken = false;
        this.text = "";
        this.font = null;
    }
    
    public RectanglePlus(final int x, final int y, final int width, final int height, final String text) {
        super(x, y, width, height);
        this.color1 = new ArrayList<Color>();
        this.color2 = new ArrayList<Color>();
        this.border = new ArrayList<Color>();
        this.color1a = new ArrayList<Color>();
        this.color2a = new ArrayList<Color>();
        this.fontColor = new ArrayList<Color>();
        this.colorState = 0;
        this.colorStateMax = 0;
        this.hoveredOver = false;
        this.doDarken = false;
        this.text = "";
        this.font = null;
        this.text = text;
    }
    
    public RectanglePlus(final int x, final int y, final int width, final int height, final Color c1, final Color c2, final Color c1a, final Color c2a, final gradientFormat format, final Color borderColor) {
        super(x, y, width, height);
        this.color1 = new ArrayList<Color>();
        this.color2 = new ArrayList<Color>();
        this.border = new ArrayList<Color>();
        this.color1a = new ArrayList<Color>();
        this.color2a = new ArrayList<Color>();
        this.fontColor = new ArrayList<Color>();
        this.colorState = 0;
        this.colorStateMax = 0;
        this.hoveredOver = false;
        this.doDarken = false;
        this.text = "";
        this.font = null;
        this.color1.add(c1);
        this.color2.add(c2);
        this.color1a.add(c1a);
        this.color2a.add(c2a);
        this.border.add(borderColor);
        this.gFormat = format;
    }
    
    public RectanglePlus(final int x, final int y, final int width, final int height, final Color c1, final Color c2, final boolean doDarken, final gradientFormat format, final Color borderColor) {
        super(x, y, width, height);
        this.color1 = new ArrayList<Color>();
        this.color2 = new ArrayList<Color>();
        this.border = new ArrayList<Color>();
        this.color1a = new ArrayList<Color>();
        this.color2a = new ArrayList<Color>();
        this.fontColor = new ArrayList<Color>();
        this.colorState = 0;
        this.colorStateMax = 0;
        this.hoveredOver = false;
        this.doDarken = false;
        this.text = "";
        this.font = null;
        this.color1.add(c1);
        this.color2.add(c2);
        this.doDarken = doDarken;
        this.border.add(borderColor);
        this.color1a.add(new Color((int)(c1.getRed() * 0.9), (int)(c1.getGreen() * 0.9), (int)(c1.getBlue() * 0.9)));
        this.color2a.add(new Color((int)(c2.getRed() * 0.9), (int)(c2.getGreen() * 0.9), (int)(c2.getBlue() * 0.9)));
        this.gFormat = format;
    }
    
    public RectanglePlus(final int x, final int y, final int width, final int height, final Color c1, final Color c2, final Color c1a, final Color c2a, final gradientFormat format, final Color borderColor, final Font font, final Color fontColor, final String text) {
        super(x, y, width, height);
        this.color1 = new ArrayList<Color>();
        this.color2 = new ArrayList<Color>();
        this.border = new ArrayList<Color>();
        this.color1a = new ArrayList<Color>();
        this.color2a = new ArrayList<Color>();
        this.fontColor = new ArrayList<Color>();
        this.colorState = 0;
        this.colorStateMax = 0;
        this.hoveredOver = false;
        this.doDarken = false;
        this.text = "";
        this.font = null;
        this.color1.add(c1);
        this.color2.add(c2);
        this.color1a.add(c1a);
        this.color2a.add(c2a);
        this.border.add(borderColor);
        this.fontColor.add(fontColor);
        this.gFormat = format;
        this.font = font;
        this.text = text;
    }
    
    public RectanglePlus(final int x, final int y, final int width, final int height, final Color c1, final Color c2, final boolean doDarken, final gradientFormat format, final Color borderColor, final Font font, final Color fontColor, final String text) {
        super(x, y, width, height);
        this.color1 = new ArrayList<Color>();
        this.color2 = new ArrayList<Color>();
        this.border = new ArrayList<Color>();
        this.color1a = new ArrayList<Color>();
        this.color2a = new ArrayList<Color>();
        this.fontColor = new ArrayList<Color>();
        this.colorState = 0;
        this.colorStateMax = 0;
        this.hoveredOver = false;
        this.doDarken = false;
        this.text = "";
        this.font = null;
        this.color1.add(c1);
        this.color2.add(c2);
        this.doDarken = doDarken;
        this.color1a.add(new Color((int)(c1.getRed() * 0.9), (int)(c1.getGreen() * 0.9), (int)(c1.getBlue() * 0.9)));
        this.color2a.add(new Color((int)(c2.getRed() * 0.9), (int)(c2.getGreen() * 0.9), (int)(c2.getBlue() * 0.9)));
        this.border.add(borderColor);
        this.fontColor.add(fontColor);
        this.gFormat = format;
        this.font = font;
        this.text = text;
    }
    
    public RectanglePlus(final int x, final int y, final int width, final int height, final Color[] c1, final Color[] c2, final Color[] c1a, final Color[] c2a, final gradientFormat format, final Color[] borderColor, final Font font, final Color[] fontColor, final String text) {
        super(x, y, width, height);
        this.color1 = new ArrayList<Color>();
        this.color2 = new ArrayList<Color>();
        this.border = new ArrayList<Color>();
        this.color1a = new ArrayList<Color>();
        this.color2a = new ArrayList<Color>();
        this.fontColor = new ArrayList<Color>();
        this.colorState = 0;
        this.colorStateMax = 0;
        this.hoveredOver = false;
        this.doDarken = false;
        this.text = "";
        this.font = null;
        for (int i = 0; i < c1.length; ++i) {
            this.color1.add(c1[i]);
            if (i >= c2.length) {
                this.color2.add(c2[c2.length - 1]);
            }
            else {
                this.color2.add(c2[i]);
            }
            if (i >= c1a.length) {
                this.color1a.add(c1a[c1a.length - 1]);
            }
            else {
                this.color1a.add(c1a[i]);
            }
            if (i >= c2a.length) {
                this.color2a.add(c2a[c2a.length - 1]);
            }
            else {
                this.color2a.add(c2a[i]);
            }
            if (i >= borderColor.length) {
                this.border.add(borderColor[borderColor.length - 1]);
            }
            else {
                this.border.add(borderColor[i]);
            }
            if (i >= fontColor.length) {
                this.fontColor.add(fontColor[fontColor.length - 1]);
            }
            else {
                this.fontColor.add(fontColor[i]);
            }
        }
        this.colorStateMax = this.color1.size();
        this.gFormat = format;
        this.font = font;
        this.text = text;
    }
    
    public RectanglePlus(final int x, final int y, final int width, final int height, final Color[] c1, final Color[] c2, final boolean doDarken, final gradientFormat format, final Color[] borderColor, final Font font, final Color[] fontColor, final String text) {
        super(x, y, width, height);
        this.color1 = new ArrayList<Color>();
        this.color2 = new ArrayList<Color>();
        this.border = new ArrayList<Color>();
        this.color1a = new ArrayList<Color>();
        this.color2a = new ArrayList<Color>();
        this.fontColor = new ArrayList<Color>();
        this.colorState = 0;
        this.colorStateMax = 0;
        this.hoveredOver = false;
        this.doDarken = false;
        this.text = "";
        this.font = null;
        for (int i = 0; i < c1.length; ++i) {
            this.color1.add(c1[i]);
            if (i >= c2.length) {
                this.color2.add(c2[c2.length - 1]);
            }
            else {
                this.color2.add(c2[i]);
            }
            this.color1a.add(new Color((int)(this.color1.get(i).getRed() * 0.9), (int)(this.color1.get(i).getGreen() * 0.9), (int)(this.color1.get(i).getBlue() * 0.9)));
            this.color2a.add(new Color((int)(this.color2.get(i).getRed() * 0.9), (int)(this.color2.get(i).getGreen() * 0.9), (int)(this.color2.get(i).getBlue() * 0.9)));
            if (i >= borderColor.length) {
                this.border.add(borderColor[borderColor.length - 1]);
            }
            else {
                this.border.add(borderColor[i]);
            }
            if (i >= fontColor.length) {
                this.fontColor.add(fontColor[fontColor.length - 1]);
            }
            else {
                this.fontColor.add(fontColor[i]);
            }
        }
        this.colorStateMax = this.color1.size();
        this.gFormat = format;
        this.doDarken = doDarken;
        this.font = font;
        this.text = text;
    }
    
    public void draw(final Graphics2D g) {
        Color c1 = null;
        Color c2 = null;
        if (this.color1.size() != 0 && this.color2.size() != 0 && this.color1a.size() != 0 && this.color2a.size() != 0) {
            if (!this.hoveredOver) {
                c1 = this.color1.get(this.colorState);
                c2 = this.color2.get(this.colorState);
            }
            else {
                c1 = this.color1a.get(this.colorState);
                c2 = this.color2a.get(this.colorState);
            }
        }
        if (c1 != null && c2 != null && this.gFormat != null) {
            final int rRange = c1.getRed() - c2.getRed();
            final int gRange = c1.getGreen() - c2.getGreen();
            final int bRange = c1.getBlue() - c2.getBlue();
            double i = 0.0;
            if (this.gFormat == gradientFormat.horizontal) {
                for (double range = this.height - 1; i <= range; ++i) {
                    g.setColor(new Color((int)(c1.getRed() - rRange * (i / range)), (int)(c1.getGreen() - gRange * (i / range)), (int)(c1.getBlue() - bRange * (i / range))));
                    g.fillRect(this.x, this.y + (int)i, this.width, 1);
                }
            }
            else if (this.gFormat == gradientFormat.vertical) {
                for (double range = this.width; i <= range - 1.0; ++i) {
                    g.setColor(new Color((int)(c1.getRed() - rRange * (i / range)), (int)(c1.getGreen() - gRange * (i / range)), (int)(c1.getBlue() - bRange * (i / range))));
                    g.fillRect(this.x + (int)i, this.y, 1, this.height);
                }
            }
        }
        if (this.font != null) {
            g.setFont(this.font);
        }
        if (this.fontColor.size() != 0) {
            g.setColor(this.fontColor.get(this.colorState));
        }
        stringGraphics.drawStringFlow(this.text, this.getBounds(), textPosition.middle, g);
        if (this.border.size() != 0) {
            g.setColor(this.border.get(this.colorState));
        }
        g.draw(this);
    }
    
    
    //METHODS
    public void hoveredOver(final boolean hovered) {
        this.hoveredOver = hovered;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public void setTextColor(final Color[] color) {
        for (int i = 0; i < color.length; ++i) {
            this.fontColor.add(color[i]);
        }
    }
    
    public void setColors(final ArrayList<Color> c1, final ArrayList<Color> c2) {
        this.color1 = c1;
        this.color2 = c2;
    }
    
    public void setDarkenedColors(final ArrayList<Color> c1a, final ArrayList<Color> c2a) {
        this.color1a = c1a;
        this.color2a = c2a;
    }
    
    public void setFont(final Font font) {
        this.font = font;
    }
    
    public void doDarken(final boolean doDarken) {
        this.doDarken = doDarken;
    }
    
    public void nextState() {
        this.colorState++;
        if (this.colorState == this.colorStateMax) {
            this.colorState = 0;
        }
    }
    
    public void setColorState(final int state) {
        this.colorState = state;
    }
    
    public int getColorState() {
        return this.colorState;
    }
    
    public void setTextColor(final Color color) {

    }
    
    public enum gradientFormat
    {
        horizontal("horizontal", 0), 
        vertical("vertical", 1);
        
        private gradientFormat(final String name, final int ordinal) {
        }
    }
}
