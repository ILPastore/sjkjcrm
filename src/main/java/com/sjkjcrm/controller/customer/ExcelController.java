package com.sjkjcrm.controller.customer;

import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.util.excel.ExcelUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description excel测试
 * @Author Zhang Qiao
 * @Date 2018-08-22 10:25
 **/
@RestController
@RequestMapping("/customer")
public class ExcelController {

    @RequestMapping("/export")
    public void export(HttpServletResponse response) {

        // 模拟从数据库获取需要导出的数据
        List<CustomerDetail> customerDetailList = new ArrayList<>();
        CustomerDetail customerDetail = new CustomerDetail();
        customerDetail.setCustomerId("1");
        customerDetail.setName("张三");
        customerDetail.setNumber("101");
        customerDetail.setType("01");
        customerDetail.setPhone("13026369859");
        customerDetail.setCorpTel("89021600");
        customerDetail.setMail("jack@123.com");
        customerDetail.setAddress("武汉市楚河汉界");
        customerDetail.setCorpType("1");
        customerDetail.setCorpGrade("01");
        customerDetail.setVisitDate(new Date());
        customerDetailList.add(customerDetail);

        // 导出操作
        try {
            ExcelUtils.exportExcel(customerDetailList, "客户列表", "啊呀", CustomerDetail.class, "客户信息.xls", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/importExcel")
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
        // xianyunpeng222
    }
}
