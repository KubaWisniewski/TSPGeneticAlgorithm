package wisniewski.jakub.NearestNeighbourAlgorithm;

import wisniewski.jakub.model.CitiesStorage;
import wisniewski.jakub.model.City;
import wisniewski.jakub.model.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NearestNeighbourAlgorithm {
    private List<City> unvisitedCities;
    private Route route;

    public NearestNeighbourAlgorithm() {
        unvisitedCities=new ArrayList<>(Arrays.asList(CitiesStorage.getCities()));
        route=new Route();
    }

    public void solveTSP(){
        int random=(int)( Math.random()*unvisitedCities.size());
        route.getRoute().set(0,unvisitedCities.get(random));
        unvisitedCities.remove(random);
        //route.getRoute().set(0,c);
        //unvisitedCities.remove(c);
        for (int i = 1; !unvisitedCities.isEmpty() ; i++) {

            int indexNearest =getIdxNearest(route.getRoute().get(i-1),unvisitedCities);
            route.getRoute().set(i,unvisitedCities.get(indexNearest));
            unvisitedCities.remove(indexNearest);
        }

        route.calculateRouteDistance();
    }

    public int getIdxNearest(City city, List<City> cities) {
        int idx=0;
        double distance=city.getDistanceTo(cities.get(0));
        for (int i = 1; i <cities.size() ; i++) {
            if (city.getDistanceTo(cities.get(i)) < distance) {
                distance = city.getDistanceTo(cities.get(i));
                idx = i;
            }
        }
        return idx;
    }

    @Override
    public String toString() {
        return "Distance: "+ route.getDistance();
    }
}
