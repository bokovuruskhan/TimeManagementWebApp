package root.timemanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.timemanagementapp.database.model.Sprint;
import root.timemanagementapp.database.repo.SprintRepository;

import java.util.List;

@Service
public class SprintService {
    @Autowired
    private SprintRepository sprintRepository;

    public List<Sprint> getAllSprints(){
        return sprintRepository.findAll();
    }

}
