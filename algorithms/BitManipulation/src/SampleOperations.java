

/**
 * Created by vishnu on 2/7/16.
 */
public class SampleOperations {

    public static char flipCase(int ch) {
        int mask = 0b0010_0000;
        return (char)(ch^mask);
    }

    public static char toChar(int num) {
        int mask = 0b0011_0000;
        return (char)(num|mask);
    }
    public static void main(String[] args) {
        System.out.println(flipCase('a'));
        System.out.println(toChar(2));
    }
}
