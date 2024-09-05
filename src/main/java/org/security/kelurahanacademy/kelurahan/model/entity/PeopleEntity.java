package org.security.kelurahanacademy.kelurahan.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_people")
public class PeopleEntity {

    @Id
    @Column
    private String id;

    @Column(name = "nik")
    private Integer nik;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String age;

    @Column(name = "rt_id", insertable = false, updatable = false)
    private String rtId;

    @JoinColumn(name = "rt_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RTEntity rt;

    public PeopleEntity(String id, Integer nik, String name, String gender, String age) {
        this.id = id;
        this.nik = nik;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
}
