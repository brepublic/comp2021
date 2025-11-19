package hk.edu.polyu.comp.comp2021.clevis;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Handles logging of operations to HTML and TXT files.
 */
public class Logger {
    private int operationIndex;
    private PrintWriter htmlWriter;
    private PrintWriter txtWriter;
    
    /**
     * Creates a new logger.
     * @param htmlFilePath the path to the HTML log file
     * @param txtFilePath the path to the TXT log file
     * @throws IOException if the files cannot be created or opened
     */
    public Logger(String htmlFilePath, String txtFilePath) throws IOException {
        this.operationIndex = 0;
        
        // Initialize HTML file with table structure
        htmlWriter = new PrintWriter(new FileWriter(htmlFilePath));
        htmlWriter.println("<!DOCTYPE html>");
        htmlWriter.println("<html>");
        htmlWriter.println("<head>");
        htmlWriter.println("<title>Clevis Operation Log</title>");
        htmlWriter.println("<style>");
        htmlWriter.println("table { border-collapse: collapse; width: 100%; }");
        htmlWriter.println("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
        htmlWriter.println("th { background-color: #f2f2f2; }");
        htmlWriter.println("</style>");
        htmlWriter.println("</head>");
        htmlWriter.println("<body>");
        htmlWriter.println("<h1>Clevis Operation Log</h1>");
        htmlWriter.println("<table>");
        htmlWriter.println("<tr><th>Operation Index</th><th>Operation Command</th></tr>");
        
        // Initialize TXT file
        txtWriter = new PrintWriter(new FileWriter(txtFilePath));
    }
    
    /**
     * Logs a command to both files.
     * @param command the command to log
     */
    public void log(String command) {
        operationIndex++;
        
        // Log to HTML file
        htmlWriter.println("<tr><td>" + operationIndex + "</td><td>" + escapeHtml(command) + "</td></tr>");
        htmlWriter.flush();
        
        // Log to TXT file
        txtWriter.println(command);
        txtWriter.flush();
    }
    
    /**
     * Closes the log files.
     */
    public void close() {
        if (htmlWriter != null) {
            htmlWriter.println("</table>");
            htmlWriter.println("</body>");
            htmlWriter.println("</html>");
            htmlWriter.close();
        }
        if (txtWriter != null) {
            txtWriter.close();
        }
    }
    
    /**
     * Escapes HTML special characters.
     * @param text the text to escape
     * @return the escaped text
     */
    private String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                  .replace("<", "&lt;")
                  .replace(">", "&gt;")
                  .replace("\"", "&quot;")
                  .replace("'", "&#39;");
    }
}

