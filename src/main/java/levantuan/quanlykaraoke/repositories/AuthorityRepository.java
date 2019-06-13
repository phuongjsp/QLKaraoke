package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    List<Authority> findAllByUsername(String username);

    @Transactional
    void deleteAuthoritiesByUsernameAndAuthority(String username, String authority);

    boolean existsByUsernameAndAuthority(String username, String role);
}
