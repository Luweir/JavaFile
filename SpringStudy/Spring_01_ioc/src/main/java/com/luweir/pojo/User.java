package com.luweir.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// 等价于<bean id="user" class="com.luweir.pojo.User"/>!
@Component
@Scope("singleton")
public class User {
    // 属性注入 相当于<property name="name" value="kuangshen" /> 也可以作用在setName上
    @Value("luweir")
    public String name;
}
