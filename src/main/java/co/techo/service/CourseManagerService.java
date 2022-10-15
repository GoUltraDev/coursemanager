package co.techo.service;

import co.techo.repository.ApplyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
public class CourseManagerService {
    @Autowired
    private ApplyRepository applyRepository;

    @Transactional(rollbackOn = {Exception.class})
    public List<Object> getAllQuery() {
        return applyRepository.selectAll();
    }
}
