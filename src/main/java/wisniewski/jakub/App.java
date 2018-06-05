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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class App {
    public static void main(String[] args) {
        long startTime, endTime;

        CitiesStorage.getCitiesFromFile("cities.txt");
        System.out.println("----------Genetic Algorithm-----------");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        Population population = new Population(200);
        for (int j = 0; j < 25; j++) {
            try (FileWriter fw = new FileWriter("Results.txt", true)) {
                PrintWriter pw= new PrintWriter(fw);
                System.out.println("----------Genetic Algorithm-----------");
                population.generateRoutes();
                System.out.println("Distance before algorithm: " + population.getBestRoute().getDistance());
                pw.print(population.getBestRoute().getDistance()+" ");
                startTime = System.nanoTime();

                for (int i = 0; i <= 100; i++) {
                    population = geneticAlgorithm.evolution(population);
                }
                endTime = System.nanoTime();
                System.out.println("Duration: " + (endTime - startTime) / 1000000 + " ms");
                System.out.println("Distance after algorithm: " + population.getBestRoute().getDistance());
                pw.print(population.getBestRoute().getDistance()+" ");
                pw.print((endTime - startTime) / 1000000+"\n");

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("----------Nearest Neighbour ALGORITHM-----------");
        NearestNeighbourAlgorithm nearestNeighbourAlgorithm = new NearestNeighbourAlgorithm();
        startTime = System.nanoTime();
        nearestNeighbourAlgorithm.solveTSP();
        endTime = System.nanoTime();
        System.out.println(nearestNeighbourAlgorithm);
        System.out.println("Duration: " + (endTime - startTime) / 1000000.0 + " ms");
        System.out.println("====================================================");


        int width = 400;
        int height = 400;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.red);
        for (City c : nearestNeighbourAlgorithm.getRoute().getRoute()) {
            g2d.fillRect(c.getX() - 2, c.getY() - 2, 4, 4);
        }
        g2d.setColor(Color.black);
        for (int i = 0; i < nearestNeighbourAlgorithm.getRoute().getRoute().size() - 1; i++) {
            City c1 = nearestNeighbourAlgorithm.getRoute().getRoute().get(i);
            City c2 = nearestNeighbourAlgorithm.getRoute().getRoute().get(i + 1);
            g2d.drawLine(c1.getX(), c1.getY(), c2.getX(), c2.getY());
        }
        int size = nearestNeighbourAlgorithm.getRoute().getRoute().size();
        City c1 = nearestNeighbourAlgorithm.getRoute().getRoute().get(size - 1);
        City c2 = nearestNeighbourAlgorithm.getRoute().getRoute().get(0);
        g2d.drawLine(c1.getX(), c1.getY(), c2.getX(), c2.getY());
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
        for (City c : population.getBestRoute().getRoute()) {
            g2d.fillRect(c.getX() - 2, c.getY() - 2, 4, 4);
        }
        g2d.setColor(Color.black);
        for (int i = 0; i < nearestNeighbourAlgorithm.getRoute().getRoute().size() - 1; i++) {
            c1 = population.getBestRoute().getRoute().get(i);
            c2 = population.getBestRoute().getRoute().get(i + 1);
            g2d.drawLine(c1.getX(), c1.getY(), c2.getX(), c2.getY());
        }
        size = population.getBestRoute().getRoute().size();
        c1 = population.getBestRoute().getRoute().get(size - 1);
        c2 = population.getBestRoute().getRoute().get(0);
        g2d.drawLine(c1.getX(), c1.getY(), c2.getX(), c2.getY());
        g2d.dispose();

        file = new File("GeneticAlg.jpg");
        try {
            ImageIO.write(bufferedImage2, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
