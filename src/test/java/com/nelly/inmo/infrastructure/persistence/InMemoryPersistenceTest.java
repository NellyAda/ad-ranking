package com.nelly.inmo.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InMemoryPersistenceTest {
	
	private static final Integer PICTURE_ID = 1;
	private static final Integer PICTURE_ID_KO = -100;
	
	private InMemoryPersistence inMemoryPersistence;
	
	@BeforeEach
	void setUp() throws Exception {
		inMemoryPersistence = new InMemoryPersistence();
	}

	@Test
	void testGetAds() {
		List<AdVO> ads = inMemoryPersistence.getAds();
		assertNotNull(ads, "ERROR: The list of ads should not be empty");
		assertTrue(ads.size() == 8);
	}

	@Test
	void testGetPictureOK() {
		Optional<PictureVO> optPicture = inMemoryPersistence.getPicture(PICTURE_ID);
		assertTrue(optPicture.isPresent(), "ERROR: There is no picture with the provided id");
		PictureVO picture = optPicture.get();
		assertTrue(PICTURE_ID.equals(picture.getId()));
	}
	
	@Test
	void testGetPictureNotFound() {
		Optional<PictureVO> optPicture = inMemoryPersistence.getPicture(PICTURE_ID_KO);
		assertFalse(optPicture.isPresent(), "ERROR: There is a picture with the provided id");
	}

}
