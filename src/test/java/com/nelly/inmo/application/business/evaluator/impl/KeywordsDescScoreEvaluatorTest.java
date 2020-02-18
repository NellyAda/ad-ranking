package com.nelly.inmo.application.business.evaluator.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nelly.inmo.application.facade.helper.ScoreEvaluator.ScoreCriteria;
import com.nelly.inmo.infrastructure.persistence.AdVO;

@ExtendWith(MockitoExtension.class)
public class KeywordsDescScoreEvaluatorTest {

	@InjectMocks
	private KeywordsDescScoreEvaluator evaluator;

	@Test
	void testCalcutateKeyWordsDescScore() {
		AdVO ad = new AdVO();
		ad.setDescription(
				"Nuevo ático céntrico recién reformado. Luminoso!!!. No deje pasar la oportunidad y adquiera este ático de lujo");
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(5, evals.size());
	}

}
