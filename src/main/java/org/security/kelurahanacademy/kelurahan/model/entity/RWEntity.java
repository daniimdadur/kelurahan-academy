package org.security.kelurahanacademy.kelurahan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_rw")
public class RWEntity {

    @Id
    @Column
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "rw_leader")
    private String rwLeader;

    @Column(name = "dusunId", insertable = false, updatable = false)
    private String dusunId;

    @JoinColumn(name = "dusunId")
    @ManyToOne(fetch = FetchType.LAZY)
    private DusunEntity dusun;

    public RWEntity(String id, String name, String rwLeader) {
        this.id = id;
        this.name = name;
        this.rwLeader = rwLeader;
    }
}
