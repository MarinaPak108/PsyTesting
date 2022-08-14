package com.psytest.controller;

import com.psytest.entity.TeacherEntity;
import com.psytest.entity.UserEntity;
import com.psytest.exception.TeacherNotFoundException;
import com.psytest.service.UserService;
import com.psytest.web.CreateTeacherFromData;
import com.psytest.web.CreateUserFormData;
import com.psytest.web.CreateUserLoginFormData;
import com.psytest.web.TeacherAssignFormData;
import com.psytest.web.form.UserLoginParameters;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Base64;

@Controller
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String showLoginUserForm(Model model) {
        model.addAttribute("formData", new CreateUserLoginFormData());
        return "users/login";
    }

    @GetMapping("/home")
    public String doLoginUser(Principal principal, RedirectAttributes atts) {
        String[] authCredentials = ((String) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).split("_");


        String result = null;
        String login = authCredentials[0];
        String password = authCredentials[1];
        String log = Base64.getEncoder().encodeToString(login.getBytes());

        if (login.equals("admin") || password.equals("admin")) {
            result = "redirect:/home/admin";
        } else if (login.equals("newUser") || password.equals("newUser")) {
            atts.addAttribute("login", login);
            result = "redirect:/profileTest";
        } else {
            String check = service.checkUser(new UserLoginParameters(login, password));
            if (check.equals("user"))
                if (password.equals(service.getUser(login).getPassword())) {
                    atts.addAttribute("login", log);
                    result = "redirect:/profile";
                } else {
                    if (password.equals(service.getUser(login).getPassword())) {
                        atts.addAttribute("login", log);
                        result = "redirect:/teacher";
                    }
                }
        }
        return result;
    }

    @GetMapping("/profile")
    public String profileUser(@RequestParam("login") String login, Model model) {
        byte[] decodedBytes = Base64.getDecoder().decode(login);
        String decodedLogin = new String(decodedBytes);
        model.addAttribute("user", service.getUser(decodedLogin));
        model.addAttribute("age", service.getUserAge(decodedLogin));
        model.addAttribute("teacher", service.getTeacherByUser(decodedLogin));
        return "users/profile";
    }

    @GetMapping("/profileTest")
    public String profileUserTest(@RequestParam("login") String login, Model model) {
        byte[] decodedBytes = Base64.getDecoder().decode(login);
        String decodedLogin = new String(decodedBytes);
        model.addAttribute("user", service.getUser(decodedLogin));
        if (service.getUser(decodedLogin).getTeacher() == null) {
            throw new TeacherNotFoundException();
        }
        return "users/profile";
    }


    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("formData", new CreateUserFormData());
        return "users/create";
    }

    @PostMapping("/create")
    public String doCreateUser(@Valid @ModelAttribute("formData") CreateUserFormData formData,
                               BindingResult bindingResult,
                               Model model, RedirectAttributes atts) {
        boolean check = service.isUserExist(formData.toParameters().getLogin());
        boolean checkT = service.isTeacherExist(formData.toParameters().getLogin());
        if (bindingResult.hasErrors()) {
            return "users/create";
        } else if (check || checkT) {
            model.addAttribute("error", "user name " + formData.getLogin() + " already exists");
            return "users/create";
        }
        String login = Base64.getEncoder().encodeToString(formData.getLogin().getBytes());
        service.createUser(formData.toParameters());
        atts.addAttribute("login", login);
        return "redirect:/profile";
    }

    @GetMapping("/home/admin")
    public String adminPage(Model model) {
        model.addAttribute("teachers", service.getTeachers());
        model.addAttribute("users", service.getUsersWithTeachers());
        return "users/admin";
    }


    @GetMapping("/home/admin/createT")
    public String showCreateTeacherForm(Model model) {
        model.addAttribute("formDataT", new CreateTeacherFromData());
        return "users/createt";
    }


    @PostMapping("/home/admin/createT")
    public String doCreateTeacher(@Valid @ModelAttribute("formDataT") CreateTeacherFromData formData,
                                  BindingResult bindingResult,
                                  Model model, RedirectAttributes atts) {
        boolean check = service.isTeacherExist(formData.toTeacherParamaters().getLogin());
        boolean checkT = service.isUserExist(formData.toTeacherParamaters().getLogin());
        String result = "null";
        if (bindingResult.hasErrors()) {
            result = "users/createt";
        } else if (check || checkT) {
            model.addAttribute("error", "login " + formData.getLogin() + " already exists");
            result = "users/createt";
        } else if (!check || !checkT) {
            String login = Base64.getEncoder().encodeToString(formData.getLogin().getBytes());
            service.createTeacher(formData.toTeacherParamaters());
            atts.addAttribute("login", login);
            result = "redirect:/teacher";
        }
        return result;
    }

    @GetMapping("/home/admin/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        TeacherEntity teacher = service.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "users/updatet";
    }


    @GetMapping("/home/admin/showFormForUpdateUser/{id}")
    public String updateFormUser(@PathVariable(value = "id") long id, Model model) {
        UserEntity user = service.getUserById(id);
        model.addAttribute("user", user);
        return "users/update";
    }

    @PostMapping("/home/admin/saveT")
    public String saveTeacher(@ModelAttribute("teacher") TeacherEntity teacher) {
        service.saveTeacher(teacher);
        return "redirect:/home/admin";
    }

    @PostMapping("/home/admin/saveU")
    public String saveUser(@ModelAttribute("user") UserEntity user) {
        service.saveUser(user);
        return "redirect:/home/admin";
    }

    @GetMapping("/home/admin/deleteTeacher/{id}")
    public String deleteTeacherById(@PathVariable(value = "id") long id) {
        service.deleteTeacherById(id);
        return "redirect:/home/admin";

    }

    @GetMapping("/home/admin/deleteUser/{id}")
    public String deleteUserById(@PathVariable(value = "id") long id) {
        service.deleteUserById(id);
        return "redirect:/home/admin";

    }


    @GetMapping("/teacher")
    public String profileTeacher(@RequestParam("login") String login, Model model) {
        byte[] decodedBytes = Base64.getDecoder().decode(login);
        String decodedLogin = new String(decodedBytes);

        model.addAttribute("teacher", service.getTeacher(decodedLogin));
        return "users/teacher";
    }

    @GetMapping("/home/admin/assignT")
    public String assignTeacher(@ModelAttribute("formDataA") TeacherAssignFormData formDataA, Model model) {
        model.addAttribute("formDataA", new TeacherAssignFormData());
        model.addAttribute("teachers", service.getCurator());
        model.addAttribute("users", service.getUsersWithoutTeachers());
        return "users/assignt";
    }

    @PostMapping("/home/admin/assignT")
    public String doAssignTeacher(@Valid @ModelAttribute("formDataA") TeacherAssignFormData formDataA) {
        service.assignTeacher(formDataA.getUser(), formDataA.getTeacher());
        return "redirect:/home/admin";
    }
}
