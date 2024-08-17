package com.example.postsorm.controller;



import com.example.postsorm.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller()
@PropertySource("classpath:upload_file.properties")
public class IndexController {
    @Autowired
    private IPostService postService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "index";
    }
}