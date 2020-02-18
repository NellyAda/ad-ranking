package com.nelly.inmo.application.service;

import java.util.Date;
import java.util.List;

import com.nelly.inmo.infrastructure.persistence.AdVO;
import com.nelly.inmo.infrastructure.persistence.PictureVO;

public interface AdsService {

	/**
	 * Retrieves the a list with all the ads in the system
	 * 
	 * @return a not null list of {@link AdVO} objects
	 */
	public List<AdVO> getAds();

	/**
	 * Given an ad retrieves the list of pictures
	 * 
	 * @param adVO
	 * @return a not null list of {@link PictureVO} objects
	 */
	public List<PictureVO> getPictures(AdVO adVO);

	/**
	 * Given an ad retrieves the list of urls to its pictures
	 * 
	 * @param adVO - an ad
	 * @return a not null list of urls
	 */
	public List<String> getPicturesUrls(AdVO adVO);

	/**
	 * This method updates the date and the score in the ad. <i>Cannot be null</i>
	 * 
	 * @param adVO  - an ad
	 * @param date  - the new date
	 * @param score - the new score
	 */
	public void update(AdVO adVO, Date date, Integer score);

}
