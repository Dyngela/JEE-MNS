package social.reseau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import social.reseau.model.Comment;
import social.reseau.model.Post;

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
        System.out.println(post.toString());
//        Object result = restTemplate.postForObject(uri, post, Post.class);
        return init();
    }

    @PostMapping("/addComment")
    public ModelAndView saveComment(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://127.0.0.1:8081/api/comments";
        System.out.println(comment.getContent());
        System.out.println(comment.getIdUser());
        System.out.println(comment.getIdMessage());
        System.out.println(comment);
//        Object result = restTemplate.postForObject(uri, comment, Comment.class);
        return init();
    }

    public ModelAndView init(){
        String uri = "http://127.0.0.1:8081/api/posts?pageStart=0&size=10";
        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.getForObject(uri, Object.class);
        ModelAndView mv = new ModelAndView("forum");
        mv.addObject("posts", result);
        mv.addObject("newPost", new Post());
        mv.addObject("newComment", new Comment());
        return mv;
    }
}
