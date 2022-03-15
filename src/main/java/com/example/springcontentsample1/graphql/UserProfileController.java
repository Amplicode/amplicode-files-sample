package com.example.springcontentsample1.graphql;

import com.example.springcontentsample1.entity.UserProfile;
import com.example.springcontentsample1.repository.UserProfileRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
public class UserProfileController {
    private final UserProfileRepository crudRepository;

    public UserProfileController(UserProfileRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @MutationMapping(name = "deleteUserProfile")
    @Transactional
    public void delete(@Argument Long id) {
        UserProfile entity = crudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }

    @QueryMapping(name = "listUserProfiles")
    @Transactional
    public List<UserProfile> findAll() {
        return crudRepository.findAll();
    }

    @QueryMapping(name = "findUserProfile")
    @Transactional
    public UserProfile findById(@Argument Long id) {
        return crudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Unable to find entity by id: %s ", id)));
    }

    @MutationMapping(name = "updateUserProfile")
    @Transactional
    public UserProfile update(@Argument UserProfile input) {
        if (input.getId() != null) {
            if (!crudRepository.existsById(input.getId())) {
                throw new RuntimeException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }
        return crudRepository.save(input);
    }

    @QueryMapping(name = "countUserProfiles")
    @Transactional
    public long count() {
        return crudRepository.count();
    }
}