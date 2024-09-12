package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.dataprovider.user.entity.RoleEntity;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;
import com.dvmrabelo.personal_finances.dataprovider.user.repository.UserRepository;
import com.dvmrabelo.personal_finances.entrypoint.api.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity createUser(User user) {
        if (userRepository.findByUsername(user.username()).isPresent()) {
            throw new RuntimeException("Username já existe.");
        }

        RoleEntity role = new RoleEntity();
        role.setName("ROLE_USER");

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.username());
        userEntity.setPassword(passwordEncoder.encode(user.password()));
        userEntity.setEnabled(true);
        userEntity.getRoleEntities().add(role);

        return userRepository.save(userEntity);
    }
}
