package doan.tuan.quanlykaraoke.services.Impl;

import doan.tuan.quanlykaraoke.model.Authorities;
import doan.tuan.quanlykaraoke.repositories.AuthoritiesRepository;
import doan.tuan.quanlykaraoke.services.AuthorityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthoritiesRepository authoritiesRepository;

    @Override
    public List<String> getAllAuthoritiesByUsername(String username) {
        return authoritiesRepository.findAllByUsername(username).stream().map(Authorities::getAuthority)
                .collect(Collectors.toList());
    }

    @Override
    public boolean setAuthoritiesForUser(String username, List<String> authorities) {
        try {
            List<String> authOld = getAllAuthoritiesByUsername(username);
            List<String> authRemove = new ArrayList<>(authOld);
            authRemove.removeAll(authorities);

            authRemove.forEach(auth -> authoritiesRepository.deleteAuthoritiesByUsernameAndAuthority(username, auth));
            authorities.removeAll(authOld);
            authorities.forEach(auth -> authoritiesRepository.save(new Authorities(username, auth)));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAllAuthoritiesOfUsername(String username) {
        authoritiesRepository.findAllByUsername(username).forEach(authoritiesRepository::delete);
        return true;
    }
}
