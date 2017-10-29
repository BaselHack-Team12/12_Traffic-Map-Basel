package com.baselhack17.team12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.google.common.io.Resources;

/**
 * //TODO write here something nicer.
 */
public class DangerCalculator {

    public static void main(String[] args) {
        DAO dao = new DAO();
        File speedingCarsPerStreet = new File(Resources.getResource("speedingCarsPerStreet.csv").getFile());
        File totalCarsPerStreet = new File(Resources.getResource("totalCarsPerStreet.csv").getFile());

        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(speedingCarsPerStreet));
            while ((line = bufferedReader.readLine()) != null) {
                String[] aLine = line.split(",");
                String streetName = aLine[0];
                String count = aLine[1];
                if (count == null || streetName == null) {
                    System.out.println("here");
                    continue;
                }
                streets street = dao.getStreet(streetName);
                if (street == null) {
                    System.out.println("here");
                    continue;
                }
                dao.updateSpeedingCarCount(street.getId(), Integer.parseInt(count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
        System.out.println("Done !");
    }
}
