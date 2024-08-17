package com.example.postsorm.controller;


import com.example.postsorm.model.Post;
import com.example.postsorm.model.PostForm;
import com.example.postsorm.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller()
@RequestMapping("/posts")
@PropertySource("classpath:upload_file.properties")
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("post", new Post());
        return "create";
    }

    @Value("${file-upload}")
    private String upload;

    @PostMapping("/save")
    public String save(PostForm postForm , RedirectAttributes redirectAttributes) {
        MultipartFile multipartFile = postForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(upload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setDescription(postForm.getDescription());
        post.setContent(postForm.getContent());
        post.setImage(fileName);
        postService.save(post);
        redirectAttributes.addFlashAttribute("success", "Post created successfully");
        return "redirect:/posts/";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "/update";
    }


    @PostMapping("/update")
    public String update(PostForm postForm, RedirectAttributes redirectAttributes) {
        MultipartFile multipartFile = postForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(upload+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Post post = postService.findById(postForm.getId());
        post.setTitle(postForm.getTitle());
        post.setDescription(postForm.getDescription());
        post.setContent(postForm.getContent());
        post.setImage(fileName);
        postService.save(post);
        redirectAttributes.addFlashAttribute("success", "Post updated successfully");
        return "redirect:/posts/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(Post post, RedirectAttributes redirectAttributes) {
        postService.delete(post.getId());
        redirectAttributes.addFlashAttribute("success", "Post deleted successfully");
        return "redirect:/posts/";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "view";
    }





}
