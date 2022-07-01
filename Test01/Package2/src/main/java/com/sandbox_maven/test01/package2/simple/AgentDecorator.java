package com.sandbox_maven.test01.package2.simple;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AgentDecorator implements MethodInterceptor {//MethodInterceptor
    public Object invoke(MethodInvocation invocation) throws Throwable {//MethodInvocation
        System.out.print(", James ");
        Object retVal = invocation.proceed();//proceed() - call interception target method
        System.out.println("!");
        return retVal;
    }
}
