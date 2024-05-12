package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TeamRepositoryTest {

    @Test
    void ensureNewTeamSuccessfullyAdded() {
        TeamRepository TeamRepository = new TeamRepository();
        SkillRepository SkillRepository = new SkillRepository();
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        JobRepository JobRepository = new JobRepository();
        Skill skill1 = SkillRepository.add("Tree Pruner").get();
        Skill skill2 = SkillRepository.add("Light Vehicle Driver").get();
        Skill skill3 = SkillRepository.add("Lifting").get();
        Job job = JobRepository.add("Member").get();
        Collaborator collaborator1 = CollaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job).get();
        Collaborator collaborator2 = CollaboratorRepository.add("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job).get();
        Collaborator collaborator3 = CollaboratorRepository.add("Exas", "2005/05/22", "2023/05/22", "Their house, duh",
                "960144802", "exas@is.real", "CC", "15017809", "256", job).get();
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator1);
        teamMembers.add(collaborator2);
        teamMembers.add(collaborator3);
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);
        teamSkills.add(skill2);
        teamSkills.add(skill3);
        assertTrue(TeamRepository.add(teamMembers, teamSkills).isPresent());
    }

    @Test
    void ensureGetAllCollaboratorsIsEmptyWithNoTeams(){
        TeamRepository TeamRepository = new TeamRepository();
        assertTrue(TeamRepository.getAllCollaboratorsInTeams().isEmpty());
    }

    @Test
    void ensureGetAllCollaboratorsInTeamsReturnsTheCorrectList() {
        TeamRepository TeamRepository = new TeamRepository();
        SkillRepository SkillRepository = new SkillRepository();
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        JobRepository JobRepository = new JobRepository();
        Skill skill1 = SkillRepository.add("Tree Pruner").get();
        Skill skill2 = SkillRepository.add("Light Vehicle Driver").get();
        Skill skill3 = SkillRepository.add("Lifting").get();
        Job job = JobRepository.add("Member").get();
        Collaborator collaborator1 = CollaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job).get();
        Collaborator collaborator2 = CollaboratorRepository.add("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job).get();
        Collaborator collaborator3 = CollaboratorRepository.add("Exas", "2005/05/22", "2023/05/22", "Their house, duh",
                "960144802", "exas@is.real", "CC", "15017809", "256", job).get();
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator1);
        teamMembers.add(collaborator2);
        teamMembers.add(collaborator3);
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);
        teamSkills.add(skill2);
        teamSkills.add(skill3);

        TeamRepository.add(teamMembers, teamSkills);

        boolean sameList = true;

        ArrayList<Collaborator> returnedList = TeamRepository.getAllCollaboratorsInTeams();
        if(returnedList.size() != teamMembers.size()) {
            sameList = false;
        }else{
            for(Collaborator collaborator : returnedList) {
                if(!teamMembers.contains(collaborator)) {
                    sameList = false;
                }
            }
        }

        assertTrue(sameList);
    }

    @Test
    void ensureAddingDuplicateTeamFails() {
        //Arrange
        TeamRepository TeamRepository = new TeamRepository();
        SkillRepository SkillRepository = new SkillRepository();
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        JobRepository JobRepository = new JobRepository();
        Skill skill1 = SkillRepository.add("Tree Pruner").get();
        Skill skill2 = SkillRepository.add("Light Vehicle Driver").get();
        Skill skill3 = SkillRepository.add("Lifting").get();
        Job job = JobRepository.add("Member").get();
        Collaborator collaborator1 = CollaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job).get();
        Collaborator collaborator2 = CollaboratorRepository.add("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job).get();
        Collaborator collaborator3 = CollaboratorRepository.add("Exas", "2005/05/22", "2023/05/22", "Their house, duh",
                "960144802", "exas@is.real", "CC", "15017809", "256", job).get();
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator1);
        teamMembers.add(collaborator2);
        teamMembers.add(collaborator3);
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);
        teamSkills.add(skill2);
        teamSkills.add(skill3);

        TeamRepository.add(teamMembers, teamSkills);

        assertTrue(TeamRepository.add(teamMembers, teamSkills).isEmpty());
    }

    @Test
    void ensureAddingDifferentTeamsWorks() {
        //Arrange
        TeamRepository TeamRepository = new TeamRepository();
        SkillRepository SkillRepository = new SkillRepository();
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        JobRepository JobRepository = new JobRepository();
        Skill skill1 = SkillRepository.add("Tree Pruner").get();
        Skill skill2 = SkillRepository.add("Light Vehicle Driver").get();
        Skill skill3 = SkillRepository.add("Lifting").get();
        Job job = JobRepository.add("Member").get();
        Collaborator collaborator1 = CollaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job).get();
        Collaborator collaborator2 = CollaboratorRepository.add("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job).get();
        Collaborator collaborator3 = CollaboratorRepository.add("Exas", "2005/05/22", "2023/05/22", "Their house, duh",
                "960144802", "exas@is.real", "CC", "15017809", "256", job).get();
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        teamMembers.add(collaborator1);
        teamMembers.add(collaborator2);
        teamMembers.add(collaborator3);
        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);
        teamSkills.add(skill2);
        teamSkills.add(skill3);

        TeamRepository.add(teamMembers, teamSkills);

        ArrayList<Collaborator> otherTeamMembers = new ArrayList<>();
        otherTeamMembers.add(teamMembers.get(1));
        otherTeamMembers.add(teamMembers.get(2));

        assertTrue(TeamRepository.add(otherTeamMembers, teamSkills).isPresent());
    }
}