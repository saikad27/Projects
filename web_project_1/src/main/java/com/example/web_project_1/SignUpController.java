package com.example.web_project_1;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SignUpController {
    @GetMapping("/")
    public String signUp(){
        return "signup.html";
    }
    @PostMapping("/")
    public String signUp(@RequestParam String username
                         , @RequestParam String email, @RequestParam String mobile
                        , @RequestParam String password1, @RequestParam String password2, Model model){


        if(mobile.length()!=10){
            model.addAttribute("message","Mobile no. should contain exactly 10 digits!");
            return "signup.html";
        }
        if(!password1.equals(password2)){
            model.addAttribute("message","Both passwords should be identical, please try again!");
            return "signup.html";
        }
        if(Database.emailAlreadyExists(email)){
            model.addAttribute("message","Email already exists in the database, please try a different email or login instead!");
            return "signup.html";
        }
        long mobileNo = Long.parseLong(mobile);
        model.addAttribute("username",username);
        Database.register(username,email,mobileNo,password1);
        return "login.html";
    }
}
