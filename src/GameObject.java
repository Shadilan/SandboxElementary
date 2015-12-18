import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Zhitnikov.Bronislav on 17.12.2015.
 */
public class GameObject {
    protected int x;
    protected int y;
    protected Image image;
    protected World world;
    protected boolean Active=true;

    public GameObject(int x,int y,World world){
        this.x=x;
        this.y=y;
        this.world=world;
        image= new BufferedImage(40,40,BufferedImage.TYPE_INT_ARGB);
        Graphics g=image.getGraphics();
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, 39, 39);
        g.drawLine(0, 0, 40, 40);
        g.drawLine(40,0,0,40);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {

        return image;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }
    public void tick(){
        if (!Active) return;
    }
}
