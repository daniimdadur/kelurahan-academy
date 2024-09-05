package org.security.kelurahanacademy.student.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_class")
public class ClassEntity {

    @Id
    @Column
    private String id;

    @Column(name = "school_class")
    private String schoolClass;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "history_id", insertable = false, updatable = false)
    private String schoolHistoryId;

    @JoinColumn(name = "history_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private HistoryEntity schoolHistory;

    public ClassEntity(String id, String schoolClass, LocalDate startDate, LocalDate endDate, String teacherName) {
        this.id = id;
        this.schoolClass = schoolClass;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacherName = teacherName;
    }
}
