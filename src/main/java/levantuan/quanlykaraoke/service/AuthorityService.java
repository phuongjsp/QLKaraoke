package levantuan.quanlykaraoke.service;


import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface AuthorityService {
    List<String> getAllAuthoritiesByUsername(String username);

    boolean setAuthoritiesForUser(String username, List<String> authorities);

    boolean deleteAllAuthoritiesOfUsername(String username);
}
