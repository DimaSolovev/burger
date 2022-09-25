package com.example.burger.security;

import com.example.burger.data.User;
import com.example.burger.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(
            UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(RegistrationForm registrationForm) {
        return "registration";
    }

    @PostMapping
    public String processRegistration(
            @ModelAttribute @Valid RegistrationForm form,
            Errors errors,
            @RequestParam String confirm,
            Model model
    ) {
        log.info("saving user: {}", form.getFullname());
        User user = form.toUser(passwordEncoder);
        if (userRepo.findByUsername(user.getUsername()) != null) {
            model.addAttribute("userExist", "User exist");
            return "registration";
        }
        if (errors.hasErrors()) {
            if (!confirm.equals(form.getPassword())) {
                model.addAttribute("passError", "Password are different");
            }
            return "registration";
        }
        userRepo.save(user);
        return "redirect:/login";
    }
}
