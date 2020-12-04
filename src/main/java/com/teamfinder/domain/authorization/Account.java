package com.teamfinder.domain.authorization;

import com.teamfinder.client.transfer.RegistrationCommand;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "ACCOUNT")
@Getter
public class Account implements Serializable {

    private final static String GENERATOR_NAME = "SEQUENCE_GENERATOR";

    @Id
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = "ACCOUNT_SEQ",
            allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
    private Long id;

    @Email(message = "validation.email.syntax")
    @Column(nullable = false,unique = true)
    @Size(max = 255)
    private String email;

    @Column(nullable = false)
    @Size(max = 255)
    private String password;

    @Column(name="ENABLED",nullable = false)
    private Boolean isEnabled;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID",nullable = false)
    private Role role;

    @OneToOne(mappedBy = "account",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private ConfirmationToken confirmationToken;

    public Account() {
    }

    Account(RegistrationCommand registrationCommand, Role role) {
        this.confirmationToken = ConfirmationToken.generateConfirmToken(this);
        this.email = registrationCommand.getEmail();
        this.password = new BCryptPasswordEncoder().encode(registrationCommand.getPassword());
        this.isEnabled = false;
        this.role = role;
    }

    void activeUser() {
        this.isEnabled = true;
    }
    static UserDetails of(Account account) {
        return org.springframework.security.core.userdetails.User
                .withUsername(account.getEmail())
                .password(account.getPassword())
                .roles(account.getRole().getName().toString())
                .build();
    }

}
