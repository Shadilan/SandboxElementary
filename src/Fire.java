import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Zhitnikov.Bronislav on 18.12.2015.
 */
public class Fire extends GameObject implements GameItem {

    public Fire(int x, int y, World world) {
        super(x,y,world);

        image=new BufferedImage(20,20,BufferedImage.TYPE_INT_ARGB);
        Graphics g=image.getGraphics();
        g.setColor(Color.RED);
        g.fillOval(0,0,20,20);
    }
    int Life=100;
    public void tick(){
        Life--;
        if (Life<0) world.removeObject(this);
    }

}
