package org.security.kelurahanacademy.student.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_history")
public class HistoryEntity {

    @Id
    @Column
    private String id;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_address")
    private String schoolAddress;

    @Column(name = "school_city")
    private String schoolCity;

    @OneToMany(mappedBy = "schoolHistory", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClassEntity> schoolClassList = new ArrayList<>();

    @Column(name = "student_id", insertable = false, updatable = false)
    private String studentId;

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private StudentEntity student;

    public void addSchoolClass(ClassEntity schoolClass) {
        this.schoolClassList.add(schoolClass);
        schoolClass.setSchoolHistory(this);
    }

    public HistoryEntity(String id, String schoolName, String schoolAddress, String schoolCity) {
        this.id = id;
        this.schoolName = schoolName;
        this.schoolAddress = schoolAddress;
        this.schoolCity = schoolCity;
    }
}
