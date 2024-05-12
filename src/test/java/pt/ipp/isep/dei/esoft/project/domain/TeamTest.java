package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void ensureCollaboratorIsCreatedSuccessfully() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Collaborator collaborator1 = new Collaborator("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
        "960144802", "emily@is.real", "CC", "15017808", "256", job);
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator);
        teamMembers.add(collaborator1);
        Skill skill1 = new Skill("Tree Pruner");
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);

        Team team = new Team(teamMembers, teamSkills);
    }

    @Test
    void ensureTeamParamsAreNotNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Team(null, null));
    }

    @Test
    void ensureTeamHasMembers() {
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        Skill skill1 = new Skill("Tree Pruner");
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);
        assertThrows(IllegalArgumentException.class,
                () -> new Team(teamMembers, teamSkills));
    }

    @Test
    void ensureTeamHasSkills() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Collaborator collaborator1 = new Collaborator("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job);
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator);
        teamMembers.add(collaborator1);
        ArrayList<Skill> teamSkills = new ArrayList<>();
        assertThrows(IllegalArgumentException.class,
                () -> new Team(teamMembers, teamSkills));
    }

    @Test
    void testEqualsSameObject() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Collaborator collaborator1 = new Collaborator("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job);
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator);
        teamMembers.add(collaborator1);
        Skill skill1 = new Skill("Tree Pruner");
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);

        Team team = new Team(teamMembers, teamSkills);

        assertEquals(team, team);
    }

    @Test
    void testEqualsDifferentClass() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Collaborator collaborator1 = new Collaborator("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job);
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator);
        teamMembers.add(collaborator1);
        Skill skill1 = new Skill("Tree Pruner");
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);

        Team team = new Team(teamMembers, teamSkills);

        assertNotEquals(team, new Object());
    }

    @Test
    void testEqualsNull() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Collaborator collaborator1 = new Collaborator("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job);
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator);
        teamMembers.add(collaborator1);
        Skill skill1 = new Skill("Tree Pruner");
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);

        Team team = new Team(teamMembers, teamSkills);

        assertNotEquals(team, null);
    }

    @Test
    void testEqualsDifferentObject() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Collaborator collaborator1 = new Collaborator("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job);
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator);
        teamMembers.add(collaborator1);
        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Light Vehicle Driver");
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);
        teamSkills.add(skill2);

        Team team = new Team(teamMembers, teamSkills);

        ArrayList<Collaborator> teamMembers1 = new ArrayList<>();
        teamMembers1.add(collaborator);
        ArrayList<Skill> teamSkills1 = new ArrayList<>();
        teamSkills1.add(skill1);

        Team team1 = new Team(teamMembers1, teamSkills1);

        assertNotEquals(team, team1);
    }

    @Test
    void testEqualsSameObjectSameMembers() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Collaborator collaborator1 = new Collaborator("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job);
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator);
        teamMembers.add(collaborator1);
        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Light Vehicle Driver");
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);
        teamSkills.add(skill2);

        Team team = new Team(teamMembers, teamSkills);

        ArrayList<Collaborator> teamMembers1 = new ArrayList<>();
        teamMembers1.add(collaborator);
        teamMembers1.add(collaborator1);
        ArrayList<Skill> teamSkills1 = new ArrayList<>();
        teamSkills1.add(skill1);

        Team team1 = new Team(teamMembers1, teamSkills1);

        assertEquals(team, team1);
    }

    @Test
    void testHashCodeSameObject() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Collaborator collaborator1 = new Collaborator("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job);
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator);
        teamMembers.add(collaborator1);
        Skill skill1 = new Skill("Tree Pruner");
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);

        Team team = new Team(teamMembers, teamSkills);

        assertEquals(team.hashCode(), team.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Collaborator collaborator1 = new Collaborator("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job);
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator);
        teamMembers.add(collaborator1);
        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Light Vehicle Driver");
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);
        teamSkills.add(skill2);

        Team team = new Team(teamMembers, teamSkills);

        ArrayList<Collaborator> teamMembers1 = new ArrayList<>();
        teamMembers1.add(collaborator);
        ArrayList<Skill> teamSkills1 = new ArrayList<>();
        teamSkills1.add(skill1);

        Team team1 = new Team(teamMembers1, teamSkills1);

        assertNotEquals(team.hashCode(), team1.hashCode());
    }

    @Test
    void testGetTeamMembers(){
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Collaborator collaborator1 = new Collaborator("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job);
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator);
        teamMembers.add(collaborator1);
        Skill skill1 = new Skill("Tree Pruner");
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);

        Team team = new Team(teamMembers, teamSkills);

        assertEquals(teamMembers, team.getTeamMembers());
    }
}