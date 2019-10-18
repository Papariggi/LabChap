package units.unitDecorator;

import units.AllUnits;

public class AxeDecorator extends UnitDecorator
{
    public AxeDecorator(AllUnits unit) {
        super(unit);

        unit.setAttack(unit.getAttack() + 20);
        unit.setName(unit.getName() + " has axe");
    }
}
