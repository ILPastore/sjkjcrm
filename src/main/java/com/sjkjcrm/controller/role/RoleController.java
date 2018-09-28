package com.sjkjcrm.controller.role;

import com.sjkjcrm.util.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: xianyunpeng
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @RequestMapping("/list.html")
    public String listHtml() {
        return "permission/role/list";
    }
}
