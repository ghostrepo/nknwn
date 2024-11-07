package com.example.unknown;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Dos {
    public static final String TAG = "Testing";
    public static final ArrayList<String> words = new ArrayList<>();
    public static final String one = "one";
    public static final String two = "two";
    public static final String three = "three";
    public static final String four = "four";
    public static final String five = "five";
    public static final String six = "six";
    public static final String seven = "seven";
    public static final String eight = "eight";
    public static final String nine = "nine";

    public static void main(String[] args) {
        System.out.println("Welcome to Online IDE!! Happy Coding :)");
        File in = new File("com/example/unknown/input.txt");
//        File out = null;
        ArrayList<Integer> output = new ArrayList<>();
        int calibration = 0;
        words.add(one);
        words.add(two);
        words.add(three);
        words.add(four);
        words.add(five);
        words.add(six);
        words.add(seven);
        words.add(eight);
        words.add(nine);

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

    private static char[] toArray(ArrayList<Character> list) {
        char[] arr = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static boolean findWord(char[] line, char[] word) {
        int index = 0;
        boolean match = false;
        for (int i = 0; i < line.length; i++) {
            if (line[i] == word[0]) {
                for (int j = 0; j < word.length && i + j < line.length; j++) {
                    if (line[i + j] == word[j]) {
                        index++;
                        if (index == word.length) {
                            match = true;
                        }
                    } else if ((j + i) == word.length) {
                        match = true;
                        break;
                    }
                }
            }
        }
        return match;
    }

    public static ArrayList<Integer> calibrate(File input) throws FileNotFoundException {
        ArrayList<Integer> intArray = new ArrayList<>();
        Scanner s = new Scanner(input);
        //Parse through each line
        while (s.hasNextLine()) {
            //Get line and save to char array
            char[] array = s.nextLine().toCharArray();
            ArrayList<Character> ch = new ArrayList<>();
            String charString = new String(array);
            for (char c : array) {
                ch.add(c);
            }
            //Check for number strings in line and replace with int value
            for (String str: words) {
                while (charString.contains(str)) {
                    if (findWord(array, str.toCharArray())) {
                        int position = charString.indexOf(str);
                        ch.add(position + 1, (sToC(str)));
                        ch.remove(position + 2);
                        charString = new String(toArray(ch));
                    }
                }
            }
            //Remove characters from array
            for (int i = ch.size() - 1; i > -1; i--) {
                if (!Character.isDigit(ch.get(i))) {
                    ch.remove(i);
                }
            }
            //Account for single-digit lines
            if (ch.size() == 1) {
                ch.add(ch.get(0));
            }
            //Drop middle elements from array
            while (ch.size() > 2) {
                ch.remove(1);
            }
            //Add new integer array to main ArrayList
            int cali = Integer.parseInt(ch.toString().replaceAll("\\D", ""));
            System.out.println("Value: " + cali);
            intArray.add(cali);
        }
        return intArray;
    }

//    public static File writeToFile(ArrayList<Integer> list) throws IOException, IOException {
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

    public static char sToC(String str) {
        char number = '0';
        switch (str) {
            case "one":
                number = '1';
                break;
            case "two":
                number = '2';
                break;
            case "three":
                number = '3';
                break;
            case "four":
                number = '4';
                break;
            case "five":
                number = '5';
                break;
            case "six":
                number = '6';
                break;
            case "seven":
                number = '7';
                break;
            case "eight":
                number = '8';
                break;
            case "nine":
                number = '9';
                break;
            default:
                break;
        }
        return number;
    }
}
