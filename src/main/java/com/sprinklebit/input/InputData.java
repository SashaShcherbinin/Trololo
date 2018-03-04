package com.sprinklebit.input;

import com.sprinklebit.input.pojo.Parameters;
import com.sprinklebit.input.pojo.Ride;
import com.sprinklebit.vlad.DistanceUtils;
import com.sprinklebit.vlad.Utils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputData {

    private DistanceUtils mUtils;
    private Parameters mParameters;
    private List<Ride> mRides = new ArrayList<>();

    public InputData(String path, DistanceUtils distanceUtils) {
        mUtils = distanceUtils;
        initInputData(path);
    }

    public Parameters getParameters() {
        return mParameters;
    }

    public List<Ride> getRides() {
        return mRides;
    }

    private void initInputData(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader);

            String line;
            String[] parametersAsStringArray;
            int[] parametersAsIntArray;

            int lineNumber = 0;
            final int firstLineIndex = 0;

            while ((line = br.readLine()) != null) {
                parametersAsStringArray = line.split(" ");
                parametersAsIntArray = new int[parametersAsStringArray.length];

                for (int i = 0; i < parametersAsStringArray.length; i++) {
                    parametersAsIntArray[i] = Integer.parseInt(parametersAsStringArray[i]);
                }

                if (lineNumber == firstLineIndex) {
                    mParameters = new Parameters(parametersAsIntArray);
                } else {
                    Point startPoint = new Point(parametersAsIntArray[0], parametersAsIntArray[1]);
                    Point endPoint = new Point(parametersAsIntArray[2], parametersAsIntArray[3]);
                    int earliestStart = parametersAsIntArray[4];
                    int latestEnd = parametersAsIntArray[5];
                    int number = lineNumber - 1;
                    int distance = mUtils.getRideDistance(startPoint, endPoint);

                    mRides.add(new Ride(startPoint, endPoint, earliestStart, latestEnd, number, distance));
                }

                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
