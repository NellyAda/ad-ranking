package com.nelly.inmo.application.facade.helper.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelly.inmo.application.facade.AdsFacade;
import com.nelly.inmo.application.facade.converter.AdConverter;
import com.nelly.inmo.application.facade.helper.AdsFacadeHelper;
import com.nelly.inmo.application.service.AdsService;
import com.nelly.inmo.infrastructure.api.PublicAd;
import com.nelly.inmo.infrastructure.api.QualityAd;
import com.nelly.inmo.infrastructure.persistence.AdVO;

@Service
public class AdsFacadeImpl implements AdsFacade {

	@Autowired
	private AdsService service;

	@Autowired
	private AdsFacadeHelper helper;

	@Autowired
	private AdConverter converter;

	private static final Integer MIN_SCORE = 40;

	@Override
	public List<QualityAd> qualityListing() {
		// Retrieves all the ads
		List<AdVO> adsVO = service.getAds();
		// Converts the ads into quality ads and return them
		return converter.toQualityAds(adsVO);
	}

	@Override
	public List<PublicAd> publicListing() {
		// Retrieves all the ads
		List<AdVO> adsVO = service.getAds();
		// Filters the ads by score getting those with a min score of 40 and ordered
		// from higher score to lower
		List<AdVO> filteredAdsVO = adsVO.stream()
				.filter(ad -> ad.getScore() != null && MIN_SCORE.compareTo(ad.getScore()) <= 0)
				.sorted(Comparator.comparingInt(AdVO::getScore).reversed()).collect(Collectors.toList());
		// Converts the ads into public ads
		List<PublicAd> publicAds = converter.toPublicAds(filteredAdsVO);
		return publicAds;
	}

	@Override
	public void calculateScore() {
		// Retrieves all the ads
		List<AdVO> adsVO = service.getAds();
		// Calculates and updates the score for each ad
		adsVO.forEach(adVO -> helper.calculateScore(adVO, MIN_SCORE));
	}

}
