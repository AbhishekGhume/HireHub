package com.abhishek.hirehub.repositories;

import com.abhishek.hirehub.models.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepo extends MongoRepository<Post, ObjectId> {

}
