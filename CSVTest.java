import java.io.IOException;
import java.util.List;

public class CSVTest {

    public static void main(String[] args) {
        try {
            // Process multiple CSV files
            String[] csvFiles = {"example.csv", "nbaplayers.csv"};
            for (String csvFile : csvFiles) {
                processCSV(csvFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void processCSV(String csvFile) throws IOException {
        // Read data from CSV file
        List<List<String>> newData = CSV.readCSV(csvFile);
        System.out.println("Data read from CSV file '" + csvFile + "':");
        for (List<String> row : newData) {
            System.out.println(row);
        }
        // Write HTML table from CSV file
        String htmlFile = csvFile.replace(".csv", ".html");
        CSV.writeHTMLTable(newData, htmlFile);
        System.out.println("HTML table written to '" + htmlFile + "'.");
    }
}
