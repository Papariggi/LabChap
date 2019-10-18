package units;

public interface Healing extends AllUnits
{
    default void heal(int health) {
        setHealth(getHealth() + health);

        if (getHealth() > 100)
            setHealth(100);
    }

}
