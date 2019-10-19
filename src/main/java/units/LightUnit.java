package units;

import units.unitDecorator.AxeDecorator;
import units.unitDecorator.HelmetDecorator;
import units.unitDecorator.HorseDecorator;
import units.unitDecorator.ShieldDecorator;

import java.util.Random;

public class LightUnit extends Unit implements SpecialAction, Healing, Copied
{
    private static final Random rndm = new Random();
    private int strength;
    private int range;

    public LightUnit()
    {
        setName("Light");
        setHealth(100);
        setAttack((rndm.nextInt(50 - 30 + 1) + 30));
        setArmor(rndm.nextInt(40 - 25 + 1) + 25);
        setCost(10);
        setRange(1);
        setStrength(rndm.nextInt(70 - 60 + 1) + 60);
    }

    public LightUnit(LightUnit lightUnit)
    {
        setName(lightUnit.getName());
        setHealth(lightUnit.getHealth());
        setAttack(lightUnit.getAttack());
        setArmor(lightUnit.getArmor());
        setCost(lightUnit.getCost());
        setRange(lightUnit.getRange());
        setStrength(lightUnit.getStrength());
    }


    public AllUnits doSpecialAction(AllUnits unit) {
        if (!(unit instanceof HeavyUnit))
            return null;

        HeavyUnit heavyUnit = (HeavyUnit) unit;

        if (heavyUnit.hasAxe() & heavyUnit.hasShield() &
        heavyUnit.hasHelmet() & heavyUnit.hasHorse())
            return null;

        switch (rndm.nextInt(4))
        {
            case 0: {
                if (!heavyUnit.hasHorse()) {
                    heavyUnit.setHorse(true);
                    return new HorseDecorator(unit);
                }
                break;
            }
            case 1: {
                if (!heavyUnit.hasHelmet()) {
                    heavyUnit.setHelmet(true);
                    return new HelmetDecorator(unit);
                }
                break;
            }
            case 2: {
                if (!heavyUnit.hasShield()) {
                    heavyUnit.setShield(true);
                    return new ShieldDecorator(unit);
                }
                break;
            }
            case 3: {
                if (!heavyUnit.hasAxe()) {
                    heavyUnit.setAxe(true);
                    return new AxeDecorator(unit);
                }
                break;
            }
        }

        return null;
    }

    @Override
    public AllUnits copy() {
        return new LightUnit(this);
    }

    @Override
    public AllUnits clone() {
        return new LightUnit(this);
    }

    @Override
    public String getInfo() {
        return super.getInfo();
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
