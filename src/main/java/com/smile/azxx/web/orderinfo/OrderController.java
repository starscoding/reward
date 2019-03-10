package com.smile.azxx.web.orderinfo;

import com.smile.azxx.entity.sysmng.User;
import com.smile.azxx.model.PartnerDetail;
import com.smile.azxx.model.RewarViedoInfo;
import com.smile.azxx.service.orderinfo.OrderService;
import com.smile.azxx.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by smile on 2018/4/18.
 */
@Controller
@RequestMapping(value = "orderService")
public class OrderController extends BaseController{

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "getOrders")
    public void getOrders(HttpServletRequest request, HttpServletResponse response){
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        User user = getUser();
        try {
            List<RewarViedoInfo> result = orderService.getOrders(startTime,endTime,user.getUsername());
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "getOrdersByUserName")
    public void getOrdersByUserName(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("userName");
        try {
            List<RewarViedoInfo> result = orderService.getOrdersByName(userName);
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "getOrdersByPName")
    public void getOrdersByPName(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("userName");
        try {
            List<RewarViedoInfo> result = orderService.getOrdersByPName(userName);
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }


    @RequestMapping(value = "getPartnerDetail")
    public void getPartnerDetail(HttpServletRequest request, HttpServletResponse response){
        try {
            List<PartnerDetail> result = orderService.getPartnerDetail();
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "countTurnover4Agency")
    public void countTurnover4Agency(HttpServletRequest request, HttpServletResponse response){
        String date = request.getParameter("date");
        User user = getUser();
        log.info(user.getUsername());
        try {
            String result = orderService.countTurnover4Agency(date,user.getUsername());
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }
    @RequestMapping(value = "countCommission")
    public void countCommission(HttpServletRequest request, HttpServletResponse response){
        String date = request.getParameter("date");
        User user = getUser();
        log.info(user.getUsername());
        try {
            String result = orderService.countCommission(date,user.getUsername());
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }
}
