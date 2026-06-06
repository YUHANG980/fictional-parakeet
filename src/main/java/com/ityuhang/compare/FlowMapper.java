package com.ityuhang.compare;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

// 1. 注意看最后两个泛型，FlowBean 换到了前面作为 Key，Text 换到了后面作为 Value
public class FlowMapper extends Mapper<LongWritable, Text, FlowBean, Text> {

    // 2. 这里的变量也要跟着换位封装
    private FlowBean outK = new FlowBean();
    private Text outV = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // 1 获取一行数据,转成字符串
        String line = value.toString();

        // 2 切割数据
        String[] split = line.split("\t");

        // 3 抓取我们需要的数据:手机号,上行流量,下行流量
        String phone = split[1];
        String up = split[split.length - 3];
        String down = split[split.length - 2];

        // 4 封装 outK (FlowBean) 和 outV (Text)
        outK.setUpFlow(Long.parseLong(up));
        outK.setDownFlow(Long.parseLong(down));
        outK.setSumFlow(); // 流量对象作为 Key

        outV.set(phone);   // 手机号作为 Value

        // 5 写出 (Key 是 FlowBean, Value 是 Text)
        context.write(outK, outV);
    }
}