package jana60.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Ingredienti;
import jana60.repo.IngredientiRepo;

@Controller
@RequestMapping
public class IngredientiController {

	@Autowired
	private IngredientiRepo repo;

	@GetMapping
	public String ingredientiList(Model model) {
		model.addAttribute("ingredienti", repo.findAll());
		model.addAttribute("newIngredienti", new Ingredienti());
		return "/category/list";
	}
}
