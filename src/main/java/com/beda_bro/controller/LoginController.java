package com.beda_bro.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    /*@PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        if(username.equals("ashok") && password.equals("123")){

            session.setAttribute("station","Ashok Nagar Police Station");

            return "redirect:/dashboard";
        }

        if(username.equals("cubbon") && password.equals("123")){

            session.setAttribute("station","Cubbon Park Police Station");

            return "redirect:/dashboard";
        }

        if(username.equals("upparpet") && password.equals("123")){

            session.setAttribute("station","Upparpet Police Station");

            return "redirect:/dashboard";
        }

        if(username.equals("jayanagar") && password.equals("123")){

            session.setAttribute("station","Jayanagar Police Station");

            return "redirect:/dashboard";
        }

        model.addAttribute("error","Invalid Username or Password");

        return "login";

    }*/

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        if(username.equals("admin") && password.equals("123")){

            session.setAttribute("user", "Administrator");
            session.setAttribute("role", "ADMIN");

            return "redirect:/dashboard";
        }

        model.addAttribute("error","Invalid Username or Password");

        return "login";
    }

}