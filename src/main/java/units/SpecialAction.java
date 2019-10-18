package units;

public interface SpecialAction
{
    AllUnits doSpecialAction(AllUnits unit);
    int getRange();
    int getStrength();
    void setRange(int range);
    void setStrength(int strength);
}
