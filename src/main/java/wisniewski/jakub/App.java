package wisniewski.jakub;

import wisniewski.jakub.genetic.GeneticAlgorithm;
import wisniewski.jakub.genetic.Population;
import wisniewski.jakub.model.CitiesStorage;
import wisniewski.jakub.model.City;
import wisniewski.jakub.nearestNeighbourAlgorithm.NearestNeighbourAlgorithm;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        CitiesStorage.getCitiesFromFile("cities.txt");
        System.out.println("Number of Cities:"+CitiesStorage.getCities().length);
        System.out.println("--------------------------------------");
        System.out.println("----------GENETIC ALGORITHM-----------");
        GeneticAlgorithm geneticAlgorithm= new GeneticAlgorithm();
        System.out.println("Mutation rate: "+geneticAlgorithm.getMutationRate());
        System.out.println("Tournament Size: "+ geneticAlgorithm.getTournamentSize());
        System.out.println("Elit: "+geneticAlgorithm.isElit());
        Population population= new Population(50);
        population.generateRoutes();
        System.out.println("Distance before algorithm: "+population.getBestRoute().getDistance());
        long startTime= System.nanoTime();
        for (int i = 0; i <=100; i++) {
            population=geneticAlgorithm.evolution(population);
        }
        long endTime=System.nanoTime();
        System.out.println("Duration: "+ (endTime-startTime)/1000000+" ms");

        System.out.println("Distance after algorithm: "+population.getBestRoute().getDistance());
        System.out.println("--------------------------------------");
        System.out.println("----------Nearest Neighbour ALGORITHM-----------");
        NearestNeighbourAlgorithm nearestNeighbourAlgorithm= new NearestNeighbourAlgorithm();
        startTime=System.nanoTime();
        nearestNeighbourAlgorithm.solveTSP();
        endTime=System.nanoTime();
        System.out.println(nearestNeighbourAlgorithm);
        System.out.println("Duration: "+ (endTime-startTime)/1000000.0+" ms");
        System.out.println("====================================================");
        CitiesStorage.getCitiesFromFile("moreCities.txt");
        System.out.println("Number of Cities:"+CitiesStorage.getCities().length);
        System.out.println("--------------------------------------");
        System.out.println("----------GENETIC ALGORITHM-----------");
        geneticAlgorithm= new GeneticAlgorithm();
        System.out.println("Mutation rate: "+geneticAlgorithm.getMutationRate());
        System.out.println("Tournament Size: "+ geneticAlgorithm.getTournamentSize());
        System.out.println("Elit: "+geneticAlgorithm.isElit());
        population= new Population(1000);
        population.generateRoutes();
        System.out.println("Distance before algorithm: "+population.getBestRoute().getDistance());
        startTime= System.nanoTime();
        for (int i = 0; i <=100; i++) {
            population=geneticAlgorithm.evolution(population);
        }
        endTime=System.nanoTime();
        System.out.println("Duration: "+ (endTime-startTime)/1000000+" ms");

        System.out.println("Distance after algorithm: "+population.getBestRoute().getDistance());
        System.out.println("--------------------------------------");
        System.out.println("----------Nearest Neighbour ALGORITHM-----------");
        nearestNeighbourAlgorithm= new NearestNeighbourAlgorithm();
        startTime=System.nanoTime();
        nearestNeighbourAlgorithm.solveTSP();
        endTime=System.nanoTime();
        System.out.println(nearestNeighbourAlgorithm);
        System.out.println("Duration: "+ (endTime-startTime)/1000000.0+" ms");

        int width = 400;
        int height =400;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.red);
        for (City c :nearestNeighbourAlgorithm.getRoute().getRoute()) {
            g2d.fillRect(c.getX()-2,c.getY()-2,4,4);
        }
        g2d.setColor(Color.black);
        for (int i = 0; i <nearestNeighbourAlgorithm.getRoute().getRoute().size()-1 ; i++) {
            City c1= nearestNeighbourAlgorithm.getRoute().getRoute().get(i);
            City c2= nearestNeighbourAlgorithm.getRoute().getRoute().get(i+1);
            g2d.drawLine(c1.getX(),c1.getY(),c2.getX(),c2.getY());
        }
        int size=nearestNeighbourAlgorithm.getRoute().getRoute().size();
        City c1= nearestNeighbourAlgorithm.getRoute().getRoute().get(size-1);
        City c2= nearestNeighbourAlgorithm.getRoute().getRoute().get(0);
        g2d.drawLine(c1.getX(),c1.getY(),c2.getX(),c2.getY());
        g2d.dispose();


        File file = new File("NNAlg.jpg");
        try {
            ImageIO.write(bufferedImage, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage bufferedImage2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g2d = bufferedImage2.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.red);
        for (City c :population.getBestRoute().getRoute()) {
            g2d.fillRect(c.getX()-2,c.getY()-2,4,4);
        }
        g2d.setColor(Color.black);
        for (int i = 0; i <nearestNeighbourAlgorithm.getRoute().getRoute().size()-1 ; i++) {
             c1= population.getBestRoute().getRoute().get(i);
             c2= population.getBestRoute().getRoute().get(i+1);
            g2d.drawLine(c1.getX(),c1.getY(),c2.getX(),c2.getY());
        }
        size=population.getBestRoute().getRoute().size();
        c1= population.getBestRoute().getRoute().get(size-1);
        c2= population.getBestRoute().getRoute().get(0);
        g2d.drawLine(c1.getX(),c1.getY(),c2.getX(),c2.getY());
        g2d.dispose();
        file = new File("GeneticAlg.jpg");
        try {
            ImageIO.write(bufferedImage2, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
