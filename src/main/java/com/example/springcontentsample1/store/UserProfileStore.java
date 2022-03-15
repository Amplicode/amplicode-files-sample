package com.example.springcontentsample1.store;

import com.example.springcontentsample1.entity.UserProfile;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;

@StoreRestResource(path = "profiles")
public interface UserProfileStore extends ContentStore<UserProfile, String> {
}
