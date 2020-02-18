package com.nelly.inmo.application.facade.converter.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nelly.inmo.application.service.AdsService;
import com.nelly.inmo.infrastructure.api.PublicAd;
import com.nelly.inmo.infrastructure.api.QualityAd;
import com.nelly.inmo.infrastructure.persistence.AdVO;

@ExtendWith(MockitoExtension.class)
class AdConverterImplTest {

	@Mock
	private AdsService service;

	@InjectMocks
	private AdConverterImpl converter;

	private List<AdVO> ads;

	@BeforeEach
	void setUp() throws Exception {
		ads = new ArrayList<AdVO>();
		ads.add(new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!",
				Collections.<Integer>emptyList(), 300, null, null, null));
		ads.add(new AdVO(2, "FLAT",
				"Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo",
				Arrays.asList(4), 300, null, null, null));
	}

	@Test
	void testGetQualityAds() {
		Mockito.when(service.getPicturesUrls(Mockito.any(AdVO.class))).thenReturn(Collections.<String>emptyList());
		List<QualityAd> qualityAds = converter.toQualityAds(ads);
		assertEquals(2, qualityAds.size());
	}

	@Test
	void testGetPublicAds() {
		Mockito.when(service.getPicturesUrls(Mockito.any(AdVO.class))).thenReturn(Collections.<String>emptyList());
		List<PublicAd> publicAds = converter.toPublicAds(ads);
		assertEquals(2, publicAds.size());
	}

}
