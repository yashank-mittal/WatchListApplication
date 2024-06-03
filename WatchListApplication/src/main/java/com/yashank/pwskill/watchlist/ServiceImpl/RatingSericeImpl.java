package com.yashank.pwskill.watchlist.ServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yashank.pwskill.watchlist.service.RatingService;

@Service
public class RatingSericeImpl implements RatingService {
	
	String apiKeyString = "https://www.omdbapi.com/?apikey=30fd226a&t=";

	@Override
	public String MovieRating(String title) {
		// TODO Auto-generated method stub
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ObjectNode> response = restTemplate.getForEntity(apiKeyString + title, ObjectNode.class);
			ObjectNode jsonResponse = response.getBody();
			return jsonResponse.path("imdbRating").asText();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Eiter movie is missing or api is down"+e.getMessage());
			return null;
		}
	}
}
