package ch.cern.todo.repository;

import ch.cern.todo.model.TasksCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksCategoryRepository extends CrudRepository<TasksCategory, Long> {


}
