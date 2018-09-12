package com.sjkjcrm.controller.login;


import com.sjkjcrm.bean.permisson.Role;
import com.sjkjcrm.bean.permisson.User;
import com.sjkjcrm.service.ILoginService;
import com.sjkjcrm.util.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class LoginController extends BaseController {
    @Autowired
    private ILoginService loginService;


    //退出的时候是get请求，主要是用于退出
    @RequestMapping(value = "/*.html",method = RequestMethod.GET)
    public String all(HttpServletRequest request){
        return request.getRequestURI().substring(0,request.getRequestURI().length()-5);
    }

    //退出的时候是get请求，主要是用于退出
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    //post登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(@RequestBody(required = false) Map map){
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> resultMap = new HashMap<>();
        try {

            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                    map.get("username").toString(),
                    map.get("password").toString());
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
        } catch (UnknownAccountException e) {

            resultMap.put("status", 500);

            resultMap.put("message", "账号不存在！");

        }catch(IncorrectCredentialsException e1){

            resultMap.put("status", 500);

            resultMap.put("message", "密码错误！");

        }catch (Exception e) {

            resultMap.put("status", 500);

            resultMap.put("message", "账号不存在");

        }
        return resultMap;
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "200");
        map.put("msg", "未登录");
        return map;
    }


    @RequestMapping(value = "/index")
    public String index(){
        return "customer/home";
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout(){
        return "logout";
    }

    //错误页面展示
    @RequestMapping(value = "/error",method = RequestMethod.POST)
    public String error(){
        return "error ok!";
    }

    //数据初始化
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser(@RequestBody Map<String,Object> map){
        User user = loginService.addUser(map);
        return "addUser is ok! \n" + user;
    }

    //角色初始化
    @RequestMapping(value = "/addRole")
    public String addRole(@RequestBody Map<String,Object> map){
        Role role = loginService.addRole(map);
        return "addRole is ok! \n" + role;
    }

    //注解的使用
    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/create")
    public String create(){
        return "Create success!";
    }
}
