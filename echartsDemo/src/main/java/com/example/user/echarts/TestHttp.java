package com.example.user.echarts;

import com.alibaba.fastjson.JSON;
import freemarker.template.TemplateException;
import org.apache.http.client.ClientProtocolException;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * 使用Http调用
 *
 */
public class TestHttp {

//    private static final String path = FreemarkerUtil.class.getClassLoader().getResource("/templates").getPath();

    public static void main(String[] args) throws ClientProtocolException, IOException, TemplateException {

        // 未配置环境变量情况下, 启动phantomjs.exe
        Runtime run = Runtime.getRuntime();
        Process process = run.exec("E:\\demo\\echartsDemo\\echartsDemo\\src\\main\\resources\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs " +
                "E:\\demo\\echartsDemo\\echartsDemo\\src\\main\\resources\\echartsconvert\\echarts-convert.js -s -p 6666");

        // 变量
        String title = "水果";
        String[] categories = new String[] { "苹果", "香蕉", "西瓜" };
        int[] values = new int[] { 3, 2, 1 };

        // 模板参数
        HashMap<String, Object> datas = new HashMap<>();
        datas.put("categories", JSON.toJSONString(categories));
        datas.put("values", JSON.toJSONString(values));
        datas.put("title", title);

        // 生成option字符串
        String option = FreemarkerUtil.generateString("option.ftl", "", datas);

        // (调用phantomjs服务生成echarts图片)根据option参数
        String base64 = EchartsUtil.generateEchartsBase64(option);
        System.out.println("BASE64:" + base64);
        generateImage(base64, "F:/echartsPng/test.png");

        process.destroy();

    }

    public static void generateImage(String base64, String path) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        try (OutputStream out = new FileOutputStream(path)){
            // 解密
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            out.write(b);
            out.flush();
        }
    }

    // 启动命令
    // phantomjs  echarts-convert.js -s -p 6666
    // C:\Users\Administrator\Desktop\phantomjs-2.1.1-windows\bin>phantomjs C:\Users\Administrator\Desktop\echartsconvert\echarts-convert.js -s -p 6666
    // https://www.jianshu.com/p/dfc28fd7d786


}