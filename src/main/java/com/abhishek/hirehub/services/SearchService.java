package com.abhishek.hirehub.services;

import com.abhishek.hirehub.models.Post;
import com.mongodb.client.MongoClient;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import com.abhishek.hirehub.repositories.SearchRepo;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.bson.Document;

@Service
public class SearchService implements SearchRepo {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByKeyword(String keyword) {
        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("hirehubdb");
        MongoCollection<Document> collection = database.getCollection("post");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                        new Document("query", keyword)
                        .append("path", Arrays.asList("profile", "description", "experience", "techs")))),
                        new Document("$sort",
                        new Document("exp", 1L)),
                        new Document("$limit", 5L)));

        result.forEach(doc -> posts.add(converter.read(Post.class,doc)));

        return posts;
    }
}
