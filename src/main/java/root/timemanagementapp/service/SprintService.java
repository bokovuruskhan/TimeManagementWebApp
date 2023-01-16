package root.timemanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.timemanagementapp.database.model.Sprint;
import root.timemanagementapp.database.model.Task;
import root.timemanagementapp.database.repo.SprintRepository;
import root.timemanagementapp.dto.SprintDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SprintService {
    @Autowired
    private SprintRepository sprintRepository;

    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    public Sprint save(Sprint sprint) {
        return sprintRepository.save(sprint);
    }


    public Sprint findById(Long sprintId) throws Exception {
        Optional<Sprint> optionalSprint = sprintRepository.findById(sprintId);
        if (optionalSprint.isPresent()) {
            return optionalSprint.get();
        } else {
            throw new Exception("Not found sprint by id=" + sprintId);
        }
    }

    public Sprint updateSprint(SprintDto sprintDto) throws Exception {
        Sprint sprint = findById(sprintDto.getId());
        sprint.setName(sprintDto.getName());
        if (sprintDto.getActive() != null) {
            sprint.setActive(sprintDto.getActive());
        }else{
            sprint.setActive(false);
        }
        return save(sprint);
    }

    public Sprint getActiveSprint()  {
        Optional<Sprint> optionalSprint = getAllSprints().stream().filter(Sprint::isActive).findFirst();
        if (optionalSprint.isPresent()) {
            return optionalSprint.get();
        } else {
            Sprint sprint = new Sprint("Активный спринт (шаблон)");
            sprint.setTasks(new ArrayList<>());
            return save(sprint);
        }
    }

}
