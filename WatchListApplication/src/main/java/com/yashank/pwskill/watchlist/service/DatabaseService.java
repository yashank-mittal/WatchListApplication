package com.yashank.pwskill.watchlist.service;

import java.util.List;

import com.yashank.pwskill.watchlist.entity.MovieEntity;

public interface DatabaseService {
	void create(MovieEntity movieEntity);
	List<MovieEntity> getAllMovies();
	MovieEntity getMovieById(Integer id);
	void update(MovieEntity movieEntity,Integer id);
	String deleteMovie(Integer id);
}
