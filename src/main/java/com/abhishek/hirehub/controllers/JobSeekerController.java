package com.abhishek.hirehub.controllers;

import com.abhishek.hirehub.models.Post;
import com.abhishek.hirehub.services.JobSeekerService;
import com.abhishek.hirehub.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/applicant")
public class JobSeekerController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private SearchService searchService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = jobSeekerService.seeAllPosts();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("{keyword}")
    public ResponseEntity<List<Post>> getSearchedPosts(@PathVariable String keyword) {
        List<Post> posts = searchService.findByKeyword(keyword);
        if(!posts.isEmpty()) return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
        else {
            return new ResponseEntity<List<Post>>(posts, HttpStatus.NOT_FOUND);
        }
    }
}
