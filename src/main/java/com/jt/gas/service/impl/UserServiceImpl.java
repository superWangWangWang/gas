package com.jt.gas.service.impl;

import com.jt.gas.entity.*;
import com.jt.gas.mapper.UserMapper;
import com.jt.gas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Gas> getData() {
        return userMapper.getData();
    }

    @Override
    public List<GasRange> getRange() {
        return userMapper.getRange();
    }

    @Override
    public GasUser finUserByAccount(String account) {
        return userMapper.finUserByAccount(account);
    }

    @Override
    public GasWarning getLatestWarn() {
        return userMapper.getLatestWarn();
    }

    @Override
    public void addLastWarn(Gas gas) {
        userMapper.addLastWarn(gas);
    }

    @Override
    public List<GasEmail> getEmailList() {
        return userMapper.getEmailList();
    }

    @Override
    public List<GasHistory> getHistoryAvg(String name, String date) {
        return userMapper.getHistoryAvg(name,date);
    }


    //====================


    @Override
    public List<Gas> getLastestData() {
        return userMapper.getLastestData();
    }

    @Override
    public List<GasRange> getGasRange() {
        return userMapper.getGasRange();
    }
}
