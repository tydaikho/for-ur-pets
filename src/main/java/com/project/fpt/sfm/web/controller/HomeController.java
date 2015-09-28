package com.project.fpt.sfm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Khắc Vỹ on 9/16/2015.
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String homePage(Model model){
        model.addAttribute("sidebar", "student/student-sidebar");


        return "home";
    }

    @RequestMapping("/student")
    public String studenPage(Model model){
        model.addAttribute("sidebar", "student/student-sidebar");

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
        model.addAttribute("content", "student/student-profile");
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
       // model.addAttribute("content", "student/xemlaivay");
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
        // model.addAttribute("content", "student/xemlaivay");
        model.addAttribute("sidebar", "admin/admin-sidebar");

        return "home";
    }
}
