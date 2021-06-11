import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to model a simplified verion of pandas' DataFrame object
 *
 * @author Bhavyai Gupta
 * @version 1.0
 * @since June 11, 2021
 */
public class DataFrame {
    /**
     * string array to store the header of the CSV file
     */
    private String[] headerNames;

    /**
     * 2D array of type double to store the actual data of the CSV file
     */
    private double[][] data;

    /**
     * integer holding the actual number of rows that contain data in the CSV file
     */
    private int numOfRows;

    /**
     * integer holding the actual number of columns in the CSV file
     */
    private int numOfCols;

    /**
     * Constructor to create an object of DataFrame by reading the CSV file whose
     * path is passed as an argument. Then, populate the headerNames and data array
     * based data present in the CSV file
     *
     * @param filename the location to the CSV file on disk that is to be loaded for
     *                 analysis
     * @throws IOException
     */
    public DataFrame(String filename) throws IOException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));

        // first row is the header, so assign it to headerNames
        this.headerNames = sc.nextLine().split(",");

        // assign the column names based on the columns in the header (assuming no
        // missing columns in the CSV)
        this.numOfCols = this.headerNames.length;

        // instantiating the data object with fixed (100) rows and columns as present in
        // the CSV file
        data = new double[100][this.numOfCols];

        // loop to read through the file and store data in the data array
        while (sc.hasNextLine()) {
            String line[] = sc.nextLine().split(",");

            for (int i = 0; i < line.length; i++) {
                this.data[this.numOfRows][i] = Double.parseDouble(line[i]);
            }

            this.numOfRows++;
        }

        sc.close();
    }

    /**
     * Method to get a DataSeries object based on the column index passed as
     * parameter
     *
     * @param x the column index
     * @return object of DataSeries representing the column with index
     *         <code>x</code> of the DataFrame
     */
    public DataSeries getColumnByIndex(int x) {
        DataSeries ds = new DataSeries(this.headerNames[x]);

        for (int i = 0; i < this.numOfRows; i++)
            ds.add(this.data[i][x]);

        return ds;
    }

    /**
     * Getter for headerNames
     *
     * @return getHeaderNames[]
     */
    public String[] getHeaderNames() {
        return this.headerNames;
    }

    /**
     * Getter for numOfRows
     *
     * @return numOfRows
     */
    public int getNumOfRows() {
        return this.numOfRows;
    }

    /**
     * Getter for numOfCols
     *
     * @return numOfCols
     */
    public int getNumOfCols() {
        return this.numOfCols;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        // array that will hold the padding for pretty print
        int[] padding = new int[this.headerNames.length];

        // loop to assign max padding based upon header length or 6 (6 is hardcoded for
        // length of double value xxx.xx)
        for (int p = 0; p < this.headerNames.length; p++) {
            padding[p] = Math.max(this.headerNames[p].length(), 6);
        }

        // sb shall hold the string that this method will return
        StringBuilder sb = new StringBuilder("");

        // integer for iteration
        int i;

        // code loop for printing divider
        // -----------------------------
        sb.append("\n+");
        for (int p = 0; p < padding.length; p++) {
            for (int q = 0; q < padding[p] + 2; q++) {
                sb.append("-");
            }
            sb.append("+");
        }

        sb.append("\n");
        // -----------------------------

        for (i = 0; i < this.headerNames.length; i++) {
            sb.append(String.format("| %s ", String.format("%-" + padding[i] + "s", this.headerNames[i])));
        }

        sb.append("|\n");

        // code loop for printing divider
        // -----------------------------
        sb.append("+");
        for (int p = 0; p < padding.length; p++) {
            for (int q = 0; q < padding[p] + 2; q++) {
                sb.append("-");
            }
            sb.append("+");
        }

        sb.append("\n");
        // -----------------------------

        int halves = Math.min(5, this.numOfRows / 2);

        // print the first half of the values in the data
        for (i = 0; i < halves; i++) {
            sb.append("|");
            for (int j = 0; j < this.data[i].length; j++) {
                sb.append(String.format(" %s |", String.format("%-" + padding[j] + "s", String.format("%.2f", this.data[i][j]))));
            }

            sb.append("\n");
        }

        // printing ... to represent skipping of rows more than 10
        if (this.numOfRows > 10) {
            sb.append("  .\n  .\n  .\n");
        }

        // adjust the value of starting point of printing depending upon count length
        // and if count is odd
        if (this.numOfRows < 10 && this.numOfRows % 2 != 0) {
            i = this.numOfRows - halves - 1;
        }

        else {
            i = this.numOfRows - halves;
        }

        // print the second half of the values in the data
        for (; i < this.numOfRows; i++) {
            sb.append("|");
            for (int j = 0; j < this.data[i].length; j++) {
                sb.append(String.format(" %s |", String.format("%-" + padding[j] + "s", String.format("%.2f", this.data[i][j]))));
            }

            sb.append("\n");
        }

        // code loop for printing divider
        // -----------------------------
        sb.append("+");
        for (int p = 0; p < padding.length; p++) {
            for (int q = 0; q < padding[p] + 2; q++) {
                sb.append("-");
            }
            sb.append("+");
        }

        sb.append("\n");
        // -----------------------------


        return sb.toString();
    }
}
