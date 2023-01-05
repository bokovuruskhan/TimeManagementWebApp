package root.timemanagementapp.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import root.timemanagementapp.database.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
