package org.security.kelurahanacademy.kelurahan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_dusun")
public class DusunEntity {

    @Id
    @Column
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "dsn_leader")
    private String dusunLeader;

    @Column(name = "kelurahan_id", insertable = false, updatable = false)
    private String kelurahanId;

    @JoinColumn(name = "kelurahan_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private KelurahanEntity kelurahan;

    @OneToMany(mappedBy = "dusun", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RWEntity> rwList = new ArrayList<>();

    public void addRW(RWEntity entity) {
        this.rwList.add(entity);
        entity.setDusun(this);
    }

    public DusunEntity(String id, String name, String dusunLeader) {
        this.id = id;
        this.name = name;
        this.dusunLeader = dusunLeader;
    }
}
