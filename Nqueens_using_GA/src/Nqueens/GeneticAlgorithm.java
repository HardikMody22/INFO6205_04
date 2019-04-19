package GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author AmanNigam
 */
public class GeneticAlgorithm {

    private double mateprobability;
    
    private int child;
    private int minimumshuf;
    private int maximumshuf;
    private int maxLength;
    private int startingpopulation;
    private int maxEp;
    private double rateofmutation;
    private int min;
    private int max;
    private int count;
    private int mutations;
    private int peak;
    private int cutoff;
    private int next;
    private ArrayList<Chromosome> Populations;
    private ArrayList<Chromosome> Result;
    private Random random;
    

    public GeneticAlgorithm(int n, int maxepoch, double mutRate) {
        maxLength = n;
        startingpopulation = 40;
        maxEp = maxepoch;
        mateprobability = 0.7;
        rateofmutation = mutRate;
        min = 10;
        max = 30;
        child = 20;
        minimumshuf = 8;
        maximumshuf = 20;
        peak = 0;
        cutoff = 0;
    }

    public boolean GeneticRun() {
        Populations = new ArrayList<Chromosome>();
        Result = new ArrayList<Chromosome>();
        random = new Random();
        random.setSeed(50);
        next = 0;
        count = 0;
        mutations = 0;
        peak = 0;
        cutoff = 0;

        boolean stop = false;
        Chromosome chromo = null;
        next = randomNumberGenerator(0, (int) Math.round(1.0 / rateofmutation));

        queensInitialization();

        while (!stop) {
            cutoff = Populations.size();
            System.out.println("The Population size is: " + cutoff);
            for (int i = 0; i < cutoff; i++) {
                chromo = Populations.get(i);
                if ((chromo.getConflicts() == 0)) {
                    stop = true;

                }
            }

            if (peak == maxEp) {
                stop = true;
            }

            findfitness();

            rouletteSelection();

            mating();

            resettingSelection();

            peak++;

        }

        if (peak >= maxEp) {
            System.out.println("Zero Solution Found");
            stop = false;
        } else {
            cutoff = Populations.size();
            for (int i = 0; i < cutoff; i++) {
                chromo = Populations.get(i);
                if (chromo.getConflicts() == 0) {
                    Result.add(chromo);
                    print(chromo);
                }
            }
        }

        System.out.print("Mutations: " + mutations + "Children: " + count);

        return stop;

    }

