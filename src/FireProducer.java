import java.awt.*;

/**
 * Created by Zhitnikov.Bronislav on 18.12.2015.
 */
public class FireProducer extends GameObject implements GameStructure{

    public FireProducer(int x, int y, World world) {
        super(x, y, world);
        Graphics g=image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 40, 40);
        g.setColor(Color.RED);
        g.drawRect(0, 0, 39, 39);
        g.drawRect(2, 2, 35, 35);
        g.fillRect(10, 10, 20, 20);
        g.setColor(Color.BLACK);
        g.fillOval(10, 10, 19, 19);
    }
    int respawn=20;
    @Override
    public int getReceipt() {
        return 0;
    }
    public void tick(){
        respawn--;
        System.out.println("tick^"+respawn);
        if (respawn<0) {
            respawn=(int)(Math.random()*100);
            double angle=Math.random()*2*Math.PI-Math.PI;
            System.out.print("Produce Fire."+(int)(Math.sin(angle)*100)+" "+(int)(Math.cos(angle)*100));
            world.addObject(new Fire((int)(Math.sin(angle)*100)+x,(int)(Math.cos(angle)*100)+y,world));
        }
    }

    @Override
    public boolean doRepair(Player player) {
        return false;
    }

    @Override
    public boolean doDamage(GameEffect effect) {
        return false;
    }
}
