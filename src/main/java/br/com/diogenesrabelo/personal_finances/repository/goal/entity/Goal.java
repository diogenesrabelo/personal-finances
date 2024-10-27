package br.com.diogenesrabelo.personal_finances.repository.goal.entity;

import jakarta.persistence.*;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String objectives;

    @Column(columnDefinition = "TEXT")
    private String response;

    private String user;

    public Goal(){}

    public Goal buildGoal(String objectives, String response, String user) {
        var goal = new Goal();
        goal.setObjectives(objectives);
        goal.setResponse(response);
        goal.setUser(user);
        return goal;
    }

    public Long getId() {
        return id;
    }

    public String getObjectives() {
        return objectives;
    }

    public String getResponse() {
        return response;
    }

    public String getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setUser(String user) {
        this.user = user;
    }


}