    public void print(Chromosome soln) {
        String[][] chessBoard = new String[maxLength][maxLength];

        for (int x = 0; x < maxLength; x++) {
            for (int y = 0; y < maxLength; y++) {
                chessBoard[x][y] = "";
            }
        }

        for (int x = 0; x < maxLength; x++) {
            chessBoard[x][soln.getGene(x)] = "*";
        }

        System.out.println("On the Board:");
        for (int y = 0; y < maxLength; y++) {
            for (int x = 0; x < maxLength; x++) {
                if (chessBoard[x][y] == "*") {
                    System.out.print("X ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.print("\n");
        }
    }

    public int randomNumberGenerator(int min, int max) {
        return (int) Math.round((max - min) * random.nextDouble() + min);
    }

    public int randomNoRepetition(int high, int reject) {
        boolean stop = false;
        int random = 0;

        while (!stop) {
            random = this.random.nextInt(high);
            if (random != reject) {
                stop = true;
            }
        }
        return random;
    }

    public void queensInitialization() {
        int shuffle = 0;
        Chromosome chromosome = null;
        int index = 0;

        for (int i = 0; i < startingpopulation; i++) {
            chromosome = new Chromosome(maxLength);
            Populations.add(chromosome);
            index = Populations.indexOf(chromosome);

            shuffle = randomNumberGenerator(minimumshuf, maximumshuf);
            mutationExchange(index, shuffle);
            Populations.get(index).calculateconflict();
        }
    }

    public void mutationExchange(int index, int exchanges) {
        int temp = 0;
        int randomGenVal1 = 0;
        int randomGenVal2 = 0;
        Chromosome chromosome = null;
        chromosome = Populations.get(index);

        for (int i = 0; i < exchanges; i++) {
            randomGenVal1 = randomNumberGenerator(0, maxLength - 1);
            randomGenVal2 = randomNoRepetition(maxLength - 1, randomGenVal1);

            temp = chromosome.getGene(randomGenVal1);
            chromosome.setGene(randomGenVal1, chromosome.getGene(randomGenVal2));
            chromosome.setGene(randomGenVal2, temp);
        }
        mutations++;
    }

    public void findfitness() {

        int sizeOfPopulation = Populations.size();
        Chromosome chromosome = null;
        double bestCase = 0;
        double worstCase = 0;

        worstCase = Collections.max(Populations).getConflicts();

        bestCase = worstCase - Collections.min(Populations).getConflicts();

        for (int i = 0; i < sizeOfPopulation; i++) {
            chromosome = Populations.get(i);
            chromosome.setFitness((worstCase - chromosome.getConflicts()) * 100.0 / bestCase);
        }
    }

    public void rouletteSelection() {
        int sizeOfPopulation = Populations.size();
        int selectMax = randomNumberGenerator(min, max);
        double generatedTotal = 0.0;
        double selectedTotal = 0.0;
        double rouletteSpin = 0.0;
        Chromosome chromosome = null;
        Chromosome otherChromosome = null;
        boolean stop = false;
        int j = 0;

        for (int i = 0; i < sizeOfPopulation; i++) {
            chromosome = Populations.get(i);
            generatedTotal += chromosome.getFitness();
        }

        generatedTotal *= 0.01;

        for (int i = 0; i < sizeOfPopulation; i++) {
            chromosome = Populations.get(i);
            chromosome.setproblem(chromosome.getFitness() / generatedTotal);
        }

        for (int i = 0; i < selectMax; i++) {
            rouletteSpin = randomNumberGenerator(0, 99);
            j = 0;
            selectedTotal = 0;
            stop = false;
            while (!stop) {
                chromosome = Populations.get(j);
                selectedTotal += chromosome.getproblem();
                if (selectedTotal >= rouletteSpin) {
                    if (j == 0) {
                        otherChromosome = Populations.get(j);
                    } else if (j >= sizeOfPopulation - 1) {
                        otherChromosome = Populations.get(sizeOfPopulation - 1);
                    } else {
                        otherChromosome = Populations.get(j - 1);
                    }
                    otherChromosome.setIfSelected(true);
                    stop = true;
                } else {
                    j++;
                }
            }
        }
    }

    public int chooseParent() {
        int parent = 0;
        Chromosome chromo = null;
        boolean stop = false;

        while (!stop) {
            parent = randomNumberGenerator(0, Populations.size() - 1);
            chromo = Populations.get(parent);
            if (chromo.isIfSelected() == true) {
                stop = true;
            }
        }

        return parent;
    }

    public int parentSelection(int nextParent) {
        int parent = 0;
        Chromosome chromosome = null;
        boolean stop = false;

        while (!stop) {
            parent = randomNumberGenerator(0, Populations.size() - 1);
            if (parent != nextParent) {
                chromosome = Populations.get(parent);
                if (chromosome.isIfSelected() == true) {
                    stop = true;
                }
            }
        }

        return parent;
    }

    public void mating() {
        int random = 0;
        int firstParent = 0;
        int secondParent = 0;
        int firstIndex = 0;
        int secondIndex = 0;
        Chromosome chromosome1 = null;
        Chromosome chromosome2 = null;

        for (int i = 0; i < child; i++) {
            firstParent = chooseParent();
            random = randomNumberGenerator(0, 100);
            if (random <= mateprobability * 100) {
                secondParent = parentSelection(firstParent);
                chromosome1 = new Chromosome(maxLength);
                chromosome2 = new Chromosome(maxLength);
                Populations.add(chromosome1);
                firstIndex = Populations.indexOf(chromosome1);
                Populations.add(chromosome2);
                secondIndex = Populations.indexOf(chromosome2);
                crossoverPartialMapping(firstParent, secondParent, firstIndex, secondIndex);

                if (count - 1 == next) {
                    mutationExchange(firstIndex, 1);
                } else if (count == next) {
                    mutationExchange(secondIndex, 1);
                }

                Populations.get(firstIndex).calculateconflict();
                Populations.get(secondIndex).calculateconflict();

                count += 2;
                if (count % (int) Math.round(1.0 / rateofmutation) == 0) {
                    next = count + randomNumberGenerator(0, (int) Math.round(1.0 / rateofmutation));

                }
            }
        }
    }

    public void crossoverPartialMapping(int chromosomeA, int chromosomeB, int children1, int children2) {
        int j = 0;
        int object1 = 0;
        int object2 = 0;
        int index1 = 0;
        int index2 = 0;
        Chromosome chromosome1 = Populations.get(chromosomeA);
        Chromosome chromosome2 = Populations.get(chromosomeB);
        Chromosome childChromosome1 = Populations.get(children1);
        Chromosome childChromosome2 = Populations.get(children2);
        int crossingPoint1 = randomNumberGenerator(0, maxLength - 1);
        int crossingPoint2 = randomNoRepetition(maxLength - 1, crossingPoint1);

        if (crossingPoint2 < crossingPoint1) {
            j = crossingPoint1;
            crossingPoint1 = crossingPoint2;
            crossingPoint2 = j;
        }

        for (int i = 0; i < maxLength; i++) {
            childChromosome1.setGene(i, chromosome1.getGene(i));
            childChromosome2.setGene(i, chromosome2.getGene(i));
        }

        for (int i = crossingPoint1; i <= crossingPoint2; i++) {

            object1 = chromosome1.getGene(i);
            object2 = chromosome2.getGene(i);

            for (j = 0; j < maxLength; j++) {
                if (childChromosome1.getGene(j) == object1) {
                    index1 = j;
                } else if (childChromosome1.getGene(j) == object2) {
                    index2 = j;
                }
            }

            if (object1 != object2) {
                childChromosome1.setGene(index1, object2);
                childChromosome1.setGene(index2, object1);
            }

            for (j = 0; j < maxLength; j++) {
                if (childChromosome2.getGene(j) == object2) {
                    index1 = j;
                } else if (childChromosome2.getGene(j) == object1) {
                    index2 = j;
                }
            }

            if (object1 != object2) {
                childChromosome2.setGene(index1, object1);
                childChromosome2.setGene(index2, object2);
            }

        }
    }

    public void resettingSelection() {

        int sizeOfPopulation = 0;
        Chromosome chromosome = null;

        sizeOfPopulation = Populations.size();
        for (int i = 0; i < sizeOfPopulation; i++) {
            chromosome = Populations.get(i);
            chromosome.setIfSelected(false);
        }

    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public ArrayList<Chromosome> getPopulations() {
        return Populations;
    }

    public void setPopulations(ArrayList<Chromosome> Populations) {
        this.Populations = Populations;
    }

    public ArrayList<Chromosome> getResult() {
        return Result;
    }

    public void setResult(ArrayList<Chromosome> Result) {
        this.Result = Result;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMutations() {
        return mutations;
    }

    public void setMutations(int mutations) {
        this.mutations = mutations;
    }

    public int getPeak() {
        return peak;
    }

    public void setPeak(int peak) {
        this.peak = peak;
    }

    public int getCutoff() {
        return cutoff;
    }

    public void setCutoff(int cutoff) {
        this.cutoff = cutoff;
    }

    public double getMateprobability() {
        return mateprobability;
    }

    public void setMateprobability(double mateprobability) {
        this.mateprobability = mateprobability;
    }

    public double getRateofmutation() {
        return rateofmutation;
    }

    public void setRateofmutation(double rateofmutation) {
        this.rateofmutation = rateofmutation;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getStartingpopulation() {
        return startingpopulation;
    }

    public void setStartingpopulation(int startingpopulation) {
        this.startingpopulation = startingpopulation;
    }

    public int getMaxEp() {
        return maxEp;
    }

    public void setMaxEp(int maxEp) {
        this.maxEp = maxEp;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getMinimumshuf() {
        return minimumshuf;
    }

    public void setMinimumshuf(int minimumshuf) {
        this.minimumshuf = minimumshuf;
    }

    public int getMaximumshuf() {
        return maximumshuf;
    }

    public void setMaximumshuf(int maximumshuf) {
        this.maximumshuf = maximumshuf;
    }
}
