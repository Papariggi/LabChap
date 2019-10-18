package strategy;

import army.Army;
import units.AllUnits;
import units.SpecialAction;

import java.util.List;

public interface AllStrategies
{
    int getRowCount();
    void setRowCount(int rowCount);
    String getInfo(Army army);
    List<AllUnits> possibleAllyTargets(Army allies, SpecialAction unit);
    List<AllUnits> possibleEnemyTargets(Army allies, Army enemies, SpecialAction unit);
}
