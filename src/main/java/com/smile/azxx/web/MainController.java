package com.smile.azxx.web;

import com.smile.azxx.service.MainService;
import com.smile.azxx.service.sysmng.UserService;
import com.smile.azxx.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by smile on 2018/4/6.
 */
@Controller
@RequestMapping(value = "/app/")
public class MainController extends BaseController {

    @Autowired
    private MainService mainService;

    @Autowired
    private UserService userService;

    // 定义一个用来包装返回消息的map变量
    private Map<String, Object> result;

    //返回View
    @RequestMapping(value = "test")
    public String test() {
        System.out.println("aaaaaaa!bbbbbbbbbbbbbb");
        return "index";
    }

    @RequestMapping(value = "jsonstring")
    @ResponseBody
    public String returnJson() {
        System.out.println("enter the method!");
        return "this is a json string!";
    }

    @RequestMapping(value = "testService")
    @ResponseBody
    public String testService() {
        String abc = mainService.testService();
        return abc;
    }

    @RequestMapping(value = "getUser")
    public void getUserInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("abc");
        try {
//            User user = userService.get(2);
            this.addResultInfo("query","test",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "testBase")
    public void testBase(HttpSession session, HttpServletRequest request,
                         HttpServletResponse response){
        System.out.println("abc");
        try {
//            User user = userService.get(1);
            this.addResultInfo("query","test",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
