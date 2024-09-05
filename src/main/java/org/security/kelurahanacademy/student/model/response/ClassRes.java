package org.security.kelurahanacademy.student.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.security.kelurahanacademy.student.model.entity.ClassEntity;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRes {
    private String id;
    private String schoolClass;
    private LocalDate startDate;
    private LocalDate endDate;
    private String teacherName;

    public ClassRes(ClassEntity classEntity){
        BeanUtils.copyProperties(classEntity, this);
    }
}
