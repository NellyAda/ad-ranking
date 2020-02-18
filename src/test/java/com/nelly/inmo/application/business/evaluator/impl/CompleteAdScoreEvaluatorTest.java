package com.nelly.inmo.application.business.evaluator.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nelly.inmo.application.facade.helper.ScoreEvaluator.ScoreCriteria;
import com.nelly.inmo.infrastructure.persistence.AdVO;

@ExtendWith(MockitoExtension.class)
public class CompleteAdScoreEvaluatorTest {

	@InjectMocks
	private CompleteAdScoreEvaluator evaluator;

	@Test
	void testCalculateCompleteAdScoreChaletOK() {
		AdVO ad = new AdVO();
		ad.setPictures(Arrays.asList(1));
		ad.setDescription("desc");
		ad.setTypology("CHALET");
		ad.setHouseSize(300);
		ad.setGardenSize(30);
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(1, evals.size());
		assertTrue(evals.contains(ScoreCriteria.IS_COMPLETE));
	}

	@Test
	void testCalculateCompleteAdScoreChaletKO() {
		AdVO ad = new AdVO();
		ad.setPictures(Arrays.asList(1));
		ad.setDescription("desc");
		ad.setTypology("CHALET");
		ad.setHouseSize(300);
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(0, evals.size());
	}

	@Test
	void testCalculateCompleteAdScoreFlatOK() {
		AdVO ad = new AdVO();
		ad.setPictures(Arrays.asList(1));
		ad.setDescription("desc");
		ad.setTypology("FLAT");
		ad.setHouseSize(300);
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(1, evals.size());
		assertTrue(evals.contains(ScoreCriteria.IS_COMPLETE));
	}

	@Test
	void testCalculateCompleteAdScoreFlatKO() {
		AdVO ad = new AdVO();
		ad.setPictures(Arrays.asList(1));
		ad.setDescription("desc");
		ad.setTypology("FLAT");
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(0, evals.size());
	}

	@Test
	void testCalculateCompleteAdScoreNoPics() {
		AdVO ad = new AdVO();
		ad.setPictures(Collections.<Integer>emptyList());
		ad.setDescription("desc");
		ad.setTypology("CHALET");
		ad.setHouseSize(300);
		ad.setGardenSize(30);
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(0, evals.size());
	}

	@Test
	void testCalculateCompleteAdScoreNoDesc() {
		AdVO ad = new AdVO();
		ad.setPictures(Arrays.asList(1));
		ad.setDescription("");
		ad.setTypology("CHALET");
		ad.setHouseSize(300);
		ad.setGardenSize(30);
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(0, evals.size());
	}

	@Test
	void testCalculateCompleteAdScoreGarage() {
		AdVO ad = new AdVO();
		ad.setPictures(Arrays.asList(1));
		ad.setDescription("");
		ad.setTypology("GARAGE");
		ad.setHouseSize(30);
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(1, evals.size());
		assertTrue(evals.contains(ScoreCriteria.IS_COMPLETE));
	}

}
