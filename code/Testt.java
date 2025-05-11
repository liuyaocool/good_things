import org.junit.Test;

import java.util.Scanner;

public class Test {

    @org.junit.Test
    public void cursor() {
        System.out.println((byte) '\r');
        for (int i = 0; i < 20; i++) {
            System.out.print(i);
        }
        System.out.print('\r'); // 将光标移至行首
        for (int i = 0; i < 20; i++) {
            System.out.print('a');
        }

    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int sm = 0, n = in.nextInt(), m = in.nextInt();
        String [] line;
        for(; n > 0; n--) {
            line = in.nextLine().split(" ");
            for(; m > 0; m--) {
                sm += Integer.parseInt(line[m-1]);
            }
        }
        System.out.println(sm);
    }

}