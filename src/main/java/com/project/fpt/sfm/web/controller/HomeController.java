package com.project.fpt.sfm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Khắc Vỹ on 9/16/2015.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("sidebar", "student/student-sidebar");
        return "index";
    }


    @RequestMapping("/home")
    public String homePage(Model model){
        model.addAttribute("sidebar", "student/student-sidebar");
        return "home";
    }

    @RequestMapping("/student")
    public String studenPage(Model model){
        model.addAttribute("sidebar", "student/student-sidebar");
        model.addAttribute("content", "student/profile");

        return "home";
    }

    @RequestMapping("/student/hocphidukien")
    public String studentHocPhiDuKien(Model model){
        model.addAttribute("content", "student/hocphidukien");
        model.addAttribute("sidebar", "student/student-sidebar");

        return "home";
    }

    @RequestMapping("/student/cackydahoc")
    public String studentCackydahoc(Model model){
        model.addAttribute("content", "student/cackydahoc");
        model.addAttribute("sidebar", "student/student-sidebar");

        return "home";
    }

    @RequestMapping("/student/cacmonchuahoanthanh")
    public String studentCacMonChuaHoanThanh(Model model){
        model.addAttribute("content", "student/cacmonchuahoanthanh");
        model.addAttribute("sidebar", "student/student-sidebar");

        return "home";
    }

    @RequestMapping("/student/profile")
    public String studentProfile(Model model){
        model.addAttribute("content", "student/profile");
        model.addAttribute("sidebar", "student/student-sidebar");

        return "home";
    }

    @RequestMapping("/student/donghocphi")
    public String studentDongHocPhi(Model model){
        model.addAttribute("content", "student/trangthaidonghocphi");
        model.addAttribute("sidebar", "student/student-sidebar");

        return "home";
    }

    @RequestMapping("/student/donghoclai")
    public String studentDongHocLai(Model model){
        model.addAttribute("content", "student/trangthaidonghoclai");
        model.addAttribute("sidebar", "student/student-sidebar");

        return "home";
    }

    @RequestMapping("/student/xemlaivay")
    public String studentXemlaivay(Model model){
        model.addAttribute("content", "student/xemlaivay");
        model.addAttribute("sidebar", "student/student-sidebar");

        return "home";
    }

    @RequestMapping("/admin/hocphi")
    public String adminHocPhi(Model model){
        model.addAttribute("content", "admin/hocphi");
        model.addAttribute("sidebar", "admin/admin-sidebar");

        return "home";
    }

    @RequestMapping("/admin/hoclai")
    public String adminHocLai(Model model){
        // model.addAttribute("content", "student/xemlaivay");
        model.addAttribute("sidebar", "admin/admin-sidebar");

        return "home";
    }

    @RequestMapping("/admin")
    public String admin(Model model){
        model.addAttribute("content", "admin/admin-home");
        model.addAttribute("sidebar", "admin/admin-sidebar");

        return "home";
    }

    @RequestMapping("/admin/danh-sach-vay-tin-dung")
    public String adminStudentLoansCredit(Model model){
        model.addAttribute("content", "admin/admin-loans-credit");
        model.addAttribute("sidebar", "admin/admin-sidebar");

        return "home";
    }

    @RequestMapping("/admin/danh-sach-tam-ngung-hoc")
    public String managerListSuspension(Model model){
        model.addAttribute("content", "admin/manager-list-suspension");
        model.addAttribute("sidebar", "admin/admin-sidebar");

        return "home";
    }


    /**
     * STAFF
     */
    @RequestMapping("/staff/nhap-thong-tin-sinh-vien")
    public String addStudentInformation(Model model){
        model.addAttribute("content", "staff/add-student-information");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        return "home";
    }

    @RequestMapping("/staff/nhap-ket-qua-hoc-tap")
    public String addStudyResult(Model model){
        model.addAttribute("content", "staff/add-study-result");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        return "home";
    }

    @RequestMapping("/staff/nhap-thong-tin-tai-chinh")
    public String addStudentFinance(Model model){
        model.addAttribute("content", "staff/add-finance-information");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        return "home";
    }

    @RequestMapping("/staff")
    public String staff(Model model){
        //model.addAttribute("content", "staff/add-finance-information");
        model.addAttribute("sidebar", "staff/staff-sidebar");

        return "home";
    }
}
