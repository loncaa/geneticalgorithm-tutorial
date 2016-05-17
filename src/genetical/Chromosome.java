package genetical;

import java.util.Arrays;

/** Candidate solution */
public class Chromosome {
	private boolean isFitnessChanged = true;
	private int fitness = 0;
	private int[] genes;

	public Chromosome(int length) {
		this.genes = new int[length];
	}

	public Chromosome initialize() {
		for (int i = 0; i < genes.length; i++) {
			if (Math.random() >= 0.5)
				genes[i] = 1;
			else
				genes[i] = 0;
		}

		return this;
	}

	public String toString() {
		return Arrays.toString(genes);
	}

	public int[] getGenes() {
		isFitnessChanged = true;
		return genes;
	}

	public int getFitness() {
		if (isFitnessChanged) {
			fitness = recalculateFitness();
			isFitnessChanged = false;
		}
		return fitness;
	}

	public int recalculateFitness() {
		int cFitness = 0;
		for (int i = 0; i < genes.length; i++) {
			if (genes[i] == GeneticAlgorithm.TARGET_ARRAY[i])
				cFitness++;
		}

		return cFitness;
	}

}
