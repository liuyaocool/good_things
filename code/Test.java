public class Test {

    public static void main(String[] args) throws Exception {
        System.out.println((byte) '\r');
        for (int i = 0; i < 20; i++) {
            System.out.print(i);
        }
        System.out.print('\r'); // 将光标移至行首
        for (int i = 0; i < 20; i++) {
            System.out.print('a');
        }

    }

}