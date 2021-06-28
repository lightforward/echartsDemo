package com.example.user.echarts;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * echarts生成图片
 * </p>
 *
 * @author wangns
 * @since 2021年4月13日10:47:15
 */
public class EchartsGenImageUtils {

    private static final String optionFtlPath = EchartsGenImageUtils.class.getClassLoader().getResource("").getPath().substring(1);

    /**
     * 生成饼图
     *
     * @param list   数据
     * @param path   生成图片路径
     * @param width  宽
     * @param heigth 高
     * @return 图片路径
     * @throws Exception
     *
     */
    public static String pieImage(List<HashMap<String, Object>> list, String path, String width, String heigth) throws Exception {

        HashMap<String, Object> datas = new HashMap<>();
        datas.put("title", "扇形图");
        datas.put("data", JSON.toJSONString(list).replaceAll("%", " "));

        // 生成option字符串
        String option = FreemarkerUtil.generateString("pieOption.ftl", optionFtlPath + "echartsFtl", datas);

        // (调用phantomjs服务生成echarts图片)根据option参数
        String base64 = EchartsUtil.generateEchartsBase64(option, width, heigth);
        String perImageurl = path + File.separatorChar + UUID.randomUUID() + "pie.png";
        EchartsUtil.generateImage(base64, perImageurl);

        return perImageurl;
    }

    /**
     * 生成柱状图
     *
     * @param categories 数据
     * @param values     数据
     * @param path       生成图片路径
     * @param width      宽
     * @param heigth     高
     * @throws Exception
     * @return图片路径
     *
     */
    public static String barImage(List<String> categories, List<Long> values, String path, String width, String heigth) throws Exception {

        HashMap<String, Object> datas = new HashMap<>();
        datas.put("title", "柱状图");
        datas.put("categories", JSON.toJSONString(categories).replaceAll("%", " "));
        datas.put("values", JSON.toJSONString(values));

        // 生成option字符串
        String option = FreemarkerUtil.generateString("barOption.ftl", optionFtlPath + "echartsFtl", datas);

        // (调用phantomjs服务生成echarts图片)根据option参数
        String base64 = EchartsUtil.generateEchartsBase64(option, width, heigth);
        String barImageurl = path + File.separatorChar + UUID.randomUUID() + "bar.png";
        EchartsUtil.generateImage(base64, barImageurl);

        return barImageurl;
    }

    /**
     * Object数组转换为指定类型数组
     *
     * @param targetType
     * @param arrayObjects
     * @param <T>
     * @return
     */
    public static <T> T[] convertArray(Class<T> targetType, Object[] arrayObjects) {
        if (targetType == null) {
            return (T[]) arrayObjects;
        }
        if (arrayObjects == null) {
            return null;
        }
        T[] targetArray = (T[]) Array.newInstance(targetType, arrayObjects.length);
        try {
            System.arraycopy(arrayObjects, 0, targetArray, 0, arrayObjects.length);
        } catch (ArrayStoreException e) {
            e.printStackTrace();
        }
        return targetArray;
    }

}
