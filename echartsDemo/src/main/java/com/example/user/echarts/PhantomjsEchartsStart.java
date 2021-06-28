package com.example.user.echarts;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <p>
 * phantomjs, echarts-convert.js 启动
 * </p>
 *
 * @author wangns
 * @since2021年4月20日10:57:35
 */
@Slf4j
public class PhantomjsEchartsStart {

    private static final String path = PhantomjsEchartsStart.class.getClassLoader().getResource("").getPath().substring(1);

    public static Process start() {
        Process process = null;
        try {
            if (isPortUsing("127.0.0.1", 6667)) {
                log.info("端口为6667进程已存在...");
                return process;
            }
            // 未配置环境变量情况下, 启动phantomjs.exe
            if (StringUtils.isNotEmpty(System.getProperty("os.name")) && System.getProperty("os.name").contains("Windows")) {
                process = Runtime.getRuntime().exec(path + "phantomjs-2.1.1-windows" + File.separator + "bin" + File.separator + "phantomjs "
                        + path + "echartsconvert" + File.separator + "echarts-convert.js -s -p 6667");
            } else {
                Runtime.getRuntime().exec("chmod 777 " + path + "phantomjs-2.1.1-linux-x86_64" + File.separator + "bin" + File.separator + "phantomjs ");
                Runtime.getRuntime().exec("chmod 777 " + path + "echartsconvert" + File.separator + "echarts-convert.js");
                process = Runtime.getRuntime().exec(path + "phantomjs-2.1.1-linux-x86_64" + File.separator + "bin" + File.separator + "phantomjs "
                        + path + "echartsconvert" + File.separator + "echarts-convert.js -s -p 6667");
            }
            log.info("phantomjs, echartsconvert启动成功...");
        } catch (Exception e) {
            log.error("phantomjs, echartsconvert启动失败...");
            e.printStackTrace();
        }
        return process;
    }

    /**
     * 结束进程
     *
     * @param process
     */
    public static void closeProcess(Process process) {
        if (process == null) {
            log.info("进程不存在...");
            return;
        }
        process.destroy();
        log.info("phantomjs, echartsconvert关闭...");
    }

    /***
     * 测试主机Host的port端口是否被使用
     * @param host
     * @param port
     * @throws UnknownHostException
     */
    public static boolean isPortUsing(String host,int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress Address = InetAddress.getByName(host);
        try {
            Socket socket = new Socket(Address,port);  //建立一个Socket连接
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }


}
