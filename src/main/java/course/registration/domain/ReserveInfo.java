package course.registration.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
@NoArgsConstructor
public class ReserveInfo {

    @Id
    @GeneratedValue
    private Long reserveId;

    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    private CourseInfo courseInfo;
}
