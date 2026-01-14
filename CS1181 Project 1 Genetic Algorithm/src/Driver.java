import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        // Read file to get blueprint list of items
        ArrayList<Item> list = GeneticAlgorithm.readData("CS1181 Project 1\\src\\items.txt");
        Random rng = new Random();

        // Initialize population size
        ArrayList<Chromosome> currentPopulation = GeneticAlgorithm.initializePopulation(list, 10);
        ArrayList<Chromosome> newPopulation = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            // Step 2 add current population to new population
            newPopulation.addAll(currentPopulation);

            // Step 3 pair off the two populations to create 10 child chromosomes
            step3(currentPopulation, newPopulation, rng);

            // Step 4 mutate 10% of the new population of size 20
            step4(newPopulation, rng);

            // Step 5 sorting
            Collections.sort(newPopulation);

            // Step 6, add 10 fittest chromosomes to a clean current population
            currentPopulation.clear();

            for (int j = 0; j < 10; j++) {
                currentPopulation.add(newPopulation.get(j));

            }
            newPopulation.clear();
        }
        // Step 8 sort and print fittest chromosome
        Collections.sort(currentPopulation);
        System.out.println(currentPopulation.get(0));

    }

    /**
     * Randomly chooses two members of the current population, breeds them, and adds
     * the children to the new population
     * 
     * @param currentPopulation list of potential parents
     * @param newPopulation     destination of child
     * @param rng               random number generator
     */
    public static void step3(ArrayList<Chromosome> currentPopulation, ArrayList<Chromosome> newPopulation, Random rng) {
        for (int i = 0; i < currentPopulation.size(); i++) {
            int parent1 = rng.nextInt(10);
            int parent2 = rng.nextInt(10);
            newPopulation.add(currentPopulation.get(parent1).crossover(newPopulation.get(parent2)));
        }

    }

    /**
     * Mutates 10% of the new population after breeding step. In this case 2
     * chromosomes
     * 
     * @param newPopulation population to add
     * @param rng           random number generator
     */
    public static void step4(ArrayList<Chromosome> newPopulation, Random rng) {
        Collections.sort(newPopulation);
        int mutate1 = rng.nextInt(20);
        int mutate2 = rng.nextInt(20);

        // Prevent duplicates
        while (mutate1 == mutate2) {
            mutate2 = rng.nextInt(20);
        }
        newPopulation.get(mutate1).mutate();
        newPopulation.get(mutate2).mutate();
    }
}
