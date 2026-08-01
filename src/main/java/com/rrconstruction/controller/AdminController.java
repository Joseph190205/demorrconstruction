package com.rrconstruction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rrconstruction.entity.Admin;
import com.rrconstruction.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @GetMapping("/login")
    public String loginPage() {
        return "adminLogin";
    }
    @GetMapping("/logout")
public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/admin/login";
}
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        Admin admin = service.login(username,password);

        if(admin!=null){

            session.setAttribute("admin", admin.getUsername());

            return "redirect:/viewContacts";

        }

        model.addAttribute("error","Invalid Username or Password");

        return "adminLogin";
    }

}