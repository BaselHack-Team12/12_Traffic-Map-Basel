/*
 * $Id: HttpFilter 3988 2017-06-21 13:47:09Z cfi $
 * Created on 29.10.17 13:40
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

public class DangerosityLevelCalculator {

    public static void main(String[] args) {
        DAO utils = new DAO();
        List<streets> streets = utils.getStreets();

        for (streets street : streets) {
            if(street.getSpeedingCarsCount() == null || street.getTotalCarsCount() ==  null) {
                System.out.println("here");
                continue;
            }
            Float dangerLevel = (float) street.getSpeedingCarsCount() / street.getTotalCarsCount() * 100;
            if (street.getSpeedingCarsCount().equals(street.getTotalCarsCount())) {
                continue;
            }
            utils.updateStreet(street.getId(), dangerLevel);
            System.out.println(street.getStreetName() + " : " + dangerLevel);
        }
    }

}
