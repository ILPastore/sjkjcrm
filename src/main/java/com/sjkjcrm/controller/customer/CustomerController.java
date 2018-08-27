package com.sjkjcrm.controller.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.service.customer.CustomerDetailService;
import com.sjkjcrm.util.controller.BaseController;
import com.sjkjcrm.util.excel.ExcelUtils;
import com.sjkjcrm.util.layui.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
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

    /**
     * excel导出
     * @param response
     */
    @RequestMapping("/export")
    public void export(HttpServletResponse response) {

        List<CustomerDetail> customerDetailList = customerDetailService.getAll();
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

    /**
     * excel导入
     */
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
        // xianyunpeng555
    }
}
