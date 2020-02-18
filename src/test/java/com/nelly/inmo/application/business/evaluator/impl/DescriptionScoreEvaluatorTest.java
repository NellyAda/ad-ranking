package com.nelly.inmo.application.business.evaluator.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nelly.inmo.application.facade.helper.ScoreEvaluator.ScoreCriteria;
import com.nelly.inmo.infrastructure.persistence.AdVO;

@ExtendWith(MockitoExtension.class)
public class DescriptionScoreEvaluatorTest {

	@InjectMocks
	private DescriptionScoreEvaluator evaluator;

	@Test
	void testCalculateDescriptionScoreFlatOver49() {
		AdVO ad = new AdVO();
		ad.setDescription("01234567890123456789012345678901234567890123456789");
		ad.setTypology("FLAT");
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(2, evals.size());
		assertTrue(evals.contains(ScoreCriteria.HAS_DESCRIPTION));
		assertTrue(evals.contains(ScoreCriteria.FLAT_DESC_LENGTH_OVER_49));
	}

	@Test
	void testCalculateDescriptionScoreFlatUnder50() {
		AdVO ad = new AdVO();
		ad.setDescription("0123456789012345678901234567890123456789");
		ad.setTypology("FLAT");
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(2, evals.size());
		assertTrue(evals.contains(ScoreCriteria.HAS_DESCRIPTION));
		assertTrue(evals.contains(ScoreCriteria.FLAT_DESC_LENGTH_UNDER_50));
	}

	@Test
	void testCalculateDescriptionScoreFlatUnder20() {
		AdVO ad = new AdVO();
		ad.setDescription("0123456789012345678");
		ad.setTypology("FLAT");
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(1, evals.size());
		assertTrue(evals.contains(ScoreCriteria.HAS_DESCRIPTION));
	}

	@Test
	void testCalculateDescriptionScoreChaletOver50() {
		AdVO ad = new AdVO();
		ad.setDescription("012345678901234567890123456789012345678901234567890");
		ad.setTypology("CHALET");
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(2, evals.size());
		assertTrue(evals.contains(ScoreCriteria.HAS_DESCRIPTION));
		assertTrue(evals.contains(ScoreCriteria.CHALET_OVER_50));
	}

	@Test
	void testCalculateDescriptionScoreChaletUnder51() {
		AdVO ad = new AdVO();
		ad.setDescription("01234567890123456789012345678901234567890123456789");
		ad.setTypology("CHALET");
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(1, evals.size());
		assertTrue(evals.contains(ScoreCriteria.HAS_DESCRIPTION));
	}

	@Test
	void testCalculateDescriptionScoreGarage() {
		AdVO ad = new AdVO();
		ad.setDescription("desc");
		ad.setTypology("GARAGE");
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(1, evals.size());
		assertTrue(evals.contains(ScoreCriteria.HAS_DESCRIPTION));
	}

	@Test
	void testCalculateDescriptionScoreEmptyDesc() {
		AdVO ad = new AdVO();
		ad.setDescription("");
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(0, evals.size());
	}

}
