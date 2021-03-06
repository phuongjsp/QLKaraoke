package levantuan.quanlykaraoke.service.Impl;


import levantuan.quanlykaraoke.entities.Authority;
import levantuan.quanlykaraoke.entities.NhanVien;
import levantuan.quanlykaraoke.entities.User;
import levantuan.quanlykaraoke.repositories.AuthorityRepository;
import levantuan.quanlykaraoke.repositories.NhanVienRepository;
import levantuan.quanlykaraoke.repositories.UserRepository;
import levantuan.quanlykaraoke.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

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

    @Override
    public Long newNhanVien(String username, String password, String fullname, String address, String sdt, String cmnd, String ngaySinh, String gioiTinh) {
        if (usersRepository.existsByUsername(username)) {
            return 0L;
        }
        NhanVien nhanVien = new NhanVien();
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        nhanVien.setUsername(username);
        nhanVien.setTenNhanVien(fullname);
        nhanVien.setDiaChi(address);
        nhanVien.setSoDienThoai(sdt);
        nhanVien.setCmnd(cmnd);
        nhanVien.setTinhTrang(true);
        try {
            nhanVien.setNgaySinh(new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        nhanVien.setGioiTinh(gioiTinh.equals("nam"));
        nhanVien = nhanVienRepository.save(nhanVien);
        user.setNhanVien(nhanVien);
        usersRepository.save(user);
        return nhanVien.getId();
    }

    @Override
    public boolean updateNhanVien(Long id,String password, String fullname,String address, String sdt, String cmnd, String ngaySinh, String gioiTinh) {
        Optional<NhanVien> nhanVienOptional = nhanVienRepository.findById(id);
        if (!nhanVienOptional.isPresent()) return false;
        NhanVien nhanVien =nhanVienOptional.get();
        User user = usersRepository.findByUsername(nhanVien.getUsername());
        user.setPassword(passwordEncoder.encode(password));
        if (fullname != null && !fullname.equals("")) nhanVien.setTenNhanVien(fullname);
        if (sdt != null && !sdt.equals("")) nhanVien.setSoDienThoai(sdt);
        if (cmnd != null && !cmnd.equals("")) nhanVien.setCmnd(cmnd);
        if (address != null && !address.equals("")) nhanVien.setDiaChi(address);
        try {
            nhanVien.setNgaySinh(new SimpleDateFormat("dd/MM/yyyy").parse(ngaySinh));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (gioiTinh != null && !gioiTinh.equals("")) nhanVien.setGioiTinh(gioiTinh.equals("nam"));
        nhanVien = nhanVienRepository.save(nhanVien);
        user.setNhanVien(nhanVien);
        usersRepository.save(user);
        return true;
    }

    @Override
    public NhanVien getNhanVien(Long id) {
        return nhanVienRepository.findById(id).orElse(new NhanVien());
    }

    @Override
    public Long getIdByUserName(String username) {
        return nhanVienRepository.findByUsername(username).getId();
    }
}
