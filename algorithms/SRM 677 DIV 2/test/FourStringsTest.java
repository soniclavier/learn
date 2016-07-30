import org.junit.Test;
import static org.junit.Assert.*;

public class FourStringsTest {
	
	@Test(timeout=2000)
	public void test0() {
		String a = "abc";
		String b = "ab";
		String c = "bc";
		String d = "b";
		assertEquals(3, new FourStrings().shortestLength(a, b, c, d));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String a = "a";
		String b = "bc";
		String c = "def";
		String d = "ghij";
		assertEquals(10, new FourStrings().shortestLength(a, b, c, d));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String a = "top";
		String b = "coder";
		String c = "opco";
		String d = "pcode";
		assertEquals(8, new FourStrings().shortestLength(a, b, c, d));
	}
	
	@Test(timeout=2000)
	public void test3() {
		String a = "thereare";
		String b = "arelots";
		String c = "lotsof";
		String d = "ofcases";
		assertEquals(19, new FourStrings().shortestLength(a, b, c, d));
	}
	
	@Test(timeout=2000)
	public void test4() {
		String a = "aba";
		String b = "b";
		String c = "b";
		String d = "b";
		assertEquals(3, new FourStrings().shortestLength(a, b, c, d));
	}
	
	@Test(timeout=2000)
	public void test5() {
		String a = "x";
		String b = "x";
		String c = "x";
		String d = "x";
		assertEquals(1, new FourStrings().shortestLength(a, b, c, d));
	}
}
