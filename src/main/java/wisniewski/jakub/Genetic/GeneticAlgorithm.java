package wisniewski.jakub.Genetic;

import wisniewski.jakub.model.Route;

import java.util.Collections;

public class GeneticAlgorithm {
    private double mutationRate=0.018;
    private int tournamentSize=10;
    private boolean elit=true;

    public GeneticAlgorithm() {
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public void setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    public boolean isElit() {
        return elit;
    }

    public void setElit(boolean elit) {
        this.elit = elit;
    }

    public GeneticAlgorithm(double mutationRate, int tournamentSize, boolean elit)
    {
        this.mutationRate=mutationRate;
        this.tournamentSize=tournamentSize;
        this.elit=elit;
    }

    public Population evolution(Population p) {
        Population population = new Population(p.getPopulationSize());
        int eliteIndx = 0;
        if (elit) {
            population.getRoutes().set(0, p.getBestRoute());
            eliteIndx=1;
        }
        for (int i = eliteIndx; i <p.getPopulationSize(); i++) {
            Route r1=tournamentSelection(p);
            Route r2=tournamentSelection(p);
            Route ch= crossover(r1,r2);
            population.getRoutes().set(i,ch);
        }
        for (int i = eliteIndx; i < population.getPopulationSize(); i++) {
            mutate(population.getRoutes().get(i));
        }
        return population;

    }
    public Route crossover(Route r1, Route r2) {
        Route child = new Route();
        int rnd1 = (int) (Math.random() * r1.getRoute().size());
        int rnd2 = (int) (Math.random() * r1.getRoute().size());
        final int start = Math.min(rnd1, rnd2);
        final int end = Math.max(rnd1, rnd2);

        for (int i = start; i < end; i++) {
            child.getRoute().set(i, r1.getRoute().get(i));
        }

        for (int i = 0; i < r2.getRoute().size(); i++) {
            if(!child.getRoute().contains(r2.getRoute().get(i)))
                for (int j = 0; j < child.getRoute().size(); j++) {
                    if(child.getRoute().toArray()[j]==null) {
                        child.getRoute().set(j, r2.getRoute().get(i));

                        break;
                    }
                }
        }
        child.calculateRouteDistance();
        return child;
    }
    private void mutate(Route route){
        for (int i = 0; i < route.getRoute().size(); i++) {
            if(Math.random()<mutationRate){
                int j =(int) (Math.random()*route.getRoute().size());
                Collections.swap(route.getRoute(),i,j);
            }
        }
        route.calculateRouteDistance();
    }


    private Route tournamentSelection(Population population){
        Population tournamnetPopulation= new Population(tournamentSize);
        for (int i = 0; i <tournamentSize ; i++) {
            tournamnetPopulation.getRoutes().set(i,population.getRoutes().get((int)(Math.random()*population.getPopulationSize())));
        }
        Route route= tournamnetPopulation.getBestRoute();
        route.calculateRouteDistance();
        return route;
    }






}