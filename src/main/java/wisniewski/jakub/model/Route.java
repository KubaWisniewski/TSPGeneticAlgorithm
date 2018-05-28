package wisniewski.jakub.model;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private List<City> route;
    private double distance;
    private int routeSize=CitiesStorage.getCities().length;

    public Route() {
        route=new ArrayList<>();
        distance=0;
        for (int i = 0; i <routeSize ; i++) {
            route.add(null);
        }
    }

    public List<City> getRoute() {
        return route;
    }

    public void setRoute(List<City> route) {
        this.route = route;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void calculateRouteDistance() {
        double distance=0;
        for (int i = 0; i <route.size()-1 ; i++) {
            distance+=route.get(i).getDistanceTo(route.get(i+1));
        }
        distance+=route.get(route.size()-1).getDistanceTo(route.get(0));
        this.distance=distance;
    }

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();
        // for (City c:route) {
        //   sb.append(c);
        // sb.append(">");
        //}
        //sb.append("\n"+route.size());
        sb.append("\n"+distance);
        return sb.toString();
    }
}