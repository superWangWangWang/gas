package com.jt.gas.entity;

/**
 * 数据库的监控数据表实体类
 */
public class Gas {
   //id       int(10)
   private Integer id;
    //name     varchar(20)   名称
    private String name;
    //time     varchar(20)   时间
    private String time;
    //yqls     varchar(20)   烟气流速
    private String yqls;
    //yqwd     varchar(20)   烟气温度
    private String yqwd;
    //yqyl     varchar(20)   烟气压力
    private String yqyl;
    //yqsd     varchar(20)   烟气湿度
    private String yqsd;
    //jw       varchar(20)   甲烷（干）
    private String jw;
    //fjwztg   varchar(20)   非甲烷总烃（干）
    private String fjwztg;
    //zt       varchar(20)   总烃
    private String zt;
    //fjwztp   varchar(20)   非甲烷总烃（排放标准）
    private String fjwztp;
    //ben      varchar(20)   苯（干）
    private String ben;
    //jb       varchar(20)   甲苯（干）
    private String jb;
    //ejb      varchar(20)   二甲苯（干）
    private String ejb;
    //jejb     varchar(20)   间二甲苯（干）
    private String jejb;
    //lejb     varchar(20)   邻二甲苯（干）
    private String lejb;
    //o2       varchar(20)   O2（干）
    private String o2;
    //btgyqll  varchar(20)   标态干烟气流量
    private String btgyqll;
    //ydjmj    varchar(20)   烟道截面积
    private String ydjmj;

    @Override
    public String toString() {
        return "Gas{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", yqls='" + yqls + '\'' +
                ", yqwd='" + yqwd + '\'' +
                ", yqyl='" + yqyl + '\'' +
                ", yqsd='" + yqsd + '\'' +
                ", jw='" + jw + '\'' +
                ", fjwztg='" + fjwztg + '\'' +
                ", zt='" + zt + '\'' +
                ", fjwztp='" + fjwztp + '\'' +
                ", ben='" + ben + '\'' +
                ", jb='" + jb + '\'' +
                ", ejb='" + ejb + '\'' +
                ", jejb='" + jejb + '\'' +
                ", lejb='" + lejb + '\'' +
                ", o2='" + o2 + '\'' +
                ", btgyqll='" + btgyqll + '\'' +
                ", ydjmj='" + ydjmj + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYqls() {
        return yqls;
    }

    public void setYqls(String yqls) {
        this.yqls = yqls;
    }

    public String getYqwd() {
        return yqwd;
    }

    public void setYqwd(String yqwd) {
        this.yqwd = yqwd;
    }

    public String getYqyl() {
        return yqyl;
    }

    public void setYqyl(String yqyl) {
        this.yqyl = yqyl;
    }

    public String getYqsd() {
        return yqsd;
    }

    public void setYqsd(String yqsd) {
        this.yqsd = yqsd;
    }

    public String getJw() {
        return jw;
    }

    public void setJw(String jw) {
        this.jw = jw;
    }

    public String getFjwztg() {
        return fjwztg;
    }

    public void setFjwztg(String fjwztg) {
        this.fjwztg = fjwztg;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getFjwztp() {
        return fjwztp;
    }

    public void setFjwztp(String fjwztp) {
        this.fjwztp = fjwztp;
    }

    public String getBen() {
        return ben;
    }

    public void setBen(String ben) {
        this.ben = ben;
    }

    public String getJb() {
        return jb;
    }

    public void setJb(String jb) {
        this.jb = jb;
    }

    public String getEjb() {
        return ejb;
    }

    public void setEjb(String ejb) {
        this.ejb = ejb;
    }

    public String getJejb() {
        return jejb;
    }

    public void setJejb(String jejb) {
        this.jejb = jejb;
    }

    public String getLejb() {
        return lejb;
    }

    public void setLejb(String lejb) {
        this.lejb = lejb;
    }

    public String getO2() {
        return o2;
    }

    public void setO2(String o2) {
        this.o2 = o2;
    }

    public String getBtgyqll() {
        return btgyqll;
    }

    public void setBtgyqll(String btgyqll) {
        this.btgyqll = btgyqll;
    }

    public String getYdjmj() {
        return ydjmj;
    }

    public void setYdjmj(String ydjmj) {
        this.ydjmj = ydjmj;
    }
}
