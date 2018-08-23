package com.sjkjcrm.controller.customer;

import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.service.customer.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerDetailService customerDetailService;

    @RequestMapping("/list")
    public String hello() {
        List<CustomerDetail> customerDetailList = customerDetailService.getAll();
        if (customerDetailList.isEmpty()) {
            return null;
        }
        return "platform/user/list";
    }
}
