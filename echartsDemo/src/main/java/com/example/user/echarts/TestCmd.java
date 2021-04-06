package com.example.user.echarts;

import com.alibaba.fastjson.JSON;
import freemarker.template.TemplateException;
import org.apache.http.client.ClientProtocolException;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

/**
 * 使用cmd命令调用
 */
public class TestCmd {

    public static void main(String[] args) throws IOException, TemplateException, InterruptedException {
        // 变量
        String title = "水果";
        String[] categories = new String[]{"苹果", "香蕉", "西瓜"};
        int[] values = new int[]{3, 2, 1};

        // 模板参数
        HashMap<String, Object> datas = new HashMap<>();
        datas.put("categories", JSON.toJSONString(categories));
        datas.put("values", JSON.toJSONString(values));
        datas.put("title", title);

        // 生成option字符串
        String option = FreemarkerUtil.generateString("option.ftl", "", datas);
        StringBuilder buf = new StringBuilder();
        for (String str : option.split("\n"))
        {
            buf.append(str.trim());
        }

        File phantomJs = new File("E:\\demo\\echartsDemo\\echartsDemo\\src\\main\\resources\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs " );
        File convertJs = new File("E:\\demo\\echartsDemo\\echartsDemo\\src\\main\\resources\\echartsconvert\\echarts-convert.js ");

        String path = "F:/echartsPng/test.png";
        File outfile = new File(path); // 文件路径（路径+文件名）
        if (!outfile.exists()) {
            File dir = new File(outfile.getParent());
            dir.mkdirs();
            outfile.createNewFile();
        }

        String cmd = "phantomJs " + phantomJs  + " convertJs " + convertJs  + " -infile " + buf + " -outfile " + path;//生成命令行
        Process process = Runtime.getRuntime().exec(cmd);
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        input.close();
        process.destroy();
    }

}
