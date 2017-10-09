package pl.edu.uksw.PlayerCatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uksw.PlayerCatalogue.model.entity.Player;
import pl.edu.uksw.PlayerCatalogue.model.entity.Team;
import pl.edu.uksw.PlayerCatalogue.model.entity.TeamDAO;
import pl.edu.uksw.PlayerCatalogue.model.repositories.PlayerJpaRepository;
import pl.edu.uksw.PlayerCatalogue.model.repositories.TeamJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Nogaz on 21.08.2017.
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/teams")
public class TeamController {

    static Logger log = Logger.getLogger(TeamController.class.getName());

    @Autowired
    private TeamJpaRepository teamJpaRepository;

    @GetMapping(value = "/")
    public List<Team> findAll() {
        return teamJpaRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Team findById(@PathVariable final Long id) {
        return teamJpaRepository.findById(id);
    }

    @PostMapping(value = "/registernewteam")
    ResponseEntity<String> registerNewTeam(@RequestBody TeamDAO input) {
        log.info("Sent team details " + input.getName());
        List<Team> teams = teamJpaRepository.findByName(input.getName());
        log.info("Found " + teams.size() + " teams with name: " + input.getName());
        if(teams.isEmpty()) {
            Team newTeam = new Team(input);
            teamJpaRepository.save(newTeam);
            return ResponseEntity.ok("Team " + newTeam.toString() + " added succefully.");
        }
        log.info("Passed team name " + input.getName() + " is already used");
        return ResponseEntity.ok("Passed team name " + input.getName() + " is already used");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> removeTeam(@PathVariable final Long id){

        final Team toRemove = teamJpaRepository.findById(id);
        if (toRemove != null) {
            teamJpaRepository.delete(id);
            log.info("Deleted team: " + toRemove.toString() + "]");
            return ResponseEntity.ok("Team " + toRemove.toString() + " deleted succefully.");
        }
        return ResponseEntity.ok("Item to delete was not found!");
    }

}
