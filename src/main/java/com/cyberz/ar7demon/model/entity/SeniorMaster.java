package com.cyberz.ar7demon.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "senior_masters")
public class SeniorMaster implements Serializable {
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(generator = "senior_masters_generator")
    @SequenceGenerator(name = "senior_masters_generator",allocationSize = 1,initialValue = 100001)
    private Integer seniorMasterId;
    @Column(length = 45, nullable = false)
    private String name;
    @Column(length = 18, nullable = false)
    private String password;
    @Column(length = 12, nullable = false)
    private String phone;
    @Column(nullable = false)
    private Long unit;
    @Column(length = 50,nullable = false,unique = true)
    private String email;

    @Column(name = "lasted_login",nullable = false,columnDefinition = "DATE")
    private Date lastedLogin;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinTable(name = "SeniorMaster_and_Master",joinColumns = @JoinColumn(name = "senior_master_id" ),
    inverseJoinColumns = @JoinColumn(name = "master_id")
    )
    private List<Master> masterList;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinTable(name = "SeniorMaster_and_Agent",joinColumns = @JoinColumn(name = "senior_master_id"),
            inverseJoinColumns = @JoinColumn(name = "agent_id")
    )
    private List<Agent> agentList;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinTable(name = "SeniorMaster_&_User",joinColumns = @JoinColumn(name = "senior_master_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userList;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public SeniorMaster(){
        this.role=Role.SENIOR_MASTER;
    }

}
