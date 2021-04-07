package eci.ieti.data;

import eci.ieti.data.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    Page<Todo> findByResponsible(String responsible, Pageable pageable);

    List<Todo> findAllByDueDateBefore(Date date);

    List<Todo> findAllByResponsibleEqualsAndPriorityGreaterThanEqual(String Responsible,int priority);

    List<Todo> findAllByDescriptionMatchesRegex(String regex);

}
