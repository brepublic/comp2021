package hk.edu.polyu.comp.comp2021.clevis;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Writes logs to HTML and TXT files.
 */
public class Logger {
    private int operationIndex;
    private PrintWriter htmlWriter;
    private PrintWriter txtWriter;
    
    /**
     * Makes a new logger.
     * @param htmlFilePath path to the HTML log file
     * @param txtFilePath path to the TXT log file
     * @throws IOException if we can't create or open the files
     */
    public Logger(String htmlFilePath, String txtFilePath) throws IOException {
        this.operationIndex = 0;
        
        // set up the HTML file with a table
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
        
        // set up the TXT file
        txtWriter = new PrintWriter(new FileWriter(txtFilePath));
    }
    
    /**
     * Writes a command to both files.
     * @param command the command to write
     */
    public void log(String command) {
        operationIndex++;
        
        // write to HTML file
        htmlWriter.println("<tr><td>" + operationIndex + "</td><td>" + escapeHtml(command) + "</td></tr>");
        htmlWriter.flush();
        
        // write to TXT file
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
     * Fixes HTML special characters so they display right.
     * @param text the text to fix
     * @return the fixed text
     */
    private String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                  .replace("<", "&lt;")
                  .replace(">", "&gt;")
                  .replace("\"", "&quot;")
                  .replace("'", "&#39;");
    }
}

