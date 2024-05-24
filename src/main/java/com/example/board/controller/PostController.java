package com.example.board.controller;

import com.example.board.model.Post;
import com.example.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 5;
        int offset = (page - 1) * pageSize;
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Optional<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id, @RequestParam String password) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent() && post.get().getPassword().equals(password)) {
            postService.deletePost(id);
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    }

    @GetMapping("/updateform")
    public String showUpdateForm(@RequestParam Long id, Model model) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get()); // Post 객체를 "post" 이름으로 모델에 추가
            return "updateform"; // updateform.html 템플릿으로 포워딩
        } else {
            // 게시글을 찾을 수 없는 경우 예외 처리
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + id);
        }
    }
    @GetMapping("/writeform")
    public String showWriteForm(Model model) {
        model.addAttribute("post", new Post()); // Post 객체를 생성하여 "post"라는 이름으로 모델에 추가
        return "writeform"; // writeform.html 템플릿으로 포워딩
    }

}
