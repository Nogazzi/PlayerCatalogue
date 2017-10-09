package pl.edu.uksw.PlayerCatalogue.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Nogaz on 21.08.2017.
 */
@Entity
@Table(name = "team")
public class Team implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    /*@OneToMany(targetEntity = Player.class, mappedBy = "team")
    private Set<Player> players;
*/
    public Team(){

    }

    public Team(final String name) {
        this.name = name;
        //this.players = new TreeSet<>();
    }

    public Team(final String name, final Set<Player> players) {
        this.name = name;
        //this.players = players;
    }

    public Team(TeamDAO teamDAO){
        this.name = teamDAO.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /*public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(final Set<Player> players) {
        this.players = players;
    }

    public void removePlayer(final Player playerToRemove){
        this.players.remove(playerToRemove);
    }

    public void addNewPlayer(final Player newPlayer){
        this.players.add(newPlayer);
    }

    public int getTeamSize(){
        return players.size();
    }
*/
    @Override
    public String toString() {
        return "Team[name: " + name /*+
                " players: " + players*/;
    }
}
