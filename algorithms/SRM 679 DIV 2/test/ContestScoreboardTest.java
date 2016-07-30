import org.junit.Test;
import static org.junit.Assert.*;

public class ContestScoreboardTest {
	
	@Test(timeout=2000)
	public void test0() {
		String[] scores = new String[] {"TVG 1/1 1/2 1/3", "AJI 1/4 1/5 1/6"};
		assertArrayEquals(new int[] {1, 1 }, new ContestScoreboard().findWinner(scores));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String[] scores = new String[] {"GLP 1/114 1/195 1/171 1/19 1/146 1/29","BKPF 1/57 1/187 1/277 1/21 1/223 1/35"};
		assertArrayEquals(new int[] {1, 1 }, new ContestScoreboard().findWinner(scores));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String[] scores = new String[] {"AAA 248/2 495/5 993/7","BBB 244/6 493/7 990/10", "CCC 248/2 495/5 993/10"};
		assertArrayEquals(new int[] {1, 0, 0 }, new ContestScoreboard().findWinner(scores));
	}
	
	@Test(timeout=2000)
	public void test3() {
		String[] scores = new String[] {"UBA 10/2 30/4 25/3 999/1000", "UNC 1/3 3/20 40/50", "UNLP 2/2 3/3 4/4 100/100", "UNR 999/1000000 999/999999", "UNS 999/100000000"};
		assertArrayEquals(new int[] {1, 0, 1, 1, 0 }, new ContestScoreboard().findWinner(scores));
	}
}
