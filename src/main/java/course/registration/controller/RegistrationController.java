package course.registration.controller;

import course.registration.domain.CourseInfo;
import course.registration.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/courses/{date}")
    public List<CourseInfo> getCoursesByDate(@PathVariable("date") String date) {
        String courseDate = date;
        return registrationService.getCoursesByDate(courseDate);
    }

    @PostMapping("/courses/{date}/reserve")
    public String reserveCourse(@PathVariable("date") String date, @RequestBody Long userId) {
        String courseDate = date;
        return registrationService.reserveCourse(courseDate, userId);
    }

    @GetMapping("/courses/{date}/status")
    public String getReservationStatus(@PathVariable("date") String date, @RequestParam Long userId) {
        String courseDate = date;
        return registrationService.getReservationStatus(courseDate, userId);
    }
}
