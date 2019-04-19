/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneticAlgorithm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author avi
 */
public class Plotter {

    private ArrayList<String> list;

    public Plotter() {
        list = new ArrayList<>();
    }

    public void add(String streamLine) {
        list.add(streamLine);
    }

    //added chromosomes
    public void add(Chromosome c) {
        int n = c.getmaximumLength();
        String board[][] = new String[n][n];

        BoardClear(board, n);

        for (int x = 0; x < n; x++) {
            board[x][c.getGene(x)] = "X";
        }

        plotonBoard(board, n);
    }

    //cleared the board and added
    public void BoardClear(String[][] board, int n) {

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                board[x][y] = "";
            }
        }
    }

    //plotted on Board
    public void plotonBoard(String[][] board, int n) {

        for (int y = 0; y < n; y++) {
            String temp = "";
            for (int x = 0; x < n; x++) {
                if ("X".equals(board[x][y])) {
                    temp += "X ";
                } else {
                    temp += ". ";
                }
            }
            list.add(temp);
        }
    }

    public void writerFile(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i));
                bw.newLine();
                bw.flush();
            }

            bw.close();
        } catch (IOException e) {
            System.out.println("Error Genearted");
        }

    }
}
