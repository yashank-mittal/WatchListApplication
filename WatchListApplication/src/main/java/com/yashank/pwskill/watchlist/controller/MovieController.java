package com.yashank.pwskill.watchlist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.yashank.pwskill.watchlist.entity.MovieEntity;
import com.yashank.pwskill.watchlist.service.DatabaseService;

import jakarta.validation.Valid;

@RestController
public class MovieController {
	
	@Autowired
	DatabaseService databaseService;
	
	@GetMapping("/watchlistitemform")
	public ModelAndView showWatchListform(@RequestParam(required = false) Integer id)
	{
		String viewName = "watchlistItemForm";
		Map<String, Object> model = new HashMap<>();
//		MovieEntity dummymoveEntity = new MovieEntity();
//		dummymoveEntity.setTitle("Dummy");
//		dummymoveEntity.setRating(0);
//		dummymoveEntity.setPriority("Low");
//		dummymoveEntity.setComment("Mast movvie");
		if(id == null)
		{
			model.put("watchListItem", new MovieEntity());
		}
		else {
			model.put("watchListItem", databaseService.getMovieById(id));
		}
		
		return new ModelAndView(viewName,model);
		
	}
	
	@PostMapping("/watchlistitemform")
	public ModelAndView submitWatchListForm(@Valid @ModelAttribute("watchListItem") MovieEntity movieEntity,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			return new ModelAndView("watchlistItemForm");
		}
		Integer id = movieEntity.getId();
		
		if(id == null)
		{
			databaseService.create(movieEntity);
		}
		else {
			databaseService.update(movieEntity,id);
		}
		
		RedirectView rd = new RedirectView();
		rd.setUrl("/watchlist");
		return new ModelAndView(rd);
	}
	
	@GetMapping("/watchlist")
	public ModelAndView getWatchList()
	{
		String viewName = "watchlist";
		Map<String, Object> model = new HashMap<>();
		List<MovieEntity> movies = databaseService.getAllMovies();
		model.put("watchlistrows", movies);
		model.put("noOfMovies", movies.size());
		return new ModelAndView(viewName,model);
	}
	
	@DeleteMapping("/watchlist")
	public ModelAndView deleteMovie(@RequestParam Integer id)
	{
		System.out.println(id);
		String viewName = "watchlist";
		Map<String, Object> model = new HashMap<>();
		model.put("deleteMovie", databaseService.deleteMovie(id));
		return new ModelAndView(viewName,model);
	}
}
