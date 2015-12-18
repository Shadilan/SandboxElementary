import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

/**
 * Created by Zhitnikov.Bronislav on 18.12.2015.
 */
public class Main {
    World world;
    JFrame frmMain;

    private Main(){
        final JFrame frmMain = new JFrame();
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMain.setSize(510, 630);
       frmMain.setVisible(true);
        final DrawPanel panel = new DrawPanel();
        panel.setPreferredSize(new Dimension(500, 600));
        frmMain.add(panel);
        world=new World(1000,1000);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                double sx=(double)panel.getWidth()/(double)500;
                double sy=(double)panel.getHeight()/(double)600;
                world.setClick((int)(e.getX()*sx),(int)(e.getY()*sy));
            }
        });
        Thread thread =new Thread(new ProcessRunnable());
        thread.start();
        world.addObject(new Fire(600,600,world));
    }
    public static void main(String[] args)
    {
       Main m=new Main();
    }
    private class ProcessRunnable implements Runnable{
        public boolean runFlag=true;
        public void run() {
            while (runFlag) {
                world.tick();
                try {

                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public class DrawPanel extends JComponent implements Runnable {

        private long t = System.nanoTime();

        public DrawPanel() {
            super();
            new Thread(this).start();
        }

        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(33);
                } catch (InterruptedException ex) {}
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            long tm = System.nanoTime() - t;

            AffineTransform t=new AffineTransform();
            Image i=world.getImage();

            double sx=(double)this.getWidth()/(double)500;
            double sy=(double)this.getHeight()/(double)600;
            t.setToScale(sx, sy);
            g2d.drawImage(i, t, null);
        }
    }
}
