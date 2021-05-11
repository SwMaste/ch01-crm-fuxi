package com.sw.crm.workbench.web.controller;

import com.sw.crm.settings.domain.User;
import com.sw.crm.settings.service.UserService;
import com.sw.crm.settings.service.impl.UserServiceImpl;
import com.sw.crm.utils.DateTimeUtil;
import com.sw.crm.utils.PrintJson;
import com.sw.crm.utils.ServiceFactory;
import com.sw.crm.utils.UUIDUtil;
import com.sw.crm.workbench.domain.Activity;
import com.sw.crm.workbench.service.ActivityService;
import com.sw.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @MASTER 统治世界的霸主 常狗狗
 * @date 2021/5/2
 */
public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if ("/workbench/activity/getUsrList.do".equals(path)){
            getUserList(req,resp);
        }else if ("/workbench/activity/save.do".equals(path)){
            save(req,resp);
        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("创建活动信息的controller");
        //从前端获取数据
        String id = UUIDUtil.getUUID();
        String owner = req.getParameter("owner");
        String name = req.getParameter("name");
        String startDate = req.getParameter("startTime");
        String endDate = req.getParameter("endTime");
        String cost = req.getParameter("cost");
        String description = req.getParameter("describe");
        System.out.println(123);
        System.out.println(owner);
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)req.getSession().getAttribute("user")).getName();
        Activity activity = new Activity();
        activity.setId(id);
        activity.setOwner(owner);
        activity.setName(name);
        activity.setStartDate(startDate);
        activity.setDescription(description);
        activity.setEndDate(endDate);
        activity.setCost(cost);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = as.save(activity);
        System.out.println(flag);
        PrintJson.printJsonFlag(resp,flag);

    }

    private void getUserList(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("进入获取User对象的控制层");
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> list = us.getUserList();
        PrintJson.printJsonObj(resp,list);
    }
}
