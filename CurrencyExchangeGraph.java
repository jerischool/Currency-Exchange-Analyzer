import java.util.Arrays;

public class CurrencyExchangeGraph {

    private final int numCurrencies; // number of currencies
    private final double[][] exchangeRates; // adjacency matrix for exchange rates

    // constructor to initialize graph with exchange rates
    public CurrencyExchangeGraph(int numCurrencies) {
        this.numCurrencies = numCurrencies;
        this.exchangeRates = new double[numCurrencies][numCurrencies];
        
        // initialize all exchange rates to infinity
        for (int i = 0; i < numCurrencies; i++) {
            Arrays.fill(exchangeRates[i], Double.POSITIVE_INFINITY);
        }
    }

    // get and set methods for exchange rate between two currencies
    public void setExchangeRate(int fromCurrency, int toCurrency, double rate) {
        exchangeRates[fromCurrency][toCurrency] = rate;
    }

    public double getExchangeRate(int fromCurrency, int toCurrency) {
        return exchangeRates[fromCurrency][toCurrency];
    }

    // method to convert exchange rates to Negative Logarithms
    public void convertToNegativeLogarithms() {
        for (int i = 0; i < numCurrencies; i++) {
            for (int j = 0; j < numCurrencies; j++) {
                if (exchangeRates[i][j] != Double.POSITIVE_INFINITY) {
                    // convert exchange rate to NEGATIVE LOGARITHM
                    exchangeRates[i][j] = -Math.log(exchangeRates[i][i]);
                }
            }
        }
    }

    // method to get the number of currencies
    public int getNumCurrencies() {
        return numCurrencies;
    }

    // method to get the adjacency matrix
    public double[][] getAdjacencyMatrix() {
        return exchangeRates;
    }

}

