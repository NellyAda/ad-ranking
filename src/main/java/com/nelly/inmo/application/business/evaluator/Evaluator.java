package com.nelly.inmo.application.business.evaluator;

import java.util.List;

import com.nelly.inmo.application.facade.helper.ScoreEvaluator.ScoreCriteria;
import com.nelly.inmo.infrastructure.persistence.AdVO;

public interface Evaluator {

	public List<ScoreCriteria> execute(AdVO adVO);

}
