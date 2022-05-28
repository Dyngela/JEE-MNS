package social.reseau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/userContact")
public class UserContactController {

    @GetMapping("/{id}")
    public ModelAndView getContacts(@PathVariable("id") Long id) {
        String uri = "http://localhost:8082/userscontact/"+id;
        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.getForObject(uri, Object.class);
        System.out.println(result);
        ModelAndView mv = new ModelAndView("userContact");
        mv.addObject("contacts", result);
        mv.addObject("userId", id);
        return mv;
    }
    
    @GetMapping("/del/{id}")
    public ModelAndView delContacts(@PathVariable("id") Long id) {
        String uri = "http://localhost:8082/userscontact/del/"+id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(uri, Object.class);
        ModelAndView mv = new ModelAndView("userContact");
        mv.addObject("confirmation", "ok");
        return mv;
    }
}
