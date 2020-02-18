package com.nelly.inmo.application.business.evaluator.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nelly.inmo.application.business.evaluator.Evaluator;
import com.nelly.inmo.application.facade.helper.ScoreEvaluator.ScoreCriteria;
import com.nelly.inmo.infrastructure.persistence.AdVO;

/**
 * In this class the execute method includes into a list a DESC_WITH_KEYWORDS
 * criteria per keyword found in the description and returns that list.
 * 
 */
@Component
public class KeywordsDescScoreEvaluator implements Evaluator {

	// Keywords in the description text
	private static final List<String> keyWords = Arrays.asList("Luminoso", "Nuevo", "Céntrico", "Reformado", "Ático");

	@Override
	public List<ScoreCriteria> execute(AdVO adVO) {
		String lowerDesc = adVO.getDescription().toLowerCase();
		List<ScoreCriteria> evals = new ArrayList<>();
		keyWords.forEach(keyword -> {
			if (lowerDesc.contains(keyword.toLowerCase()))
				evals.add(ScoreCriteria.DESC_WITH_KEYWORDS);
		});
		return evals;
	}

}
