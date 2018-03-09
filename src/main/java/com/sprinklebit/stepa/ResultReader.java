package com.sprinklebit.stepa;

import com.sun.deploy.util.ArrayUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultReader {
    private List<StepaRoute> stepaRoutes = new ArrayList<>();

    public ResultReader(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader);

            String line;
            String[] parametersAsStringArray;
            int[] parametersAsIntArray;

            while ((line = br.readLine()) != null) {
                parametersAsStringArray = line.split(" ");
                parametersAsIntArray = new int[parametersAsStringArray.length - 1];
                int ridesQty = Integer.parseInt(parametersAsStringArray[0]);

                for (int i = 1; i < parametersAsStringArray.length; i++) {
                    parametersAsIntArray[i - 1] = Integer.parseInt(parametersAsStringArray[i]);
                }

                StepaRoute route = new StepaRoute(ridesQty, parametersAsIntArray);

                stepaRoutes.add(route);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<StepaRoute> getStepaRoutes() {
        return stepaRoutes;
    }
}
