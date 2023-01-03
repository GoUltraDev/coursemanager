package co.techo.entity.courses;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Table(name = "courses")
@Data
@Entity
@ToString
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    private Location location;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Date date_start;

    private Date date_end;

    private String coursename;

    private int courseyear;

    private String color;

    private String description;

    @Enumerated(EnumType.STRING)
    private List listed;

    private Date listed_date;


}
