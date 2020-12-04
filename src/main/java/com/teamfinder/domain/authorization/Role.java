package com.teamfinder.domain.authorization;

import com.teamfinder.domain.authorization.enums.SystemRole;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "ROLE")
@Getter
public class Role {

    private final static String GENERATOR_NAME = "SEQUENCE_GENERATOR";

    @Id
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = "ROLE_SEQ",
            allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = GENERATOR_NAME)
    private Long id;

    @Column(unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    @Size(max=255)
    private SystemRole name;

    public Role() {

    }

    public Role(SystemRole name) {
        this.name = name;
    }
}