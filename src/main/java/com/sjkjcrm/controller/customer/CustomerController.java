package com.sjkjcrm.controller.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.service.customer.CustomerDetailService;
import com.sjkjcrm.util.controller.BaseController;
import com.sjkjcrm.util.excel.ExcelUtils;
import com.sjkjcrm.util.layui.ResultModel;
import com.sjkjcrm.util.layui.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * 客户管理模块controller
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerDetailService customerDetailService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultModel<List<CustomerDetail>> queryCustomerListByCondition(Page<CustomerDetail> page) {
        List<CustomerDetail> customerDetailList = customerDetailService.getCustomerByCondition(page);
//        return super.getLayPage4List(new ResultModel<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg(), customerDetailList));
        return new ResultModel<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg(), customerDetailList.size() + "", customerDetailList);
    }

    @RequestMapping("/homepage")
    public String homepage() {
        return "customer/home";
    }

    @RequestMapping("/customerpage")
    public String customerPage() {
        return "customer/list";
    }

    @RequestMapping("/customerpage1")
    public String customerPage1() {
        return "permission/list";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/addcustomer")
    public String addCustomer() {
        return "customer/edit";
    }

    /**
     * 修改客户信息·
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/editCustomer")
    public String editCustomer(@RequestParam(name = "id", required = false) String id, Model model) {
        System.out.println("id=====" + id);
        CustomerDetail customerDetail = customerDetailService.selectCustomerDetailById(id);
        model.addAttribute("customerDetail", customerDetail);
        return "customer/update";
    }

    /**
     * 新增客户信息
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public ResultModel insertCustomer(CustomerDetail customerDetail) {
        customerDetailService.insertCustomer(customerDetail);
        return new ResultModel(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg());
    }

    /**
     * 更新客户信息
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultModel updateCustomer(CustomerDetail customerDetail) {
        customerDetailService.updateCustomer(customerDetail);
        return new ResultModel(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg());
    }

    /**
     * 删除客户信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteCustomer")
    @ResponseBody
    public ResultModel deleteCustomer(@RequestParam(name = "id", required = true) String id) {
        if (!Objects.isNull(id)) {
            customerDetailService.deleteCustomer(id);
        }
        return new ResultModel(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg());
    }

    /**
     * excel导出
     * @param response
     */
    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        Page<CustomerDetail> page = new Page<>();
        List<CustomerDetail> customerDetailList = customerDetailService.getCustomerByCondition(page);
        if (customerDetailList.isEmpty()) {
            return;
        }

        // 导出操作
        try {
            ExcelUtils.exportExcel(customerDetailList, "客户列表", "客户信息", CustomerDetail.class, "customertemplate.xls", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * excel导入
     */
    @RequestMapping(value = "/importExcel", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ResultModel importExcel(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            customerDetailService.importExcel(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultModel(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg());
    }
}
