package levantuan.quanlykaraoke.service.Impl;


import levantuan.quanlykaraoke.entities.Authority;
import levantuan.quanlykaraoke.repositories.AuthorityRepository;
import levantuan.quanlykaraoke.service.AuthorityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public List<String> getAllAuthoritiesByUsername(String username) {
        return authorityRepository.findAllByUsername(username).stream().map(Authority::getAuthority)
                .collect(Collectors.toList());
    }

    @Override
    public boolean setAuthoritiesForUser(String username, List<String> authorities) {
        try {
            List<String> authOld = getAllAuthoritiesByUsername(username);
            List<String> authRemove = new ArrayList<>(authOld);
            authRemove.removeAll(authorities);

            authRemove.forEach(auth -> authorityRepository.deleteAuthoritiesByUsernameAndAuthority(username, auth));
            authorities.removeAll(authOld);
            authorities.forEach(auth -> authorityRepository.save(new Authority(username, auth)));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAllAuthoritiesOfUsername(String username) {
        authorityRepository.findAllByUsername(username).forEach(authorityRepository::delete);
        return true;
    }
}
