package com.transaction.spring.service;

import com.transaction.spring.dao.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceB {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void required() {
        userAccountMapper.serviceBInsert();
        //TODO exception :int result = 1 / 0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiredNew() {
        userAccountMapper.serviceBInsert();
        //TODO exception :int result = 1 / 0;
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nested() {
        userAccountMapper.serviceBInsert();
        //TODO exception :int result = 1 / 0;
//        int result = 1 / 0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void support() {
        userAccountMapper.serviceBInsert();
        //TODO exception :int result = 1 / 0;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupport() {
        userAccountMapper.serviceBInsert();
        //TODO exception :int result = 1 / 0;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatory() {
        userAccountMapper.serviceBInsert();
        //TODO exception :int result = 1 / 0;
    }

    @Transactional(propagation = Propagation.NEVER)
    public void never() {
        userAccountMapper.serviceBInsert();
        //TODO exception :int result = 1 / 0;
    }

}
