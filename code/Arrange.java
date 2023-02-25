import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class Arrange {
    public static void main(String[] args) throws Exception {
        String path = "/Users/liuyao/Desktop/PROJECT/good_things/video/";
        final JSONObject jsonObject = new JSONObject();
        AtomicReference<TreeMap> obj = new AtomicReference<>();
        readTextFile(path + "japan.txt", line -> {
            if (line.trim().isEmpty()) return;
            if (line.startsWith("--")) {
                obj.set(new TreeMap());
                jsonObject.put(line.substring(2), obj.get());
                return;
            }
            line += " ";
            int i = line.indexOf(" ");
            String hao = line.substring(0, i).toUpperCase();
            String desc = line.substring(i + 1).trim();
            if (desc.endsWith(".mp4")) desc = desc.substring(0, desc.length()-4);

            obj.get().put(hao, desc);
        });
        FileOutputStream fos = new FileOutputStream(path + "japan.json");
        fos.write(jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8));
        fos.flush();
    }

    public static void readTextFile(String path, Consumer<String> callback) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            callback.accept(line);
        }
    }
}
