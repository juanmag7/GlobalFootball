package com.apuestas.globalfootball.util;

import com.apuestas.globalfootball.model.Match;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    public static List<Match> load(String fileName) {
        List<Match> matches = new ArrayList<>();

        try {
            InputStream is = CSVLoader.class.getResourceAsStream("/" + fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            br.readLine(); // Leer encabezado
            String line;

            // Formato real de tu CSV -> dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

            while ((line = br.readLine()) != null) {

                // Tu CSV usa punto y coma (;)
                String[] data = line.split(";");

                LocalDate date = LocalDate.parse(data[0].trim(), formatter);
                String homeTeam = data[1].trim();
                String awayTeam = data[2].trim();
                int homeGoals = Integer.parseInt(data[3].trim());
                int awayGoals = Integer.parseInt(data[4].trim());

                matches.add(new Match(date, homeTeam, awayTeam, homeGoals, awayGoals));
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return matches;
    }
}
