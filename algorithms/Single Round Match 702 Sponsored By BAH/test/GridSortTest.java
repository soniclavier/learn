import org.junit.Test;
import static org.junit.Assert.*;

public class GridSortTest {
	
	@Test(timeout=2000)
	public void test0() {
		int n = 2;
		int m = 2;
		int[] grid = new int[] {
 1,2,
 3,4
};
		assertEquals("Possible", new GridSort().sort(n, m, grid));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int n = 2;
		int m = 2;
		int[] grid = new int[] {
 3,4,
 1,2
};
		assertEquals("Possible", new GridSort().sort(n, m, grid));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int n = 2;
		int m = 2;
		int[] grid = new int[] {
 4,3,
 1,2
};
		assertEquals("Impossible", new GridSort().sort(n, m, grid));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int n = 1;
		int m = 10;
		int[] grid = new int[] {4,5,1,2,9,8,3,10,7,6};
		assertEquals("Possible", new GridSort().sort(n, m, grid));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int n = 3;
		int m = 5;
		int[] grid = new int[] {
 10,6,8,9,7,
 5,1,3,4,2,
 15,11,13,14,12
};
		assertEquals("Possible", new GridSort().sort(n, m, grid));
	}
	
	@Test(timeout=2000)
	public void test5() {
		int n = 6;
		int m = 2;
		int[] grid = new int[] {
 11,12,
 2,1,
 9,10,
 7,8,
 6,5,
 3,4
};
		assertEquals("Impossible", new GridSort().sort(n, m, grid));
	}
}
