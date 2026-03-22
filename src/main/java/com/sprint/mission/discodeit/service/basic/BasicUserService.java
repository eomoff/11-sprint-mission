package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class BasicUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(String name) {
        User user = new User(name);
        return userRepository.save(user);
    }

    @Override
    public User find(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(UUID id, String naem) {
        User user = userRepository.findById(id);
        if (user != null) {
            user.update(naem);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(id);
    }
}
