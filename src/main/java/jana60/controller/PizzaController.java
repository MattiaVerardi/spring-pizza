package jana60.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Pizza;
import jana60.repo.PizzaRepo;

@Controller
@RequestMapping("/")
public class PizzaController {

	@Autowired
	private PizzaRepo repo;

	@GetMapping
	public String pizzaList(Model m) {
		m.addAttribute("pizza", repo.findAll());
		return "/pizza/list";
	}

	@GetMapping("/add")
	public String pizzaForm(Model m) {
		m.addAttribute("pizza", new Pizza());
		return "/pizza/add";
	}
}
