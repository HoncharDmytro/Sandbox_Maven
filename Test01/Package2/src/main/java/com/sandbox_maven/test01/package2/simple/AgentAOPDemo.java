package com.sandbox_maven.test01.package2.simple;

import org.springframework.aop.framework.ProxyFactory;

public class AgentAOPDemo {
    public static void main(String[] args) {
        Agent target = new Agent();//Interception target

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new AgentDecorator());//give decorator to proxy
        pf.setTarget(target);//give target to proxy

        Agent proxy = (Agent) pf.getProxy();//receive proxy

        target.speak();//original call
        proxy.speak();//proxy call
    }
}
