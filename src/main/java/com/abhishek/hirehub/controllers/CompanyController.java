package com.abhishek.hirehub.controllers;

import com.abhishek.hirehub.models.Post;
import com.abhishek.hirehub.services.CompanyService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class CompanyController {

    @Autowired
    private CompanyService postService;

    // Create a new post
    @PostMapping
    public ResponseEntity<Post> sendPost(@RequestBody Post post) {
        Post savedPost = postService.sendPost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }

    // Get all posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    // Get post by ID
    @GetMapping("id/{myId}")
    public ResponseEntity<Post> getPostById(@PathVariable ObjectId myId) {
        Optional<Post> post = postService.getPostById(myId);
        if (post.isPresent()) {
            return new ResponseEntity<Post>(post.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
    }

    // Update post by ID
    @PutMapping("id/{myId}")
    public ResponseEntity<Post> updatePost(@PathVariable ObjectId myId, @RequestBody Post newPost) {
        Optional<Post> oldPostOptional = postService.getPostById(myId);

        if (oldPostOptional.isPresent()) {
            Post oldPost = oldPostOptional.get();

            oldPost.setProfile(newPost.getProfile());
            oldPost.setDescription(newPost.getDescription());
            oldPost.setExperience(newPost.getExperience());
            oldPost.setTechs(newPost.getTechs());

            // Save updated post
            Post updatedPost = postService.sendPost(oldPost);
            return new ResponseEntity<Post>(updatedPost, HttpStatus.OK);
        }

        return new ResponseEntity<Post>(HttpStatus.NOT_FOUND); // Post not found
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<Post> deletePost(@PathVariable ObjectId myId) {
        if(postService.deletePost(myId)) {
            return new ResponseEntity<Post>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
    }

}
