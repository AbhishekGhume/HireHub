package com.abhishek.hirehub.repositories;

import com.abhishek.hirehub.models.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobSeekerRepo extends MongoRepository<Post, ObjectId> {
}
