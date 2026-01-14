import java.io.*;
import java.util.*;
/**
 * This class generates the chromosome item lists to be used and modified
 */
public class GeneticAlgorithm {
    /**
     * Reads the file of items and creates an array list to be made a chromosome
     * @param filename items.txt to be read
     * @return the finished chromosome
     * @throws FileNotFoundException if file cannot be found
     */
    public static ArrayList<Item> readData(String filename) throws FileNotFoundException{
        FileInputStream fileStream = new FileInputStream(filename);
        Scanner fileReader = new Scanner(fileStream);
        ArrayList<Item> list = new ArrayList<Item>();

        //Loop through file, parsing fields seperated by commas, and adds them as items to the array list
        while(fileReader.hasNextLine()) {
            //Got help with field separation from Stack Overflow
            String str = fileReader.nextLine();
            String[]tokens = str.split(",");
            list.add(new Item(tokens[0], Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2].replace(" ", ""))));
            
        }
        fileReader.close();
        return list;
        //Reads items from file and creates a list
    }
    /**
     * Creates a population of properly initialized chromosomes
     * @param items the generic list of items for each chromosome
     * @param populationSize the chosen size of the population to create
     * @return a population or an array list of chromosomes
     */
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {
        ArrayList<Chromosome> list = new ArrayList<Chromosome>();
        // adds a copy of the base set of items to each new chromosome in the population list
        for (int i = 0; i < populationSize; i++) {
            list.add(new Chromosome(items));
        }
        return list;
    }
}