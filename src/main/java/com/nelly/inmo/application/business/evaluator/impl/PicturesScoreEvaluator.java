package com.nelly.inmo.application.business.evaluator.impl;

import java.util.ArrayList;
/**
 * In this class the execute method returns a list of criterias referred to a list of pictures in an ad.<br/>
 * If the list of pictures is empty, the NO_PICTURES criteria will be included<br/>
 * For each HD picture the criteria HD_PICTURE will be included. For each SD picture the criteria SD_PICTURE will
 * be included
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nelly.inmo.application.business.evaluator.Evaluator;
import com.nelly.inmo.application.facade.helper.ScoreEvaluator.ScoreCriteria;
import com.nelly.inmo.application.service.AdsService;
import com.nelly.inmo.infrastructure.persistence.AdVO;
import com.nelly.inmo.infrastructure.persistence.PictureVO;

@Component
public class PicturesScoreEvaluator implements Evaluator {

	// Picture quality identifiers
	private static final String HD = "HD";
	private static final String SD = "SD";

	@Autowired
	private AdsService service;

	@Override
	public List<ScoreCriteria> execute(AdVO adVO) {

		List<PictureVO> pictures = service.getPictures(adVO);
		List<ScoreCriteria> evals = new ArrayList<>();
		if (pictures.isEmpty())
			evals.add(ScoreCriteria.NO_PICTURES);
		else
			pictures.forEach(picture -> {
				if (HD.equals(picture.getQuality()))
					evals.add(ScoreCriteria.HD_PICTURE);
				else if (SD.equals(picture.getQuality()))
					evals.add(ScoreCriteria.SD_PICTURE);
			});
		return evals;
	}

}
