package com.alexhuang.spring.tx;

public interface ITransaction {

    /**
     * 提交事务
     */
    void commit();

    /**
     * 回滚事务
     */
    void rollback();

}
