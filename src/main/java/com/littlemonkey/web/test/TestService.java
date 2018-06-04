package com.littlemonkey.web.test;

import org.springframework.stereotype.Service;

@Service("test")
public class TestService {

    public void get(int name) {
        System.out.println("??????????????");
        System.out.println(name);
    }
}
