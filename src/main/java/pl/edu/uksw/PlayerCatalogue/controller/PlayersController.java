package pl.edu.uksw.PlayerCatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uksw.PlayerCatalogue.model.entity.Player;
import pl.edu.uksw.PlayerCatalogue.model.repositories.PlayerJpaRepository;
import pl.edu.uksw.PlayerCatalogue.model.repositories.TeamJpaRepository;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Nogaz on 21.08.2017.
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/players")
public class PlayersController {

    static Logger log = Logger.getLogger(PlayersController.class.getName());

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Autowired
    private TeamJpaRepository teamJpaRepository;

    @GetMapping(value = "/")
    public List<Player> findAll() {

        log.info("findAll");
        return playerJpaRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Player findById(@PathVariable final Long id) {
        log.info("findById: " + id);
        return playerJpaRepository.findById(id);
    }

    @GetMapping(value = "/byteamname")
    public List<Player> findByTeamName(@PathVariable final String teamName) {
        log.info("findByTeamName: " + teamName);
        return playerJpaRepository.findByTeam(teamName);
    }

    @RequestMapping(value = "/registerplayer", method = RequestMethod.POST)
    ResponseEntity<String> registerNewEvent(@RequestBody Player input) {
        log.info("Sent player details" + input);
        log.info("Sent player details. Name: " + input.getName()
                + ", Surname: " + input.getSurname()
                + ", birthdate: " + input.getBirthDate()
                + ", nationality: " + input.getNationality()
                + ", team: " + input.getTeam()
                + ", email: " + input.getEmail());
        log.info("Find by email result: " + playerJpaRepository.findByEmail(input.getEmail()));
        if (playerJpaRepository.findByEmail(input.getEmail()) == null){

            Player newPlayer = new Player(
                    input.getName(),
                    input.getSurname(),
                    input.getBirthDate(),
                    input.getNationality(),
                    input.getTeam(),
                    input.getEmail());
            playerJpaRepository.save(newPlayer);

            return ResponseEntity.ok("Event " + newPlayer.toString() + " added succefully.");
        }
        return ResponseEntity.ok("Passed email " + input.getEmail() + " is used");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeEvent(@PathVariable final Long id){
        log.info("Wants to delete playerById: " + id);
        final Player toRemove = playerJpaRepository.findById(id);

        if (toRemove != null) {
            playerJpaRepository.delete(toRemove);
            log.info("Deleted player: " + toRemove.toString());
            return ResponseEntity.ok("Event " + toRemove.toString() + " deleted succefully.");
        }
        return ResponseEntity.ok("Item to delete was not found!");
    }
}
