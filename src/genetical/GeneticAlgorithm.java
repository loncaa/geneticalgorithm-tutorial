package genetical;

public class GeneticAlgorithm {
	public static final int POPULATION_SIZE = 8;
	public static final int[] TARGET_ARRAY = {1,1,0,1,0,0,1,1,1,0,1,1};
	public static final int ELITE_CHROMOSOMES = 1;
	public static final int TOURNAMENT_SELECTION_SIZE = 4;
	private static final double MUTATION_RATE = 0.25;
	
	public Population evolve(Population pop) {
		Population p = mutatePopulation(crossoverPopulation(pop));
		//Population p = crossoverPopulation(pop);
		p.sortChromosomesByFitness();
		return p;
	}
	
	/**Mutacija ovisi o mutation rate postotku*/
	private Population mutatePopulation(Population pop){
		Population mutatePopulation = new Population(pop.getChromosomes().length);
		for(int i = 0; i < ELITE_CHROMOSOMES; i++) {
			mutatePopulation.getChromosomes()[i] = pop.getChromosomes()[i];
		}
		
		//mutacija sve osim elitnog kromosoma
		for(int i = ELITE_CHROMOSOMES; i < pop.getChromosomes().length; i++) {
			mutatePopulation.getChromosomes()[i] = mutateChromosome(pop.getChromosomes()[i]);
		}
		return mutatePopulation;
	}
	
	private Chromosome mutateChromosome(Chromosome chromosome) {
		Chromosome mutateChromosome = new Chromosome(TARGET_ARRAY.length);
		for(int i = 0; i < chromosome.getGenes().length; i++){
			if(Math.random() < MUTATION_RATE) {
				if(Math.random() < 0.5) mutateChromosome.getGenes()[i] = 1;
				else mutateChromosome.getGenes()[i] = 0;
			}
			else mutateChromosome.getGenes()[i] = chromosome.getGenes()[i];
		} 
		return mutateChromosome;
	}
	
	/**Krosover pravi novu populaciju ali ELITNE KROMOSOME prebacuje iz stare populacije u novu,
	 * ELITNI kromosomi se ne mjenjaju kroz mutaciju i krosover, to su najbolji kormosomi
	 * Posto sortiramo kromosome po fitnes funkciji, znaci da ce to biti oni prvi u nizu*/
	private Population crossoverPopulation(Population pop){
		Population crossoverPopulation = new Population(pop.getChromosomes().length);
		for(int i = 0; i < ELITE_CHROMOSOMES; i++) {
			crossoverPopulation.getChromosomes()[i] = pop.getChromosomes()[i];
		}
		
		//krosover sve osim elitnog kromosoma
		for(int i = ELITE_CHROMOSOMES; i < pop.getChromosomes().length; i++) {
			Chromosome c1 = selectTournamentPopulation(pop).getChromosomes()[0]; //od n random odabranih kromosoma iz populacije, uzima se najbolji za krizanje
			Chromosome c2 = selectTournamentPopulation(pop).getChromosomes()[0];
			crossoverPopulation.getChromosomes()[i] = crossoverChromosome(c1, c2); //križaju se i spremaju se na mjesto odma poslje elitnog kromosoma
		}
		
		return crossoverPopulation;
	}
	
	//random križanje dvaju kromosoma
	private Chromosome crossoverChromosome(Chromosome c1, Chromosome c2) {
		Chromosome crossoverChromosome = new Chromosome(TARGET_ARRAY.length);
		
		//random odabir gena
		for(int i = 0; i < crossoverChromosome.getGenes().length; i++){
			if(Math.random() >= 0.5) crossoverChromosome.getGenes()[i] = c1.getGenes()[i];
			else crossoverChromosome.getGenes()[i] = c2.getGenes()[i];
		}
		return crossoverChromosome;
	}
	
	//random je odabrano n kromosoma iz populacije
	private Population selectTournamentPopulation(Population pop) {
		Population turnamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);
		for(int i = 0; i < TOURNAMENT_SELECTION_SIZE; i++){
			turnamentPopulation.getChromosomes()[i] = pop.getChromosomes()[(int)(Math.random()*pop.getChromosomes().length)];
		}
		turnamentPopulation.sortChromosomesByFitness();
		
		return turnamentPopulation;
	}
}
