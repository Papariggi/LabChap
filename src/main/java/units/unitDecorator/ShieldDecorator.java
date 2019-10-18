package units.unitDecorator;

import units.AllUnits;

public class ShieldDecorator extends UnitDecorator
{
    public ShieldDecorator(AllUnits unit) {
        super(unit);

        unit.setArmor(unit.getArmor() + 30);
        unit.setName(unit.getName() + " has shield");
    }
}
