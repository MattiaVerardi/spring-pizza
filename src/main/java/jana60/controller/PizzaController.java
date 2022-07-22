package jana60.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		return "/pizza/edit";
	}

	@PostMapping("/add")
	public String save(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult br) {
		if (br.hasErrors()) {
			return "/pizza/edit";
		} else {
			repo.save(formPizza);
			return "redirect:/";
		}
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pizzaId, RedirectAttributes ra) {
		Optional<Pizza> result = repo.findById(pizzaId);
		if (result.isPresent()) {
			repo.delete(result.get());
			ra.addFlashAttribute("successMessage", "La pizza " + result.get().getNome() + " è stata eliminata!");
			return "redirect:/";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con id " + pizzaId + " non è presente");
		}
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer pizzaId, Model model) {
		Optional<Pizza> result = repo.findById(pizzaId);
		if (result.isPresent()) {
			model.addAttribute("pizza", result.get());
			return "/pizza/edit";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con id " + pizzaId + " non è presente");
		}

	}
}
