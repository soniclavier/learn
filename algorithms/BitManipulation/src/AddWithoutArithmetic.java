

public class AddWithoutArithmetic {

    public static void main(String[] args) {
        System.out.println(add(121,14));
    }

    /**
     * add two ints a & b
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {
        if (a > b) {
            //a < b
            return add(b, a);
        }

        int result = 0;
        int c = 0;
        int i = 0;

        while(b > 0) {
            int a1 = (a & 1);
            int b1 = (b & 1);
            int d = a1 ^ b1;
            int r = d ^ c;

            if ((a1  == 1 && b1 == 1) || (a1 == 1 && c == 1) || (b1 == 1 && c == 1)) {
                c = 1;
            } else {
                c = 0;
            }

            result |= (r << i);

            a = a >> 1;
            b = b >> 1;
            i++;
        }
        result |= ((b ^ c) << i);

        return result;

    }

}
