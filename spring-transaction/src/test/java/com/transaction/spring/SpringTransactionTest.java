package com.transaction.spring;

import com.transaction.spring.service.TransactionServiceA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringTransactionTest {

    @Autowired
    private TransactionServiceA transactionServiceA;

    @Test
    void testRequired() {
        transactionServiceA.testRequired();
    }

    @Test
    void testRequiredNew() {
        transactionServiceA.testRequiredNew();
    }

    @Test
    void testNested() {
        transactionServiceA.testNested();
    }

    @Test
    void testSupport() {
        transactionServiceA.testSupport();
    }

    @Test
    void testNotSupport() {
        transactionServiceA.testNotSupport();
    }

    @Test
    void testMandatory() {
        transactionServiceA.testMandatory();
    }

    @Test
    void testNever() {
        transactionServiceA.testNever();
    }


}
