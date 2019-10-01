package cs203.hw02.tetris;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TetrisViewer 
{
    public static void main( String[] args )
    {
        Random rand = new Random();
        int fMax = 1000; // Frame Max
        int fMin = 0; // Frame Min
        int maxDisp = fMax/4; // Max Displacement; 
            // Margins of origin are 0.25*fMax
        
        int rx = rand.nextInt((maxDisp*2))-maxDisp; //random x
        int ry = rand.nextInt((maxDisp*2))-maxDisp; //random x
        int rmax = rx>=ry?rx:ry;
        int rt = 4;//rand.nextInt(15)+3; // random tetonimo
        int rs = rand.nextInt((fMax-rmax)/((2*rt)-1))+4; // random size
        int fCen = ((fMax-fMin)/2);

        int rr = rand.nextInt(256);
        int rb = rand.nextInt(256);
        int rg = rand.nextInt(256);
        Color rc = new Color(rr,rg,rb);

        NtrisComponent t = new NtrisComponent(rt,rc,fCen+rx,fCen+ry,rs);

        // Constants: fMax, maxDisp
        // Variables: size
        // (n*size) < (fMax-maxDisp)

        JFrame frame= new JFrame();
        frame.setSize(fMax,fMax);
        frame.setTitle("CS 203 lecture 6");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.add(t);
        frame.setVisible(true);
    }
}
