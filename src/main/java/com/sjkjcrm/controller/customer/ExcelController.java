package com.sjkjcrm.controller.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.service.customer.CustomerDetailService;
import com.sjkjcrm.util.excel.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description excel导入导出
 * @Author Zhang Qiao
 * @Date 2018-08-22 10:25
 **/
@RestController
@RequestMapping("/customer")
public class ExcelController {

    @Autowired
    private CustomerDetailService customerDetailService;

    @RequestMapping("/export1")
    public void export(HttpServletResponse response) {
        Page<CustomerDetail> page = new Page<>();
        List<CustomerDetail> customerDetailList = customerDetailService.getCustomerByCondition(page);
        if (customerDetailList.isEmpty()) {
            return;
        }

        // 导出操作
        try {
            ExcelUtils.exportExcel(customerDetailList, "客户列表", "客户信息", CustomerDetail.class, "客户信息.xls", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/importExcel2")
    public void importExcel() {
        String filePath = "D:\\客户信息.xls";
        // 解析excel，
        try {
            List<CustomerDetail> customerDetailList = ExcelUtils.importExcel(filePath, 1, 1, CustomerDetail.class);
            // 也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
            System.out.println("导入数据一共【" + customerDetailList.size() + "】行");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO 保存数据库
        // xianyunpeng555
    }
}
