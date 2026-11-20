package ca.hccis.fortnite.controllers;

import ca.hccis.fortnite.bo.MatchEntryBO;
import ca.hccis.fortnite.entity.MatchEntryDto;
import ca.hccis.fortnite.jpa.entity.MatchEntry;
import ca.hccis.fortnite.repositories.MatchEntryRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller to administer crud. Note that the code was taken from
 * Fred Campos' project from 2021 which also had modifications from Ferhad in
 * 2022.
 *
 * @author BJM
 * @since 20261023
 */
@Controller
@RequestMapping("/matchentry")
public class MatchEntryController {

    private final MatchEntryRepository _smr;

    @Autowired
    public MatchEntryController(MatchEntryRepository smr) {
        _smr = smr;
    }

    @Autowired
    private MessageSource messageSource;
    private static final Logger logger = LoggerFactory.getLogger(MatchEntryController.class);

    @RequestMapping("")
    public String home(Model model) {
        Iterable<MatchEntry> matchEntries = _smr.findAll();
        model.addAttribute("matchEntry",matchEntries);
        return "matchentry/list";
    }

    /**
     * Page to edit
     *
     * @param id    ID
     * @param model
     * @author BJM
     * @since 20251201
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, HttpSession session) {

        Optional matchEntry = _smr.findById(id);
        if (matchEntry.isPresent()) {
            model.addAttribute("matchEntry", matchEntry.get());
            return "matchentry/add";
        }

        //todo How can we best communcicate this to the view.
        model.addAttribute("messageError", "Could not load the matches");
        Iterable<MatchEntry> matchEntries = _smr.findAll();
        model.addAttribute("matchEntries", matchEntries);
        model.addAttribute("matchEntry", new MatchEntry());
        return "matchentry/list";
    }

    /**
     * Page to edit
     *
     * @param model
     * @author BJM
     * @since 20251201
     */
    @RequestMapping("/add")
    public String add(Model model) {
        MatchEntry newMatchEntry = new MatchEntry();
//        newSquashMatch.setCreatedDateTime(CisUtility.getCurrentDate("yyyy-MM-dd hh:mm"));
//        newSquashMatch.setMatchDate(CisUtility.getCurrentDate("yyyy-MM-dd"));
        model.addAttribute("matchEntry", newMatchEntry);
        return "matchentry/add";
    }

    /**
     * Submit method that processes add and edit and any form submission
     *
     * @param model
     * @param request
     * @param matchEntry what is being added or modified
     * @param bindingResult                   Result of SQL
     * @return add with errors or busPass
     * @author CIS2232
     * @since 20251201
     */
    @RequestMapping("/submit")
    public String submit(Model model, HttpServletRequest request, @Valid @ModelAttribute("matchEntry") MatchEntry matchEntry, BindingResult bindingResult) {
        boolean valid = true;

        //Sprint 5 business validation
//        ArrayList<String> validationErrors = SkillsAssessmentSquashTechnicalValidationBO.validateAssessmentDate(skillsAssessmentSquashTechnical);
//        if (validationErrors.size() > 0) {
//            valid = false;
//        }

        if (!valid || bindingResult.hasErrors()) {
            System.out.println("--------------------------------------------");
            System.out.println("Validation error - BJM");
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getObjectName() + "-" + error.toString() + "-" + error.getDefaultMessage());
            }
            System.out.println("--------------------------------------------");
//            skillsAssessmentSquashTechnical.setTechnicalScore(0);
            model.addAttribute("matchEntry", matchEntry);
            //model.addAttribute("businessValidationErrorsAssessmentDate", validationErrors);
            return "matchentry/add";
        }

        //BJM 20261105 detemine the winner
//        matchEntry.setWinnerName(SquashMatchBO.determineWinner(squashMatch));

        _smr.save(matchEntry);
        return "redirect:/matchentry";
    }

    @RequestMapping("/list/edit")
    public String showCreateForm(Model model) {
        MatchEntryDto matchEntryForm = new MatchEntryDto();

        Iterable<MatchEntry> matchEntries = _smr.findAll();
        ArrayList<MatchEntry> tempList = new ArrayList<>();
        for (MatchEntry current : matchEntries) {
            tempList.add(current);
        }
        matchEntryForm.setMatchEntries(tempList);
        model.addAttribute("form", matchEntryForm);
        return "matchentry/listedit";
    }

    @RequestMapping("/list/edit/submit")
    public String listEditSubmit(@ModelAttribute MatchEntryDto form, Model model) {

        _smr.saveAll(form.getMatchEntries());

        MatchEntryDto matchEntryForm = new MatchEntryDto();

        Iterable<MatchEntry> matchEntries = _smr.findAll();
        ArrayList<MatchEntry> tempList = new ArrayList<>();
        for (MatchEntry current : matchEntries) {
            tempList.add(current);
        }
        matchEntryForm.setMatchEntries(tempList);
        model.addAttribute("form", matchEntryForm);
        return "matchentry/listedit";

    }


    /**
     * Page to edit
     *
     * @param id    ID
     * @param model
     * @author BJM
     * @since 20251201
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {

        _smr.deleteById(id);

        //todo How can we best communcicate this to the view.
        model.addAttribute("messageSuccess", "Deleted fortnite match");

        //reload the list
        Iterable<MatchEntry> matchEntries = _smr.findAll();
        model.addAttribute("matchEntries", matchEntries);
        model.addAttribute("matchEntry", new MatchEntry());
        return "matchentry/list";
    }


}
