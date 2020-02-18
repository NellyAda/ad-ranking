package com.nelly.inmo.application.business.evaluator.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nelly.inmo.application.business.evaluator.Evaluator;
import com.nelly.inmo.application.facade.helper.ScoreEvaluator.ScoreCriteria;
import com.nelly.inmo.infrastructure.persistence.AdVO;

/**
 * In this class the execute method returns a list with IS_COMPLETE criteria if
 * the ad is complete
 * 
 * @author Nelida Nkumu Mbomio Ada
 *
 */
@Component
public class CompleteAdScoreEvaluator implements Evaluator {

	@Override
	public List<ScoreCriteria> execute(AdVO adVO) {
		List<ScoreCriteria> evals = new ArrayList<>();
		if (isComplete(adVO)) {
			evals.add(ScoreCriteria.IS_COMPLETE);
		}
		return evals;
	}

	/**
	 * This method checks if the ad is complete. To be complete, it must have at
	 * least one picture and fit some other conditions related to the typology.
	 * Additionally, if there is no description and it is not a garage, is not
	 * complete<br/>
	 * 
	 * @param adVO - the ad
	 * @return true if the ad is complete, ioc returns false
	 */
	private boolean isComplete(AdVO adVO) {
		if (adVO.getPictures().isEmpty()) {
			return false;
		}
		if (adVO.getDescription().isEmpty() && !"GARAGE".equals(adVO.getTypology())) {
			return false;
		}

		return isCompleteByTypology(adVO);
	}

	/**
	 * Checks if the ad has all the info required to be complete according to its
	 * typology
	 * 
	 * @param adVO - The ad
	 * @return true if it is typologically complete, ioc returns false
	 */
	private boolean isCompleteByTypology(AdVO adVO) {
		String typology = adVO.getTypology();
		switch (typology) {
		case "FLAT":
			return isCompleteFlat(adVO);
		case "CHALET":
			return isCompleteChalet(adVO);

		default:
			return true;
		}
	}

	/**
	 * Returns a boolean indicating if the ad has info about the house size and the
	 * garden size
	 * 
	 * @param adVO - an ad
	 * @return true if the information is there, ioc returns false
	 */
	private boolean isCompleteChalet(AdVO adVO) {
		if (adVO.getHouseSize() != null && adVO.getGardenSize() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a boolean indicating id the ad has info about the house size
	 * 
	 * @param adVO - an ad
	 * @return true if the information is there, ioc returns false
	 */
	private boolean isCompleteFlat(AdVO adVO) {
		if (adVO.getHouseSize() != null) {
			return true;
		}
		return false;
	}

}
