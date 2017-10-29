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

import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;


public class RadarDataDumper {

    private static int carCount = 0;
    private static int streetCount = -1;

    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();

        String directoryPath = args[0];
        File directory = new File(directoryPath);

        String[] extensions = new String[]{"txt"};

        long startTime = System.currentTimeMillis();

        List<File> files = newArrayList(FileUtils.iterateFiles(directory, extensions, true));

        for (File file : files) {
            try {
                long fileStartTime = System.currentTimeMillis();
                List<String> lines = FileUtils.readLines(file);

                String streetName = lines.get(2);
                streetName = streetName.toLowerCase();

                streetName = StringUtils.substringAfterLast(streetName, " ");
                if (streetName.contains("-")) {
                    streetName = StringUtils.substringAfter(streetName, "-");
                }

                String lastStreetName = "";
                if (!lastStreetName.equals(streetName)) {
                    ++streetCount;
                }
                int streetId = hibernateUtils.getOrCreateStreet(streetName, streetCount);

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

                    cars car = new cars();
                    car.setId(carCount++);
                    car.setSpeed(speed);
                    car.setSize(length);
                    car.setTimeStamp(new Timestamp(timestamp.getMillis()));
                    car.setStreetId(streetId);
                    hibernateUtils.persist(car);
                    System.out.print(
                            lines.indexOf(line)
                                    +"/"+lines.size()
                                    +" lines | "
                                    +files.indexOf(file)
                                    +"/"+files.size()
                                    +" files | "
                                    +"current elapsed time: "
                                    + (fileStartTime - System.currentTimeMillis()) / 1000
                                    + " | "
                                    + "total elapsed time: "
                                    + (startTime - System.currentTimeMillis()) / 1000
                                    +"\r");
                }
            } catch (Exception ex) {
                System.out.println("Failed for file " + file.getName() + ": " + ex.getMessage());
            } finally {
                System.out.println("File complete.");
            }
        }
        System.out.println("Done.");
    }
}
