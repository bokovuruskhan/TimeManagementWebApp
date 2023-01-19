package root.timemanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.timemanagementapp.database.model.Sprint;
import root.timemanagementapp.dto.SprintDto;
import root.timemanagementapp.service.SprintService;

@RestController
@RequestMapping("/sprint")
public class SprintController {

    @Autowired
    private SprintService sprintService;


    @PostMapping("/update")
    public Sprint updateSprint(@RequestBody SprintDto sprintDto) throws Exception {
        return sprintService.updateSprint(sprintDto);
    }

}
