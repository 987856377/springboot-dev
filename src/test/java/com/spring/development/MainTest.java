package com.spring.development;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.RuntimeUtil;

public class MainTest {
    public static void main(String[] args) {
        String phone = "18530320215";
        System.out.println("PhoneUtil.hideBetween(phone) = " + PhoneUtil.hideBetween(phone));

        System.out.println("RuntimeUtil.execForStr(\"dir\") = " + RuntimeUtil.exec("dir"));
    }
}
