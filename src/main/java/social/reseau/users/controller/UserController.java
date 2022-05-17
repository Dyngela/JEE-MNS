package social.reseau.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import social.reseau.users.form.UserForm;
import social.reseau.users.model.Gender;
import social.reseau.users.model.User;
import social.reseau.users.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> getMe(){
        return ResponseEntity.ok(userService.getAuthenticatedUser());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ModelAndView getAll(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize) {
        ModelAndView mv = new ModelAndView("users");
        mv.addObject("paginatedUsers", userService.getAll(pageNumber, pageSize));
        return mv;
    }

    @GetMapping("/add")
    public String userForm(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "useradd";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute("userForm") UserForm userForm) {
        System.out.println(userForm);
        String firstName = userForm.getFirstName();
        String lastName = userForm.getLastName();
        String errorMessage = "";

        if (firstName != null && firstName.length() > 0
                && lastName != null && lastName.length() > 0) {
            User user = new User();
            user.setFirstName(userForm.getFirstName());
            user.setLastName(userForm.getLastName());
            if (userForm.getId() != null) {
                user.setId(userForm.getId());
            }
            try {
                user.setBirthDate(LocalDate.of(
                        Integer.parseInt(userForm.getBirthDateYear()),
                        Integer.parseInt(userForm.getBirthDateMonth()),
                        Integer.parseInt(userForm.getBirthDateDay()))
                );
            } catch (Exception e) {

            }
            user.setGender(Gender.valueOf(userForm.getGender()));
            user.setPhoneNumber(userForm.getPhoneNumber());
            user.setEmail(userForm.getEmail());
            user.setUsername(userForm.getUsername());
            user.setPassword(userForm.getPassword());

            if (user.getId() == null) {
                userService.create(user);
            } else {
                userService.update(user);
            }

            return "redirect:/users/all?pageNumber=0&pageSize=20";
        } else {
            errorMessage = "Le nom et le prénom doivent être renseignés";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "useradd";
    }

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable("id") Long id) {
        if (id != null) {
            ModelAndView mv = new ModelAndView("useredit");
            User user = userService.getById(id);
            UserForm userForm = new UserForm();
            userForm.setId(user.getId());
            userForm.setFirstName(user.getFirstName());
            userForm.setLastName(user.getLastName());
            userForm.setBirthDateDay(user.getBirthDate().toString().split("-")[2]);
            userForm.setBirthDateMonth(user.getBirthDate().toString().split("-")[1]);
            userForm.setBirthDateYear(user.getBirthDate().toString().split("-")[0]);
            userForm.setGender(user.getGender().toString());
            userForm.setPhoneNumber(user.getPhoneNumber());
            userForm.setEmail(user.getEmail());
            userForm.setUsername(user.getUsername());
            userForm.setPassword(user.getPassword());
            mv.addObject("userForm", userForm);
            return mv;
        } else {
            return getAll(0, 10);
        }
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (id != null) {
            userService.delete(id);
        }
        return "redirect:/users/all?pageNumber=0&pageSize=20";
    }

}
