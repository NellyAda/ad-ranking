package com.nelly.inmo.application.facade.helper.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelly.inmo.application.business.evaluator.Evaluator;
import com.nelly.inmo.application.facade.helper.AdsFacadeHelper;
import com.nelly.inmo.application.facade.helper.ScoreEvaluator;
import com.nelly.inmo.application.facade.helper.ScoreEvaluator.ScoreCriteria;
import com.nelly.inmo.application.service.AdsService;
import com.nelly.inmo.infrastructure.persistence.AdVO;

@Service
public class AdsFacadeHelperImpl implements AdsFacadeHelper {

	@Autowired
	private AdsService service;

	@Autowired
	private ScoreEvaluator scoreEvaluator;

	private List<Evaluator> evaluators;

	@Autowired
	public AdsFacadeHelperImpl(List<Evaluator> evaluators) {
		this.evaluators = evaluators;
	}

	@Override
	public void calculateScore(AdVO adVO, Integer minScore) {
		Integer score = getAdScore(adVO);
		Date date = calculateIrrelevantSince(adVO, score, minScore);
		service.update(adVO, date, score);
	}

	/**
	 * Calculates the score for an ad
	 * 
	 * @param adVO - the ad
	 * @return a not null Integer with the ad score
	 */
	private Integer getAdScore(AdVO adVO) {
		List<ScoreCriteria> evals = new ArrayList<>();
		evaluators.forEach(evaluator -> evals.addAll(evaluator.execute(adVO)));
		return scoreEvaluator.getScore(evals);
	}

	/**
	 * Calculates the irrelevant since date for an ad according to its new score and
	 * the minimum score.<br/>
	 * If the score is null, returns the current date<br/>
	 * If the score is the minimum or more and it was irrelevant before, returns
	 * null<br/>
	 * If the score is under the minimum and it was not irrelevant before, returns
	 * the current date<br/>
	 * In other cases returns the date in the ad.
	 * 
	 * @param adVO     - the ad object to update. <i>Cannot be null</i>
	 * @param score    - the new score
	 * @param minScore - the minimum score for relevance
	 */
	private Date calculateIrrelevantSince(AdVO adVO, Integer score, Integer minScore) {
		int newScoreCmp = minScore.compareTo(score);
		// No previous evaluation
		if (adVO.getScore() == null) {
			if (newScoreCmp > 0) { // If the score is under the minimum
				return new Date();
			} else {
				return adVO.getIrrelevantSince();
			}
		}
		int currentScoreCmp = minScore.compareTo(adVO.getScore());
		// Case when the relevance status does not change
		if (newScoreCmp == currentScoreCmp) {
			return adVO.getIrrelevantSince();
		}

		if (newScoreCmp > 0) { // Previously relevant, now irrelevant
			return new Date();
		} else { // Previously irrelevant, now relevant
			return null;
		}
	}

}
