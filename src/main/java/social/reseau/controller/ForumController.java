package social.reseau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/forum")
public class ForumController {

    @GetMapping
    public ModelAndView getPosts() {
        String uri = "http://172.16.1.168:8081/api/posts?pageStart=0&size=10";
        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.getForObject(uri, Object.class);
        System.out.println(result);
        ModelAndView mv = new ModelAndView("forum");
        mv.addObject("posts", result);
        return mv;
    }

    @PostMapping("/addPost")
    public ModelAndView savePerson(@RequestBody Object post) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://172.16.1.168:8081/api/posts?pageStart=0&size=10";
//        Object result = restTemplate.postForObject(uri);

        return null;

    }
}
