import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSV {
    
    /*
     *  Description: Reads the csv file by adding each value seperated by a comma
     *  This also seperates the values from each respective row 
     */
    public static List<List<String>> readCSV(String filePath) throws IOException {
        List<List<String>> rows = new ArrayList<>();
        // Reads line by line, which means row by row
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // While the line is not empty, keep adding values into the array spliting it by a comma ","
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Add the values into it's respective row array
                List<String> row = new ArrayList<>();
                for (String value : values) {
                    row.add(value);
                }
                rows.add(row);
            }
        }
        return rows;
    }
    
    /*
     *  Description: Creates the html content to display a table
     */
    public static void writeHTMLTable(List<List<String>> data, String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("<html><head><style>table, th, td { border: 1px solid black; border-collapse: collapse; padding: 5px; }</style></head><body>");
            bw.write("<table>");
            for (List<String> row : data) {
                bw.write("<tr>");
                for (String value : row) {
                    bw.write("<td>" + value + "</td>");
                }
                bw.write("</tr>");
            }
            bw.write("</table></body></html>");
        }
    }
    
    public static void main(String[] args) {
        try {
            // Asks user to enter the file path of the CSV file
            // Example: example.csv or if it's inside a folder: foldername/example.csv
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the file path of the CSV file: ");
            String filePath = reader.readLine();
            
            // Read data from CSV file
            List<List<String>> newData = readCSV(filePath);
            
            // Asks user to enter the file name of the html file they want
            System.out.print("Enter the file name of what you want the CSV table to be called (ADD .html at the end of the name): ");
            String fileName = reader.readLine();
            // Create HTML table and print it out into a new file
            writeHTMLTable(newData, fileName);
            System.out.println("HTML table created successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
