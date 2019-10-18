package units;

import java.util.Random;

public class HeavyUnit extends Unit
{
    private static final Random rndm = new Random();
    private boolean horse;
    private boolean shield;
    private boolean axe;
    private boolean helmet;

    public HeavyUnit()
    {
        setName("Heavy");
        setHealth(100);
        setAttack(rndm.nextInt(70 - 50 + 1) + 50);
        setArmor(rndm.nextInt(80 - 60 + 1) + 60);
        setCost(25);
    }

    public HeavyUnit(HeavyUnit heavyUnit)
    {
        setName(heavyUnit.getName());
        setHealth(heavyUnit.getHealth());
        setAttack(heavyUnit.getAttack());
        setArmor(heavyUnit.getArmor());
        setCost(heavyUnit.getCost());
    }


    @Override
    public AllUnits clone()
    {
        return new HeavyUnit(this);
    }


    public boolean hasHelmet() {
        return helmet;
    }

    public void setHelmet(boolean helmet) {
        this.helmet = helmet;
    }

    public boolean hasAxe() {
        return axe;
    }

    public void setAxe(boolean axe) {
        this.axe = axe;
    }

    public boolean hasShield() {
        return shield;
    }

    public void setShield(boolean shield) {
        this.shield = shield;
    }

    public boolean hasHorse() {
        return horse;
    }


    public void setHorse(boolean horse) {
        this.horse = horse;
    }
}
