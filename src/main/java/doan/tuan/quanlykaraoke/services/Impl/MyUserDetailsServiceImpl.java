package doan.tuan.quanlykaraoke.services.Impl;

import doan.tuan.quanlykaraoke.model.Authorities;
import doan.tuan.quanlykaraoke.model.User;
import doan.tuan.quanlykaraoke.repositories.AuthoritiesRepository;
import doan.tuan.quanlykaraoke.repositories.UsersRepository;
import doan.tuan.quanlykaraoke.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findUsersByUsername(username);
        org.springframework.security.core.userdetails.User.UserBuilder builder;
        if (user != null) {

            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.disabled(!user.isEnabled());
            builder.password(usersRepository.getPasswordByUsername(username));
            String[] authorities = authoritiesRepository.findAllByUsername(username).stream()
                    .map(Authorities::getAuthority).toArray(String[]::new);

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
}
