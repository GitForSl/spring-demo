package com.transaction.spring.service;

import com.transaction.spring.dao.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceA {

    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private TransactionServiceB transactionServiceB;

    /**
     * 若当前存在事务，就加入当前事务，若没有事务则新启一个事务
     * <p>
     * 1： B异常，数据全部回滚
     * 2： A异常，数据全部回滚 (REQUIRED与REQUIRES_NEW有)
     * 3： B异常A捕获B异常，数据全部回滚 (REQUIRED与NESTED有)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequired() {
        userAccountMapper.serviceAInsert();
        //REQUIRED
        transactionServiceB.required();
        //TODO exception :int result = 1 / 0;
    }

    /**
     * 不管当前存不存在事务，都新启一个事务，将当前存在的事务挂起
     * <p>
     * 1： B异常，数据全部回滚
     * 2： A异常，A回滚
     * 3： B异常A捕获B异常，B回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequiredNew() {
        userAccountMapper.serviceAInsert();
        //REQUIRES_NEW
        transactionServiceB.requiredNew();
//        try{ transactionServiceB.nested(); } catch (Exception e) {}
        //TODO exception :int result = 1 / 0;
    }

    /**
     * 嵌套事务，若当前存在事务，就当前事务里嵌套一个事务运行，若当前不存在事务， 则以Propagation.REQUIRED方式，既开启一个新的事务
     * <p>
     * 1： B异常，数据全部回滚
     * 2： A异常，数据全部回滚
     * 3： B异常A捕获B异常，B回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testNested() {
        userAccountMapper.serviceAInsert();
        //NESTED
        transactionServiceB.nested();
//        try{ transactionServiceB.nested(); } catch (Exception e) {}
        //TODO exception :int result = 1 / 0;
    }

    /**
     * 支持当前事务，若当前存在事务，就加入当前事务，若当前没有事务，就以非事务的方式运行
     * <p>
     * 1： B异常，数据全部回滚
     * 2： A异常，数据全部回滚
     * 3： B异常A捕获B异常，数据全部回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testSupport() {
        userAccountMapper.serviceAInsert();
        //SUPPORTS
        transactionServiceB.support();
//        try{ transactionServiceB.nested(); } catch (Exception e) {}
        //TODO exception :int result = 1 / 0;
    }

    /**
     * 以非事务的方式运行，若当前存在事务，就将当前事务挂起
     * <p>
     * 1： B异常，数据都不回滚
     * 2： A异常，A回滚
     * 3： B异常A捕获B异常，A回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testNotSupport() {
        userAccountMapper.serviceAInsert();
        //NOT_SUPPORTED
        transactionServiceB.notSupport();
//        try{ transactionServiceB.nested(); } catch (Exception e) {}
        //TODO exception :int result = 1 / 0;
    }

    /**
     * 以事务方式运行，若当前存在事务，就加入当前事务，若当前没有事务，就抛出异常
     * <p>
     * 1： B异常，数据全部回滚
     * 2： A异常，数据全部回滚
     * 3： B异常A捕获B异常，数据全部回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testMandatory() {
        userAccountMapper.serviceAInsert();
        //MANDATORY
        transactionServiceB.mandatory();
//        try{ transactionServiceB.nested(); } catch (Exception e) {}
        //TODO exception :int result = 1 / 0;
    }

    /**
     * 以非事务方式运行，若当前存在事务，就抛出异常
     * <p>
     * 1： B异常，数据全部回滚
     * 2： A异常，数据全部回滚
     * 3： B异常A捕获B异常，数据全部回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testNever() {
        userAccountMapper.serviceAInsert();
        //NEVER
        transactionServiceB.never();
//        try{ transactionServiceB.nested(); } catch (Exception e) {}
        //TODO exception :int result = 1 / 0;
    }

}
