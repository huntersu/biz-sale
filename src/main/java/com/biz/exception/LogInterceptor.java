package com.biz.exception;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class LogInterceptor implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable  {
        System.out.println("method之前执行了————————————————————————————————————————————————");
        Logger loger = LoggerFactory.getLogger(invocation.getClass());

        loger.info("--Log By Andy Chan -----------------------------------------------------------------------------");
        loger.info("方法之前的操作********"+invocation.getMethod() + ":BEGIN!--(Andy ChanLOG)");// 方法前的操作
        Method method = invocation.getMethod();
        String name = method.getName();//执行的方法名
        Object obj = invocation.proceed();// 执行方法后返回的结果
        Object[] arguments = invocation.getArguments();//方法入参
        for (Object argument : arguments) {
            System.out.println("参数："+argument);
        }

        loger.info("方法之后的操作********"+invocation.getMethod() + ":END!--(Andy ChanLOG)");// 方法后的操作
        loger.info("-------------------------------------------------------------------------------------------------");

        return obj;
    }
}
