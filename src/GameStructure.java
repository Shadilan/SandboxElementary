/**
 * Created by Zhitnikov.Bronislav on 17.12.2015.
 */
public interface GameStructure {
    public int getReceipt();
    public boolean doRepair(Player player);
    public boolean doDamage(GameEffect effect);
}
