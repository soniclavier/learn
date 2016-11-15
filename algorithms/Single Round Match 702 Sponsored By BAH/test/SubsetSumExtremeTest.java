import org.junit.Test;
import static org.junit.Assert.*;

public class SubsetSumExtremeTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] block = new int[] {1,2,3};
		int[] face = new int[] {6,5};
		assertEquals(0.5, new SubsetSumExtreme().getExpectation(block, face), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] block = new int[] {1,2,1}	;
		int[] face = new int[] {1,2};
		assertEquals(0.5, new SubsetSumExtreme().getExpectation(block, face), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] block = new int[] {10,11,12};
		int[] face = new int[] {3,4,5,6};
		assertEquals(33.0, new SubsetSumExtreme().getExpectation(block, face), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] block = new int[] {1,1,1,1};
		int[] face = new int[] {1};
		assertEquals(0.0, new SubsetSumExtreme().getExpectation(block, face), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test4() {
		int[] block = new int[] {3,2,2,3};
		int[] face = new int[] {2,3,2,3,2,3};
		assertEquals(2.1875, new SubsetSumExtreme().getExpectation(block, face), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test5() {
		int[] block = new int[] {968,423,592,419,321,253,62,42,12,32,2,4};
		int[] face = new int[] {968,423,592,419,321,253,62,42,12,32,2,4};
		assertEquals(1996.9320680897076, new SubsetSumExtreme().getExpectation(block, face), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test6() {
		int[] block = new int[] {12,11,10,9,8,7,6,5,4,3,2,1};
		int[] face = new int[] {12,12,12,12,12,6,6,6,3,3,2,1};
		assertEquals(40.03765576104895, new SubsetSumExtreme().getExpectation(block, face), 1e-9);
	}
}
