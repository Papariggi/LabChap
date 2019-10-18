package factory;

import units.*;

import java.util.Random;

public  class UnitFactory
{
    public AllUnits createUnit()
    {
        Random rndm = new Random();
        int chance = rndm.nextInt(100);

        if (chance < 40)
            return new LightUnit();
        else if (chance < 60)
            return new HeavyUnitProxy(new HeavyUnit());
        else if (chance < 80)
            return new ClericUnit();
        else if (chance < 90)
            return new BowmanUnit();
        else
            return new WizardUnit();
    }
}
