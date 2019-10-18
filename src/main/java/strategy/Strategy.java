package strategy;

import army.Army;

public abstract class Strategy implements AllStrategies
{
    private int rowCount;

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }


}
