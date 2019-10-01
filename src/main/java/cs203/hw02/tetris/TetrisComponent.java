package cs203.hw02.tetris;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import java.util.ArrayList;


public class TetrisComponent extends JComponent {

    private int shape;
    private Color color;
    private int xOff;
    private int yOff;
    private int size = 50;

    public TetrisComponent (int shape, Color color, int xOff, int yOff, int size) {
        int shapeMin = 0;
        int shapeMax = 6;
        int xMin = 0;
        int xMax = 1000;
        int yMin = xMin;
        int yMax = xMax;
        int sizeMin = 0;
        int sizeMax = 1000;
        this.shape = shape>shapeMax?shapeMax:(shape<shapeMin?shapeMin:shape);
        this.color = color;
        this.xOff = xOff>xMax?xMax:(xOff<xMin?xMin:xOff);
        this.yOff = yOff>yMax?yMax:(yOff<yMin?yMin:yOff);
        this.size = size>sizeMax?sizeMax:(size<sizeMin?sizeMin:size);
        
        System.out.println("SHAPE: "+this.shape);
        System.out.println("COLOR: "+this.color);
        System.out.println("X: "+this.xOff);
        System.out.println("Y: "+this.yOff);
        System.out.println("SZ: "+this.yOff);
    }


	public void paintComponent(Graphics graphic){	
        // Initialization
        Graphics2D g = (Graphics2D)graphic;
        g.setStroke(new BasicStroke(size/20));
        ArrayList<Rectangle> r = new ArrayList<>();
        int xi = 0; int yi = 0;
        int xo = 0; int yo = 0;
        int xs = xOff; int ys = yOff;
        Random rand = new Random();

        // Drawing Squares
        for(int i = 0; i < 4; i++) {
            r.add(new Rectangle(xs+(xi*size),ys+(yi*size),
                size,size));
            g.setColor(color);
            g.fill(r.get(i));
            g.setColor(Color.black);
            g.draw(r.get(i));
            do {
            xi = rand.nextInt(3)-1; // -1|0|1
            yi = rand.nextInt(3)-1; // -1|0|1
            } while ( ((xi+yi)*(xi+yi)!=1) || ( (xo==-xi) && (yo==-yi) ) );
            xs += xo*size; ys += yo*size;
            xo = xi; yo = yi;
            //CO-1: ABS(SUM(x,y))==1
            //CO-2: (x,y)!=(xNew,yNew)
            color = color.darker();
        }

	}
	

	private static final long serialVersionUID = 1L;

}
