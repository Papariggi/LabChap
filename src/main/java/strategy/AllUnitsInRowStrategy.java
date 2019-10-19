package strategy;

import army.Army;
import units.AllUnits;
import units.SpecialAction;

import java.util.ArrayList;
import java.util.List;

public class AllUnitsInRowStrategy extends Strategy
{

    public AllUnitsInRowStrategy(Army first, Army second)
    {
        setRowCount(Math.min(first.size(), second.size()));
    }


    @Override
    public List<AllUnits> possibleAllyTargets(Army allies, SpecialAction unit) {
        List<AllUnits> allyTargets = new ArrayList<>(5);
        int index = allies.indexOf((AllUnits) unit);
        int rowIndex = (((allies.size() - index) % getRowCount()) == 0) ?
                ((allies.size() - index) % getRowCount()) : getRowCount() -
                ((allies.size() - index) % getRowCount());

        switch (rowIndex)
        {
            case 0: {
                allyTargets.add(allies.get(index + 1));
                allyTargets.add(allies.get(index + getRowCount()));
                allyTargets.add(allies.get(index + getRowCount() + 1));

                if (index >= getRowCount() - 1)
                    allyTargets.add(allies.get(index - getRowCount() + 1));
                if (index >= getRowCount())
                    allyTargets.add(allies.get(index - getRowCount()));
                break;
            }
            case 1: {
                for (int i = 0; i < 4; i++) {
                    allyTargets.add(allies.get(index + 1 + i));
                    int j = index - 1 - i;
                    if (j >= 0)
                        allyTargets.add(allies.get(j));
                }
                break;
            }
            case 2: {
                allyTargets.add(allies.get(index + getRowCount()));
                allyTargets.add(allies.get(index + getRowCount() + 1));

                if (index > getRowCount() - 1)
                    allyTargets.add(allies.get(index - getRowCount()));
                if (index > getRowCount())
                    allyTargets.add(allies.get(index - 1));
                break;
            }
        }

        return allyTargets;
    }

    @Override
    public List<AllUnits> possibleEnemyTargets(Army allies, Army enemies, SpecialAction unit) {
        List<AllUnits> allyTargets = new ArrayList<>(5);
        int index = allies.indexOf((AllUnits) unit);
        int rowIndex = (((allies.size() - index) % getRowCount()) == 0) ?
                ((allies.size() - index) % getRowCount()) : getRowCount() -
                ((allies.size() - index) % getRowCount());
        int lineIndex = (allies.size() - index - 1) / getRowCount();

        if (lineIndex >= unit.getRange()) //todo: check
            return allyTargets;

        int targetsCount = unit.getRange() - lineIndex;
        for (int i = enemies.size() - getRowCount() + rowIndex;
             i >= 0 && targetsCount > 0; i -= getRowCount(), targetsCount--)
            allyTargets.add(enemies.get(i));

        return allyTargets;

    }

    @Override
    public String getInfo(Army army) {
        String info = String.format("Army %s:", army.getName());
        for (int j = 0; j < getRowCount(); j++)
        {
            info += String.format("\nRow %d:", j + 1);
            for (int line = 1, i = army.size() - getRowCount() + j; i >= 0; line++, i -= getRowCount())
                info += String.format("\nLine %d. %s", line, army.get(i).getInfo());
        }
        return info;
    }
}
