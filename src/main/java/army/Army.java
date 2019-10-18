package army;

import units.AllUnits;

import java.util.Stack;

public class Army
{
    private Stack<AllUnits> army;
    private String name;

    public Army(String name)
    {
        army = new Stack<>();
        setName(name);
    }

    public Army(Army copiedArmy)
    {
        setName(copiedArmy.getName());

        for (AllUnits unit : copiedArmy.getArmy()) {
            army.push(unit.clone());
        }
    }

    public Army getSnapshot(){
        return new Army(this);
    }

    public void push(AllUnits unit){
        army.push(unit);
    }

    public AllUnits pop(){
        if (!army.isEmpty())
            return army.pop();
        else
            return null;
    }

    public int remove(AllUnits unit)
    {
        int i = army.indexOf(unit);
        army.remove(unit);
        return i;
    }

    public AllUnits get(int index) {
        return army.get(index);
    }
    public int size() {
        return army.size();
    }

    public boolean isEmpty() {
        return army.size() == 0;
    }

    public int indexOf(AllUnits unit) {
        return army.indexOf(unit);
    }

    public Stack<AllUnits> getArmy() {
        return army;
    }

    public void setArmy(Stack<AllUnits> army) {
        this.army = army;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
