package units.unitDecorator;

import units.AllUnits;
import units.Unit;

public abstract class UnitDecorator extends Unit
{
    public AllUnits unit;

    public UnitDecorator(AllUnits unit) {
        this.unit = unit;
    }

    public AllUnits getUnit() {
        return unit;
    }

    public void setUnit(AllUnits unit) {
        this.unit = unit;
    }
}
