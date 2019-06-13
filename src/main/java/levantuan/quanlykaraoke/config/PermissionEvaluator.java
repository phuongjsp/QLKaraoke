package levantuan.quanlykaraoke.config;

import levantuan.quanlykaraoke.entities.Authority;
import levantuan.quanlykaraoke.entities.User;
import levantuan.quanlykaraoke.repositories.AuthorityRepository;
import levantuan.quanlykaraoke.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PermissionEvaluator implements org.springframework.security.access.PermissionEvaluator {

    private final UserRepository usersRepository;
    private final AuthorityRepository authorityRepository;


    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }

    public boolean hasRealTime(Authentication authentication, String role) {
        List<String> roleList = authorityRepository.findAllByUsername(authentication.getName()).stream()
                .map(Authority::getAuthority).collect(Collectors.toList());
        for (String r : roleList) {
            if (r.equals(role)) return true;
        }
        return false;
    }

    public boolean hasPermissionForUser(Authentication authentication, String username) {
        if (username == null) return false;
        if (!usersRepository.existsByUsername(username)) {
            throw new EntityNotFoundException(
                    String.format("User with username %s not found", username));
        }
        User authenticationUser = usersRepository.findByUsername(authentication.getName());
        return Objects.equals(authenticationUser.getUsername(), username);
    }


}
