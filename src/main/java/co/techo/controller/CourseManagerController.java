package co.techo.controller;

import co.techo.dto.RequestPayload;
import co.techo.service.CourseManagerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static co.techo.common.CourseManagerConstant.getDateTime;

@RequestMapping("course-manager/api/v1/report")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Log4j2
public class CourseManagerController {

    @Autowired
    private CourseManagerService service;

    @GetMapping(value = "index")
    public String welcome() {
        log.info("Index event is called at : {}", getDateTime());
        String result = "Welcome to course-manager api at time : " + getDateTime();
        log.info("---------------------------------------------------------------------------------------------------");
        return result;
    }

    @PostMapping(value = "confirm")
    public String[][] getCourseConfirmReport(@RequestBody RequestPayload request) {
        return service.getCourseConfirmReport(request);
    }

    @PostMapping(value = "signing")
    public String[][] getCourseSigningReport(@RequestBody RequestPayload request) {
        return service.getCourseSigningReport(request);
    }

    @PostMapping(value = "van")
    public String[][] getVanSigningReport(@RequestBody RequestPayload request) {
        return service.getVanSigningReport(request);
    }

}
