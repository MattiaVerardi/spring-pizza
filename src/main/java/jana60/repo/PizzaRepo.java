package jana60.repo;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Pizza;

public interface PizzaRepo extends CrudRepository<Pizza, Integer> {

}
