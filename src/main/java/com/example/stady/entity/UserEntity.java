package com.example.stady.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = true)
    private String login;
    @Column(nullable = true)
    private String password;
    @Column(nullable = true)
    private String email;
    private String phone;
    private LocalDateTime createDate;
    private Long isActive;
    private String actevationCode;

    @PrePersist
    public void prePersistCreateData() {
        this.createDate = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createDate=" + createDate +
                ", isActive=" + isActive +
                ", actevationCode='" + actevationCode + '\'' +
                '}';
    }
}
