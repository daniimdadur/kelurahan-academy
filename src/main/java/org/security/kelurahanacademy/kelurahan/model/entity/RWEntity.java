package org.security.kelurahanacademy.kelurahan.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_RW")
public class RWEntity {

    @Id
    @Column
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "rw_leader")
    private String rwLeader;
}
