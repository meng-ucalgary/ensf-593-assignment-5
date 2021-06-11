import java.util.regex.*;

/**
 * TextAnalyzer class to calculate the number of words and sentences in a given
 * string
 *
 * @author Bhavyai Gupta
 * @version 1.0
 * @since June 11, 2021
 */
public class TextAnalyzer {
    /**
     * integer to store the number of sentences in the analyzed text
     */
    private int numOfSentences;

    /**
     * integer to store the number of words in the analyzed text
     */
    private int numOfWords;

    /**
     * Constructor to create object of TextAnalyzer and to analyze the text by
     * counting the number of words and sentences in a parameter <code>str</code>
     * and store the results in instance variables
     *
     * @param str The text that needs to be analyzed
     */
    public TextAnalyzer(String str) {
        str = str.trim();

        if (str.equals("")) {
            this.numOfSentences = 0;
            this.numOfWords = 0;
        }

        else {
            /*
             * problem with split() emerges when examples like "How are you? Hello" are
             * used. split() will split this example sentence into "How are you" and
             * "Hello", thus reporing two sentences (which is not correct).
             *
             * Another example can be a single word like "Hey". This is not a sentence, this
             * is just a word
             */
            // this.numOfSentences = str.split("[.?!]+", 0).length;

            Pattern pattern = Pattern.compile("[.?!]+");
            Matcher matcher = pattern.matcher(str);
            this.numOfSentences = (int) matcher.results().count();

            pattern = Pattern.compile("\\s");

            // failing for multiple line breaks
            // Matcher matcher = pattern.matcher(str);
            // this.numOfWords = (int) matcher.results().count() + 1;

            String words[] = pattern.split(str);

            // loop to not count the empty words (which may arise due to multiple line
            // breaks)
            for (String word : words) {
                if (!word.equals(""))
                    this.numOfWords++;
            }
        }
    }

    /**
     * Getter for numOfSentences
     *
     * @return numOfSentences
     */
    public int getNumOfSentences() {
        return this.numOfSentences;
    }

    /**
     * Getter for numOfWords
     *
     * @return numOfWords
     */
    public int getNumOfWords() {
        return this.numOfWords;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("The text contains %d sentences and %s words", this.getNumOfSentences(), this.getNumOfWords());
    }
}
