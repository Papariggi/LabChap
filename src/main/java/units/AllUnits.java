package units;


import units.observer.Observer;
import units.observer.ObserverManager;

public interface AllUnits extends ObserverManager
{
    AllUnits fight(AllUnits unit);
    AllUnits copy();
    String getInfo();
    String getName();
    int getHealth();
    int getAttack();
    int getArmor();
    int getCost();
    void setHealth(int health);
    void setArmor(int armor);
    void setAttack(int attack);
    void setName(String name);
    void setCost(int cost);

}
