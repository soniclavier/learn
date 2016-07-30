import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class ListeningSongs {
	
	public int listen(int[] durations1, int[] durations2, int minutes, int T) {
		int seconds = minutes*60;
		Arrays.sort(durations1);
		Arrays.sort(durations2);
		int count1 = 0;
		int count2 = 0;
		int i=0;
		int j=0;
		for(i=0;i<durations1.length && count1<T;i++) {
			if (durations1[i]<=seconds) {
				count1++;
				seconds-=durations1[i];
			}
		}

		for(j=0;j<durations2.length && count2<T;j++) {
			if (durations2[j]<=seconds) {
				count2++;
				seconds-=durations2[j];
			}
		}

		for(;i<durations1.length && j<durations2.length && seconds >0;) {
			if (durations1[i]<durations2[j]) {
				if (durations1[i] <= seconds) {
					count1++;
					seconds -= durations1[i];
					i++;
				} else {
					break;
				}
			} else {
				if (durations2[j] <= seconds) {
					count2++;
					seconds -= durations2[j];
					j++;
				} else {
					break;
				}
			}
		}

		while (seconds >0 && i<durations1.length) {
			if (durations1[i] <= seconds) {
				count1++;
				seconds -= durations1[i];
				i++;
			} else {
				break;
			}
		}

		while (seconds >0 && j<durations2.length) {
			if (durations2[j] <= seconds) {
				count2++;
				seconds -= durations2[j];
				j++;
			} else {
				break;
			}
		}
		if (count1 <T || count2 <T)
			return -1;
		return count1+count2;
	}
}
