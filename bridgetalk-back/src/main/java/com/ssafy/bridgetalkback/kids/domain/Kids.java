package com.ssafy.bridgetalkback.kids.domain;

import com.ssafy.bridgetalkback.global.BaseEntity;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.domain.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@Table(name="kids")
public class Kids extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "kids_uuid", columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(nullable = false, length = 20)
    private String kidsName;

    @Column(length = 100)
    private String kidsEmail;

    @Column(nullable = false, length = 20)
    private String kidsNickname;

    @Column(nullable = false, length = 10)
    private String kidsDino;

    @Embedded
    private KidsPassword kidsPassword;

    @Convert(converter = Role.RoleConverter.class)
    @Column(nullable = false, length = 10)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_uuid")
    private Parents parents;

    private Kids(Parents parents, String kidsName, String kidsEmail, String kidsNickname, String kidsDino,
                 KidsPassword kidsPassword) {
        this.uuid = UUID.randomUUID();
        this.parents = parents;
        this.kidsName = kidsName;
        this.kidsEmail = kidsEmail;
        this.kidsNickname = kidsNickname;
        this.kidsDino = kidsDino;
        this.kidsPassword = kidsPassword;
        this.role = Role.USER;
    }

    public static Kids createKids(Parents parents, String kidsName, String kidsEmail, String kidsNickname, String kidsDino,
                                  KidsPassword kidsPassword) {
        return new Kids(parents, kidsName, kidsEmail, kidsNickname, kidsDino, kidsPassword);
    }

    public void updateKidsEmail(String updateKidsEmail) {
        this.kidsEmail = updateKidsEmail;
    }

    public void updateProfile(String nickname, String dino) {
        this.kidsNickname = nickname;
        this.kidsDino = dino;
    }
}
