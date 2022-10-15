package co.techo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RequestMapping("course/manager/api/v1/")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Log4j2
public class CourseManagerController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String welcome() {
        log.info("Index event is called at : {}", getDateTime());
        String result = "Welcome to course manager api at time : " + getDateTime();
        log.info("---------------------------------------------------------------------------------------------------");
        return result;
    }

    private String getDateTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String result = now.format(format);
        return result + " (Asia/Bangkok) ";
    }
}
