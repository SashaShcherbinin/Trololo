package com.sprinklebit.input;

import com.sprinklebit.input.pojo.Ride;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputData {

    public List<Ride> readInputData(String path) {

        List<Ride> inputParameters = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader);

            String line;
            String[] parametersAsStringArray;
            int[] parametersAsIntArray;

            while ((line = br.readLine()) != null) {
                parametersAsStringArray = line.split(" ");
                parametersAsIntArray = new int[parametersAsStringArray.length];

                for (int i = 0; i < parametersAsStringArray.length; i++) {
                    parametersAsIntArray[i] = Integer.parseInt(parametersAsStringArray[i]);
                }

//                inputParameters.add(parametersAsIntArray);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputParameters;
    }
}
