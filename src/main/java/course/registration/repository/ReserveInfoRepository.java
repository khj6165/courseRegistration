package course.registration.repository;

import course.registration.domain.ReserveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface ReserveInfoRepository extends JpaRepository<ReserveInfo, Long> {
    List<ReserveInfo> findByUserId(Long userId);
}