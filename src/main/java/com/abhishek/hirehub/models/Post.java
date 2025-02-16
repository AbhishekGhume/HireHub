package com.abhishek.hirehub.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String profile;
    @NonNull
    private String description;
    @NonNull
    private String experience;
    @NonNull
    private String[] techs;
}
