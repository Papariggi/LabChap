package units;

import java.util.Random;

public class ClericUnit extends Unit implements SpecialAction, Healing
{
    private static final Random rndm = new Random();
    private int strength;
    private int range;

    public ClericUnit()
    {
        setName("Clearic");
        setHealth(100);
        setAttack(rndm.nextInt(35 - 20 + 1) + 20);
        setArmor(rndm.nextInt(40 - 20 + 1) + 20);
        setCost(15);
        setRange(1);
        setStrength(rndm.nextInt(90 - 60 + 1) + 60);
    }

    public ClericUnit(ClericUnit clericUnit)
    {
        setName(clericUnit.getName());
        setHealth(clericUnit.getHealth());
        setAttack(clericUnit.getAttack());
        setArmor(clericUnit.getArmor());
        setCost(clericUnit.getCost());
        setRange(clericUnit.getRange());
        setStrength(clericUnit.getStrength());
    }

    @Override
    public AllUnits doSpecialAction(AllUnits unit) {
        if (unit instanceof Healing)
        {
            ((Healing)unit).heal(getStrength());
            return unit;
        }

        return null;
    }

    @Override
    public String getInfo()
    {
        return super.getInfo() + String.format(", strength: %d", getStrength());
    }

    @Override
    public AllUnits copy() {
        return new ClericUnit(this);
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
