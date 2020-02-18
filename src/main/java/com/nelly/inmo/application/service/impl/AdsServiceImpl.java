package com.nelly.inmo.application.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelly.inmo.application.service.AdsService;
import com.nelly.inmo.infrastructure.persistence.AdVO;
import com.nelly.inmo.infrastructure.persistence.InMemoryPersistence;
import com.nelly.inmo.infrastructure.persistence.PictureVO;

@Service
public class AdsServiceImpl implements AdsService {

	@Autowired
	private InMemoryPersistence persistence;

	@Override
	public List<AdVO> getAds() {
		return persistence.getAds();
	}

	@Override
	public List<PictureVO> getPictures(AdVO adVO) {
		List<PictureVO> picturesVO = new ArrayList<>();
		adVO.getPictures().forEach(id -> {
			Optional<PictureVO> optPicture = persistence.getPicture(id);
			if (optPicture.isPresent()) {
				picturesVO.add(optPicture.get());
			}
		});
		return picturesVO;

	}

	@Override
	public List<String> getPicturesUrls(AdVO adVO) {
		List<PictureVO> picturesVO = getPictures(adVO);
		List<String> picturesUrls = new ArrayList<>();
		picturesVO.forEach(picture -> {
			picturesUrls.add(picture.getUrl());
		});
		return picturesUrls;
	}

	@Override
	public void update(AdVO adVO, Date date, Integer score) {
		persistence.update(adVO, date, score);
	}

}
