package com.nelly.inmo.application.facade;

import java.util.List;

import com.nelly.inmo.infrastructure.api.PublicAd;
import com.nelly.inmo.infrastructure.api.QualityAd;

/**
 * 
 * @author Nelida Nkumu
 * 
 *         This is the interface required to manipulate advertisements
 *
 */
public interface AdsFacade {

	/**
	 * This method extracts the list of all advertisements in the system.
	 * 
	 * @return a not null list of {@link QualityAd} objects
	 */
	public List<QualityAd> qualityListing();

	/**
	 * This method extracts the list of all the relevant advertisements in the
	 * system.
	 * 
	 * @return a not null list of {@link PublicAd} objects
	 */
	public List<PublicAd> publicListing();

	/**
	 * For each ad in the system calculates the score and date of irrelevance. This
	 * modifications will be updated in the system<br/>
	 * If the score is over 39 and it was irrelevant before, the date of irrelevance
	 * will be removed<br/>
	 * If the score is under 40 and it was not irrelevant before, the date of
	 * irrelevance will be updated with the current date<br/>
	 * In other cases the date of irrelevance will not be modified.
	 */
	public void calculateScore();

}
