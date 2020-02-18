package com.nelly.inmo.application.facade.helper;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Nelida Nkumu Mbomio
 * 
 * This class has all the utilities to evaluate an ad score.
 * NOTE: enum ScoreCriteria could be removed and included into a properties file in order to add or remove requirements
 * avoiding manipulating the code
 *
 */
@Component
public class ScoreEvaluator {
	
	
	public enum ScoreCriteria {
		NO_PICTURES(-10),
		HD_PICTURE(20),
		SD_PICTURE(10),
		HAS_DESCRIPTION(5),
		FLAT_DESC_LENGTH_UNDER_50(10),
		FLAT_DESC_LENGTH_OVER_49(30),
		CHALET_OVER_50(20),
		DESC_WITH_KEYWORDS(5),
		IS_COMPLETE(40);
	
		private final Integer score;
	
		ScoreCriteria(Integer score) {
			this.score = score;
		}
	
		public Integer getScore() {
			return score;
		}
	}


	/**
	 * Given a list of {@link ScoreCriteria} sum up their related scores. If the result is under 0, returns 0. If the
	 * result is over 100, returns 100
	 * @param evals
	 * @return an integer with the score
	 */
	public Integer getScore(List<ScoreCriteria> evals) {
		Integer score = 0;
		for (ScoreCriteria eval : evals) {
			score += eval.getScore();
		}
		if (score < 0) {
			score = 0;
		} else if (score > 100) {
			score = 100;
		}
		return score;
	}
}
