package com.abhishek.hirehub.services;

import com.abhishek.hirehub.models.Post;
import com.abhishek.hirehub.repositories.JobSeekerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerService {

    @Autowired
    private JobSeekerRepo jobSeekerRepo;

    public List<Post> seeAllPosts() {
        return jobSeekerRepo.findAll();
    }
}
