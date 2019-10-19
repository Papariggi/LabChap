package command;

import army.Army;
import battleMap.BattleMap;

public interface AllCommands
{
    void makeMove();
    void undo();
    void redo();
    BattleMap getBattleMap();
    void setBattleMap(BattleMap battleMap);
    Army getFirstBeforeMove();
    void setFirstBeforeMove(Army army);
    Army getSecondBeforeMove();
    void setSecondBeforeMove(Army army);
    Army getFirstAfterMove();
    void setFirstAfterMove(Army army);
    Army getSecondAfterMove();
    void setSecondAfterMove(Army army);
}
