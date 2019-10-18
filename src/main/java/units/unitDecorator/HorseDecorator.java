package units.unitDecorator;

import units.AllUnits;

public class HorseDecorator extends UnitDecorator
{
    public HorseDecorator(AllUnits unit) {
        super(unit);

        unit.setAttack(unit.getAttack() + 20);
        unit.setArmor(unit.getArmor() + 10);
        unit.setName(unit.getName() + " has horse");
    }
}
