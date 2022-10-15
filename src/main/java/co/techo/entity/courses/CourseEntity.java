package co.techo.entity.courses;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "courses")
@Data
@Entity
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private State state;
    private Location loaction;
    private Category category;
    private Date date_start;
    private Date date_end;
    private String coursename;
    private Integer courseyear;
    private String color;
    private String description;
    private List listed;
    private Date listed_date;
}
