import java.io.File;
import java.util.Comparator;
import java.util.TreeSet;

public class Merge {

    public static void main(String[] args) throws Exception {
        File folder = new File("/Users/liuyao/Documents/jp/video/aa/2");
        String suffix = ".mp4";

        if (!folder.isDirectory()) {
            System.out.println("不是目录");
            return;
        }
        TreeSet<String> set = new TreeSet<String>() {
            @Override
            public Comparator<String> comparator() {
                return (s1, s2) -> {
                    for (int i = 0; i < Math.min(s1.length(), s2.length()) ; i++)
                        if (s1.charAt(i) != s2.charAt(i)) return s1.charAt(i) - s2.charAt(i);
                    return s1.length() - s2.length();
                };
            }
        };
        for (File f : folder.listFiles()) {
            String fullPath = f.getAbsolutePath();
            if (!fullPath.endsWith(suffix)) continue;
            set.add(fullPath);
        }
        if (0 == set.size()) {
            System.out.println("没有可合并文件");
            return;
        }
        String outPath = "";
        String[] command = new String[6 + set.size() *2];
        StringBuilder fc = new StringBuilder();
        int i = 0, j = 0;
        command[i++] = "ffmpeg";
        for (String s : set) {
            command[i++] = "-i";
            command[i++] = outPath = s;
            fc.append("[").append(j++).append(":v]");
        }
        fc.append("concat=n=").append(set.size()).append(":v=1:a=0[outv]");
        command[i++] = "-filter_complex";
        command[i++] = fc.toString();
        command[i++] = "-map";
        command[i++] = "[outv]";
        command[i++] = outPath.substring(0, outPath.length() - suffix.length()) + "-merge" + suffix;
        boolean merge = Util.run(command);
        System.out.format(merge ? "合并成功: %s" : "合并失败: %s", outPath);
    }

}
