package com.sjkjcrm.controller.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.service.customer.CustomerDetailService;
import com.sjkjcrm.util.controller.BaseController;
import com.sjkjcrm.util.layui.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerDetailService customerDetailService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultModel<List<CustomerDetail>> queryCustomerList(Page<CustomerDetail> page) {
        List<CustomerDetail> customerDetailList = customerDetailService.getAll();
        return new ResultModel<>("0", "", customerDetailList);
    }

    @RequestMapping("/homepage")
    public String homepage() {
        return "customer/home";
    }

    @RequestMapping("/customerpage")
    public String customerPage() {
        return "customer/list";
    }

    /**
     * 新增客户
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/addcustomer")
    public String addCustomer(@RequestParam(name = "id",required = false)String id, Model model) {
//        SysMenu sysMenu = menuService.getById(id).getData();
//        if (sysMenu == null){
//            sysMenu = new SysMenu();
//            sysMenu.setParentNode(new SysMenu());
//        }
//        model.addAttribute("sysMenu",sysMenu);
        return "customer/edit";
    }
}
