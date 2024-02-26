package com.example.ProductManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getHomePage(Model model)
    {
        model.addAttribute("showLogIn" , true);
        return "homePage";
    }


    @GetMapping("/userRegistration")
    public String getRegistrationPage(Model model)
    {
        User user = new User();
        model.addAttribute("user", user);
        return "registrationPage";
    }

    @PostMapping("/addUser")
    public String addUser(User user)
    {
        userService.addUser(user);
        {
            return "redirect:/userRegistration";
        }
    }

    @GetMapping("/login")
        public String getLoginPage()
    {
            return "loginPage";
    }

    @PostMapping("/checkAuthentication")
    public String getUser(@RequestParam String email , @RequestParam String password,Model model)
    {
        boolean isUserExist = userService.authenticate(email, password);
        if(isUserExist)
        {
            model.addAttribute("showLogout" , true);
            return "redirect:viewAllProductsPage";
        }
        else
        {
            return "redirect:/login?error";
        }
    }
}
