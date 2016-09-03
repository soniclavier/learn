import org.junit.Test;
import static org.junit.Assert.*;

public class BearChairsTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] atLeast = new int[] {1,21,11,7};
		int d = 10;
		assertArrayEquals(new int[] {1, 21, 11, 31 }, new BearChairs().findPositions(atLeast, d));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] atLeast = new int[] {1,21,11,7};
		int d = 11;
		assertArrayEquals(new int[] {1, 21, 32, 43 }, new BearChairs().findPositions(atLeast, d));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] atLeast = new int[] {1000000,1000000,1000000,1};
		int d = 1000000;
		assertArrayEquals(new int[] {1000000, 2000000, 3000000, 4000000 }, new BearChairs().findPositions(atLeast, d));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] atLeast = new int[] {1000000,1000000,1000000,1};
		int d = 999999;
		assertArrayEquals(new int[] {1000000, 1999999, 2999998, 1 }, new BearChairs().findPositions(atLeast, d));
	}
}
