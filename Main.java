//Course:       CS4242
//Author:       Cody Harrison
//StudentID:    000721123
//Assignment:   #2
//Due Date:     10/22/2018
// This program will eventually generate target phrase through the generation of random characters and by applying the
// genetic algorithm.
public class Main {

    private static int popSize = 100;

    public static void main(String args[]){

        //Populates the first generation of sentences with popSize amount of sentences
        population.generatePop(popSize);

        //Loops until target sentence is achieved
        while(population.isDone == false) {

            //calculates fitness of population
            population.calcFit();

            //Creates next Gen of population by combining sentences in the mating pool
            population.generate();
            System.out.println();

            //Prints current gen population
            //population.printPop();
            //System.out.println();

            //Prints current gen mating pool
            //population.printPool();
            //System.out.println();
        }
    }
}
