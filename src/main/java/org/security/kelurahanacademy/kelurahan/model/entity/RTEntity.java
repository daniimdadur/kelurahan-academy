package org.security.kelurahanacademy.kelurahan.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_rt")
public class RTEntity {

    @Id
    @Column
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "rt_leader")
    private String rtLeader;

    @Column(name = "rw_id", insertable = false, updatable = false)
    private String rwId;

    @JoinColumn(name = "rw_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RWEntity rw;

    @OneToMany(mappedBy = "rt", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PeopleEntity> peopleList = new ArrayList<>();

    public void addPeople(PeopleEntity peopleEntity) {
        this.peopleList.add(peopleEntity);
        peopleEntity.setRt(this);
    }

    public RTEntity(String id, String name, String rtLeader) {
        this.id = id;
        this.name = name;
        this.rtLeader = rtLeader;
    }
}
