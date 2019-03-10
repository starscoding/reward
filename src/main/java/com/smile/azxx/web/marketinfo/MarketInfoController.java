package com.smile.azxx.web.marketinfo;

import com.smile.azxx.entity.sysmng.User;
import com.smile.azxx.model.AgencyDetail;
import com.smile.azxx.model.MarketCountInfo;
import com.smile.azxx.model.MarketUserInfo;
import com.smile.azxx.service.orderinfo.OrderService;
import com.smile.azxx.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by smile on 2018/5/2.
 */
@Controller
@RequestMapping(value = "marketInfo")
public class MarketInfoController extends BaseController{

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "getCountInfo")
    public void getCountInfo(HttpServletRequest request, HttpServletResponse response){
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        User user = getUser();
        log.info(startTime+","+endTime+","+user.getUsername());
        try {
            List<MarketCountInfo> result = orderService.getCountInfo(startTime,endTime,user.getUsername());
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }
    @RequestMapping(value = "getMarketUserInfo")
    public void getMarketUserInfo(HttpServletRequest request, HttpServletResponse response){
        User user = getUser();
        log.info(user.getUsername());
        try {
            List<MarketUserInfo> result = orderService.getMarketUserInfo(user.getUsername());
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "countTurnover")
    public void countTurnover(HttpServletRequest request, HttpServletResponse response){
        String date = request.getParameter("date");
        User user = getUser();
        log.info(user.getUsername());
        try {
            String result = orderService.countTurnover(date,user.getUsername());
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "countAdminTurnover")
    public void countAdminTurnover(HttpServletRequest request, HttpServletResponse response){
        String date = request.getParameter("date");
        log.info(date);
        try {
            String result = orderService.countTurnover4Admin(date);
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "countOrderNum")
    public void countOrderNum(HttpServletRequest request, HttpServletResponse response){
        String date = request.getParameter("date");
        User user = this.getUser();
        log.info(date);
        try {
            String result = orderService.countOrderNum(date,user.getUsername());
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "countMarketNum")
    public void countMarketNum(HttpServletRequest request, HttpServletResponse response){
        String date = request.getParameter("date");
        User user = getUser();
        log.info(user.getUsername());
        try {
            int result = orderService.countMarketNum(date,user.getUsername());
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "getAgencyDetail")
    public void getAgencyDetail(HttpServletRequest request, HttpServletResponse response){
        try {
            List<AgencyDetail> result = orderService.getAgencyDetail();
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }
}
