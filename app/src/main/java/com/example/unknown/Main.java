package com.example.unknown;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final String TAG = "Testing";

    public static void main(String[] args) {
        System.out.println("Welcome to Online IDE!! Happy Coding :)");
        File in = new File("com/example/unknown/input.txt");
//        File out = null;
        ArrayList<Integer> output = new ArrayList<>();
        int calibration;

        try {
            output = calibrate(in);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        calibration = sum(output);
        System.out.println("The sum of all calibration values is: " + calibration);

//        try {
//            out = writeToFile(output);
//        } catch (IOException io) {
//            System.out.println("Error: IO Exception. " + io);
//        }
//
//        assert out != null;
//        if (out.exists()) {
//            System.out.println("File created successfully");
//        }
    }

    public static ArrayList<Integer> calibrate(File input) throws FileNotFoundException {
        ArrayList<Integer> intArray = new ArrayList<>();
        Scanner s = new Scanner(input);

        //Parse through each line
        while (s.hasNextLine()) {
            //One line of input
            char[] array = s.nextLine().toCharArray();
            ArrayList<Character> ch = new ArrayList<>();

            //Create integer array from each line of input
            for (char c : array) {
                if (Character.isDigit(c)) {
                    ch.add(c);
                }
            }
            if (ch.size() == 1) {
                ch.add(ch.get(0));
            }
            //Drop middle elements from array
            for (int i = 0; i <= ch.size(); i++) {
                if (ch.size() > 2) {
                    ch.remove(1);
                }
            }
            //Add new integer array to main ArrayList
            int cali = Integer.parseInt(ch.toString().replaceAll("[\\D]", ""));
            System.out.println("Value: " + cali);
            intArray.add(cali);
        }

        return intArray;
    }

//    public static File writeToFile(ArrayList<Integer> list) throws IOException {
//        File outputFile = new File("com/example/unknown/output.txt");
//        if (outputFile.exists()) {
//            if (outputFile.delete()) {
//                System.out.println("Stale file deleted.");
//            } else {
//                System.out.println("Unable to delete output file.");
//            }
//        }
//
//        FileWriter writer = null;
//        if (outputFile.createNewFile()) {
//            writer = new FileWriter(outputFile, true);
//            for (Integer i: list) {
//                writer.write(i + "\n");
//            }
//        } else {
//            System.out.println("Failure to create output file");
//        }
//
//        if (writer != null) {
//            writer.close();
//        }
//        return outputFile;
//    }

    public static int sum(ArrayList<Integer> values) {
        int total = 0;
        for (Integer num: values) {
            total += num;
        }

        return total;
    }
}
