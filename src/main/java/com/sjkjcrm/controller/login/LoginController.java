package com.sjkjcrm.controller.login;

import com.sjkjcrm.WebSecurityConfig;
import com.sjkjcrm.util.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证登录controller
 */
@Controller
public class LoginController extends BaseController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/loginPost")
    @ResponseBody
    public Map<String, Object> loginPost(String account, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        if (!"123456".equals(password)) {
            map.put("success", false);
            map.put("message", "密码错误");
            return map;
        }

        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, account);

        map.put("success", true);
        map.put("message", "登录成功");
        return map;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }

}
