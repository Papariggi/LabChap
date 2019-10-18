package units;

import java.io.FileWriter;
import java.io.IOException;

public class HeavyUnitProxy extends HeavyUnit implements Cloneable
{
    private HeavyUnit heavyUnit;

    public HeavyUnitProxy(HeavyUnit heavyUnit)
    {
        this.heavyUnit = heavyUnit;
        setName(heavyUnit.getName());
        setHealth(heavyUnit.getHealth());
        setAttack(heavyUnit.getAttack());
        setArmor(heavyUnit.getArmor());
        setCost(heavyUnit.getCost());
    }

    @Override
    public AllUnits fight(AllUnits unit) {
        String info = String.format("Battle. {0} x {1}. ",
                getInfo(), unit.getInfo());
        AllUnits afterFight = heavyUnit.fight(unit);

        if(afterFight == null)
            info += "Opponent has died. ";
        else
            info += String.format("After fight: {0} .", unit.getInfo());

        log(info);
        return afterFight;
    }

    public void log(String info)
    {
        try(FileWriter writer = new FileWriter("ProxyLog.txt", true))
        {
            writer.write(info);
            writer.append('\n');
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public AllUnits clone() {
        return new HeavyUnitProxy(this);
    }

    @Override
    public String getInfo() {
        return heavyUnit.getInfo();
    }
}
