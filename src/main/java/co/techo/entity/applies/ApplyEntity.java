package co.techo.entity.applies;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Table(name = "applies")
@Data
@Entity
public class ApplyEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer member_id;
    private Integer course_id;

    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    private Firsttime firsttime;

    @Enumerated(EnumType.STRING)
    private Confirm confirmed;

    @Enumerated(EnumType.STRING)
    private Van van;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Shelter shelter;

    private Integer priority_id;

    private String remark;

    private String created_by;
    private String updated_by;
    private Date created_at;
    private Date updated_at;

}
