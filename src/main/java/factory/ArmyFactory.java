package factory;

import army.Army;
import units.AllUnits;

public class ArmyFactory
{
    public Army createArmy(int money, String name)
    {
        UnitFactory unitFactory = new UnitFactory();
        Army army = new Army(name);

        while (money >= 10)
        {
            AllUnits unit = unitFactory.createUnit();
            if (money - unit.getCost() >= 0)
            {
                army.push(unit);
                money -= unit.getCost();
            }
        }
        return army;
    }
}
