package org.security.kelurahanacademy.kelurahan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.security.kelurahanacademy.kelurahan.model.entity.KelurahanEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanRes {
    private String id;
    private String name;
    private String kec;
    private List<DusunRes> dusunList = new ArrayList<>();

    public KelurahanRes(KelurahanEntity kelurahanEntity) {
        BeanUtils.copyProperties(kelurahanEntity, this);

        if (!kelurahanEntity.getDusunList().isEmpty()) {
            for (DusunEntity dusunEntity : kelurahanEntity.getDusunList()) {
                this.dusunList.add(new DusunRes(dusunEntity));
            }
        }
    }
}
