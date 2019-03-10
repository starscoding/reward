package com.smile.azxx.web.LinkMng;

import com.smile.azxx.entity.ConfInfo;
import com.smile.azxx.entity.LinkInfo;
import com.smile.azxx.entity.RewardInfo;
import com.smile.azxx.entity.sysmng.User;
import com.smile.azxx.model.VideoAndLinkInfo;
import com.smile.azxx.service.LinkMng.LinkMngService;
import com.smile.azxx.service.sysconfig.SysConfService;
import com.smile.azxx.util.NumberValidationUtils;
import com.smile.azxx.util.RandomGUID;
import com.smile.azxx.util.ShortUrlUtils;
import com.smile.azxx.web.common.BaseController;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by smile on 2018/5/6.
 */
@Controller
@RequestMapping(value = "linkmng")
public class LinkMngController extends BaseController {

    @Autowired
    private LinkMngService linkMngService;

    @Autowired
    private SysConfService sysConfService;

    @RequestMapping(value = "addLinkInfos")
    public void addLinkInfos(HttpServletRequest request, HttpServletResponse response){
        String videoIds[] = request.getParameterValues("videoIds[]");
        User user = this.getUser();
//        String videoPrice = request.getParameter("videoPrice");
        String videoPrice = "2.99";
        String safeUrl = "";

        ConfInfo confInfo = sysConfService.getConfByName("safeDomain");
        if(confInfo!=null)
            safeUrl = confInfo.getValue();

        log.info("safeUrl :"+safeUrl);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<LinkInfo> records = new ArrayList<>();
        if(ArrayUtils.isNotEmpty(videoIds)){
            for (int i = 0; i < videoIds.length; i++) {
                LinkInfo temp = new LinkInfo();
                String id = new RandomGUID().getUUID32();
                String shortUrl = ShortUrlUtils.getShortUrl("http://"+safeUrl+"/reward/wxpay/toOrder.jsp?linkId="+id);
                temp.setId(id);
                temp.setRecordTime(sdf.format(new Date()));
                temp.setShortUrl(shortUrl);
                temp.setUserName(user.getUsername());
                temp.setVideoId(videoIds[i]);
                temp.setVideoPrice(new BigDecimal(videoPrice));
                records.add(temp);
            }
        }
        try {
            if(CollectionUtils.isNotEmpty(records)){
                linkMngService.addLinkInfos(records);
            }
            this.addResultInfo(SUCCESS,"生成链接成功!",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"生成链接失败!",null);
            this.responseResult(response,this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "getLinkInfos")
    public void getLinkInfos(HttpServletRequest request,HttpServletResponse response){
        User user = this.getUser();
        try {
            List<LinkInfo> result = linkMngService.getLinkInfos(user.getUsername());
            this.addResultInfo(SUCCESS,"获取链接成功!",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"获取链接失败!",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "delLinkInfos")
    public void delLinkInfos(HttpServletRequest request,HttpServletResponse response){
        String[] ids = request.getParameterValues("ids[]");
        try {
            linkMngService.delLinkInfos(ids);
            this.addResultInfo(SUCCESS,"删除链接成功!",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"删除链接失败!",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "getVideoAndLinkInfo")
    public void getVideoAndLinkInfo(HttpServletRequest request,HttpServletResponse response){
        User user = this.getUser();
        try {
            List<VideoAndLinkInfo> result = linkMngService.getVideoAndLinkInfo(user.getUsername());
            this.addResultInfo(SUCCESS,"获取信息成功!",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"获取信息失败!",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "getOrdersByLinkId")
    public void getOrdersByLinkId(HttpServletRequest request,HttpServletResponse response){
        String linkId = request.getParameter("linkId");
        try {
            List<RewardInfo> result = linkMngService.getOrdersByLinkId(linkId);
            this.addResultInfo(SUCCESS,"获取打赏明细成功!",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"获取打赏明细失败!",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "updatePrice")
    public void updatePrice(HttpServletRequest request,HttpServletResponse response){
        String[] ids = request.getParameterValues("ids[]");
        String price = request.getParameter("price");
        try {
            String msg = "";
            if(NumberValidationUtils.isPositiveDecimal(price)||NumberValidationUtils.isPositiveInteger(price)){
                linkMngService.updatePrice(ids,price);
                msg = "修改成功";
                this.addResultInfo(SUCCESS,msg,null);
                this.responseResult(response,this.getResultJSONStr());
            }else{
                msg = "请输入正确的价格！";
                this.addResultInfo(FAILURE,msg,null);
                this.responseResult(response,this.getResultJSONStr());
            }
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"获取打赏明细失败!",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }
}
