package units.observer;

import army.Army;
import units.AllUnits;

public interface Observer
{
    void handleEvent(AllUnits unit);
}
