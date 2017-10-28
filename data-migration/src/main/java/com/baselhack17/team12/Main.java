/*
 * $Id: HttpFilter 3988 2017-06-21 13:47:09Z cfi $
 * Created on 28.10.17 15:58
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

import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


public class Main {

    public static void main(String[] args) {

        List<Streets> streets = new ArrayList<Streets>();
        List<Cars> cars = new ArrayList<Cars>();


        //String directoryPath = args[0];
        //File directory = new File(directoryPath);

        String[] extensions = new String[]{
                "txt"
        };

        //List<File> files = newArrayList(FileUtils.iterateFiles(directory, extensions, true));

        List<File> files = new ArrayList();
        try {
            files.add(new File(Resources.getResource("test.txt").toURI()));
        } catch (Exception ex) {

        }

        for (File file : files) {
            try {
                List<String> lines = FileUtils.readLines(file);

                String streetname = lines.get(2);
                streetname = StringUtils.substringAfterLast(streetname, " ");
                if (streetname.contains("-")) {
                    streetname = StringUtils.substringAfter(streetname, "-");
                }
                System.out.println(streetname);
                Streets street = new Streets();
                street.setStreetName(streetname);

                if(HibernateUtils.isStreetExists(Streets.class, street.getStreetName())) {
                    System.out.println("test");
                }

                boolean found = false;
                for (Streets s : streets) {
                    if (s.getStreetName().equals(streetname)) {
                        street = s;
                        found = true;
                    }
                }
                if (!found) {
                    streets.add(street);
                }

                lines = lines.subList(6, lines.size() - 1);
                for (String line : lines) {
                    String[] values = line.split("\t");
                    Double speed = Double.parseDouble(values[0]);
                    String[] time = values[1].split(":");
                    String[] date = values[2].split("\\.");
                    DateTime timestamp = new DateTime(DateTimeZone.forID("Europe/Zurich"))
                            .withHourOfDay(Integer.parseInt(time[0]))
                            .withMinuteOfHour(Integer.parseInt(time[1]))
                            .withSecondOfMinute(Integer.parseInt(time[2]))
                            .withDayOfMonth(Integer.parseInt(date[0]))
                            .withMonthOfYear(Integer.parseInt(date[1]))
                            .withYearOfCentury(Integer.parseInt(date[2]));
                    System.out.println(timestamp.toString());

                    Double length = Double.parseDouble(values[4]);

                    Cars car = new Cars();
                    car.setSpeed(speed);
                    car.setSize(length);
                    car.setTimeStamp(new java.sql.Date(timestamp.toDate().getTime()));
                    car.setStreetId(street.getId());

                    cars.add(car);
                    //Persist car

                }

            } catch (IOException ex) {
                System.out.println("Failed for file " + file.getName() + ": " + ex.getMessage());
            }
            finally {
                System.out.println("Done.");
            }
        }
    }
}
