import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class BearPair {
	
	public int bigDistance(String s) {
		int diff = -1;
		for(int i=0;i<s.length();i++) {
			int j = i+1;
			while (j<s.length()) {
				if (s.charAt(i) != s.charAt(j)) {
					if ((j-i) > diff)
						diff = j-i;
				}
				j++;
			}
		}

		return diff;

	}
}
