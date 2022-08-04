package jana60.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jana60.model.Pizza;
import jana60.repo.PizzaRepo;

@RestController
@CrossOrigin
@RequestMapping("/api/pizza")
public class PizzaRestController {

	@Autowired
	private PizzaRepo repo;

	@GetMapping
	public List<Pizza> get() {
		return (List<Pizza>) repo.findAll();
	}

	@GetMapping("/{id}")
	public Pizza getById(@PathVariable Integer id) {
		Optional<Pizza> pizza = repo.findById(id);
		if (pizza.isPresent()) {
			return pizza.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
		}
	}
}
