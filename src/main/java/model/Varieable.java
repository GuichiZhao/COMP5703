package model;

/**
 * Created by guichi on 28/03/2015.
 */
public class Varieable {
    private String name;
    private boolean isPresent;

    public boolean isPresent() {
        return isPresent;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    public String getName() {

        return name;
    }

    public Varieable(boolean isPresent, String name) {

        this.isPresent = isPresent;
        this.name = name;
    }

    @Override
    public String toString() {
        return name ;
    }
}
