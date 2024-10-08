package org.security.kelurahanacademy.kelurahan.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_kelurahan")
public class KelurahanEntity {

    @Id
    @Column
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "kec")
    private String kec;

    @OneToMany(mappedBy = "kelurahan", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DusunEntity> dusunList = new ArrayList<>();

    public void addDusun(DusunEntity entity) {
        this.dusunList.add(entity);
        entity.setKelurahan(this);
    }

    public KelurahanEntity(String id, String name, String kec) {
        this.id = id;
        this.name = name;
        this.kec = kec;
    }
}
