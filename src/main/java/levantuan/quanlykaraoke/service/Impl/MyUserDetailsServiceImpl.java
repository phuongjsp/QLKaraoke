package levantuan.quanlykaraoke.service.Impl;


import levantuan.quanlykaraoke.entities.Authority;
import levantuan.quanlykaraoke.entities.User;
import levantuan.quanlykaraoke.repositories.AuthorityRepository;
import levantuan.quanlykaraoke.repositories.UserRepository;
import levantuan.quanlykaraoke.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByUsername(username);
        org.springframework.security.core.userdetails.User.UserBuilder builder;
        if (user != null) {

            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.disabled(!user.isEnabled());
            builder.password(user.getPassword());
            String[] authorities = authorityRepository.findAllByUsername(username).stream()
                    .map(Authority::getAuthority).toArray(String[]::new);

            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }

    @Override
    public List<String> getAllUsername() {
        return usersRepository.findAll().stream().map(User::getUsername).collect(Collectors.toList());
    }

    @Override
    public boolean registerUser(String username, String password, List<String> roles) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        usersRepository.save(user);
        roles.forEach(role -> saveAuthority(username, role));
        return false;
    }

    private void saveAuthority(String username, String role) {
        Authority authorities = new Authority();
        authorities.setUsername(username);
        authorities.setAuthority(role);
        authorityRepository.save(authorities);
    }
    @Override
    public List<String> getAllRoles(String username) {
        return authorityRepository.findAllByUsername(username).stream().map(Authority::getAuthority).collect(Collectors.toList());
    }

    @Override
    public boolean updateRole(String username, List<String> roles) {
        List<String> allRoles = getAllRoles(username);
        allRoles.removeAll(roles);
        System.out.println(allRoles);
        allRoles.forEach(role -> authorityRepository.deleteAuthoritiesByUsernameAndAuthority(username, role));
        roles.forEach(role -> {
            if (!authorityRepository.existsByUsernameAndAuthority(username, role))
                saveAuthority(username, role);
        });


        return true;
    }

    @Override
    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        User user = usersRepository.findByUsername(username);
        if (!passwordEncoder.matches(oldPassword, user.getPassword()))
            return false;
        user.setPassword(passwordEncoder.encode(newPassword));
        usersRepository.save(user);
        return true;
    }
}
