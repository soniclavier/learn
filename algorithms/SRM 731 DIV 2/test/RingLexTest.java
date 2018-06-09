import org.junit.Test;
import static org.junit.Assert.*;

public class RingLexTest {
	
	@Test(timeout=2000)
	public void test0() {
		String s = "cba";
		assertEquals("abc", new RingLex().getmin(s));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String s = "acb";
		assertEquals("abc", new RingLex().getmin(s));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String s = "abacaba";
		assertEquals("aaaabcb", new RingLex().getmin(s));
	}
	
	@Test(timeout=2000)
	public void test3() {
		String s = "aaabb";
		assertEquals("aabab", new RingLex().getmin(s));
	}
	
	@Test(timeout=2000)
	public void test4() {
		String s = "azzzabbb";
		assertEquals("abazabaz", new RingLex().getmin(s));
	}
	
	@Test(timeout=2000)
	public void test5() {
		String s = "abbaac";
		assertEquals("aaaaaa", new RingLex().getmin(s));
	}
	
	@Test(timeout=2000)
	public void test6() {
		String s = "fsdifyhsoe";
		assertEquals("dehifsfsoy", new RingLex().getmin(s));
	}
}
