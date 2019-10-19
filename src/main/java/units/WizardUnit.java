package units;

import java.util.Random;

public class WizardUnit extends Unit implements SpecialAction, Healing
{
    private static final Random rndm = new Random();
    private int strength;
    private int range;

    public WizardUnit()
    {
        setName("Wizard");
        setHealth(100);
        setAttack(rndm.nextInt(45 - 30 + 1) + 30);
        setArmor(rndm.nextInt(20 - 5 + 1) + 5);
        setCost(30);
        setRange(1);
        this.setStrength(rndm.nextInt(95 - 45 + 1) + 45);
    }

    public WizardUnit(WizardUnit wizardUnit)
    {
        setName(wizardUnit.getName());
        setHealth(wizardUnit.getHealth());
        setAttack(wizardUnit.getAttack());
        setArmor(wizardUnit.getArmor());
        setCost(wizardUnit.getCost());
        setRange(wizardUnit.getRange());
        this.setStrength(wizardUnit.getStrength());
    }

    @Override
    public AllUnits doSpecialAction(AllUnits unit) {
        if (unit instanceof Copied)
        {
            if (getStrength() > rndm.nextInt(100))
                return ((Copied) unit).clone();
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
        return new WizardUnit(this);
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
