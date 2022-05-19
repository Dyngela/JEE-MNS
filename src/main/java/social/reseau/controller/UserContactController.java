package social.reseau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/userContact")
public class UserContactController {

    @GetMapping
    public ModelAndView getContacts(String userId) {
        String uri = "http://172.16.1.168:8081/api/usersContact/{userId}";
        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.getForObject(uri, Object.class);
        System.out.println(result);
        ModelAndView mv = new ModelAndView("userContact");
        mv.addObject("posts", result);
        return mv;
    }
}
