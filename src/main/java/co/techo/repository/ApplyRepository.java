package co.techo.repository;

import co.techo.entity.applies.ApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<ApplyEntity, Long> {
    String SQL_SELECT_ALL = "select * from applies a where a.member_id = 66";

    @Modifying
    @Transactional
    @Query(value = SQL_SELECT_ALL, nativeQuery = true)
    List<Object> selectAll();
}
