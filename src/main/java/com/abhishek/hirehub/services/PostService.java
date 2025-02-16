package com.abhishek.hirehub.services;

import com.abhishek.hirehub.models.Post;
import com.abhishek.hirehub.repositories.PostRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    public Post sendPost(Post post) {
        return postRepo.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public Optional<Post> getPostById(ObjectId myId) {
        return postRepo.findById(myId);
    }

    public boolean deletePost(ObjectId myId) {
        Optional<Post> post = getPostById(myId);

        if(post.isPresent()) {
            postRepo.deleteById(myId);
            return true;
        }

        return false;
    }
}
