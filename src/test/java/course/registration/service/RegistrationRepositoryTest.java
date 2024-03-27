package course.registration.service;

import course.registration.domain.CourseInfo;
import course.registration.domain.ReserveInfo;
import course.registration.repository.CourseInfoRepository;
import course.registration.repository.ReserveInfoRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RegistrationRepositoryTest {
    @Autowired
    CourseInfoRepository courseInfoRepository;
    @Autowired
    ReserveInfoRepository reserveInfoRepository;

//    @BeforeEach
//    @Transactional
//    @Rollback(false)
//    void setUp() {
//        // CourseInfo 생성
//        CourseInfo courseInfo1 = new CourseInfo();
//        courseInfo1.setCourseDate("20240401");
//        courseInfoRepository.save(courseInfo1);
//
//        CourseInfo courseInfo2 = new CourseInfo();
//        courseInfo2.setCourseDate("20240402");
//        courseInfoRepository.save(courseInfo2);
//    }

    @Test
    @Transactional
    @Rollback(false)
    public void testCourseInfo() throws Exception{
        //given
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setCourseId(1L);
        courseInfo.setCourseDate("20240401");

        //when
        courseInfoRepository.save(courseInfo);

        //then
        Optional<CourseInfo> foundCourseInfo = courseInfoRepository.findById(courseInfo.getCourseId());
        assertTrue(foundCourseInfo.isPresent());
        assertEquals(courseInfo.getCourseDate(), foundCourseInfo.get().getCourseDate());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testReserveCourse() throws Exception{
        // CourseInfo 생성
        CourseInfo courseInfo1 = new CourseInfo();
        courseInfo1.setCourseDate("20240401");
        courseInfoRepository.save(courseInfo1);

        CourseInfo courseInfo2 = new CourseInfo();
        courseInfo2.setCourseDate("20240402");
        courseInfoRepository.save(courseInfo2);
        // 예약하기
        //CourseInfo courseInfo1 = courseInfoRepository.findByCourseDate("20240401");
        ReserveInfo reserveInfo1 = new ReserveInfo();
        reserveInfo1.setUserId(1L);
        reserveInfo1.setCourseInfo(courseInfo1);
        reserveInfoRepository.save(reserveInfo1);

        //CourseInfo courseInfo2 = courseInfoRepository.findByCourseDate("20240402");
        ReserveInfo reserveInfo2 = new ReserveInfo();
        reserveInfo2.setUserId(2L);
        reserveInfo2.setCourseInfo(courseInfo2);
        reserveInfoRepository.save(reserveInfo2);

        ReserveInfo reserveInfo3 = new ReserveInfo();
        reserveInfo3.setUserId(3L);
        reserveInfo3.setCourseInfo(courseInfo2);
        reserveInfoRepository.save(reserveInfo3);

        // 예약 확인
        assertEquals(1, courseInfo1.getReserveInfos().size());
        assertEquals(2, courseInfo2.getReserveInfos().size());
    }



}