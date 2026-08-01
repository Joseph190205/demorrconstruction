package com.rrconstruction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rrconstruction.entity.Contact;
import com.rrconstruction.service.ContactService;
import com.rrconstruction.service.MailService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ContactController {


    @Autowired
    private ContactService service;


    @Autowired
    private MailService mailService;



    @GetMapping("/contact")
    public String contactPage(Model model) {

        model.addAttribute("contact", new Contact());

        return "contact";
    }



    @PostMapping("/saveContact")
    public String save(Contact contact, Model model) {


        service.save(contact);


        model.addAttribute(
            "success",
            "Thank you for contacting us. We will get back to you soon."
        );


        model.addAttribute("contact", new Contact());


        return "contact";
    }





    @GetMapping("/viewContacts")
    public String viewContacts(Model model,
                               HttpSession session) {


        if(session.getAttribute("admin") == null){

            return "redirect:/admin/login";

        }


        model.addAttribute(
            "contacts",
            service.getAll()
        );


        return "viewContacts";
    }





    @GetMapping("/reply/{id}")
    public String replyPage(@PathVariable int id,
                            Model model,
                            HttpSession session) {


        if(session.getAttribute("admin") == null){

            return "redirect:/admin/login";

        }


        Contact contact = service.getById(id);


        model.addAttribute(
            "contact",
            contact
        );


        return "reply";
    }





    @PostMapping("/sendReply")
    public String sendReply(
            @RequestParam int id,
            @RequestParam String reply) {



        Contact contact = service.getById(id);



        // save reply in database

        contact.setReply(reply);

        service.save(contact);



        // send email

        mailService.sendMail(
                contact.getEmail(),
                "Reply from RR Construction",
                reply
        );



        return "redirect:/viewContacts";
    }





    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id,
                         HttpSession session) {


        if(session.getAttribute("admin") == null){

            return "redirect:/admin/login";

        }


        service.delete(id);


        return "redirect:/viewContacts";
    }


}