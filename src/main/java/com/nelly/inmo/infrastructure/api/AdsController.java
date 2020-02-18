package com.nelly.inmo.infrastructure.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nelly.inmo.application.facade.AdsFacade;

@RestController
public class AdsController {

	@Autowired
	private AdsFacade facade;

	/**
	 * Retrieves a not null {@link ResponseEntity} object with the full list of
	 * advertisements and the status of the operation .
	 * 
	 * @return a {@link ResponseEntity} containing:</br>
	 *         <ul>
	 *         <li>a not null list of objects {@link QualityAd} (<i>Can be
	 *         empty</i>)</li>
	 *         <li>{@link HttpStatus.OK}
	 *         </ul>
	 */
	@GetMapping(value = "api/v1.0/qualityListing")
	public ResponseEntity<List<QualityAd>> qualityListing() {
		List<QualityAd> ads = facade.qualityListing();
		return new ResponseEntity<List<QualityAd>>(ads, HttpStatus.OK);
	}

	/**
	 * Retrieves a not null {@link ResponseEntity} object with the full list of
	 * relevant advertisements (score over 39) ordered by more relevant and the
	 * status of the operation .
	 * 
	 * @return a {@link ResponseEntity} containing:</br>
	 *         <ul>
	 *         <li>a not null list of objects {@link PublicAd} (<i>Can be
	 *         empty</i>)</li>
	 *         <li>{@link HttpStatus.OK}
	 *         </ul>
	 */
	@GetMapping(value = "api/v1.0/publicListing")
	public ResponseEntity<List<PublicAd>> publicListing() {
		List<PublicAd> ads = facade.publicListing();
		return new ResponseEntity<List<PublicAd>>(ads, HttpStatus.OK);
	}

	/**
	 * Internally calculates the score for every advertisement and, if the score is
	 * under 40, the current date will be stored.
	 * 
	 * @return a not null {@link ResponseEntity} object with the http status
	 *         {@link HttpStatus.OK}.
	 */
	@PutMapping(value = "api/v1.0/calculateScore")
	public ResponseEntity<Void> calculateScore() {
		facade.calculateScore();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
