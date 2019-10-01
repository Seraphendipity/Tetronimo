package cs203.hw02.tetris;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

public class TetrisGrid 
{
    public static void main( String[] args )
    {
        Random rand = new Random();
        int fMin = 0;
        int fMax = 1000;
        int size = 10;
        int rr;
        int rb;
        int rg;
        Color rc;
        int n = 4;
        int box = ((2*n)-1)*size;
        int x = box/2;
        int y = box/2;

        JFrame frame = new JFrame();
        frame.setSize(fMax,fMax);
        frame.setTitle("Tetris Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int i = 0;
        ArrayList<TetrisComponent> t = new ArrayList<>();
        while( y < fMax ) {
            while( x < fMax ) {
                rr = rand.nextInt(256);
                rb = rand.nextInt(256);
                rg = rand.nextInt(256);
                rc = new Color(rr,rg,rb);

                t.add(new TetrisComponent(4, rc, x, y, size));
                frame.add(t.get(i));
                x += box;
                System.out.println(x);
                i++;
            }
            y += box;
            x = box/2;
        }


        frame.setVisible(true);
    }
}