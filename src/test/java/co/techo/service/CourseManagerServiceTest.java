package co.techo.service;

import co.techo.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseManagerServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseManagerService myClass;


    @Test
    public void testGetCourseInfo() {
        when(courseRepository.findCourseType2(1)).thenReturn(new String[][]{
                {"1", "2", "3", "Course Name", "2022-01-01", "2022-01-03", "1"}
        });

        Map<String, String> info = myClass.getCourseInfo(1);
        assertEquals("4", info.get("courseType"));
        assertEquals("Course Name", info.get("courseName"));
        assertEquals("2022-01-01", info.get("dateStart"));
        assertEquals("2022-01-03", info.get("dateEnd"));
        assertEquals("1", info.get("categoryId"));
    }

//    @Test
    public void testGetCourseInfo_Anapanasati1Days_TechoVipassana() {
        when(courseRepository.findCourseType2(1)).thenReturn(new String[][]{
                {"1", "2", "3", "Course Name", "2022-01-01", "2022-01-01", "1"}
        });
        when(courseRepository.findCourseType2(4)).thenReturn(new String[][]{
                {"1", "2", "3", "Course Name", "2022-01-01", "2022-01-03", "4"}
        });

        Map<String, String> info1 = myClass.getCourseInfo(1);
        System.out.println("info1 : {}" + info1);
        assertAll(
                () -> assertEquals("4", info1.get("courseType")),
                () -> assertEquals("Course Name", info1.get("courseName")),
                () -> assertEquals("2022-01-01", info1.get("dateStart")),
                () -> assertEquals("2022-01-01", info1.get("dateEnd")),
                () -> assertEquals("1", info1.get("categoryId"))
        );

        Map<String, String> info4 = myClass.getCourseInfo(4);
        System.out.println("info4 : {}" + info4);
        assertAll(
                () -> assertEquals("4", info4.get("courseType")),
                () -> assertEquals("Course Name", info4.get("courseName")),
                () -> assertEquals("2022-01-01", info4.get("dateStart")),
                () -> assertEquals("2022-01-03", info4.get("dateEnd")),
                () -> assertEquals("4", info4.get("categoryId"))
        );
    }
}
