package org.security.kelurahanacademy.student.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.security.kelurahanacademy.student.model.entity.HistoryEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryRes {
    private String id;
    private String schoolName;
    private String schoolAddress;
    private String schoolCity;
    private List<ClassRes> schoolClassList = new ArrayList<>();

    public HistoryRes(HistoryEntity schoolHistory) {
        BeanUtils.copyProperties(schoolHistory, this);

        if (!schoolHistory.getSchoolClassList().isEmpty()) {
            this.schoolClassList = schoolHistory.getSchoolClassList().stream().map(schoolClass -> new ClassRes(schoolClass)).collect(Collectors.toList());
        }
    }
}
