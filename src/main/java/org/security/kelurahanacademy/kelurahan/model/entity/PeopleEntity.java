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
@Table(name = "tbl_people")
public class PeopleEntity {

    @Id
    @Column
    private String id;

    @Column(name = "nik")
    private Integer nik;

    @Column(name = "name")
    private String nama;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String age;
}
