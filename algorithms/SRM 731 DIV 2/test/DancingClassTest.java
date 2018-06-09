import org.junit.Test;
import static org.junit.Assert.*;

public class DancingClassTest {
	
	@Test(timeout=2000)
	public void test0() {
		int n = 2;
		int k = 1;
		assertEquals("Equal", new DancingClass().checkOdds(n, k));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int n = 3;
		int k = 2;
		assertEquals("High", new DancingClass().checkOdds(n, k));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int n = 4;
		int k = 4;
		assertEquals("Low", new DancingClass().checkOdds(n, k));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int n = 500;
		int k = 500;
		assertEquals("High", new DancingClass().checkOdds(n, k));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int n = 40;
		int k = 397;
		assertEquals("Low", new DancingClass().checkOdds(n, k));
	}
	
	@Test(timeout=2000)
	public void test5() {
		int n = 1;
		int k = 1;
		assertEquals("Low", new DancingClass().checkOdds(n, k));
	}
}
