package com.jt.gas.mapper;

import com.jt.gas.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 倒序查询数据表
     * @return
     */
    @Select("SELECT * FROM `sewage` ORDER BY `time` DESC")
    List<Gas> getData();

    /**
     * 查询正常范围
     * @return
     */
    @Select("SELECT * FROM `sewage_range`")
    List<GasRange> getRange();



    /**
     * 获取最新的一条警报数据
     * @return
     */
    @Select("SELECT * FROM `sewage_warning` ORDER BY `time` DESC")
    GasWarning getLatestWarn();

    /**
     * 插入警报表 数据
     * @param gas
     */
    @Insert("INSERT INTO `sewage_warning` (`time`,`ph`,`ss`,`cod`,`nh`,`cu`,`ni`,`fr`) VALUES (#{sewage.time},#{sewage.ph},#{sewage.ss},#{sewage.cod},#{sewage.nh},#{sewage.cu},#{sewage.ni},#{sewage.fr})")
    void addLastWarn(@Param("sewage") Gas gas);

    /**
     * 获取需要接受的email
     * @return
     */
    @Select("SELECT * FROM `sewage_email`")
    List<GasEmail> getEmailList();

    /**
     * 查询历史平均数
     * @param name
     * @param date
     * @return
     */
    @Select("SELECT AVG(CAST(${name} AS DECIMAL(10,4))) AS `avg`,LEFT(`time`,10) AS `time`FROM `sewage`WHERE LEFT(`time`,8) = #{date}  GROUP BY LEFT(`time`,10) ORDER BY `time` ASC")
    List<GasHistory> getHistoryAvg(String name, String date);

    List<GasLog> getSewageLog(String uid);

    //==============================================

    /**
     * 根据设备名称 获取 最新的数据
     * @return
     */
    @Select("SELECT gas.* FROM gas RIGHT JOIN (SELECT `name`,MAX(`time`) AS `time` FROM gas GROUP BY `name`) AS b ON gas.`time` = b.time AND gas.`name` = b.name ORDER BY `name` ASC")
    List<Gas> getLastestData();

    /**
     * 获取范围表数据
     * @return
     */
    @Select("SELECT * FROM `gas_range`")
    List<GasRange> getGasRange();

    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    @Select("SELECT * FROM `gas_user` WHERE `account` = #{account}")
    GasUser finUserByAccount(String account);
}
