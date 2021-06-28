package com.example.user.echarts;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 使用Http调用
 */
public class TestHttp {

    private static String relativePath = System.getenv("SP_HOME") + File.separatorChar + "echartsPng";

    public static void main(String[] args) {
        try {
            Process process = PhantomjsEchartsStart.start();
//        //创建文件夹
//        String folderPath = relativePath + File.separatorChar + UUID.randomUUID();
            File file = new File(relativePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            // 变量
//        String title = "水果";
            List<String> categories = new ArrayList<String>(Arrays.asList("苹果", "香蕉", "梨子"));
            List<Long> values = new ArrayList<>(Arrays.asList(3L, 2L, 1L));
            String barUrl = null;
            barUrl = EchartsGenImageUtils.barImage(categories, values, relativePath, "380", "450");
            System.out.println("生成路径=============" + barUrl);
//        PhantomjsEchartsStart.closeProcess(process);

        } catch (Exception e) {
//            e.printStackTrace();
        }

    }


}
