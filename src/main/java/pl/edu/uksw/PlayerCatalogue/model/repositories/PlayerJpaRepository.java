package pl.edu.uksw.PlayerCatalogue.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uksw.PlayerCatalogue.model.entity.Player;

import java.util.List;

/**
 * Created by Nogaz on 21.08.2017.
 */
public interface PlayerJpaRepository extends JpaRepository<Player, Long> {
    Player findById(Long id);
    List<Player> findByName(String name);
    List<Player> findBySurname(String surname);
    List<Player> findByTeamName(String teamName);
    Player findByEmail(String email);
}
