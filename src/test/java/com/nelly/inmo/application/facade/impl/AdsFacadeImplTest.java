package com.nelly.inmo.application.facade.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nelly.inmo.application.facade.converter.impl.AdConverterImpl;
import com.nelly.inmo.application.facade.helper.impl.AdsFacadeImpl;
import com.nelly.inmo.application.service.AdsService;
import com.nelly.inmo.application.service.impl.AdsServiceImpl;
import com.nelly.inmo.infrastructure.api.PublicAd;
import com.nelly.inmo.infrastructure.persistence.AdVO;

/**
 * 
 * @author Nelida Nkumu Mbomio Ada
 *
 *         The method {@link AdsFacadeImpl#qualityListing()} will not be
 *         implemented as it just invokes two methods already tested in
 *         {@link AdsServiceImpl#getAds()} and
 *         {@link AdConverterImpl#toQualityAds(List)}
 */
@ExtendWith(MockitoExtension.class)
class AdsFacadeImplTest {

	@Mock
	private AdsService service;

	@Spy
	private AdConverterImpl converter;

	@InjectMocks
	private AdsFacadeImpl facade;

	private List<AdVO> ads;

	@BeforeEach
	void setUp() throws Exception {
		ads = new ArrayList<AdVO>();
		ads.add(new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!",
				Collections.<Integer>emptyList(), 300, null, 15, new Date()));
		ads.add(new AdVO(2, "FLAT",
				"Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo",
				Arrays.asList(4), 300, null, 100, null));
	}

	@Disabled
	@Test
	void testPublicListing() {
		Mockito.when(service.getAds()).thenReturn(ads);
		List<PublicAd> publicAds = facade.publicListing();
		assertEquals(1, publicAds.size());
		PublicAd publicAd = publicAds.get(0);
		assertTrue(publicAd.getId().equals(2));
	}

	@Disabled
	@Test
	void testCalculateScore() {
		fail("Not yet implemented");
	}

}
