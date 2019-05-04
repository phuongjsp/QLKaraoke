package doan.tuan.quanlykaraoke.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface MyUserDetailsService extends UserDetailsService {


    @PreAuthorize(value = "@permissionEvaluator.hasRealTime(authentication,'ADMIN')")
    List<String> getAllUsername();
}
