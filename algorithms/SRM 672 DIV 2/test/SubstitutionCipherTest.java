import org.junit.Test;
import static org.junit.Assert.*;

public class SubstitutionCipherTest {
	
	@Test(timeout=2000)
	public void test0() {
		String a = "CAT";
		String b = "DOG";
		String y = "GOD";
		assertEquals("TAC", new SubstitutionCipher().decode(a, b, y));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String a = "BANANA";
		String b = "METETE";
		String y = "TEMP";
		assertEquals("", new SubstitutionCipher().decode(a, b, y));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String a = "THEQUICKBROWNFOXJUMPSOVERTHELAZYHOG";
		String b = "UIFRVJDLCSPXOGPYKVNQTPWFSUIFMBAZIPH";
		String y = "DIDYOUNOTICESKIPPEDLETTER";
		assertEquals("CHCXNTMNSHBDRJHOODCKDSSDQ", new SubstitutionCipher().decode(a, b, y));
	}
}
