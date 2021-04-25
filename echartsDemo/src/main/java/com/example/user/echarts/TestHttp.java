package com.example.user.echarts;

import com.alibaba.fastjson.JSON;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String[] categories = new String[] { "苹果", "香蕉", "~!#$^&*()_+/|{} [] %"};
        int[] values = new int[] { 3, 2, 1 };

        // 模板参数
        HashMap<String, Object> datas = new HashMap<>();
        datas.put("categories", JSON.toJSONString(categories).replace("%", " "));
        datas.put("values", JSON.toJSONString(values));
        datas.put("title", title);

        // 生成option字符串
        String option = FreemarkerUtil.generateString("option.ftl", "", datas);

        // (调用phantomjs服务生成echarts图片)根据option参数
        String base64 = EchartsUtil.generateEchartsBase64(option, "800", "800");
        System.out.println("BASE64:" + base64);
        generateImage(base64, "F:/echartsPng/TestHttp.png");

        process.destroy();

    }

    public static String escapeQueryChars(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        //查询字符串一般不会太长，挨个遍历也花费不了多少时间
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // These characters are part of the query syntax and must be escaped
            if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')'
                    || c == ':' || c == '^'	|| c == '[' || c == ']' || c == '\"'
                    || c == '{' || c == '}' || c == '~' || c == '*' || c == '?'
                    || c == '|' || c == '&' || c == ';' || c == '/' || c == '.'
                    || c == '$' || Character.isWhitespace(c)) {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
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
