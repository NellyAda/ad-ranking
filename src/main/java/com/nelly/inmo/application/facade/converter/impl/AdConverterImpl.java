package com.nelly.inmo.application.facade.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelly.inmo.application.facade.converter.AdConverter;
import com.nelly.inmo.application.service.AdsService;
import com.nelly.inmo.infrastructure.api.PublicAd;
import com.nelly.inmo.infrastructure.api.QualityAd;
import com.nelly.inmo.infrastructure.persistence.AdVO;

@Service
public class AdConverterImpl implements AdConverter {

	@Autowired
	private AdsService service;

	@Override
	public List<QualityAd> toQualityAds(List<AdVO> adsVO) {
		List<QualityAd> qualityAds = new ArrayList<>();
		adsVO.forEach(adVO -> {
			QualityAd ad = new QualityAd();
			ad.setDescription(adVO.getDescription());
			ad.setGardenSize(adVO.getGardenSize());
			ad.setHouseSize(adVO.getHouseSize());
			ad.setId(adVO.getId());
			ad.setIrrelevantSince(adVO.getIrrelevantSince());
			ad.setPictureUrls(service.getPicturesUrls(adVO));
			ad.setScore(adVO.getScore());
			ad.setTypology(adVO.getTypology());
			qualityAds.add(ad);
		});
		return qualityAds;
	}

	@Override
	public List<PublicAd> toPublicAds(List<AdVO> adsVO) {
		List<PublicAd> publicAds = new ArrayList<>();
		adsVO.forEach(adVO -> {
			PublicAd ad = new PublicAd();
			ad.setDescription(adVO.getDescription());
			ad.setGardenSize(adVO.getGardenSize());
			ad.setHouseSize(adVO.getHouseSize());
			ad.setId(adVO.getId());
			ad.setPictureUrls(service.getPicturesUrls(adVO));
			ad.setTypology(adVO.getTypology());
			publicAds.add(ad);
		});
		return publicAds;
	}

}
