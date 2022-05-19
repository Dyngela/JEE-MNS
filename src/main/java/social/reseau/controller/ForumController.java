package social.reseau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import social.reseau.model.Comment;
import social.reseau.model.Post;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/forum")
public class ForumController {

    @GetMapping
    public ModelAndView getPosts() {
        return init();
    }

    @PostMapping
    public ModelAndView savePost(Post post) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://127.0.0.1:8081/api/posts";
        post.setDate(Timestamp.valueOf(LocalDateTime.now()));
        restTemplate.postForEntity(uri, post, Post.class);
        return init();
    }

    @PostMapping("/addComment")
    public ModelAndView saveComment(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://127.0.0.1:8081/api/comments";
        comment.setDate(Timestamp.valueOf(LocalDateTime.now()));
        Object result = restTemplate.postForEntity(uri, comment, Comment.class);
        return init();
    }

    @PostMapping("/deletePost")
    public ModelAndView deletePost(Post post) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://127.0.0.1:8081/api/posts/{id}";
        Map< String, String > params = new HashMap< String, String >();
        params.put("id", post.getIdMessage().toString());
        restTemplate.delete(uri, params);
        return init();
    }

    public ModelAndView init(){
        String uri = "http://127.0.0.1:8081/api/posts?pageStart=0&size=100";
        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.getForObject(uri, Object.class);
        ModelAndView mv = new ModelAndView("forum");
        mv.addObject("iduser", 1);
        mv.addObject("posts", result);
        mv.addObject("newPost", new Post());
        mv.addObject("newComment", new Comment());
        mv.addObject("deletePost", new Post());
        return mv;
    }
}
