package command;

import battleMap.BattleMap;

public class PlayToEndCommand extends Command
{
    public PlayToEndCommand(BattleMap battleMap)
    {
        setBattleMap(battleMap);
    }

    @Override
    public void makeMove() {
        setFirstBeforeMove(getBattleMap().getFirstArmy().getSnapshot());
        setSecondBeforeMove(getBattleMap().getSecondArmy().getSnapshot());
        getBattleMap().playToTheEnd();
        setFirstAfterMove(getBattleMap().getFirstArmy().getSnapshot());
        setSecondAfterMove(getBattleMap().getSecondArmy().getSnapshot());
    }

    @Override
    public void undo() {
        getBattleMap().setFirstArmy(getFirstBeforeMove());
        getBattleMap().setSecondArmy(getSecondBeforeMove());
    }

    @Override
    public void redo() {
        getBattleMap().setSecondArmy(getFirstAfterMove());
        getBattleMap().setSecondArmy(getSecondAfterMove());
    }
}
