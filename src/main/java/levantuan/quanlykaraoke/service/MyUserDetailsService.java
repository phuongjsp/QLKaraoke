package levantuan.quanlykaraoke.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface MyUserDetailsService extends UserDetailsService {

    List<String> getAllUsername();

    boolean registerUser(String username, String password, List<String> roles);

    List<String> getAllRoles(String username);

    boolean updateRole(String username, List<String> roles);

    boolean updatePassword(String username, String oldPassword, String newPassword);
}
