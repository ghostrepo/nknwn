package com.example.unknown;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day2 {
    static final String RED = "RED";
    static final String GREEN = "GREEN";
    static final String BLUE  = "BLUE";
    static final int mRed = 12;
    static final int mGreen = 13;
    static final int mBlue = 14;

    public static void main(String[] args) {
        File in = new File("com/example/unknown/input2.txt");
        try {
            cube(in);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static void cube(File input) throws FileNotFoundException {
        int id = 0;
        Scanner s = new Scanner(input);
        ArrayList<String> games = new ArrayList<>();
        while (s.hasNextLine()) {
            games.add(s.nextLine());
        }

        int total = 0;
        for (String g : games) {
            id++;
            if (isPossible(parseGame(g))) {
//                System.out.println("Game " + id + " is possible");
                total += id;
            }
        }
        System.out.println(total);
    }

    private static HashMap<String, Integer> parseRound(String round) {
        HashMap<String, Integer> output = new HashMap<>();
        ArrayList<String> colors = new ArrayList<>(Arrays.asList(round.split(",")));
        for (String c : colors) {
            if (c.contains("red")) {
                output.put(RED, Integer.parseInt(c.replaceAll("\\D", "")));
            } else if (c.contains("green")) {
                output.put(GREEN, Integer.parseInt(c.replaceAll("\\D", "")));
            } else if (c.contains("blue")) {
                output.put(BLUE, Integer.parseInt(c.replaceAll("\\D", "")));
            }
        }
        return output;
    }

    private static ArrayList<HashMap<String, Integer>> parseGame(String game) {
        ArrayList<HashMap<String, Integer>> output = new ArrayList<>();
        ArrayList<String> rounds = new ArrayList<>(Arrays.asList(game.split("[;:]")));
        for (String r : rounds) {
            if (!r.contains("Game")) {
                output.add(parseRound(r));
            }
        }
        return output;
    }

    private static boolean isPossible(ArrayList<HashMap<String, Integer>> game) {
        for (HashMap<String, Integer> map : game) {
            for (String key : map.keySet()) {
                if (map.get(key) != null) {
                    switch (key) {
                        case RED:
                            if (map.get(key) > mRed) {
                                return false;
                            } else {
                                break;
                            }
                        case GREEN:
                            if (map.get(key) > mGreen) {
                                return false;
                            } else {
                                break;
                            }
                        case BLUE:
                            if (map.get(key) > mBlue) {
                                return false;
                            } else {
                                break;
                            }
                    }
                } else {
                    break;
                }
            }
        }
        return true;
    }
}
