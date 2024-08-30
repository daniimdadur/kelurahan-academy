package org.security.kelurahanacademy.kelurahan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
