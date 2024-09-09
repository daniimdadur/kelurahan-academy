package org.security.kelurahanacademy.kelurahan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DusunRes {
    private String id;
    private String name;
    private String dusunLeader;
    private String kelurahanId;
    private String kelurahanName;
    private List<RWRes> rwList = new ArrayList<>();

    public DusunRes(DusunEntity dusunEntity) {
        BeanUtils.copyProperties(dusunEntity, this);
        this.kelurahanId = dusunEntity.getKelurahanId();
        this.kelurahanName = dusunEntity.getKelurahan().getName();

        if (!dusunEntity.getRwList().isEmpty()) {
            this.rwList = dusunEntity.getRwList().stream().map(RWRes::new).collect(Collectors.toList());
        }
    }
}
