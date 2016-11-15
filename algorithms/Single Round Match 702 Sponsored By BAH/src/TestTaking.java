import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class TestTaking {
	
	public int findMax(int questions, int guessed, int actual) {
		if (questions == 0) {
			return 0;
		}
		int min = Math.min(guessed, actual);
		return min + (questions  - (min + Math.abs(guessed - actual)));
	}
}
