package com.example.user.WeiXinSignIn;

/**
 * 描述:<p>用户打卡信息</p>
 *
 * @author WangNS
 * @date 2022年6月16日14:13:13
 */
public class UserSiginInformation {

    /**
     * 用户id
     */
    private String userid;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 打卡规则名称
     */
    private String groupname;

    /**
     * 打卡类型。字符串，目前有：上班打卡，下班打卡，外出打卡
     */
    private String checkin_type;

    /**
     * 异常类型，字符串，包括：时间异常，地点异常，未打卡，wifi异常，非常用设备。如果有多个异常，以分号间隔
     */
    private String exception_type;

    /**
     * 打卡时间。Unix时间戳
     */
    private String checkin_time;

    /**
     * 打卡地点title
     */
    private String location_title;

    /**
     * 打卡地点详情
     */
    private String location_detail;

    /**
     * 打卡wifi名称
     */
    private String wifiname;

    /**
     * 打卡备注
     */
    private String notes;

    /**
     * 打卡的MAC地址/bssid
     */
    private String wifimac;

    /**
     * 打卡的附件media_id，可使用media/get获取附件
     */
    private String mediaids;

    /**
     * 位置打卡地点纬度，是实际纬度的1000000倍，与腾讯地图一致采用GCJ-02坐标系统标准
     */
    private String lat;

    /**
     * 位置打卡地点经度，是实际经度的1000000倍，与腾讯地图一致采用GCJ-02坐标系统标准
     */
    private String lng;

    /**
     * 打卡设备id
     */
    private String deviceid;

    /**
     * 标准打卡时间，指此次打卡时间对应的标准上班时间或标准下班时间
     */
    private String sch_checkin_time;

    /**
     * 规则id，表示打卡记录所属规则的id
     */
    private String groupid;

    /**
     * 班次id，表示打卡记录所属规则中，所属班次的id
     */
    private String schedule_id;


    /**
     * 时段id，表示打卡记录所属规则中，某一班次中的某一时段的id，如上下班时间为9:00-12:00、13:00-18:00的班次中，9:00-12:00为其中一组时段
     */
    private String timeline_id;

    public UserSiginInformation() {

    }

    public UserSiginInformation(String userid, String username, String groupname, String checkin_type, String exception_type, String checkin_time,
                                String location_title, String location_detail, String wifiname, String notes, String wifimac, String mediaids, String lat,
                                String lng, String deviceid, String sch_checkin_time, String groupid, String schedule_id, String timeline_id) {
        this.userid = userid;
        this.username = username;
        this.groupname = groupname;
        this.checkin_type = checkin_type;
        this.exception_type = exception_type;
        this.checkin_time = checkin_time;
        this.location_title = location_title;
        this.location_detail = location_detail;
        this.wifiname = wifiname;
        this.notes = notes;
        this.wifimac = wifimac;
        this.mediaids = mediaids;
        this.lat = lat;
        this.lng = lng;
        this.deviceid = deviceid;
        this.sch_checkin_time = sch_checkin_time;
        this.groupid = groupid;
        this.schedule_id = schedule_id;
        this.timeline_id = timeline_id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getCheckin_type() {
        return checkin_type;
    }

    public void setCheckin_type(String checkin_type) {
        this.checkin_type = checkin_type;
    }

    public String getException_type() {
        return exception_type;
    }

    public void setException_type(String exception_type) {
        this.exception_type = exception_type;
    }

    public String getCheckin_time() {
        return checkin_time;
    }

    public void setCheckin_time(String checkin_time) {
        this.checkin_time = checkin_time;
    }

    public String getLocation_title() {
        return location_title;
    }

    public void setLocation_title(String location_title) {
        this.location_title = location_title;
    }

    public String getLocation_detail() {
        return location_detail;
    }

    public void setLocation_detail(String location_detail) {
        this.location_detail = location_detail;
    }

    public String getWifiname() {
        return wifiname;
    }

    public void setWifiname(String wifiname) {
        this.wifiname = wifiname;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWifimac() {
        return wifimac;
    }

    public void setWifimac(String wifimac) {
        this.wifimac = wifimac;
    }

    public String getMediaids() {
        return mediaids;
    }

    public void setMediaids(String mediaids) {
        this.mediaids = mediaids;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getSch_checkin_time() {
        return sch_checkin_time;
    }

    public void setSch_checkin_time(String sch_checkin_time) {
        this.sch_checkin_time = sch_checkin_time;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(String schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getTimeline_id() {
        return timeline_id;
    }

    public void setTimeline_id(String timeline_id) {
        this.timeline_id = timeline_id;
    }

    @Override
    public String toString() {
        return "UserSiginInformation{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", groupname='" + groupname + '\'' +
                ", checkin_type='" + checkin_type + '\'' +
                ", exception_type='" + exception_type + '\'' +
                ", checkin_time='" + checkin_time + '\'' +
                ", location_title='" + location_title + '\'' +
                ", location_detail='" + location_detail + '\'' +
                ", wifiname='" + wifiname + '\'' +
                ", notes='" + notes + '\'' +
                ", wifimac='" + wifimac + '\'' +
                ", mediaids='" + mediaids + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", deviceid='" + deviceid + '\'' +
                ", sch_checkin_time='" + sch_checkin_time + '\'' +
                ", groupid='" + groupid + '\'' +
                ", schedule_id='" + schedule_id + '\'' +
                ", timeline_id='" + timeline_id + '\'' +
                '}';
    }

}
