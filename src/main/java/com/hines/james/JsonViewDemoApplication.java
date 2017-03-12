package com.hines.james;

import com.hines.james.models.Fan;
import com.hines.james.models.Player;
import com.hines.james.models.Team;
import com.hines.james.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class JsonViewDemoApplication {
    @Autowired
    TeamRepository teamRepository;

	public static void main(String[] args) {
		SpringApplication.run(JsonViewDemoApplication.class, args);
	}

    @PostConstruct
    public void init() {
        List<Team> teams = new ArrayList<>();

        Set<Player> buckeyes = new HashSet<>();

        Set<Fan> bosaFans = new HashSet<>();
        bosaFans.add(new Fan("Magne Petar", 22, 130, 8, "Columbus, OH"));
        bosaFans.add(new Fan("Willifrid Atanse", 27, 220, 11, "Dayton, OH"));
        bosaFans.add(new Fan("Miloslaw Eniola", 15, 130, 8, "Cincinnati, OH"));
        buckeyes.add(new Player("Joey Bosa", "Defensive End", 23, 64, 250, bosaFans));

        Set<Fan> elliotFans = new HashSet<>();
        elliotFans.add(new Fan("Ortwin Hyeon-U", 44, 160, 7, "St. Louis, MO"));
        elliotFans.add(new Fan("Nikola Mehmood", 39, 205, 11, "Cleveland, OH"));
        elliotFans.add(new Fan("Sergei Nechtan", 22, 180, 12, "Kansas City, MO"));
        buckeyes.add(new Player("Ezekiel Elliot", "Running Back", 22, 59, 215, elliotFans));

        Set<Fan> barrettFans = new HashSet<>();
        barrettFans.add(new Fan("Lleu Leander", 44, 160, 7, "El Paso, TX"));
        barrettFans.add(new Fan("Hartwig Martinus", 39, 205, 11, "Dallas, TX"));
        barrettFans.add(new Fan("Friduman Linos", 22, 180, 12, "Houston, TX"));
        buckeyes.add(new Player("JT Barrett", "Quarterback", 21, 60, 205, barrettFans));

        teams.add(new Team("The Ohio State Buckeyes", "Columbus, OH", "Brutus Buckeye", buckeyes));

        Set<Player> cavaliers = new HashSet<>();
        cavaliers.add(new Player("Lebron James", "Small Forward", 30, 68, 240, null));
        cavaliers.add(new Player("Kyrie Irving", "Point Guard", 24, 68, 195, null));
        cavaliers.add(new Player("Kevin Love", "Small Forward", 26, 69, 235, null));

        teams.add(new Team("Cleveland Cavaliers", "Cleveland, OH", "Moondog", cavaliers));

        teamRepository.save(teams);
    }
}