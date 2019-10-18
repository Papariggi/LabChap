package units.unitDecorator;

import units.AllUnits;

public class HelmetDecorator extends UnitDecorator
{
    public HelmetDecorator(AllUnits unit) {
        super(unit);

        unit.setArmor(unit.getArmor() + 20);
        unit.setName(unit.getName() + " has helmet");
    }
}
