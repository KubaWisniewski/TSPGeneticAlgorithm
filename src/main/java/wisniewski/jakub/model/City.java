package wisniewski.jakub.model;

import java.util.Objects;

public class City {
    private Integer x;
    private Integer y;

    public City() {
    }

    public City(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public double getDistanceTo(City city) {
        return Math.sqrt(
                ((this.getX()-city.getX())*(this.getX()-city.getX()))+
                        ((this.getY()-city.getY())*(this.getY()-city.getY()))
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(x, city.x) &&
                Objects.equals(y, city.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y +"\n";
    }
}
