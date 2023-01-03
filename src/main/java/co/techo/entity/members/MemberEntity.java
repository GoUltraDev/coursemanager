package co.techo.entity.members;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Table(name = "members")
@Data
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String name;
    private String surname;
    private String nickname;
    private Integer age;
    private Date birthdate;
    @Enumerated(EnumType.STRING)
    private Buddhism buddhism;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String phone;
    private String phone_desc;
    private String phone_2;
    private String phone_2_desc;
    private String phone_slug;
    @Enumerated(EnumType.STRING)
    private Blacklist blacklist;
    private String email;
    private String province;
    private String country;
    private String facebook;
    private String organization;
    private String expertise;
    private String degree;
    private String career;
    private Integer techo_year;
    private Integer techo_courses;
    private Date blacklist_release;
    private String blacklist_remark;
    private String pseudo;
    private String url_apply;
    private String url_history;
    private String url_image;
    private String created_by;
    private String updated_by;
    private Date created_at;
    private Date updated_at;
}
