package com.jt.gas.service;

import com.jt.gas.entity.*;

import java.util.List;

public interface UserService {
    List<Gas> getData();
    List<GasRange> getRange();
    GasUser finUserByAccount(String account);
    GasWarning getLatestWarn();
    void addLastWarn(Gas gas);
    List<GasEmail> getEmailList();
    List<GasHistory> getHistoryAvg(String name, String date);



    List<Gas> getLastestData();
    List<GasRange> getGasRange();
}
