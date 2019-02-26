//Course:       CS4242
//Author:       Cody Harrison
//StudentID:    000721123
//Assignment:   #2
//Due Date:     10/22/2018
// This program will eventually generate target phrase through the generation of random characters and by applying the
// genetic algorithm.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class population {

    //Target phrase: to be or not to be
    private static String phrase = "to be or not to be";
    //Char array to hold target phrase
    private static char[] phraseArray = phrase.toCharArray();
    //String to hold alphabet
    private final static String alphabet = "abcdefghijklmnopqrstuvwxyz ";
    //List to hold current population of sentences
    private static List<char[]> population = new ArrayList<>();
    //List to hold current mating pool of sentences
    private static List<char[]> matingPool = new ArrayList<>();
    //Rate at which a random char in sentence will change to another random char. Out of 100
    private final static int mutationRate = 2;
    //Generation counter
    private static int generation = 0;
    //Boolean to check if target phrase has been acquired
    public static boolean isDone = false;

    //Generates random char from alphabet
    public static char randChar(){
        int r = new Random().nextInt(27);
        return alphabet.charAt(r);
    }

    //Creates first generation of sentences
    public static void generatePop(int popSize){
        //For every element in the population
        for (int k = 0; k < popSize; k++) {
            //create sentence of same length as target phrase
            char[] sentence = new char[phraseArray.length];
            //for every sentence
            for (int i = 0; i < sentence.length; i++) {
                //fill with random chars
                sentence[i] = randChar();
                //System.out.print(sentence[i]);
            }
            //Add sentence to population
            population.add(sentence);
            //System.out.println();
        }
    }

    //Method to print current population
    public static void printPop(){
        System.out.println("Population of Generation " + generation);
        char[] sentence = {};
        for(int i = 0; i < population.size(); i++){
            sentence = population.get(i);
            System.out.println(Arrays.toString(sentence));
        }
    }

    //Method to print current mating pool
    public static void printPool(){
        System.out.println("Mating Pool of Generation " + generation);
        char[] sentence = {};
        for(int i = 0; i < matingPool.size(); i++){
            sentence = matingPool.get(i);
            System.out.println(Arrays.toString(sentence));
        }
    }

    //Method to calculate fitness
    public static void calcFit(){

        matingPool.clear();
        int maxFit = 0;
        char[] bestSentence = {};

        //For every element in the population
        for (int i = 0; i < population.size(); i++){

            char[] sentence = {};
            int fitness = 0;
            sentence = population.get(i);

            //Check to see which chars are in same position as target phrase and determine that sentence's fitness
            for(int j = 0; j < sentence.length; j++){
                if (sentence[j] == phraseArray[j]){
                    fitness++;
                }
            }

            //Find most fit sentence
            if (fitness > maxFit){
                maxFit = fitness;
                bestSentence = sentence;
            }

            //System.out.println("Fitness: " + fitness);

                //If a sentence's fitness is greater than 0 add it to the mating pool that many times
                //By adding sentences multiple times to the mating pool it is more likely to be chosen
                //which will result in a higher average fitness of next generation
                for (int d = 0; d < fitness; d++) {
                    matingPool.add(sentence);
                }
            //Check to see if target phrase has been achieved
            if (fitness == sentence.length){
                isDone = true;
            }
        }

        System.out.println("Best Sentence of Gen: " + generation);
        System.out.println(Arrays.toString(bestSentence));

    }

    //Method to combine two parent char[]
    private static char[] breed(char[] parentA, char[] parentB){
        char[] child = new char[parentA.length];
        //Randomly generate a midpoint
        int midpoint = new Random().nextInt(child.length);
        //Copy parent A and parent B to child
        for (int i = 0; i < child.length; i++){
            if (i < midpoint)
                child[i] = parentA[i];
            else
                child[i]=parentB[i];
        }
        return child;
    }

    //Method to mutate child which expedites process
    private static char[] mutate(char[] child){
        //If random number is <= mutationrate, replace current element with random char
        for(int i = 0; i < child.length; i++){
            if (new Random().nextInt(100) <= mutationRate){
                child[i] = randChar();
            }
        }

        return child;
    }

    //Method to generate next generation of population based off what is in the mating pool
    public static void generate(){
        //For every element in the population
        for (int i = 0; i < population.size();i++){
            //Create a child from parent A and parent B, where A and B are chosen randomly form the mating pool
            char[] child = {};
            int a = new Random().nextInt(matingPool.size());
            int b = new Random().nextInt(matingPool.size());
            char[] parentA = matingPool.get(a);
            char[] parentB = matingPool.get(b);
            child = breed(parentA,parentB);
            mutate(child);
            population.set(i,child);
        }
        generation++;
    }
}