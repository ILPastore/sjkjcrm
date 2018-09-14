package com.sjkjcrm.controller.user;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.bean.permisson.User;
import com.sjkjcrm.service.ILoginService;
import com.sjkjcrm.util.controller.BaseController;
import com.sjkjcrm.util.layui.ResultModel;
import com.sjkjcrm.util.layui.ResultStatus;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private ILoginService loginService;

    @RequestMapping("/list.html")
    public String listHtml() {
        return "permission/user/list";
    }

    @RequestMapping("/edit.html")
    public String editHtml() {
        return "permission/user/edit";
    }


    @RequestMapping("/list")
    @ResponseBody
    public ResultModel<List<User>> list(Page<User> page) {
        List<User> customerDetailList = loginService.queryList(page);
//        return super.getLayPage4List(new ResultModel<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg(), customerDetailList));
        return new ResultModel<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg(), customerDetailList.size() + "", customerDetailList);
    }

    /**
     * 新增用户信息
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultModel insertCustomer(User user) {
        loginService.addUser(user);
//        customerDetailService.insertCustomer(customerDetail);
       return new ResultModel(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg());
    }
}
