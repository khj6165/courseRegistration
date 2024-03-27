package course.registration.service;

import course.registration.domain.CourseInfo;
import course.registration.domain.ReserveInfo;
import course.registration.repository.CourseInfoRepository;
import course.registration.repository.ReserveInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegistrationService {

    private final CourseInfoRepository courseInfoRepository;
    private final ReserveInfoRepository reserveInfoRepository;

    public List<CourseInfo> getCoursesByDate(String courseDate) {
        return (List<CourseInfo>) courseInfoRepository.findByCourseDate(courseDate);
    }

    @Transactional
    public String reserveCourse(String courseDate, Long userId) {
        CourseInfo courseInfo = courseInfoRepository.findByCourseDate(courseDate);
        if (courseInfo == null) {
            return "No courses available for the given date";
        }

        List<ReserveInfo> reservations = courseInfo.getReserveInfos();
        if (reservations.size() >= 30) {
            return "Maximum capacity reached for the course on " + courseDate;
        }

        boolean isAlreadyReserved = reservations.stream()
                .anyMatch(reserve -> reserve.getUserId().equals(userId));
        if (isAlreadyReserved) {
            return "User " + userId + " has already reserved the course on " + courseDate;
        }

        ReserveInfo newReservation = new ReserveInfo();
        newReservation.setUserId(userId);
        newReservation.setCourseInfo(courseInfo);
        reserveInfoRepository.save(newReservation);
        return "User " + userId + " has successfully reserved the course on " + courseDate;
    }

    public String getReservationStatus(String courseDate, Long userId) {
        CourseInfo courseInfo = courseInfoRepository.findByCourseDate(courseDate);
        if (courseInfo == null) {
            return "No courses available for the given date";
        }
        List<ReserveInfo> reservations = courseInfo.getReserveInfos();
        boolean isReserved = reservations.stream()
                .anyMatch(reserve -> reserve.getUserId().equals(userId));
        if (isReserved) {
            return "User " + userId + " has successfully reserved the course on " + courseDate;
        } else {
            return "User " + userId + " has not reserved the course on " + courseDate;
        }
    }
}
