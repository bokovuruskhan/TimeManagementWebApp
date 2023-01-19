package root.timemanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.timemanagementapp.dto.StatisticsDto;

@Service
public class StatisticsService {

    @Autowired
    private SprintService sprintService;

    public StatisticsDto getActiveSprintStatistics() {
        return new StatisticsDto(sprintService.getActiveSprint());
    }


}
