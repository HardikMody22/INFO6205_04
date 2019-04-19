/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nqueens;

/**
 *
 * @author avi
 */
public class RunNQueens {

    Plotter logplotter;

    GeneticAlgorithm galg;
    int maxLength;
    long[] runningtime;
    int maxRun;

    public RunNQueens() {
        logplotter = new Plotter();
        maxRun = 50;
        runningtime = new long[maxRun];
    }

    public void test(int maxLength, double mutationRate, int maxEpoch) {
        this.maxLength = maxLength;
        galg = new GeneticAlgorithm(this.maxLength, maxEpoch, mutationRate);
        long testStart = System.nanoTime();
        String filepath = "GA-N" + this.maxLength + "-" + mutationRate + "-" + maxEpoch + ".txt";
        long startingTime = 0;
        long endingTime = 0;
        long totalTime = 0;
        int failure = 0;
        int success = 0;

        Parameterlog();

        for (int i = 0; i < maxRun;) {
            startingTime = System.nanoTime();
            if (galg.GeneticRun()) {
                endingTime = System.nanoTime();
                totalTime = endingTime - startingTime;

                System.out.println("Cycle " + (i + 1));
                System.out.println("" + totalTime);

                System.out.println("Success!");

                runningtime[i] = totalTime;
                i++;
                success++;

                logplotter.add((String) ("Cycle: " + i));
                logplotter.add((String) ("Running Time: " + totalTime));
                logplotter.add((String) ("Target of Epoch hit: " + galg.getPeak()));
                logplotter.add((String) ("The new population size generated: " + galg.getCutoff()));
                logplotter.add("");

                for (Chromosome c : galg.getResult()) {
                    logplotter.add(c);
                    logplotter.add("");
                }
            } else {
                failure++;

            }

            if (failure >= 100) {
                System.out.println("Invalid Parametres");
                break;
            }
            startingTime = 0;
            endingTime = 0;
            totalTime = 0;
        }

        System.out.println("Success: " + success + " failures: " + failure);

        logplotter.add("Summary");
        logplotter.add("");

        for (int x = 0; x < runningtime.length; x++) {
            logplotter.add(Long.toString(runningtime[x]));
        }

        long testEnd = System.nanoTime();
        logplotter.add(Long.toString(testStart));
        logplotter.add(Long.toString(testEnd));
        logplotter.add(Long.toString(testEnd - testStart));

        logplotter.writerFile(filepath);
        printRuntimes();

    }

    public void Parameterlog() {
        logplotter.add("Genetic Algorithm");
        logplotter.add("Parameters");
        logplotter.add((String) ("Maximum Length divided by Number " + maxLength));
        logplotter.add((String) ("Starting Population of chromosomes: " + galg.getStartingpopulation()));
        logplotter.add((String) ("Maximum  epochs: " + galg.getMaxEp()));
        logplotter.add((String) ("Probability of mating of chromosome: " + galg.getMateprobability()));
        logplotter.add((String) ("Rate of Mutation between chromosomes: " + galg.getRateofmutation()));
        logplotter.add((String) ("Minimum number of parent selected: " + galg.getMin()));
        logplotter.add((String) ("Maximum number of parent selected: " + galg.getMax()));
        logplotter.add((String) ("Offspring generated per generation: " + galg.getChild()));
        logplotter.add((String) ("Number of minimum shuffle done: " + galg.getMinimumshuf()));
        logplotter.add((String) ("Number of maximum shuffle done: " + galg.getMaximumshuf()));
        logplotter.add("");
    }

    public void printRuntimes() {
        for (long x : runningtime) {
            System.out.println("run with time " + x + " nanoseconds");
        }
    }

    public static void main(String args[]) {
        RunNQueens tester = new RunNQueens();
        int N = 10;
        for (int i = 4; i <= N; i++) {
            System.out.println("Experiment No: " + i);
            tester.test(i, 0.001, 1000);
        }
    }

}
