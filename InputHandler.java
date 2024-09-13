import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputHandler {
    
    // method to read currency exchange data from a file and create a CurrencyExchangeGraph object
    public static CurrencyExchangeGraph readCurrencyExchangeData(String filePath) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        // read first line to get number of currencies and their names
        line = reader.readLine();
        String[] currencyNames = line.split(", ");
        int numCurrencies = Integer.parseInt(currencyNames[2]);

        // create a CurrencyExchangeGraph object with the number of currencies
        CurrencyExchangeGraph graph = new CurrencyExchangeGraph(numCurrencies);

        // read exchange rates from the file
        int row = 0;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(", ");
            for (int col = 0; col < tokens.length; col++) {
                // skip diagonal elements (self-exchange rates all are 1)
                if (row != col) { 
                    double rate = Double.parseDouble(tokens[col]);
                    graph.setExchangeRate(row, col, rate);
                }
            }
            row++;
        }
        reader.close();
        return graph;
    }

    // method to print the contents of a CurrencyExchangeGraph (for debugging purposes)
    public static void printCurrencyExchangeGraph(CurrencyExchangeGraph graph) {
        int numCurrencies = graph.getNumCurrencies();
        double[][] matrix = graph.getAdjacencyMatrix();

        System.out.println("Currency Exchange Rates:");
        for (int i = 0; i < numCurrencies; i++) {
            for (int j = 0; j < numCurrencies; j++) {
                if (matrix[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(matrix[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }
}
