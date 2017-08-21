package pl.edu.uksw.PlayerCatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uksw.PlayerCatalogue.model.entity.Player;
import pl.edu.uksw.PlayerCatalogue.model.entity.Team;
import pl.edu.uksw.PlayerCatalogue.model.repositories.PlayerJpaRepository;
import pl.edu.uksw.PlayerCatalogue.model.repositories.TeamJpaRepository;

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

    @RequestMapping(value = "/registerteam", method = RequestMethod.POST)
    ResponseEntity<String> registerNewTeam(@RequestBody Team input) {
        log.info("Sent team details" + input);
        if (teamJpaRepository.findByName(input.getName()) != null){
            teamJpaRepository.save(input);
            return ResponseEntity.ok("Team " + input.toString() + " added succefully.");
        }
        return ResponseEntity.ok("Passed team name " + input.getName() + " is already used");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeTeam(@PathVariable final Long id){
        final Team toRemove = teamJpaRepository.findById(id);
        if (toRemove != null) {
            teamJpaRepository.delete(toRemove);
            log.info("Deleted team: " + toRemove.toString());
            return ResponseEntity.ok("Team " + toRemove.toString() + " deleted succefully.");
        }
        return ResponseEntity.ok("Item to delete was not found!");
    }

}
