package org.security.kelurahanacademy.student.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.security.kelurahanacademy.student.model.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRes {
    private String id;
    private Integer nis;
    private String firstName;
    private String lastName;
    private String address;
    private List<HistoryRes> schoolHistoryList = new ArrayList<>();

    public StudentRes(StudentEntity studentEntity) {
        this.id = studentEntity.getId();
        this.nis = studentEntity.getNis();
        this.firstName = studentEntity.getFirstName();
        this.lastName = studentEntity.getLastName();
        this.address = studentEntity.getAddress();

        if (!studentEntity.getSchoolHistoryList().isEmpty()) {
            this.schoolHistoryList = studentEntity.getSchoolHistoryList().stream().map(schoolHistory -> new HistoryRes(schoolHistory)).collect(Collectors.toList());
        }
    }
}
