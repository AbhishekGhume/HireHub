package com.abhishek.hirehub.repositories;

import com.abhishek.hirehub.models.Post;

import java.util.List;

public interface SearchRepo {

    List<Post> findByKeyword(String keyword);
}
