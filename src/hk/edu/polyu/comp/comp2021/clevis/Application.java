package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main application class for the Clevis drawing tool.
 * Provides a command-line interface and handles logging.
 */
public class Application {

    public static void main(String[] args) {
        // Parse command-line arguments
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
            System.err.println("Usage: java hk.edu.polyu.comp.comp2021.clevis.Application -html <html_file> -txt <txt_file>");
            System.exit(1);
        }
        
        // Initialize logger
        Logger logger = null;
        try {
            logger = new Logger(htmlFilePath, txtFilePath);
        } catch (IOException e) {
            System.err.println("Error: Failed to create log files: " + e.getMessage());
            System.exit(1);
        }
        
        // Initialize Clevis
        Clevis clevis = new Clevis();
        
        // Command-line interface
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            String input = scanner.nextLine();
            String trimmedInput = input.trim();
            
            // Skip empty lines
            if (trimmedInput.isEmpty()) {
                continue;
            }
            
            // Log the command
            logger.log(trimmedInput);
            
            // Execute the command
            String output = clevis.executeCommand(trimmedInput);
            
            // Check if quit command
            if (output.equals("quit")) {
                running = false;
            } else if (!output.isEmpty()) {
                // Print output (errors or results)
                System.out.println(output);
            }
        }
        
        // Close logger
        logger.close();
        scanner.close();
    }
}
