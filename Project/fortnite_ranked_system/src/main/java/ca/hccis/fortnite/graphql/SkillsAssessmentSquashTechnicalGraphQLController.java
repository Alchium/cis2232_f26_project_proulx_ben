/*
package ca.hccis.fortnite.graphql;

import ca.hccis.fortnite.jpa.entity.MatchEntry;
import ca.hccis.fortnite.repositories.SkillsAssessmentSquashTechnicalRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SkillsAssessmentSquashTechnicalGraphQLController {

    private final SkillsAssessmentSquashTechnicalRepository repo;

    public SkillsAssessmentSquashTechnicalGraphQLController(SkillsAssessmentSquashTechnicalRepository repo) {
        this.repo = repo;
    }

    @QueryMapping
    public List<MatchEntry> assessments() {
        List<MatchEntry> all = new ArrayList<>();
        repo.findAll().forEach(all::add);
        return all;
    }

    @QueryMapping
    public MatchEntry assessmentById(@Argument Integer id) {
        Optional<MatchEntry> opt = repo.findById(id);
        return opt.orElse(null);
    }

    @QueryMapping
    public List<MatchEntry> findByAthlete(@Argument String name) {
        return repo.findByAthleteNameContaining(name);
    }

    @QueryMapping
    public List<MatchEntry> findByAssessor(@Argument String name) {
        return repo.findByAssessorNameContaining(name);
    }

    @MutationMapping
    public MatchEntry createAssessment(@Argument SkillsAssessmentSquashTechnicalInput input) {
        MatchEntry entity = input.toEntity();
        SkillsAssessmentSquashTechnicalBO.calculateTechnicalScore(entity);
        return repo.save(entity);
    }

    @MutationMapping
    public MatchEntry updateAssessment(@Argument Integer id, @Argument SkillsAssessmentSquashTechnicalInput input) {
        MatchEntry entity = input.toEntity();
        entity.setMatchId(id);
        SkillsAssessmentSquashTechnicalBO.calculateTechnicalScore(entity);
        return repo.save(entity);
    }

    @MutationMapping
    public Boolean deleteAssessment(@Argument Integer id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}*/
