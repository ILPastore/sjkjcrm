package com.sjkjcrm.controller.role;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.permisson.Role;
import com.sjkjcrm.bean.permisson.User;
import com.sjkjcrm.service.role.RoleService;
import com.sjkjcrm.util.controller.BaseController;
import com.sjkjcrm.util.layui.ResultModel;
import com.sjkjcrm.util.layui.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: xianyunpeng
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/list.html")
    public String listHtml() {
        return "permission/role/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ResultModel<List<Role>> list(Page<User> page) {
        List<Role> customerDetailList = roleService.queryList(page);
        return new ResultModel<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg(), customerDetailList.size() + "", customerDetailList);
    }
}
