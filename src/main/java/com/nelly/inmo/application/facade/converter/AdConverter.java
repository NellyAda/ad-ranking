package com.nelly.inmo.application.facade.converter;

import java.util.List;

import com.nelly.inmo.infrastructure.api.PublicAd;
import com.nelly.inmo.infrastructure.api.QualityAd;
import com.nelly.inmo.infrastructure.persistence.AdVO;

public interface AdConverter {

	/**
	 * Converts a list of {@link AdVO} objects into a list of {@link QualityAd}
	 * objects
	 * 
	 * @param adsVO - a list of ads
	 * @return a not null list of ads for quality responsible
	 */
	public List<QualityAd> toQualityAds(List<AdVO> adsVO);

	/**
	 * Converts a list of {@link AdVO} objects into a list of {@link PublicAds}
	 * objects
	 * 
	 * @param adsVO - a list of ads
	 * @return a not null list of ads for the public
	 */
	public List<PublicAd> toPublicAds(List<AdVO> adsVO);

}
