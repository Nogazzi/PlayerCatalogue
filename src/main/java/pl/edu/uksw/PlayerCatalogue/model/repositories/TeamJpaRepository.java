package pl.edu.uksw.PlayerCatalogue.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uksw.PlayerCatalogue.model.entity.Player;
import pl.edu.uksw.PlayerCatalogue.model.entity.Team;

import java.util.List;

/**
 * Created by Nogaz on 21.08.2017.
 */
public interface TeamJpaRepository extends JpaRepository<Team, Long> {
    Team findById(Long id);
    List<Team> findByName(String name);
    Team findByPlayerId(Long playerId);
}