/**
 * Created by Zhitnikov.Bronislav on 18.12.2015.
 */
public class FireStruct extends GameObject implements GameStructure{

    public FireStruct(int x, int y, World world) {
        super(x, y, world);
    }
    int hp=5;
    @Override
    public int getReceipt() {
        return 1;
    }
    public void tick(){
        for (GameObject o:world.getObjs()){
            int distance= (int) Math.sqrt(Math.pow(o.getX()-x,2)+Math.pow(o.getY()-y,2));
            if (distance<500){
                if (o instanceof GameMob  && distance<100){

                    world.removeObject(this);
                    world.addObject(new BoomEffect(x,y,world));
                } else if (o instanceof Player && distance>100){
                    world.removeObject(this);
                    world.addObject(new BoomEffect(x,y,world));
                }


            }
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
