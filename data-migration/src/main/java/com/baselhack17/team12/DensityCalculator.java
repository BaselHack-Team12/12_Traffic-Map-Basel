/*
 * $Id: HttpFilter 3988 2017-06-21 13:47:09Z cfi $
 * Created on 29.10.17 14:13
 * 
 * Copyright (c) 2017 by bluesky IT-Solutions AG,
 * Kaspar-Pfeiffer-Strasse 4, 4142 Muenchenstein, Switzerland.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of bluesky IT-Solutions AG ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with bluesky IT-Solutions AG.
 */
package com.baselhack17.team12;

import java.util.List;

public class DensityCalculator {

    public static void main(String[] args) {
        DAO utils = new DAO();
        List<streets> streets = utils.getStreets();
        Long totalCarCount = utils.getTotalCarCount();
        Float totalDensity = 0f;

        for (streets street : streets) {
            if(street.getTotalCarsCount() == null) continue;
            Float density = (float) street.getTotalCarsCount() / totalCarCount * 100;
            totalDensity += density;
            utils.updateDensityForStreet(street.getId(), density);
            System.out.println(street.getStreetName() + " : " + density);
        }
        System.out.println("Total: " + totalDensity);
    }
}
