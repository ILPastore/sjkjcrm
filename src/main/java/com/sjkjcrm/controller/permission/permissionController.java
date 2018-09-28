package com.sjkjcrm.controller.permission;

import com.sjkjcrm.util.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: xianyunpeng
 */
@Controller
@RequestMapping("/permission")
public class permissionController extends BaseController {
    @RequestMapping("/list.html")
    public String listHtml() {
        return "permission/permission/list";
    }
}
