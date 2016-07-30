import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class FourStrings {
	
	public int shortestLength(String a, String b, String c, String d) {
        String temp;
		for(int i =0;i<4;i++) {
            if (a.length()<b.length()){
                temp = a;
                a = b;
                b = temp;
            }
            if (b.length() < c.length()) {
                temp = b;
                b = c;
                c = temp;
            }
            if (c.length() < d.length()) {
                temp = c;
                c = d;
                d = temp;
            }
        }

		return 0;

	}
}
