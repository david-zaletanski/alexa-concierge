package net.dzale.alexa.concierge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.dzale.alexa.concierge.model.AlexaConciergeUser;
import net.dzale.alexa.concierge.model.entity.AlexaConciergeUserEntity;
import net.dzale.alexa.concierge.model.repository.AlexaConciergeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Provides access to and management for users of the concierge service.
 *
 * @author dzale
 */
@Service
public class ConciergeUserService {

    @Autowired
    AlexaConciergeUserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper objectMapper;

    public boolean addUser(AlexaConciergeUser user) {

        // Hash User's Password for DB Insertion
        String userPassword = user.getPassword();
        String hashedUserPassword = passwordEncoder.encode(userPassword);
        user.setPassword(hashedUserPassword);

        // Fill in other information
        Date created = new Date();
        user.setCreationDate(created);
        user.setLastLoginDate(created);

        AlexaConciergeUserEntity userEntity = objectMapper.convertValue(user, AlexaConciergeUserEntity.class);
        userRepository.save(userEntity);

        return userEntity.getId() != null;
    }

    public List<AlexaConciergeUser> getUsers() {
        List<AlexaConciergeUser> users = new ArrayList<>();
        for (AlexaConciergeUserEntity user : userRepository.findAll()) {
            users.add(objectMapper.convertValue(user, AlexaConciergeUser.class));
        }
        return users;
    }

    public AlexaConciergeUser getUserByUsername(String username) {
        AlexaConciergeUserEntity userEntity = userRepository.findOneByUsername(username);
        if (userEntity == null || userEntity.getId() == null)
            return null;

        AlexaConciergeUser user = objectMapper.convertValue(userEntity, AlexaConciergeUser.class);
        return user;
    }

    public AlexaConciergeUser getUser(Long userId) {
        AlexaConciergeUserEntity userEntity = userRepository.findOneById(userId);
        if (userEntity == null || userEntity.getId() == null)
            return null;

        AlexaConciergeUser user = objectMapper.convertValue(userEntity, AlexaConciergeUser.class);
        return user;
    }

}
