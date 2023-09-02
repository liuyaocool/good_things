import sun.nio.ch.FileChannelImpl;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Convert {

    static Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) throws Exception {
//        convert(new File("/Users/liuyao/Documents/jp/video/aa"), ".avi");
        convert(new File("/Users/liuyao/Documents/jp/video"), ".ts");
    }

    static void convert(File file, String suffix) throws Exception{
        if (!file.exists()) {
            System.out.println("路径不存在");
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

        String[] command = {"ffmpeg", "-i", fullPath, "-c:v", "copy", "-y", fullPathNew};
        System.out.format(Util.run(command) ? "转换成功: %s\n" : "转换失败: %s\n", fullPath);
    }
}
