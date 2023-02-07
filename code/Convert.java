import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Convert {

    static Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) throws Exception {
        convert(new File("/Users/liuyao/Documents/av/webm"), ".ts");
    }

    static void convert(File file, String suffix) throws Exception{
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File f: file.listFiles()) {
                convert(f, suffix);
            }
            return;
        }
        String fullPath = file.getAbsolutePath();
        if (!fullPath.endsWith(suffix)) {
            return;
        }
        String fullPathNew = fullPath.substring(0, fullPath.length() - suffix.length()) + ".mp4";

        Process process = runtime.exec(String.format(
                "ffmpeg -i %s -c:v copy -y %s", fullPath, fullPathNew));
        if (0 != process.waitFor()) {
            if (1 == process.exitValue()) {
                InputStream is = process.getInputStream();
                byte[] bytes = is.readAllBytes();
                System.out.println(new String(bytes, StandardCharsets.UTF_8)
                        .replace("\n", ""));
                System.out.format("执行失败: %s\n", fullPath);
                return;
            }
        }
        System.out.format("convert: %s\n", fullPath);
    }
}
