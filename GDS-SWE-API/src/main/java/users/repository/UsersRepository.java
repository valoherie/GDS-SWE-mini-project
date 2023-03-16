package users.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import users.entity.UsersEntity;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    UsersEntity findByName(String name);

    @Query(value="select * from users WHERE (:min is null or users.salary >= :min) AND (:max is null or users.salary <= :max)", nativeQuery = true)
    Page<UsersEntity> findAll(@Param("min") Float min, @Param("max") Float max, Pageable pageable);

}
