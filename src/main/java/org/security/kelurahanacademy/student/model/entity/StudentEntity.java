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
@Table(name = "tbl_student")
public class StudentEntity {

    @Id
    @Column
    private String id;

    @Column(name = "nis")
    private Integer nis;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoryEntity> schoolHistoryList = new ArrayList<>();

    public void addSchoolHistory(HistoryEntity schoolHistory) {
        this.schoolHistoryList.add(schoolHistory);
        schoolHistory.setStudent(this);
    }

    public StudentEntity(String id, Integer nis, String firstName, String lastName, String address) {
        this.id = id;
        this.nis = nis;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
