package genetical;

import java.util.Arrays;

public class Population {

	private Chromosome[] chromosomes;

	public Population(int length) {
		chromosomes = new Chromosome[length];
	}

	public Population initialize() {
		for (int i = 0; i < chromosomes.length; i++) {
			chromosomes[i] = new Chromosome(GeneticAlgorithm.TARGET_ARRAY.length).initialize();
		}

		sortChromosomesByFitness(); //sortira i na prvo mjesto stavi onoga sa najvecim fitnesom
		return this;
	}

	public void sortChromosomesByFitness() {
		Arrays.sort(chromosomes, (c1, c2) -> {
			int flag = 0;
			if (c1.getFitness() > c2.getFitness())
				flag = -1;
			else if (c1.getFitness() < c2.getFitness())
				flag = 1;
			return flag;
		});

	}

	public Chromosome[] getChromosomes() {
		return chromosomes;
	}
	
	
}
