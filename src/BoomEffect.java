import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Zhitnikov.Bronislav on 18.12.2015.
 */
public class BoomEffect extends GameObject implements GameEffect {
    int rad=0;
    public BoomEffect(int x, int y, World world) {
        super(x, y, world);
        image=new BufferedImage(40,40,BufferedImage.TYPE_INT_ARGB);
        Graphics g=image.getGraphics();
        g.setColor(Color.RED);
        g.drawOval(0,0,39+rad,39+rad);
    }
    public void tick(){
        rad++;
        image=new BufferedImage(40,40,BufferedImage.TYPE_INT_ARGB);
        Graphics g=image.getGraphics();
        g.setColor(Color.RED);
        g.drawOval(0,0,39+rad,39+rad);
        for (GameObject o:world.getObjs()){
            int distance= (int) Math.sqrt(Math.pow(o.getX()-x,2)+Math.pow(o.getY()-y,2));
            if (distance<500){
                if (o instanceof GameMob && distance<40+rad){
                    ((GameMob) o).doDamage(this);
                }
            }
        }
    }

    @Override
    public int getPower() {
        return 40-rad;
    }
}
