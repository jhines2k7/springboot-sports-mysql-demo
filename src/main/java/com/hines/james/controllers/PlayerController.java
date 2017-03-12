package com.hines.james.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hines.james.models.Player;
import com.hines.james.repositories.PlayerRepository;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.JsonViewSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import static com.monitorjbl.json.Match.match;

@RequestMapping("/")
@RepositoryRestController
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    private Environment environment;

//    private JsonResult json = JsonResult.instance();

//    @Value("${client2.exclude}")
//    String[] exclude;
//
//    @Value("${client2.include")
//    String[] include;

    @ResponseBody
    @RequestMapping(value = "players/{id}", method = RequestMethod.GET)
    public Player getPlayer(@PathVariable Long id) throws JsonProcessingException {
        Player player = null;

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(JsonView.class, new JsonViewSerializer());
        mapper.registerModule(module);

//        String[] include = getIncludedProps("client1").split(",");
//        String[] exclude = getExcludedProps("client1").split(",");

        String[] include = getIncludedProps("client2").split(",");
        String[] exclude = getExcludedProps("client2").split(",");

        String json = mapper.writeValueAsString(
            JsonView.with(playerRepository.findOne(id))
                .onClass(Player.class, match()
                    .include(include)
                    .exclude(exclude)
                ));

        try {
            player = mapper.readValue(json, Player.class);
        } catch (IOException io) {
            System.err.println("Caught IOException: " + io.getMessage());
        }

        return player;
    }

    private String getIncludedProps(String client) {
        return environment.resolvePlaceholders("${" + client + ".include}");
    }

    private String getExcludedProps(String client) {
        return environment.resolvePlaceholders("${" + client + ".exclude}");
    }
}