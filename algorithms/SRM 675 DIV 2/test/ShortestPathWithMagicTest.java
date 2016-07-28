import org.junit.Test;
import static org.junit.Assert.*;

public class ShortestPathWithMagicTest {
	
	@Test(timeout=2000)
	public void test0() {
		String[] dist = new String[] {"094",
 "904",
 "440"};
		int k = 1;
		assertEquals(4.5, new ShortestPathWithMagic().getTime(dist, k), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test1() {
		String[] dist = new String[] {"094",
 "904",
 "440"};
		int k = 2;
		assertEquals(4.0, new ShortestPathWithMagic().getTime(dist, k), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test2() {
		String[] dist = new String[] {"094",
 "904",
 "440"};
		int k = 50;
		assertEquals(4.0, new ShortestPathWithMagic().getTime(dist, k), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test3() {
		String[] dist = new String[] {"094",
 "904",
 "440"};
		int k = 0;
		assertEquals(8.0, new ShortestPathWithMagic().getTime(dist, k), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test4() {
		String[] dist = new String[] {"076237",
 "708937",
 "680641",
 "296059",
 "334508",
 "771980"};
		int k = 1;
		assertEquals(3.5, new ShortestPathWithMagic().getTime(dist, k), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test5() {
		String[] dist = new String[] {"00",
 "00"};
		int k = 50;
		assertEquals(0.0, new ShortestPathWithMagic().getTime(dist, k), 1e-9);
	}
}
