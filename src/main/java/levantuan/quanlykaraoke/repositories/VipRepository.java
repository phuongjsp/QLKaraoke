package levantuan.quanlykaraoke.repositories;

import levantuan.quanlykaraoke.entities.Vip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VipRepository extends JpaRepository<Vip,Long> {
}
