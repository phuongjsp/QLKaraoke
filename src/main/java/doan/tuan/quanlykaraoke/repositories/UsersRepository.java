package doan.tuan.quanlykaraoke.repositories;

import doan.tuan.quanlykaraoke.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    User findUsersByUsername(String username);

    User getById(int id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into users(username, password, enabled) values (?1,?2,true)")
    void snewuser(String username, String password);

    @Query(nativeQuery = true, value = "select u.password from users u where u.username=?1")
    String getPasswordByUsername(String username);

    boolean existsByUsername(String username);

}
