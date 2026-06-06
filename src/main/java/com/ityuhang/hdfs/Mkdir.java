package com.ityuhang.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * projectName: HdfsClientDemo
 *
 * @author: yuhang
 * description:
 */
public class Mkdir {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        FileSystem fs = null;
        try {
            // 1. 这里的端口号请填你刚才 cat 出来的那个
            // 2. 记得赋值给 fs
            // 3. 加上第三个参数 "root" (或你的 Hadoop 用户名)
            fs = FileSystem.get(new URI("hdfs://hadoop100:8020"), conf, "root");

            fs.mkdirs(new Path("/hdfs"));
            System.out.println("目录创建成功！");

        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 别忘了关流
            if (fs != null) {
                try { fs.close(); } catch (IOException e) { e.printStackTrace(); }
            }
        }
    }
}
