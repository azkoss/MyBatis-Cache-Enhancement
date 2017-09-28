package com.github.wanggit.mybatis.cache.enhancement;

import com.github.wanggit.mybatis.cache.enhancement.dao.entity.Account;
import com.github.wanggit.mybatis.cache.enhancement.utils.XClassUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

@RunWith(JUnit4.class)
public class XClassUtilsTests {

    @Test
    public void testXClassUtilsGetOriginalClazz(){
        // No Proxy
        Account account = new Account();
        Class clazz = XClassUtils.getOriginalClazz(account);
        Assert.assertTrue(Account.class.equals(clazz));
        // Cglib
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Account.class);
        enhancer.setCallback(NoOp.INSTANCE);
        Object object = enhancer.create();
        clazz = XClassUtils.getOriginalClazz(object);
        Assert.assertTrue(Account.class.equals(clazz));
        // Double Cglib
        enhancer = new Enhancer();
        enhancer.setSuperclass(Account.class);
        enhancer.setCallback(NoOp.INSTANCE);
        Class cglibClazz = enhancer.createClass();
        enhancer = new Enhancer();
        enhancer.setSuperclass(cglibClazz);
        enhancer.setCallback(NoOp.INSTANCE);
        object = enhancer.create();
        clazz = XClassUtils.getOriginalClazz(object);
        Assert.assertTrue(Account.class.equals(clazz));
    }

}
