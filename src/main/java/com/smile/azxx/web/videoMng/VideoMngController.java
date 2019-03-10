package com.smile.azxx.web.videoMng;

import com.smile.azxx.entity.ConfInfo;
import com.smile.azxx.entity.VideoInfo;
import com.smile.azxx.entity.sysmng.User;
import com.smile.azxx.service.sysconfig.SysConfService;
import com.smile.azxx.service.videomng.VideoService;
import com.smile.azxx.util.RandomGUID;
import com.smile.azxx.util.VideoProcUtil;
import com.smile.azxx.web.common.BaseController;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by smile on 2018/5/3.
 */
@Controller
@RequestMapping(value = "videomng")
public class VideoMngController extends BaseController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private SysConfService sysConfService;

    @RequestMapping(value = "getVideoes")
    public void getVideoes(HttpServletRequest request, HttpServletResponse response) {
        User user = getUser();
        String videoType = request.getParameter("videoType");
        try {
            List<VideoInfo> result = videoService.getAllVideo(user.getUsername(), videoType);
            this.addResultInfo(SUCCESS, "获取视频列表成功！", result);
            this.responseResult(response, getResultJSONStr());
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "获取视频列表失败！", null);
            this.responseResult(response, getResultJSONStr());
        }
    }

    @RequestMapping(value = "uploadVideoes")
//    @RequiresPermissions("sysconfig")
    public void uploadVideoes(MultipartHttpServletRequest request, HttpServletResponse response) {
        User user = getUser();
        List<VideoInfo> videoes = new ArrayList<>();
        String type = request.getParameter("type");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(new Date());
        try {
            List<MultipartFile> fileList = request.getFiles("files");
            ConfInfo conf = sysConfService.getConfByName("videoPath");
            ConfInfo imgPathConf = sysConfService.getConfByName("videoImgPath");
            String msg = "";
            if (CollectionUtils.isNotEmpty(fileList)) {
                for (int i = 0; i < fileList.size(); i++) {
                    VideoInfo temp = new VideoInfo();
                    String path = conf.getValue();
                    String imgpath = imgPathConf.getValue();
//                    String path = "/usr/java/tomcat9/webapps/reward/";
                    MultipartFile file = fileList.get(i);
                    log.info("文件名："+file.getOriginalFilename()+"文件大小:"+file.getSize()/(1024*1024)+"文件类型："+file.getContentType());
                    String id = new RandomGUID().getUUID32();
                    String fileName = file.getOriginalFilename();
                    DecimalFormat df = new DecimalFormat("#.##");
                    String fileSize = df.format(new BigDecimal(file.getSize()).divide(new BigDecimal(1024)).divide(new BigDecimal(1024)))+"";
                    if(StringUtils.isNotBlank(fileName)&&!fileName.toLowerCase().endsWith(".mp4")){
                        msg +=fileName+"文件上传失败！";
                        break;
                    }
                    this.saveFile(file.getInputStream(), path, id);
                    VideoProcUtil.randomGrabberFFmpegImage(path + id, imgpath, id,1);
                    temp.setId(id);
                    temp.setPublisher(user.getUsername());
                    temp.setPublishTime(nowTime);
                    temp.setTitle(fileName.substring(0, fileName.indexOf(".")));
                    temp.setUrl("/video/" + id);
                    temp.setImgName(id+".png");
                    temp.setTimeLength(this.getVideoTimeLength(path,id));
                    temp.setFileSize(fileSize);
                    temp.setFileName(fileName);
                    if (StringUtils.isBlank(type))
                        temp.setType("0");
                    else
                        temp.setType("1");
                    videoes.add(temp);
                }
            }
            if (CollectionUtils.isNotEmpty(videoes))
                videoService.addVideoes(videoes);
            if(StringUtils.isBlank(msg))
                msg = "文件上传成功！";
            this.addResultInfo(SUCCESS, msg, null);
            this.responseResult(response, getResultJSONStr());
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "上传失败！", null);
            this.responseResult(response, getResultJSONStr());
        }
    }

    @RequestMapping(value = "delVideo")
    public void delVideo(HttpServletRequest request, HttpServletResponse response) {
        String[] ids = request.getParameterValues("videoIds[]");
        try {
            videoService.delVideo(ids);
            this.addResultInfo(SUCCESS, "视频删除成功！", null);
            this.responseResult(response, getResultJSONStr());
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "视频删除失败！", null);
            this.responseResult(response, getResultJSONStr());
        }
    }

    public String getVideoTimeLength(String path,String fileName){
        DecimalFormat df = new DecimalFormat("#.##");
        File source = new File(path+fileName);
        Encoder encoder = new Encoder();
        MultimediaInfo m = null;
        try {
            m = encoder.getInfo(source);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        long ls = m.getDuration() / 1000;//ls是获取到的秒数
        int hour = (int) (ls/3600);
        int minute = (int)(ls%3600)/60;
        int second = (int)(ls%60);
        String h = hour<10?"0"+hour:hour+"";
        String mStr = minute<10?"0"+minute:minute+"";
        String s = second<10?"0"+second:second+"";
        String result = h+":"+mStr+":"+s;
        return result;
    }
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.##");
        String path = "/usr/java/tomcat9/webapps/reward";
        String fileName = "";
        File source = new File(path+fileName);
        Encoder encoder = new Encoder();
        MultimediaInfo m = null;
        try {
            m = encoder.getInfo(source);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        long ls = m.getDuration() / 1000;//ls是获取到的秒数
        int hour = (int) (ls/3600);
        int minute = (int)(ls%3600)/60;
        int second = (int)(ls%60);
        String h = hour<10?"0"+hour:hour+"";
        String mStr = minute<10?"0"+minute:minute+"";
        String s = second<10?"0"+second:second+"";
        String result = h+":"+mStr+":"+s;
    }
//    private String ReadVideoTime(File source) {
//        Encoder encoder = new Encoder();
//        String length = "";
//        try {
//            MultimediaInfo m = encoder.getInfo(source);
//            long ls = m.getDuration()/1000;
//            int hour = (int) (ls/3600);
//            int minute = (int) (ls%3600)/60;
//            int second = (int) (ls-hour*3600-minute*60);
//            length = hour+"'"+minute+"''"+second+"'''";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return length;
//    }

}
