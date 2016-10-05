package main.java.com.joshwithee.scrambler.controller;

import javax.validation.Valid;

import main.java.com.joshwithee.scrambler.model.Exercise;
import main.java.com.joshwithee.scrambler.model.Goal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("goal")
public class GoalController {
	
	@RequestMapping(value = { "/addMinutes" })
	public String addMinutes(@ModelAttribute("exercise") Exercise exercise) {

		System.out.println("exercise: " + exercise.getMinutes());

		return "addMinutes";
	}

	@RequestMapping(value = "addGoal", method = RequestMethod.GET)
	public String addGoal(Model model) {
		Goal goal = new Goal();
		goal.setMinutes(10);
		model.addAttribute("goal", goal);

		return "addGoal";
	}

	@RequestMapping(value = "addGoal", method = RequestMethod.POST)
	public String updateGoal(@Valid @ModelAttribute("goal") Goal goal,
			BindingResult result) {

		System.out.println("result has errors: " + result.hasErrors());

		System.out.println("Minutes updated: " + goal.getMinutes());

		if (result.hasErrors()) {
			return "addGoal";
		}

		return "redirect:addMinutes.html";
	}

}
