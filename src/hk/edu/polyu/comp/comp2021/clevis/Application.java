package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.Logger;
import hk.edu.polyu.comp.comp2021.clevis.controller.Clevis;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        String htmlFilePath = null;
        String txtFilePath = null;
        
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-html") && i + 1 < args.length) {
                htmlFilePath = args[i + 1];
                i++;
            } else if (args[i].equals("-txt") && i + 1 < args.length) {
                txtFilePath = args[i + 1];
                i++;
            }
        }
        
        if (htmlFilePath == null || txtFilePath == null) {
            System.err.println("Error: Both -html and -txt file paths must be provided.");
            System.err.println("Usage: java hk.edu.polyu.comp.comp2021.clevis.view.Application -html <html_file> -txt <txt_file>");
            System.exit(1);
        }

        Logger logger = null;
        try {
            logger = new Logger(htmlFilePath, txtFilePath);
        } catch (IOException e) {
            System.err.println("Error: Failed to create log files: " + e.getMessage());
            System.exit(1);
        }

        Clevis clevis = new Clevis();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            String input = scanner.nextLine();
            String trimmedInput = input.trim();

            if (trimmedInput.isEmpty()) {
                continue;
            }

            logger.log(trimmedInput);

            String output = clevis.executeCommand(trimmedInput);
            
            // Check if quit command
            if (output.equals("quit")) {
                running = false;
            } else if (!output.isEmpty()) {
                System.out.println(output);
            }
        }

        logger.close();
        scanner.close();
    }
}
