package battleMap;

import army.Army;
import strategy.AllStrategies;
import strategy.OneOnOneStrategy;
import units.*;
import units.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleMap
{
    private static final Random rndm = new Random();
    private AllStrategies strategy;
    private Army firstArmy;
    private Army secondArmy;

    private Subscriber subscriber;

    private String currentMoveInfo;
    private String allGameInfo;
    private boolean end;

    public BattleMap(Army firstArmy, Army secondArmy) {
        setFirstArmy(firstArmy);
        setSecondArmy(secondArmy);
        strategy = new OneOnOneStrategy();
        subscriber = new Subscriber();
    }

    private BattleMap() {}

    public void subscribe() {
        for (AllUnits unit : firstArmy.getArmy()) {
            unit.addObserver(subscriber);
        }
    }

    public void unsubscribe() {
        for (AllUnits unit : firstArmy.getArmy()) {
            unit.removeObserver(subscriber);
        }
    }

    public List<AllUnits> getFirstLine(Army army)
    {
        List<AllUnits> firstLine = new ArrayList<>();
        int min = Math.min(strategy.getRowCount(), army.size());
        for (int i = 0; i < min; i++)
            firstLine.add(army.get(army.size() - 1 - i));
        return firstLine;
    }


    public void armyVsArmy(Army first, Army second)
    {
        if (isEnd())
            return;

        List<AllUnits> firstLineInFirst = getFirstLine(first);
        List<AllUnits> firstLineInSecond = getFirstLine(second);

        int min = Math.min(strategy.getRowCount(),
                Math.min(first.size(), second.size()));

        for (int i = 0; i < min; i++)
        {
            AllUnits attacker = firstLineInFirst.get(i);
            AllUnits target = firstLineInSecond.get(i);

            currentMoveInfo += String.format("\n\nArmy %s. %s    ***vs.***   Army %s. %s",
                    first.getName(), attacker.getInfo(), second.getName(), target.getInfo());
            AllUnits unitAfterFight = attacker.fight(target);

            if (unitAfterFight.getHealth() == 0) {
                currentMoveInfo += String.format("\n\nArmy %s. %s is dead.\n", second.getName(), unitAfterFight.getName());
                unitAfterFight.notifyObs();
                second.remove(unitAfterFight);
            }
            else
                currentMoveInfo += String.format("\nArmy %s. %s was attacked.\n", second.getName(), unitAfterFight.getInfo());
        }
    }

    public List<SpecialAction> getSpecialUnitsInRow(Army army, int row)
    {
        List<SpecialAction> specials = new ArrayList<>();
        for (int i = army.size() - row - 1; i >= 0; i -= strategy.getRowCount()) {
            if (army.get(i) instanceof SpecialAction)
                specials.add((SpecialAction) army.get(i));
        }

        return specials;
    }

    public List<AllUnits> getTargets(Army first, Army second, SpecialAction unit)
    {
        if (unit instanceof BowmanUnit)
        return strategy.possibleEnemyTargets(first, second, unit);
            else
        return strategy.possibleAllyTargets(first, unit);
    }

    public void specialActions(Army first, Army second)
    {
        if (isEnd() || first.size() < strategy.getRowCount() + 1)
            return;

        for (int i = 0; i < strategy.getRowCount(); i++)
        {
            List<SpecialAction> specials = getSpecialUnitsInRow(first, i);
            if (specials.size() == 0)
                continue;

            int specialIndex = rndm.nextInt(specials.size());
            List<AllUnits> targets = getTargets(first, second, specials.get(specialIndex));
            if (targets.size() == 0)
                continue;

            int targetIndex = rndm.nextInt(targets.size());

            AllUnits beforeSpecial = targets.get(targetIndex).copy();
            AllUnits afterSpecial = specials.get(specialIndex).
                    doSpecialAction(targets.get(targetIndex));

            currentMoveInfo += "\nSpecial action.";

            if (specials.get(specialIndex) instanceof BowmanUnit) {
                if (afterSpecial != null) {
                    currentMoveInfo += String.format("\n\nArmy %s. %s    ******critical damage******    to Army %s. %s",
                            first.getName(), ((AllUnits)specials.get(specialIndex)).getInfo(), second.getName(),
                            beforeSpecial.getInfo());

                if (afterSpecial.getHealth() == 0) {
                    currentMoveInfo += String.format("\n\nArmy %s. %s dead.\n", second.getName(),
                            afterSpecial.getInfo());
                    afterSpecial.notifyObs();
                    second.remove(afterSpecial);
                }
                else
                    currentMoveInfo += String.format("\n\nArmy %s. %s got critical damage\n", second.getName(),
                            afterSpecial.getInfo());
            }
            else if (specials.get(specialIndex) instanceof ClericUnit) {
                if (afterSpecial != null) {
                    currentMoveInfo += String.format("\n\nArmy %s. %s ******healing******* Army %s. %s\n",
                            first.getName(), ((ClericUnit) specials.get(specialIndex)).getInfo(), first.getName(),
                            beforeSpecial.getInfo());
                    currentMoveInfo += String.format("\n\nArmy %s. %s healed\n", first.getName(),
                            (afterSpecial.getInfo()));
                }
                else {
                    currentMoveInfo += String.format("\n\nNo one was healed from the army %s. ", first.getName());
                }
            }
            else if (specials.get(specialIndex) instanceof WizardUnit) {
                if (afterSpecial != null) {
                    currentMoveInfo += String.format("\nАrmy %s. %s    *****cloning*****   Аrmy %s. %s\n",
                            first.getName(), ((WizardUnit) specials.get(specialIndex)).getInfo(), first.getName(),
                            beforeSpecial.getInfo());
                    currentMoveInfo += String.format("\n\nАrmy %s. %s was cloned.\n", first.getName(),
                            afterSpecial.getInfo());
                }
                else {
                    currentMoveInfo += String.format("\n\nNo one was cloned from the army %s.\n", first.getName());
                }
            }
            else if (specials.get(specialIndex) instanceof LightUnit) {
                    if (afterSpecial != null) {
                        currentMoveInfo += String.format("\n\nArmy %s. %s     ****dressing****      Army %s. %s",
                                first.getName(), ((LightUnit) specials.get(specialIndex)).getInfo(), first.getName(),
                                beforeSpecial.getInfo());
                        currentMoveInfo += String.format("\n\nArmy %s. %s was dressed.\n", first.getName(),
                                (afterSpecial.getInfo()));
                    }
                    else {
                        currentMoveInfo += String.format("\n\nNo one was dressed from the army %s. ", first.getName());
                    }
                }
            }
        }
    }

    public void move()
    {
        if (isEnd())
        {
            currentMoveInfo = "Game is over. ";
            return;
        }

        currentMoveInfo = "\n\n----------Battle------------";

        armyVsArmy(firstArmy, secondArmy);
        armyVsArmy(secondArmy, firstArmy);
        specialActions(firstArmy, secondArmy);
        specialActions(secondArmy, firstArmy);
    }

    public boolean isEnd()
    {
        if (end)
            return true;

        if (firstArmy.isEmpty() || secondArmy.isEmpty())
        {
            end = true;
            currentMoveInfo += "\n\n\t******** Game over. ";
            if (firstArmy.isEmpty())
                currentMoveInfo += "The second army is won. ********\n";
            else
                currentMoveInfo += "The first army won. ********\n";
            return true;
        }

        return false;
    }

    public void playToTheEnd() {
        while (!isEnd()) {
            move();
            allGameInfo += currentMoveInfo;
        }
    }


    public String getArmiesInfo() {
        return String.format("%s\n\n vs. \n\n%s",
                strategy.getInfo(firstArmy), strategy.getInfo(secondArmy));
    }

    public String getAllGameInfo() {
        return allGameInfo;
    }

    public void setAllGameInfo(String allGameInfo) {
        this.allGameInfo = allGameInfo;
    }

    public String getCurrentMoveInfo() {
        return currentMoveInfo;
    }

    public void setCurrentMoveInfo(String currentMoveInfo) {
        this.currentMoveInfo = currentMoveInfo;
    }

    public Army getSecondArmy() {
        return secondArmy;
    }

    public void setSecondArmy(Army secondArmy) {
        this.secondArmy = secondArmy;
    }

    public AllStrategies getStrategy() {
        return strategy;
    }

    public void setStrategy(AllStrategies strategy) {
        this.strategy = strategy;
    }

    public Army getFirstArmy() {
        return firstArmy;
    }

    public void setFirstArmy(Army firstArmy) {
        this.firstArmy = firstArmy;
    }
}
