package com.ityuhang.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UploadFile { // 类名可以改得更有意义一点
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        FileSystem fs = null;
        try {
            // 1. 连接 HDFS (确保 hadoop100 和 8020 端口与你的集群一致)
            fs = FileSystem.get(new URI("hdfs://hadoop100:8020"), conf, "root");

            // 2. 定义路径
            // 这里的第一个参数是 Windows 本地文件的路径
            Path srcPath = new Path("D:/word.txt");
            // 第二个参数是 HDFS 的目标路径
            Path dstPath = new Path("/word.txt");

            // 3. 执行上传操作
            // 参数含义：(是否删除源文件, 是否覆盖目标文件, 源路径, 目标路径)
            fs.copyFromLocalFile(false, true, srcPath, dstPath);
            
            System.out.println("文件上传成功！");

        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try { fs.close(); } catch (IOException e) { e.printStackTrace(); }
            }
        }
    }
}