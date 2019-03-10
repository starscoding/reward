package com.smile.azxx.service;

import com.smile.azxx.service.common.BaseService;
import org.springframework.stereotype.Component;

/**
 * Created by smile on 2018/4/6.
 */
@Component
public class MainService extends BaseService {

    public String testService(){
        return "enter service";
    }
}
