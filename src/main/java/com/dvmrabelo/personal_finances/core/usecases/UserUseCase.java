package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.dataprovider.user.entity.RoleEntity;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;
import com.dvmrabelo.personal_finances.dataprovider.user.repository.UserRepository;
import com.dvmrabelo.personal_finances.entrypoint.api.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserUseCase implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity createUser(User user) {
        if (userRepository.findByUsername(user.username()).isPresent()) {
            throw new RuntimeException("Username já existe.");
        }

        RoleEntity role = new RoleEntity("ROLE_USER");

        UserEntity userEntity = new UserEntity(user.username(), passwordEncoder.encode(user.password()), true, Set.of(role));

        return userRepository.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                user.getAuthorities()
        );
    }
}
