package com.sjkjcrm.controller.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.service.customer.CustomerDetailService;
import com.sjkjcrm.util.controller.BaseController;
import com.sjkjcrm.util.excel.ExcelUtils;
import com.sjkjcrm.util.layui.ResultModel;
import com.sjkjcrm.util.layui.ResultStatus;
import lombok.extern.slf4j.Slf4j;
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
 * @author nb2018
 */
@Controller
@RequestMapping("/customer")
@Slf4j
public class CustomerController extends BaseController {

    @Autowired
    private CustomerDetailService customerDetailService;

    @RequestMapping("/list")
    @ResponseBody
    public ResultModel<List<CustomerDetail>> queryCustomerListByCondition(Page<CustomerDetail> page) {
        List<CustomerDetail> customerDetailList = customerDetailService.getCustomerByCondition(page);
        return new ResultModel<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg(), customerDetailService.getDataCount(), customerDetailList);
    }

    /**
     * 修改客户信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/editCustomer")
    public String editCustomer(@RequestParam(name = "id", required = false) String id, Model model) {
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
        } else {
            for (CustomerDetail customerDetail : customerDetailList) {
                if ("0".equals(customerDetail.getCustomerStatus())) {
                    customerDetail.setCustomerStatus("意向客户");
                } else if ("1".equals(customerDetail.getCustomerStatus())) {
                    customerDetail.setCustomerStatus("重点客户");
                } else if ("2".equals(customerDetail.getCustomerStatus())) {
                    customerDetail.setCustomerStatus("公海客户");
                } else if ("3".equals(customerDetail.getCustomerStatus())) {
                    customerDetail.setCustomerStatus("合作客户");
                } else if ("4".equals(customerDetail.getCustomerStatus())) {
                    customerDetail.setCustomerStatus("保护客户");
                } else {
                    customerDetail.setCustomerStatus("");
                }
            }
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
