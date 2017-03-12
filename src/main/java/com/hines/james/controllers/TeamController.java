package com.hines.james.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hines.james.models.Team;
import com.hines.james.repositories.TeamRepository;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.JsonViewSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import static com.monitorjbl.json.Match.match;

@RequestMapping("/")
@RepositoryRestController
public class TeamController {
    @Autowired
    TeamRepository teamRepository;

    @ResponseBody
    @RequestMapping(value = "teams/{id}", method = RequestMethod.GET)
    public Team getTeam(@PathVariable Long id) throws JsonProcessingException {
        Team team = null;

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(JsonView.class, new JsonViewSerializer());
        mapper.registerModule(module);

//        String json = mapper.writeValueAsString(
//                JsonView.with(teamRepository.findOne(id))
//                        .onClass(Team.class, match()
//                            .include("*"))
//                        .onClass(Player.class, match()
//                            .include(new String[] {"name", "position", "fans"})
//                            .exclude("*"))
//                        .onClass(Fan.class, match()
//                            .include("*")
//                            .exclude(new String[] {"weight", "shoeSize", "address"})
//                        ));

        String json = mapper.writeValueAsString(
                JsonView.with(teamRepository.findOne(id))
                        .onClass(Team.class, match()
                            .include("*")
                            .exclude(new String[] {
                                    "location",
                                    "players.height",
                                    "players.weight",
                                    "players.fans.weight",
                                    "players.fans.age"
                            })
                        ));

        try {
            team = mapper.readValue(json, Team.class);
        } catch (IOException io) {
            System.err.println("Caught IOException: " + io.getMessage());
        }

        return team;
    }
}
