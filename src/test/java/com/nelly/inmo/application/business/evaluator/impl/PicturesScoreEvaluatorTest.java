package com.nelly.inmo.application.business.evaluator.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nelly.inmo.application.facade.helper.ScoreEvaluator.ScoreCriteria;
import com.nelly.inmo.application.service.AdsService;
import com.nelly.inmo.infrastructure.persistence.AdVO;
import com.nelly.inmo.infrastructure.persistence.PictureVO;

@ExtendWith(MockitoExtension.class)
public class PicturesScoreEvaluatorTest {

	@Mock
	private AdsService service;

	@InjectMocks
	private PicturesScoreEvaluator evaluator;

	@Test
	void testCalculatePicturesScore() {
		PictureVO pic1 = new PictureVO();
		pic1.setQuality("HD");
		PictureVO pic2 = new PictureVO();
		pic2.setQuality("SD");
		List<PictureVO> pictures = Arrays.asList(pic1, pic2);
		AdVO ad = new AdVO();
		Mockito.when(service.getPictures(ad)).thenReturn(pictures);
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(2, evals.size());
		assertTrue(evals.contains(ScoreCriteria.HD_PICTURE));
		assertTrue(evals.contains(ScoreCriteria.SD_PICTURE));
	}

	@Test
	void testCalculatePicturesScoreEmptyPictures() {
		AdVO ad = new AdVO();
		Mockito.when(service.getPictures(ad)).thenReturn(Collections.<PictureVO>emptyList());
		List<ScoreCriteria> evals = evaluator.execute(ad);
		assertEquals(1, evals.size());
		assertTrue(evals.contains(ScoreCriteria.NO_PICTURES));
	}

}
