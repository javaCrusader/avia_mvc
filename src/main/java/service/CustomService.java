package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CustomService {

    @Autowired
    TestService test;

    @PostConstruct
    public void initIt() throws Exception {
        System.out.println("Init method begin ");
        test.dbActivity();
    }
}
