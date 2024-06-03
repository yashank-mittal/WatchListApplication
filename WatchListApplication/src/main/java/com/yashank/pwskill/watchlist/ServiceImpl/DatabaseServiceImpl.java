package com.yashank.pwskill.watchlist.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yashank.pwskill.watchlist.ExceptionHandle.ResourceNotFoundException;
import com.yashank.pwskill.watchlist.entity.MovieEntity;
import com.yashank.pwskill.watchlist.repository.MovieRepository;
import com.yashank.pwskill.watchlist.service.DatabaseService;
import com.yashank.pwskill.watchlist.service.RatingService;

@Service
public class DatabaseServiceImpl implements DatabaseService {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	RatingService ratingService;

	@Override
	public void create(MovieEntity movieEntity) {
		// TODO Auto-generated method stub
		String rating = ratingService.MovieRating(movieEntity.getTitle()); 
		if(rating != null)
		{
			movieEntity.setRating(Float.parseFloat(rating));
		}
		movieRepository.save(movieEntity);
	}

	@Override
	public List<MovieEntity> getAllMovies() {
		// TODO Auto-generated method stub
		return movieRepository.findAll();
	}

	@Override
	public MovieEntity getMovieById(Integer id) {
		// TODO Auto-generated method stub
		return movieRepository.findById(id).get();
	}

	@Override
	public void update(MovieEntity movieEntity, Integer id) {
		// TODO Auto-generated method stub
		MovieEntity fetchMovieEntity = getMovieById(id);
		fetchMovieEntity.setTitle(movieEntity.getTitle());
		fetchMovieEntity.setRating(movieEntity.getRating());
		fetchMovieEntity.setPriority(movieEntity.getPriority());
		fetchMovieEntity.setComment(movieEntity.getComment());
		movieRepository.save(fetchMovieEntity);
	}

	@Override
	public String deleteMovie(Integer id) {
		// TODO Auto-generated method stub
		movieRepository.deleteById(id);
		return "Deleted Successfully";
	}
	
}
