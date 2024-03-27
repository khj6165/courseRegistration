package course.registration.repository;

import course.registration.domain.CourseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

public interface CourseInfoRepository extends JpaRepository<CourseInfo, Long> {
    CourseInfo findByCourseDate(String courseDate);
}
