package units;

import java.util.Random;

public class BowmanUnit extends Unit implements SpecialAction, Healing, Copied
{
    private static final Random rndm = new Random();
    private int strength;
    private int range;

    public BowmanUnit()
    {
        setName("Bowman");
        setHealth(100);
        setAttack(rndm.nextInt(75 - 55 + 1) + 55);
        setArmor(rndm.nextInt(25 - 10 + 1) + 10);
        setCost(15);
        setRange(5);
        setStrength((rndm.nextInt(90 - 70 + 1) + 70));
    }

    public BowmanUnit(BowmanUnit bowmanUnit)
    {
        setName(bowmanUnit.getName());
        setHealth(bowmanUnit.getHealth());
        setAttack(bowmanUnit.getAttack());
        setArmor(bowmanUnit.getArmor());
        setCost(bowmanUnit.getCost());
        setRange(bowmanUnit.getRange());
        setStrength(bowmanUnit.getStrength());
    }

    @Override
    public AllUnits doSpecialAction(AllUnits unit) {
        if (getStrength() > unit.getArmor() + unit.getHealth())
            return this;

        unit.setArmor(unit.getArmor() - getStrength());
        if (unit.getArmor() < 0) {
            unit.setHealth(unit.getHealth() + unit.getArmor());
            unit.setArmor(0);
        }
        return unit;
    }


    @Override
    public String getInfo()
    {
        return super.getInfo() + String.format(", strength: %d", getStrength());
    }

    @Override
    public AllUnits copy() {
        return new BowmanUnit(this);
    }

    @Override
    public AllUnits clone() {
        return new BowmanUnit(this);
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public void setRange(int range) {
        this.range = range;
    }
}
