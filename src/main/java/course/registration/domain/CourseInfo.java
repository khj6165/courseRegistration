package course.registration.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class CourseInfo {
    @Id
    @GeneratedValue
    private Long courseId;

    private String courseDate;

    @OneToMany(mappedBy = "courseInfo", cascade = CascadeType.ALL)
    private List<ReserveInfo> reserveInfos = new ArrayList<>();
}
