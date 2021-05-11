package com.sw.crm.settings.web.controller;

import com.sw.crm.settings.domain.User;
import com.sw.crm.settings.service.UserService;
import com.sw.crm.settings.service.impl.UserServiceImpl;
import com.sw.crm.utils.MD5Util;
import com.sw.crm.utils.PrintJson;
import com.sw.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author 北京动力节点
 */
public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //进入控制层
        String path = request.getServletPath();
        if ("/settings/user/login.do".equals(path)){
            login(request,response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        //获取前端传过来的账号和密码
        String act = request.getParameter("act");
        String pwd = request.getParameter("pwd");
        //将密码从明文改为密文
        pwd = MD5Util.getMD5(pwd);
        //获取ip地址
        String IP = request.getRemoteAddr();
        //获取Server对象，使用动态代理的方式
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        try {
            User user = us.login(act,pwd,IP);
            //如果验证成功表示，账号和密码是正确的
            request.getSession().setAttribute("user",user);
            PrintJson.printJsonFlag(response,true);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }
}




































