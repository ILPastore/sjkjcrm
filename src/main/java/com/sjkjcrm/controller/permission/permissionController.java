package com.sjkjcrm.controller.permission;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.permisson.Permission;
import com.sjkjcrm.bean.permisson.Role;
import com.sjkjcrm.bean.permisson.User;
import com.sjkjcrm.service.permission.PermissionService;
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
@RequestMapping("/permission")
public class permissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/list.html")
    public String listHtml() {
        return "permission/permission/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ResultModel<List<Permission>> list(Page<User> page) {
        List<Permission> customerDetailList = permissionService.queryList(page);
        return new ResultModel<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg(), customerDetailList.size() + "", customerDetailList);
    }
}
