package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File input = new File("input.txt");
        Scanner scan_ = new Scanner(System.in);
        System.out.println("Enter the desired type:");
        System.out.println("Word: 'dasldasd', 'ab', etc.");
        System.out.println("Int: '1','24','124', etc.");
        System.out.println("Float: '1.2', '0.0', '132.3123', etc.");
        System.out.println("Char: 'a', 'b', '$', etc.");
        String type = scan_.nextLine();

        File dir = new File("outputs");
        Boolean dir_cr = dir.mkdirs();
        String path = "outputs\\output_" + type + ".txt";
        File output = new File(path);

        try {

            FileWriter myWriter = new FileWriter(path);

            try (Scanner scan = new Scanner(input)) {

                while (scan.hasNextLine()) {
                    String s = scan.nextLine();

                    // Определяем тип
                    String type_of_line = "Int";
                    for (int i = 0; i < s.length(); i++) {
                        if (s.charAt(i) == '.' && type_of_line.equals("Int")) {
                            type_of_line = "Float";
                        }
                        if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != '.') {
                            type_of_line = "Word";
                        }
                    }
                    if (type_of_line.equals("Word") && s.length() == 1) {
                        type_of_line = "Char";
                    }
                    // Тип определен

                    if (type_of_line.equals(type)) {
                        // Выводим подобранные строки в файл
                        myWriter.write(s+'\r'+'\n');
                    }
                }

            }
            catch (FileNotFoundException exp) {

                System.out.println("I don't see input file, bro :C");

            }
            myWriter.close();

        } catch (IOException e) {

            System.out.println("Something with output, bro :C");

        }
    }
}