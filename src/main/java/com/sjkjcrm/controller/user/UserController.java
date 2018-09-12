package com.sjkjcrm.controller.user;

import com.sjkjcrm.util.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @RequestMapping("/list")
    public String customerPage() {
        return "customer/list";
    }
}
