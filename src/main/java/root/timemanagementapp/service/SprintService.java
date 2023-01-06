package root.timemanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.timemanagementapp.database.model.Sprint;
import root.timemanagementapp.database.repo.SprintRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SprintService {
    @Autowired
    private SprintRepository sprintRepository;

    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    public Sprint getActiveSprint() throws Exception {
        Optional<Sprint> optionalSprint = getAllSprints().stream().filter(Sprint::isActive).findFirst();
        if (optionalSprint.isPresent()) {
            return optionalSprint.get();
        } else {
            throw new Exception("No active sprint");
        }
    }

}
