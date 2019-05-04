package doan.tuan.quanlykaraoke.services;


import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize(value = "@permissionEvaluator.hasRealTime(authentication,'ADMIN')")
public interface AuthorityService {
    List<String> getAllAuthoritiesByUsername(String username);

    boolean setAuthoritiesForUser(String username, List<String> authorities);

    boolean deleteAllAuthoritiesOfUsername(String username);
}
