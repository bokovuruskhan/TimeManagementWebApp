package root.timemanagementapp.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import root.timemanagementapp.database.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

}
