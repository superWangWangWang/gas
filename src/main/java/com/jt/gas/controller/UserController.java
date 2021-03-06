package com.jt.gas.controller;

import com.github.pagehelper.PageHelper;
import com.jt.gas.entity.*;
import com.jt.gas.service.impl.UserServiceImpl;
import com.jt.gas.utils.EmailUtils;
import com.jt.gas.vo.LayuiVo;
import com.jt.gas.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/index");
        //获取最新的一行数据
//        PageHelper.startPage(0,1);
//        List<Gas> dataList = userServiceImpl.getData();
//        Gas data = dataList.get(0);
        //最新的数据
        PageHelper.startPage(0,5);
        List<Gas> dataList = userServiceImpl.getLastestData();
        System.out.println(dataList);
//        LocalDateTime dateTime = LocalDateTime.parse(data.getTime(), );
//        Long time = dateTime.toEpochSecond(ZoneOffset.of("+8"));//实时表中的最新一条的时间戳--秒

//        LocalDateTime temTime = LocalDateTime.parse(data.getTime(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//        String time = temTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println("==============="+time);
//        data.setTime(time);
//        System.out.println(data);
        modelAndView.addObject("dataList",dataList);

        //获取正常范围
        List<GasRange> rangeList = userServiceImpl.getGasRange();
        modelAndView.addObject("rangeList",rangeList);

        return modelAndView;
    }

    /**
     * 跳转到用户详情页面
     * @return
     */
    @RequestMapping("history")
    public ModelAndView history(String date){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/history");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(date);
        if (date == "" || date == null){

            date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println(date);
            modelAndView.addObject("date",date);

        }else {
            modelAndView.addObject("date",date);

        }
        date = date.replaceAll("-","");
        //date = "20201120";


        List<GasHistory> phHistory = userServiceImpl.getHistoryAvg("ph", date);
        modelAndView.addObject("phHistory",phHistory);

        List<GasHistory> ssHistory = userServiceImpl.getHistoryAvg("ss", date);
        modelAndView.addObject("ssHistory",ssHistory);

        List<GasHistory> codHistory = userServiceImpl.getHistoryAvg("cod", date);
        modelAndView.addObject("codHistory",codHistory);

        List<GasHistory> nhHistory = userServiceImpl.getHistoryAvg("nh", date);
        modelAndView.addObject("nhHistory",nhHistory);

        List<GasHistory> cuHistory = userServiceImpl.getHistoryAvg("cu", date);
        modelAndView.addObject("cuHistory",cuHistory);

        List<GasHistory> niHistory = userServiceImpl.getHistoryAvg("ni", date);
        modelAndView.addObject("niHistory",niHistory);

        List<GasHistory> frHistory = userServiceImpl.getHistoryAvg("fr", date);
        modelAndView.addObject("frHistory",frHistory);

        return modelAndView;
    }
    /**
     * 获取最新数据的接口
     * @return
     */
    @RequestMapping("getLatestData")
    @ResponseBody
    public ResultVo getLatestData(){
        ResultVo resultVo = new ResultVo();

        PageHelper.startPage(0,5);
        List<Gas> dataList = userServiceImpl.getLastestData();
        List<GasRange> gasRange = userServiceImpl.getGasRange();
        //Gas data = dataList.get(0);
        dataList.forEach(l->{
            LocalDateTime temTime = LocalDateTime.parse(l.getTime(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String time = temTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.println("==============="+time);
            l.setTime(time);
            for (GasRange g : gasRange){
                System.out.println(l);
                System.out.println(g);

                switch (g.getName()){
                    case "yqls":
                        if (g.getMin() <= Double.parseDouble(l.getYqls()) && Double.parseDouble(l.getYqls()) <= g.getMax()){
                            l.setYqls("<span style='color: green'>"+ l.getYqls() +"</span>");
                        }else {
                            l.setYqls("<span style='color: red'>"+ l.getYqls() +"</span>");
                        }
                        break;
                    case "yqwd":
                        if (g.getMin() <= Double.parseDouble(l.getYqwd()) && Double.parseDouble(l.getYqwd()) <= g.getMax()){
                            l.setYqwd("<span style='color: green'>"+ l.getYqwd() +"</span>");
                        }else {
                            l.setYqwd("<span style='color: red'>"+ l.getYqwd() +"</span>");
                        }
                        break;
                    case "yqyl":
                        if (g.getMin() <= Double.parseDouble(l.getYqyl()) && Double.parseDouble(l.getYqyl()) <= g.getMax()){
                            l.setYqyl("<span style='color: green'>"+ l.getYqyl() +"</span>");
                        }else {
                            l.setYqyl("<span style='color: red'>"+ l.getYqyl() +"</span>");
                        }
                        break;
                    case "yqsd":
                        if (g.getMin() <= Double.parseDouble(l.getYqsd()) && Double.parseDouble(l.getYqsd()) <= g.getMax()){
                            l.setYqsd("<span style='color: green'>"+ l.getYqsd() +"</span>");
                        }else {
                            l.setYqsd("<span style='color: red'>"+ l.getYqsd() +"</span>");
                        }
                        break;
                    case "jw":
                        if (g.getMin() <= Double.parseDouble(l.getJw()) && Double.parseDouble(l.getJw()) <= g.getMax()){
                            l.setJw("<span style='color: green'>"+ l.getJw() +"</span>");
                        }else {
                            l.setJw("<span style='color: red'>"+ l.getJw() +"</span>");
                        }
                        break;
                    case "fjwztg":
                        if (g.getMin() <= Double.parseDouble(l.getFjwztg()) && Double.parseDouble(l.getFjwztg()) <= g.getMax()){
                            l.setFjwztg("<span style='color: green'>"+ l.getFjwztg() +"</span>");
                        }else {
                            l.setFjwztg("<span style='color: red'>"+ l.getFjwztg() +"</span>");
                        }
                        break;
                    case "zt":
                        if (g.getMin() <= Double.parseDouble(l.getZt()) && Double.parseDouble(l.getZt()) <= g.getMax()){
                            l.setZt("<span style='color: green'>"+ l.getZt() +"</span>");
                        }else {
                            l.setZt("<span style='color: red'>"+ l.getZt() +"</span>");
                        }
                        break;
                    case "fjwztp":
                        if (g.getMin() <= Double.parseDouble(l.getFjwztp()) && Double.parseDouble(l.getFjwztp()) <= g.getMax()){
                            l.setFjwztp("<span style='color: green'>"+ l.getFjwztp() +"</span>");
                        }else {
                            l.setFjwztp("<span style='color: red'>"+ l.getFjwztp() +"</span>");
                        }
                        break;
                    case "ben":
                        if (g.getMin() <= Double.parseDouble(l.getBen()) && Double.parseDouble(l.getBen()) <= g.getMax()){
                            l.setBen("<span style='color: green'>"+ l.getBen() +"</span>");
                        }else {
                            l.setBen("<span style='color: red'>"+ l.getBen() +"</span>");
                        }
                        break;
                    case "jb":
                        if (g.getMin() <= Double.parseDouble(l.getJb()) && Double.parseDouble(l.getJb()) <= g.getMax()){
                            l.setJb("<span style='color: green'>"+ l.getJb() +"</span>");
                        }else {
                            l.setJb("<span style='color: red'>"+ l.getJb() +"</span>");
                        }
                        break;
                    case "ejb":
                        if (g.getMin() <= Double.parseDouble(l.getEjb()) && Double.parseDouble(l.getEjb()) <= g.getMax()){
                            l.setEjb("<span style='color: green'>"+ l.getEjb() +"</span>");
                        }else {
                            l.setEjb("<span style='color: red'>"+ l.getEjb() +"</span>");
                        }
                        break;
                    case "jejb":
                        if (g.getMin() <= Double.parseDouble(l.getJejb()) && Double.parseDouble(l.getJejb()) <= g.getMax()){
                            l.setJejb("<span style='color: green'>"+ l.getJejb() +"</span>");
                        }else {
                            l.setJejb("<span style='color: red'>"+ l.getJejb() +"</span>");
                        }
                        break;
                    case "lejb":
                        if (g.getMin() <= Double.parseDouble(l.getLejb()) && Double.parseDouble(l.getLejb()) <= g.getMax()){
                            l.setLejb("<span style='color: green'>"+ l.getLejb() +"</span>");
                        }else {
                            l.setLejb("<span style='color: red'>"+ l.getLejb() +"</span>");
                        }
                        break;
                    case "o2":
                        if (g.getMin() <= Double.parseDouble(l.getO2()) && Double.parseDouble(l.getO2()) <= g.getMax()){
                            l.setO2("<span style='color: green'>"+ l.getO2() +"</span>");
                        }else {
                            l.setO2("<span style='color: red'>"+ l.getO2() +"</span>");
                        }
                        break;
//                    case "btgyqll":
//                        if (g.getMin() < Double.parseDouble(l.getBtgyqll()) && Double.parseDouble(l.getBtgyqll()) < g.getMax()){
//                            l.setBtgyqll("<span style='color: green'>"+ l.getBtgyqll() +"</span>");
//                        }else {
//                            l.setBtgyqll("<span style='color: red'>"+ l.getBtgyqll() +"</span>");
//                        }
//                        break;
//                    case "ydjmj":
//                        if (g.getMin() < Double.parseDouble(l.getYdjmj()) && Double.parseDouble(l.getYdjmj()) < g.getMax()){
//                            l.setYdjmj("<span style='color: green'>"+ l.getYdjmj() +"</span>");
//                        }else {
//                            l.setYdjmj("<span style='color: red'>"+ l.getYdjmj() +"</span>");
//                        }
//                        break;

                }




            }

        });




        System.out.println(dataList);

        resultVo.setData(dataList);

        return resultVo;
    }

    /**
     * 5秒查一次数据库，发现异常就警报
     * @throws MessagingException
     * @throws ParseException
     */
//    @Scheduled(cron = "*/5 * * * * *")
//    private void timingQuery() throws MessagingException, ParseException {
//        //EmailUtils.sendEmail();
//
//        //查询数据库最新的一条，如果有异常，再查异常表对照
//        PageHelper.startPage(0,1);
//        List<Gas> list = userServiceImpl.getData();
//        Gas data = list.get(0);//最新的一条数据
//        LocalDateTime dateTime1 = LocalDateTime.parse(data.getTime(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//        Long time_last = dateTime1.toEpochSecond(ZoneOffset.of("+8"));//实时表中的最新一条的时间戳--秒
//
//
//        String style =
//                "<style type='text/css'>" +
//                "table {" +
//                "    border:solid 1px #cccccc;" +
//                "}" +
//                "td,th {" +
//                "    padding: 5px;border:solid 1px #cccccc;" +
//                "}" +
//                "</style>";
//
//
//        String str1 =
//                "<table>" +
//                "<thead>" +
//                "<tr>" +
//                    "<th>监控名称</th>" +
//                    "<th>正常范围</th>" +
//                    "<th>实时数据</th>" +
////                    "<th>单位</th>" +
//                "</tr>" +
//                "</thead>" +
//
//                "<tbody>" ;
//
//        String msg = "";
//
//        String str2 =
//                "</tbody>" +
//                "</table>";
//
//        //查询正常范围表
//        List<GasRange> rangeList = userServiceImpl.getRange();
//        //警报表中最新的一条数据
//        PageHelper.startPage(0,1);
//        GasWarning latestWarn = userServiceImpl.getLatestWarn();
//
//        if (latestWarn == null){
//            Boolean err = false;
//            //判断 如果有异常存在
//            for (int i = 0;i<rangeList.size();i++){
//                switch (rangeList.get(i).getName()){
//                    case "ph":
//                        if (!check("ph",Double.parseDouble(data.getPh()),rangeList)){
//                            err = true;
//                        }
//                        break;
//                    case "ss":
//                        if (!check("ss",Double.parseDouble(data.getSs()),rangeList)){
//                            err = true;
//                        }
//                        break;
//                    case "cod":
//                        if (!check("cod",Double.parseDouble(data.getCod()),rangeList)){
//                            err = true;
//                        }
//                        break;
//                    case "nh":
//                        if (!check("nh",Double.parseDouble(data.getNh()),rangeList)){
//                            err = true;
//                        }
//                        break;
//                    case "cu":
//                        if (!check("cu",Double.parseDouble(data.getCu()),rangeList)){
//                            err = true;
//                        }
//                        break;
//                    case "ni":
//                        if (!check("ni",Double.parseDouble(data.getNi()),rangeList)){
//                            err = true;
//                        }
//                        break;
//                }
//            }
//
//            //如果存在异常，那么就警报，插入异常表
//            if (err){
//                //循环范围表，对每一个字段都处理成table，
//                for (int i = 0;i<rangeList.size();i++){
//                    switch (rangeList.get(i).getName()){
//                        case "ph":
//                            msg =  msg + getTrTd(rangeList.get(i).getName(), data.getPh(), rangeList);
//                            break;
//                        case "ss":
//                            msg =  msg + getTrTd(rangeList.get(i).getName(), data.getSs(), rangeList);
//                            break;
//                        case "cod":
//                            msg =  msg + getTrTd(rangeList.get(i).getName(), data.getCod(), rangeList);
//                            break;
//                        case "nh":
//                            msg =  msg + getTrTd(rangeList.get(i).getName(), data.getNh(), rangeList);
//                            break;
//                        case "cu":
//                            msg =  msg + getTrTd(rangeList.get(i).getName(), data.getCu(), rangeList);
//                            break;
//                        case "ni":
//                            msg =  msg + getTrTd(rangeList.get(i).getName(), data.getNi(), rangeList);
//                            break;
//                    }
//                }
//                //插入警报表
//                userServiceImpl.addLastWarn(data);
//            }
//
//        }else {
//            //警报表存在数据
//            LocalDateTime dateTime2 = LocalDateTime.parse(latestWarn.getTime(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//            Long time_old = dateTime2.toEpochSecond(ZoneOffset.of("+8"));//警报表中的最新一条的时间戳--秒
//
//            Boolean err = false;
//            //判断 如果有异常存在
//            for (int i = 0;i<rangeList.size();i++){
//                switch (rangeList.get(i).getName()){
//                    case "ph":
//                        if (!check("ph",Double.parseDouble(data.getPh()),rangeList)){
//                            //数据确实异常了，那么判断时间间隔--5分钟
//                            if(time_last - time_old > 300){
//                                err = true;
//                            }
//                        }
//                        break;
//                    case "ss":
//                        if (!check("ss",Double.parseDouble(data.getSs()),rangeList)){
//                            //数据确实异常了，那么判断时间间隔--5分钟
//                            if(time_last - time_old > 300){
//                                err = true;
//                            }
//                        }
//                        break;
//                    case "cod":
//                        if (!check("cod",Double.parseDouble(data.getCod()),rangeList)){
//                            //数据确实异常了，那么判断时间间隔--2小时
//                            if(time_last - time_old > 7200){
//                                err = true;
//                            }
//                        }
//                        break;
//                    case "nh":
//                        if (!check("nh",Double.parseDouble(data.getNh()),rangeList)){
//                            //数据确实异常了，那么判断时间间隔--2小时
//                            if(time_last - time_old > 7200){
//                                err = true;
//                            }
//                        }
//                        break;
//                    case "cu":
//                        if (!check("cu",Double.parseDouble(data.getCu()),rangeList)){
//                            //数据确实异常了，那么判断时间间隔--2小时
//                            if(time_last - time_old > 7200){
//                                err = true;
//                            }
//                        }
//                        break;
//                    case "ni":
//                        if (!check("ni",Double.parseDouble(data.getNi()),rangeList)){
//                            //数据确实异常了，那么判断时间间隔--2小时
//                            if(time_last - time_old > 7200){
//                                err = true;
//                            }
//                        }
//                        break;
//                }
//            }
//
//            //如果存在异常，那么就警报，插入异常表
//            if (err){
//                //循环范围表，对每一个字段都处理成table，
//                for (int i = 0;i<rangeList.size();i++){
//                    switch (rangeList.get(i).getName()){
//                        case "ph":
//                            msg =  msg + getTrTd(rangeList.get(i).getName(), data.getPh(), rangeList);
//                            break;
//                        case "ss":
//                            msg =  msg + getTrTd(rangeList.get(i).getName(), data.getSs(), rangeList);
//                            break;
//                        case "cod":
//                            msg =  msg + getTrTd(rangeList.get(i).getName(), data.getCod(), rangeList);
//                            break;
//                        case "nh":
//                            msg =  msg + getTrTd(rangeList.get(i).getName(), data.getNh(), rangeList);
//                            break;
//                        case "cu":
//                            msg =  msg + getTrTd(rangeList.get(i).getName(), data.getCu(), rangeList);
//                            break;
//                        case "ni":
//                            msg =  msg + getTrTd(rangeList.get(i).getName(), data.getNi(), rangeList);
//                            break;
//                    }
//                }
//                //插入警报表
//                userServiceImpl.addLastWarn(data);
//            }
//
//        }
//
//
//        //发送邮件
//        if (msg != ""){
//            List<GasEmail> emailList = userServiceImpl.getEmailList();
//            EmailUtils.sendEmail(style + str1 + msg + str2,emailList);
//        }
//
//
//    }

    /**
     * 检查是否异常，正常返回true，异常返回false
     * @param name
     * @param value
     * @param rangeList
     * @return
     */
    public boolean check(String name,Double value,List<GasRange> rangeList){
        //ph  9.123
        //查数据库--范围
        //List<AnalysisRange> rangeList = userServiceImpl.getRange();
        for (int i = 0;i<rangeList.size();i++){
            if (rangeList.get(i).getName().equals(name)){//ph  == ph
                //判断
                if ( value > rangeList.get(i).getMin()  && value < rangeList.get(i).getMax()){
                    return true;
                }else {
                    return false;
                }
            }
        }
        System.out.println("============ err =================数据库范围表没有此字段！！！");
        return true;//找不到范围表对应的name，返回true 防止调试的时候乱报错，所以要更新范围表后再使用此方法
    }

    /**
     * 根据传来的 名称、值、范围表，返回完整的table（tr-td）附加颜色，正常范围绿色，异常红色
     * @param name
     * @param value
     * @param rangeList
     * @return
     */
    private String getTrTd(String name,String value,List<GasRange> rangeList){
        String msg = "";

        for (int i = 0;i<rangeList.size();i++){
            if (rangeList.get(i).getName().equals(name)){
                String str1  =
                        "<tr>" +
                                "<td>" + name + "</td>" +
                                "<td>" + rangeList.get(i).getMin() + "-" + rangeList.get(i).getMax() + "</td>" ;
                String str2 =
//                                "<td></td>" +
                                "</tr>" ;
                if (Double.parseDouble(value) > rangeList.get(i).getMin() && Double.parseDouble(value) < rangeList.get(i).getMax()){
                    msg = msg + str1 +
                            "<td style='color: green'>" + value + "</td>"
                            + str2;
                }else {
                    msg = msg + str1 +
                            "<td style='color: red'>" + value + "</td>"
                            + str2;
                }

            }
        }

        return msg;
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpServletResponse response){
        request.getSession().removeAttribute("user");

        Cookie cookie1 = new Cookie("sewage_account",null);
        Cookie cookie2 = new Cookie("sewage_password",null);
        cookie1.setMaxAge(0);//设置存活时间，“0”即马上消失
        cookie1.setPath("/");
        cookie2.setMaxAge(0);//设置存活时间，“0”即马上消失
        cookie2.setPath("/");
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        return "redirect:/public/login";
    }

    /**
     * 获取email列表接口
     * @return
     */
    @RequestMapping("emailList")
    @ResponseBody
    private LayuiVo emailList(){
        System.out.println("========================================================");
        LayuiVo layuiVo = new LayuiVo();
        layuiVo.setMsg("ok");
        PageHelper.startPage(0,10);
        //接受警报的邮箱账号信息
        List<GasEmail> emailList = userServiceImpl.getEmailList();
        layuiVo.setData(emailList);
        return layuiVo;
    }
}
