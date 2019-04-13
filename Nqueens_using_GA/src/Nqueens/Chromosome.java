package Nqueens;

/**
 *
 * @author Hardik
 */
public class Chromosome implements Comparable<Chromosome> {

    private int maximumLength;
    private int[] gene;
    private double fitness;
    private int conflicts;
    private boolean Ifselected;
    private double problem;

// This Method Creates new Chromosomes
    public Chromosome(int maxLength) {
        maximumLength = maxLength;
        gene = new int[maximumLength];
        conflicts = 0;
        fitness = 0.0;
        Ifselected = false;
        problem = 0.0;
        initChromosome();
    }

    public void initChromosome() {
        for (int i = 0; i < maximumLength; i++) {
            this.gene[i] = i;
        }
    }

    public int getmaximumLength() {
        return maximumLength;
    }

    public void setmaximumLength(int maximumLength) {
        this.maximumLength = maximumLength;
    }

    public int[] getGene() {
        return gene;
    }

    public int getGene(int index) {
        return gene[index];
    }

    public void setGene(int[] gene) {
        this.gene = gene;
    }

    public void setGene(int index, int position) {
        this.gene[index] = position;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getConflicts() {
        return conflicts;
    }

    public void setConflicts(int conflicts) {
        this.conflicts = conflicts;
    }

    public boolean isIfSelected() {
        return Ifselected;
    }

    public void setIfSelected(boolean selected) {
        this.Ifselected = selected;
    }

    public double getproblem() {
        return problem;
    }

    public void setproblem(double problem) {
        this.problem = problem;
    }

// Creates Blank Board
    public void blankBoard(String[][] board) {

        for (int i = 0; i < maximumLength; i++) {
            for (int j = 0; j < maximumLength; j++) {
                board[i][j] = "";
            }
        }

    }

// Arranges Queens    
    public void arrangequeens(String[][] board) {
        for (int i = 0; i < maximumLength; i++) {
            board[i][gene[i]] = "X";
        }
    }

// Calculating Conflicts    
    public void calculateconflict() {
        String[][] board = new String[maximumLength][maximumLength];
        int row = 0;
        int column = 0;

        int temporaryRow = 0;
        int temporaryColumn = 0;

        /*      To check the Neighbouring Blocks in Vertical-Foward, Vertical-Backward, Horizontal-Forward,
        Horizontal-Backward, Diagonal-Left-Leaning-Forward, Diagonal-Left-Leaning-Backward,
       Diagonal-Right-Leaning-Forward and Diagonal-Right-Leaning-Backward
         */
        int[] diagonalX = new int[]{-1, 1, -1, 1};
        int[] diagonalY = new int[]{-1, 1, 1, -1};

        //initialize the value for Stop
        boolean stop = false;

        //Initializing Conflicts
        int conflict = 0;

        blankBoard(board);
        arrangequeens(board);

        //Code Below Checks for Conflicts
        //i goes from 0 to maximumLength
        //j checks in all four directions
        for (int i = 0; i < maximumLength; i++) {
            row = i;
            column = gene[i];

            for (int j = 0; j < 4; j++) {

                temporaryRow = row;
                temporaryColumn = column;
                stop = false;

                while (!stop) {
                    temporaryRow += diagonalX[j];
                    temporaryColumn += diagonalY[j];

                    if ((temporaryRow < 0 || temporaryRow >= maximumLength) || (temporaryColumn < 0 || temporaryColumn >= maximumLength)) {
                        stop = true;

                    } else {
                        if (board[temporaryRow][temporaryColumn].equals("X")) {
                            conflict += 1;

                        }
                    }
                }
            }
        }

        this.conflicts = conflict;
    }

    @Override
    public int compareTo(Chromosome chromosome) {
        return this.conflicts - chromosome.getConflicts();
    }

}
