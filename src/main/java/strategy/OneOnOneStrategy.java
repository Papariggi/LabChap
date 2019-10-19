package strategy;

import army.Army;
import units.AllUnits;
import units.SpecialAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OneOnOneStrategy extends Strategy
{
    public OneOnOneStrategy()
    {
        setRowCount(1);
    }

    @Override
    public List<AllUnits> possibleAllyTargets(Army allies, SpecialAction unit) {
        List<AllUnits> allyTargets = new ArrayList<>(5);
        int index = allies.indexOf((AllUnits) unit);
        int possibleTargetStart = (index - unit.getRange() > 0) ?
                (index - unit.getRange()) : 0;
        int possibleTargetEnd = ((index + unit.getRange()) > allies.size()) ?
                allies.size() : (index + unit.getRange());

        for (int i = possibleTargetStart; i < possibleTargetEnd; i++) {
            allyTargets.add(allies.get(i));
        }

        return allyTargets;
    }

    @Override
    public List<AllUnits> possibleEnemyTargets(Army allies, Army enemies, SpecialAction unit) {
        List<AllUnits> enemyTargets = new ArrayList<>(5);
        int index = allies.indexOf((AllUnits) unit);
        int possibleTargetCount = (index >= (allies.size() - unit.getRange())) ?
                (unit.getRange() - (allies.size() - 1 - index)) : 0;

        if (possibleTargetCount > enemies.size())
            possibleTargetCount = enemies.size();

        for (int i = 0; i < possibleTargetCount; i++)
            enemyTargets.add(enemies.get(enemies.size() - 1 - i));

        return enemyTargets;
    }

    @Override
    public String getInfo(Army army) {
        String info = String.format("Army %s:", army.getName());
        for (int i = 1; i <= army.size(); i++)
            info += String.format("\n%d. %s", i, army.get(i - 1).getInfo());

        return info;
    }
}
