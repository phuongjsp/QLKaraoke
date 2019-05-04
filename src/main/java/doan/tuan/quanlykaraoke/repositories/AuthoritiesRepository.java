package doan.tuan.quanlykaraoke.repositories;

import doan.tuan.quanlykaraoke.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
    List<Authorities> findAllByUsername(String username);

    @Transactional
    void deleteAuthoritiesByUsernameAndAuthority(String username, String authority);

    boolean existsByUsernameAndAuthority(String username, String auth);
}
