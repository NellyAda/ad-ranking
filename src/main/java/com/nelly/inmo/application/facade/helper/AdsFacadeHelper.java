package com.nelly.inmo.application.facade.helper;

import com.nelly.inmo.infrastructure.persistence.AdVO;

/**
 * @author Nelida Nkumu Mbomio Ada This class is a helper for
 *         {@link AdsFacadeHelper}
 *
 */
public interface AdsFacadeHelper {

	/**
	 * Calculates the score and date of irrelevance for an ad ({@link AdVO}). This
	 * modifications will be updated in the system<br/>
	 * If the score is the minimum or more and it was irrelevant before, the date of
	 * irrelevance will be removed<br/>
	 * If the score is under the minimum and it was not irrelevant before, the date
	 * of irrelevance will be updated with the current date<br/>
	 * In other cases the date of irrelevance will not be modified.
	 * 
	 * @param adVO     - and ad
	 * @param minScore - the minimum score
	 * @return
	 */
	public void calculateScore(AdVO adVO, Integer minScore);

}
