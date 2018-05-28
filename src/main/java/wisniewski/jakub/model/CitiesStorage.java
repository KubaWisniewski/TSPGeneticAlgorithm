package wisniewski.jakub.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CitiesStorage {
    private static Integer sizeCityStorage=0;
    private static City[] cities=new City[sizeCityStorage];

    public static void addCity(City city) {
        sizeCityStorage++;
        City[] citiesStorage = new City[sizeCityStorage];
        for (int i = 0; i < sizeCityStorage-1; i++) {
            citiesStorage[i] = cities[i];
        }
        citiesStorage[sizeCityStorage - 1] = city;
        cities = citiesStorage;
    }

    public static City getCityByIdx(int idx) {
        return cities[idx];
    }

    public static City[] getCities() {
        return cities;
    }

    public static void getCitiesFromFile(String fileName) {
        sizeCityStorage=0;
        cities=new City[sizeCityStorage];
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                String[] s = scanner.nextLine().split(" ");
                addCity(new City(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Integer getSizeCityStorage() {
        return sizeCityStorage;
    }

    public static void setSizeCityStorage(Integer sizeCityStorage) {
        CitiesStorage.sizeCityStorage = sizeCityStorage;
    }

    public static void setCities(City[] cities) {
        CitiesStorage.cities = cities;
    }
}
