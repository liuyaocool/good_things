import java.io.IOException;
import java.io.InputStream;

public class Util {
    static Runtime runtime = Runtime.getRuntime();

    static boolean run(String[] command) throws InterruptedException, IOException {
        StringBuilder sb = new StringBuilder();
        for (String s : command) sb.append(s).append(" ");

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true); // 将标准错误流合并到标准输出流
        Process process = processBuilder.start();
//        Process process = runtime.exec(command);
        InputStream is = process.getInputStream();
        byte[] bytes = new byte[16];
        int readLen;
        while ((readLen = is.read(bytes)) >= 0) {
            if (sb.length() > 128) {
                System.out.print(sb.toString());
                sb = new StringBuilder();
            }
            for (int i = 0; i < readLen; i++) {
                sb.append(13 == bytes[i] ? '\n' : (char) bytes[i]);
            }
        }
        System.out.println(sb.toString());
        return 0 == process.waitFor() || 1 != process.exitValue();
    }

}
