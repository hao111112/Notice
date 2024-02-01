package com.hystrix.notice.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hystrix.notice.common.BaseResponse;
import com.hystrix.notice.common.ErrorCode;
import com.hystrix.notice.common.ValidGroup;
import com.hystrix.notice.exception.BusinessException;
import com.hystrix.notice.mapper.NoticeMapper;
import com.hystrix.notice.pojo.Notice;
import com.hystrix.notice.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class NoticeController {
    @Autowired
    NoticeMapper noticeMapper;

    @RequestMapping("/")
    public String toIndex(){
        return "adminLogin";
    }
    @RequestMapping("/adminLogin")
    public String adminLogin(){
        return "redirect:getNoticeList";
    }
    //跳转到查看公告列表界面
    @RequestMapping("/toNoticeList")
    public String toNoticeList(Model model){
        //数据库匹配内容
        LambdaQueryWrapper<Notice> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Notice::getCreatedTime);
        queryWrapper.eq(Notice::getStatus,0);
        List<Notice> notices = noticeMapper.selectList(queryWrapper);
        model.addAttribute("notice", notices.get(0));
        model.addAttribute("notices",notices);
        return "hello";
    }
    //更新公告内容
    @PostMapping("updateNotice")
    public String updateNotice(@Validated(value = ValidGroup.Crud.Update.class) Notice notice){

        notice.setUpdateTime(new Date());
        int i = noticeMapper.updateById(notice);
        if (i == 0) {
            throw new BusinessException(100,"更新失败");
        }
        return "redirect:getNoticeList";
    }
    //上传公告内容
    @PostMapping("uploadNotice")
    public String upload(@Validated(value = ValidGroup.Crud.Create.class)Notice notice,
                         @RequestPart("photos")MultipartFile[] files){
        notice.setCreatedTime(new Date());
        int i= noticeMapper.insert(notice);
        //TODO 文件处理待做
        /*for (MultipartFile file : files) {
            boolean b = Util.checkSuffix(file);
            if (!b){
                throw new BusinessException(ErrorCode.OPERATION_ERROR);
            }
        }

        List<String> list=null;
        try {
            list=Util.addToDirectory(files);
        }catch (Exception e){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        System.err.println(list);
        if (i == 0) {
            throw new BusinessException(100,"新增失败");
        }*/
        return "redirect:getNoticeList";
    }
    //获取公告列表
    @GetMapping("getNoticeList")
    public String getNoticeList(Model model){
        LambdaQueryWrapper<Notice> queryWrapper = new LambdaQueryWrapper<>();
        //按时间排序，拿到最新数据
        queryWrapper.orderByDesc(Notice::getCreatedTime);
        List<Notice> notices = noticeMapper.selectList(queryWrapper);
       model.addAttribute("notices",notices);
        return "index";
    }
    //跳转更新公告界面
    @GetMapping("toUpdateNotice")
    public String getNewestNotice(Integer nid,Model model){
        Notice notice = noticeMapper.selectById(nid);
        model.addAttribute("notice",notice);
        return "updateNotice";
    }
    //跳转添加公告
    @GetMapping("/notice/toAddNotice")
    public String toAddNotice(){
        return "addNotice.html";
    }

    //删除公告
    @RequestMapping("/delNotice")
    public String delNotice( Integer nid){
        int i = noticeMapper.deleteById(nid);
        if (i == 0) {
            throw new BusinessException(100,"删除失败");
        }
        return "redirect:getNoticeList";
    }
}
