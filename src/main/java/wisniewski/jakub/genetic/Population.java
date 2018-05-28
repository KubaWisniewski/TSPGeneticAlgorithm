package wisniewski.jakub.genetic;

import wisniewski.jakub.model.CitiesStorage;
import wisniewski.jakub.model.City;
import wisniewski.jakub.model.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Population{
    private List<Route> routes;
    private int populationSize;

    public Population(int populationSize) {
        this.populationSize=populationSize;
        routes=new ArrayList<>();
        for (int i = 0; i <populationSize ; i++) {
            routes.add(new Route());
        }
    }

    public void generateRoutes(){
        for (int i = 0; i <populationSize ; i++) {
            Route route= new Route();
            List<City> list=new ArrayList<City>(Arrays.asList(CitiesStorage.getCities()));
            Collections.shuffle(list);
            route.setRoute(list);
            route.calculateRouteDistance();
            routes.set(i,route);
        }
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public Route getBestRoute() {
        Route route=routes.get(0);
        for (int i = 0; i <populationSize ; i++) {
            if(routes.get(i).getDistance()<route.getDistance())
                route=routes.get(i);
        }
        return route;
    }
}