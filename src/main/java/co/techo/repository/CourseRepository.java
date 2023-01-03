package co.techo.repository;

import co.techo.entity.courses.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    String SQL_CHECK_COURSE_TYPE = "SELECT * from courses c WHERE c.id = :id";

    @Transactional
    @Query(value = SQL_CHECK_COURSE_TYPE, nativeQuery = true)
    CourseEntity findCourseType(@Param("id") int course_id);

    @Transactional
    @Query(value = SQL_CHECK_COURSE_TYPE, nativeQuery = true)
    String[][] findCourseType2(@Param("id") int course_id);
}
