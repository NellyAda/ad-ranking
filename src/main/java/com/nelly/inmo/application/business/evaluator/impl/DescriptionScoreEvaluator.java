package com.nelly.inmo.application.business.evaluator.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nelly.inmo.application.business.evaluator.Evaluator;
import com.nelly.inmo.application.facade.helper.ScoreEvaluator.ScoreCriteria;
import com.nelly.inmo.infrastructure.persistence.AdVO;

/**
 * In this class the execute method returns the list of criterias referred to
 * the description length in an ad.<br/>
 * If the ad has a description, the criteria HAS_DESCRIPTION will be
 * included<br/>
 * If the typology is FLAT and the length of the description is over 49, the
 * criteria FLAT_DESC_LENGTH_OVER_49 will be included. If the description length
 * is under 50, the criteria FLAT_DESC_LENGTH_UNDER_50 will be included<br/>
 * If the typology is CHALET and the length of the description is over 50, the
 * criteria CHALET_OVER_50 will be included
 * 
 */

@Component
public class DescriptionScoreEvaluator implements Evaluator {

	@Override
	public List<ScoreCriteria> execute(AdVO adVO) {
		List<ScoreCriteria> evals = new ArrayList<>();
		if (!adVO.getDescription().isEmpty()) {

			evals.add(ScoreCriteria.HAS_DESCRIPTION);
			int descLen = adVO.getDescription().length();
			switch (adVO.getTypology()) {
			case "FLAT":
				if (descLen >= 50) {
					evals.add(ScoreCriteria.FLAT_DESC_LENGTH_OVER_49);
				} else if (descLen >= 20) {
					evals.add(ScoreCriteria.FLAT_DESC_LENGTH_UNDER_50);
				}
				break;
			case "CHALET":
				if (descLen > 50) {
					evals.add(ScoreCriteria.CHALET_OVER_50);
				}
			default:
				break;
			}
		}
		return evals;
	}

}
