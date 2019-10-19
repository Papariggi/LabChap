package strategy;

import army.Army;
import units.AllUnits;
import units.SpecialAction;

import java.util.ArrayList;
import java.util.List;

public class ThreeOnThreeStrategy extends Strategy
{
    public ThreeOnThreeStrategy()
    {
        setRowCount(3);
    }

    @Override
    public List<AllUnits> possibleAllyTargets(Army allies, SpecialAction unit) {
        List<AllUnits> allyTargets = new ArrayList<>(5);
        int index = allies.indexOf((AllUnits) unit);
        int rowIndex = (allies.size() - index - 1) % getRowCount();
        int lineIndex = (allies.size() - index - 1) / getRowCount();

        for (int i = 0; i < allies.size(); i++) {
            int currRowIndex = (allies.size() - i - 1) % getRowCount();
            int currLineIndex = (allies.size() - i - 1) / getRowCount();

            //checking distance between allies
            if (Math.sqrt((currRowIndex - rowIndex) * (currRowIndex - rowIndex) +
                    (currLineIndex - lineIndex) * (currLineIndex - lineIndex)) <=
                    unit.getRange()) {
                allyTargets.add(allies.get(i));
            }
        }

        return allyTargets;
    }

    @Override
    public List<AllUnits> possibleEnemyTargets(Army allies, Army enemies, SpecialAction unit) {
        List<AllUnits> enemyTargets = new ArrayList<>(5);
        int index = allies.indexOf((AllUnits) unit);
        int rowIndex = (allies.size() - index - 1) % getRowCount();
        int lineIndex = (allies.size() - index - 1) / getRowCount();

        if (lineIndex >= unit.getRange()) //todo:check method
            return enemyTargets;

        int targetsCount = unit.getRange() - lineIndex;

        for (int i = enemies.size() - 1 - rowIndex;
             i >= 0 && targetsCount > 0; i -= getRowCount(), targetsCount--)
            enemyTargets.add(enemies.get(i));

        return enemyTargets;
    }

    @Override
    public String getInfo(Army army) {
        String info = String.format("Army %s:", army.getName());
        for (int j = 0; j < getRowCount(); j++)
        {
            info += String.format("\nRow %d:", j + 1);
            for (int line = 1, i = army.size() - j - 1; i >= 0; line++, i -= getRowCount())
                info += String.format("\nLine %d. %s",line, army.get(i).getInfo());
        }
        return info;
    }
}
