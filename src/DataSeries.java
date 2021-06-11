/**
 * Class to model a simplified version of pandas' Series object
 *
 * @author Bhavyai Gupta
 * @version 1.0
 * @since June 11, 2021
 */
public class DataSeries implements IBasicStats {
    /**
     * stores the label of the data
     */
    private String label;

    /**
     * 1D array of type double to store the data
     */
    private double[] data;

    /**
     * stores the valid size of the data. Size is different from the length of data
     * which is fixed as 100.
     */
    private int count;

    /**
     * Constructor to create new object of type DataSeries. Instance variable label
     * is initialized with the string argument and data array is initialized to
     * default value of 100
     *
     * @param label string holding the label of the data
     */
    public DataSeries(String label) {
        this.label = label;
        this.data = new double[100];
    }

    /**
     * Method to add values to the DataSeries' instance variable data
     *
     * @param x the value to be added
     */
    public void add(double x) {
        this.data[this.count++] = x;
    }

    /**
     * Getter for count
     *
     * @return count
     */
    public int size() {
        return this.count;
    }

    /**
     * Method to fetch the minimum value of the DataSeries' instance variable data
     *
     * @return min the minimum value in the array data
     */
    @Override
    public double getMin() {
        double min = this.data[0];

        for (int i = 1; i < this.count; i++) {
            min = Math.min(min, this.data[i]);
        }

        return min;
    }

    /**
     * Method to fetch the maximum value of the DataSeries' instance variable data
     *
     * @return max the maximum value in the array data
     */
    @Override
    public double getMax() {
        double max = this.data[0];

        for (int i = 1; i < this.count; i++) {
            max = Math.max(max, this.data[i]);
        }

        return max;
    }

    /**
     * Method to fetch the sum of all the values of the DataSeries' instance
     * variable data
     *
     * @return sum of all values in the array data
     */
    @Override
    public double getSum() {
        double sum = 0.0;

        for (int i = 0; i < this.count; i++) {
            sum += this.data[i];
        }

        return sum;
    }

    /**
     * Method to fetch the average of the values stored in DataSeries' instance
     * variable data
     *
     * @return mean the average of the values stored in variable data
     */
    @Override
    public double getMean() {
        double mean = this.getSum() / this.count;

        return mean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append(this.label + " [");
        int i;

        // variable to split the data in two halves for printing
        int halves = Math.min(5, this.count / 2);

        // print the first half of the values in the data
        for (i = 0; i < halves; i++) {
            sb.append(String.format("%.2f " , data[i]));
        }

        // add "..." if count is more than 10
        if (this.count > 10) {
            sb.append("... ");
        }

        // adjust the value of starting point of printing depending upon count length
        // and if count is odd
        if (this.count < 10 && this.count % 2 != 0) {
            i = this.count - halves - 1;
        }

        else {
            i = this.count - halves;
        }

        // print the second half of the values in the data
        for (; i < this.count; i++) {
            sb.append(String.format("%.2f", data[i]));

            if (i != this.count - 1) {
                sb.append(" ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}
