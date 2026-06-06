package com.ityuhang.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DownloadTest {
    public static void main(String[] args) {
        // 1. HDFS 上的源文件路径
        Path srcPath = new Path("/word.txt");
        
        // 2. Windows 本地的目标路径（确保 D:/hdfs 这个目录在电脑上已经存在）
        Path outPath = new Path("D:/hdfs/word.txt");

        Configuration conf = new Configuration();
        FileSystem fs = null;
        
        try {
            // 3. 获取连接，加上 "root" 防止权限报错，确认 IP 和端口是否为 8020
            fs = FileSystem.get(new URI("hdfs://hadoop100:8020"), conf, "root");


            fs.copyToLocalFile(false, srcPath, outPath, true);

            System.out.println("文件下载成功！请检查 D:/hdfs 目录。");

        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭资源
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}