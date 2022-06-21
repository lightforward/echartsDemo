package com.example.user.WeiXinSignIn;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:<p>获取企业微信打卡记录, 获取access_token</p>
 *
 * @author WangNS
 * @date 2022年6月15日17:52:33
 */
public class WeiXinSignInRecordUtil {

    public static Logger logger = LoggerFactory.getLogger(WeiXinSignInRecordUtil.class);

//    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 企业ID
     */
    public static final String CorpId = "wwd76b277305cc022b";
//    public static String CorpId = PropertyHolder.getProperty("CorpId");

    /**
     * 通讯录同步Secret
     */
    public static final String CorpSecret = "Y4qtdS5E2qta59oUHZFzSevvlRk1akVrk_o1R9wjujE";

    /**
     * 打卡应用的secret
     */
    public static final String SigninSecret = "zwzlTrcay_GSNtD42SP6lXTJkUDKEdAYXNBrQ1oEMDI";

    /**
     * 获取token的url
     */
    public static final String GetToken = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    /**
     * 取打卡数据url
     */
    public static final String GetCheckinData = "https://qyapi.weixin.qq.com/cgi-bin/checkin/getcheckindata";

    /**
     * 获取所有人员打卡信息url
     */
    public static final String GetUserlist = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist";

    /**
     * 获取userid  url
     */
    public static final String GetUserId = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserid";

    /**
     * 获取月打卡信息
     */
    public static final String MonthSigInRecord = "https://qyapi.weixin.qq.com/cgi-bin/checkin/getcheckin_monthdata";

    /**
     * 企业微信打卡每次最多取100条记录
     */
    public static final Integer MAX_NUM = 100;

    public static void main(String[] args) {
        getUserIdByMobile("15198025084");

        Map<String, String> timeMap = new HashMap<>();
        timeMap.put("ksTime", "1654876800");
        timeMap.put("jsTime", "1654963199");
        JSONObject json = getMonthSigInRecord("ZhangYinXia", timeMap);
        JSONObject jsonObject = json.getJSONArray("datas").getJSONObject(0).getJSONObject("summary_info");
        // 应打卡天数
        int work_days = jsonObject.getInteger("work_days");
        // 实际打卡天数
        int regular_days = jsonObject.getInteger("regular_days");
        // 异常天数
        int except_days = jsonObject.getInteger("except_days");


        System.out.println("应打卡天数:" + work_days + " 实际打卡天数:" + regular_days + " 异常天数:" + except_days);

    }

    /**
     * 根据用户电话号码, 或获取企业微信id
     *
     * @param mobile 用户绑定企业微信电话号码
     * @return userid 用户企业微信id
     */
    public static String getUserIdByMobile(String mobile) {
        try {
            String rq = GetToken + "?Corpid=" + CorpId + "&Corpsecret=" + CorpSecret;
            JSONObject gettokenRs = JSONObject.parseObject(doGet(rq));
            String access_token = gettokenRs.getString("access_token");

            JSONObject body = new JSONObject();
            body.put("mobile", mobile);

            JSONObject checkobj = JSONObject.parseObject(doPost(GetUserId + "?access_token=" + access_token, body));
            String userid = checkobj.getString("userid");
            logger.info("企业微信根据用户电话获取userid = " + userid);
            return userid;
        } catch (Exception e) {
            logger.error("企业微信根据用户电话获取userid失败...........");
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getMonthSigInRecord(String userid, Map<String, String> timeMap) {

        List<String> userIdList = new ArrayList<>();
        userIdList.add(userid);

        JSONObject body = new JSONObject();
        body.put("starttime", timeMap.get("ksTime"));
        body.put("endtime", timeMap.get("jsTime"));
        body.put("useridlist", userIdList);

        String rqSignIn = GetToken + "?Corpid=" + CorpId + "&Corpsecret=" + SigninSecret;
        JSONObject getTokenSignin = JSONObject.parseObject(doGet(rqSignIn));
        String access_token_signin = getTokenSignin.getString("access_token");

        JSONObject checkobj = JSONObject.parseObject(doPost(MonthSigInRecord + "?access_token=" + access_token_signin, body));

        return checkobj;

    }


    /**
     * get请求
     *
     * @param getUrl
     * @return
     */
    public static String doGet(String getUrl) {
        try {
            URL url = new URL(getUrl);
            //设置连接方式
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //设置主机连接时间超时时间3000毫秒
            conn.setConnectTimeout(3000);
            //设置读取远程返回数据的时间3000毫秒
            conn.setReadTimeout(3000);
            //发送请求
            conn.connect();
            //获取输入流
            InputStream is = conn.getInputStream();
            //封装输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            //接收读取数据
            StringBuffer sb = new StringBuffer();

            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            if (null != br) {
                br.close();
            }
            if (null != is) {
                is.close();
            }
            //关闭连接
            conn.disconnect();
            return sb.toString();
        } catch (Exception e) {
            e.getMessage();
        }
        return "";
    }

    /**
     * 原生http请求
     *
     * @param sendUrl 请求的Url
     * @param body    传入的参数
     * @return
     */
    public static String doPost(String sendUrl, JSONObject body) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = null;
        try {
            URL url = new URL(sendUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //发送POST请求必须设置为true
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //设置连接超时时间和读取超时时间
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //获取输出流
            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(body.toJSONString());
            out.flush();
            out.close();
            //取得输入流，并使用Reader读取
            if (200 == conn.getResponseCode()) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                    System.out.println(line);
                }
            } else {
                System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return result.toString();
    }

    /**
     * 将map转String
     *
     * @param map
     * @return
     */
    private static String mapToString(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (String i : map.keySet()) {
            index++;
            if (map.size() == index) {
                sb.append(i + "=" + map.get(i));
            } else {
                sb.append(i + "=" + map.get(i) + "&");
            }
        }
        return sb.toString();
    }


}