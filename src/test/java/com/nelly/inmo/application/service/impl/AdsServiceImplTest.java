package com.nelly.inmo.application.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nelly.inmo.infrastructure.persistence.AdVO;
import com.nelly.inmo.infrastructure.persistence.InMemoryPersistence;
import com.nelly.inmo.infrastructure.persistence.PictureVO;

@ExtendWith(MockitoExtension.class)
public class AdsServiceImplTest {

	private static final Integer AD_ID = -1;
	private static final Integer AD_NO_PIC_ID = -2;
	private static final Integer PIC_ID = -1;
	private static final String URL = "picture/url";

	@Mock
	private InMemoryPersistence inMemoryPersistence;

	@InjectMocks
	private AdsServiceImpl adsService;

	private List<AdVO> adsInMemory;
	private AdVO ad;
	private AdVO adNoPics;

	private PictureVO picture;
	private List<PictureVO> picturesInMemory;

	@BeforeEach
	void setUp() throws Exception {

		picture = new PictureVO();
		picture.setId(PIC_ID);
		picture.setUrl(URL);

		picturesInMemory = Arrays.asList(picture);

		ad = new AdVO();
		ad.setId(AD_ID);
		ad.setPictures(Arrays.asList(PIC_ID));

		adNoPics = new AdVO();
		adNoPics.setId(AD_NO_PIC_ID);
		adNoPics.setPictures(Collections.<Integer>emptyList());

		adsInMemory = Arrays.asList(ad, adNoPics);
	}

	@Test
	void testGetAds() {
		Mockito.when(inMemoryPersistence.getAds()).thenReturn(adsInMemory);

		List<AdVO> ads = adsService.getAds();
		assertEquals(ads.size(), adsInMemory.size());
		assertTrue(AD_ID.equals(ads.get(0).getId()));
	}

	@Test
	void testGetPictures() {
		Mockito.when(inMemoryPersistence.getPicture(PIC_ID)).thenReturn(Optional.of(picture));

		List<PictureVO> pictures = adsService.getPictures(ad);
		assertEquals(pictures.size(), picturesInMemory.size());
		assertTrue(PIC_ID.equals(pictures.get(0).getId()));
	}

	@Test
	void testGetPicturesNotExistingAd() {

		List<PictureVO> pictures = adsService.getPictures(ad);
		assertNotNull(pictures);
		assertTrue(pictures.size() == 0);
	}

	@Test
	void testGetPicturesUrls() {
		Mockito.when(inMemoryPersistence.getPicture(PIC_ID)).thenReturn(Optional.of(picture));

		List<String> picturesUrls = adsService.getPicturesUrls(ad);
		assertEquals(picturesUrls.size(), picturesInMemory.size());
		assertTrue(URL.equals(picturesUrls.get(0)));
	}

	@Test
	void testGetPicturesUrlsAdWithoutPictures() {
		List<String> picturesUrls = adsService.getPicturesUrls(adNoPics);
		assertNotNull(picturesUrls);
		assertTrue(picturesUrls.size() == 0);
	}

}
