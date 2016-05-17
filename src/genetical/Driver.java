package genetical;

import java.util.Arrays;

public class Driver {

	public static void main(String[] args) {
		Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initialize();
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
		
		System.out.println("---------------------------------");
		System.out.println("Chromosomes: #0 : | Fittest chromosome fitness: " + population.getChromosomes()[0].getFitness());
		printPopulation(population, "Target chromosome: " + Arrays.toString(GeneticAlgorithm.TARGET_ARRAY));
		
		int generationNumber = 0;
		while(population.getChromosomes()[0].getFitness() < GeneticAlgorithm.TARGET_ARRAY.length) {
			generationNumber++;
			
			population = geneticAlgorithm.evolve(population);

			System.out.println("---------------------------------");
			System.out.println("Chromosomes: #" + generationNumber + " : | Fittest chromosome fitness: " + population.getChromosomes()[0].getFitness());
			printPopulation(population, "Target chromosome: " + Arrays.toString(GeneticAlgorithm.TARGET_ARRAY));
			
		}
		
		System.out.println("---------------------------------");
		System.out.println("Found in #" + generationNumber + " generation");
	}

	public static void printPopulation(Population pop, String heading) {
		System.out.println(heading);
		System.out.println("---------------------------------");
		for(int i = 0; i < pop.getChromosomes().length; i++) {
			System.out.println("Chromosomes: #"+ i + " : " + pop.getChromosomes()[i].toString() + " | Fitness:" + pop.getChromosomes()[i].getFitness());
		}
	}
	
}
