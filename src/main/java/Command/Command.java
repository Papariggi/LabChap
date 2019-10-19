package command;

import army.Army;
import battleMap.BattleMap;

public abstract class Command implements AllCommands
{
    private BattleMap battleMap;
    private Army firstBeforeMove;
    private Army secondBeforeMove;
    private Army firstAfterMove;
    private Army secondAfterMove;


    public BattleMap getBattleMap() {
        return battleMap;
    }

    public void setBattleMap(BattleMap battleMap) {
        this.battleMap = battleMap;
    }

    public Army getFirstBeforeMove() {
        return firstBeforeMove;
    }

    public void setFirstBeforeMove(Army firstBeforeMove) {
        this.firstBeforeMove = firstBeforeMove;
    }

    public Army getSecondBeforeMove() {
        return secondBeforeMove;
    }

    public void setSecondBeforeMove(Army secondBeforeMove) {
        this.secondBeforeMove = secondBeforeMove;
    }

    public Army getFirstAfterMove() {
        return firstAfterMove;
    }

    public void setFirstAfterMove(Army firstAfterMove) {
        this.firstAfterMove = firstAfterMove;
    }

    public Army getSecondAfterMove() {
        return secondAfterMove;
    }

    public void setSecondAfterMove(Army secondAfterMove) {
        this.secondAfterMove = secondAfterMove;
    }
}
