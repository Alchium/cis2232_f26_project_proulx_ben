package ca.hccis.fortnite.controllers;

import ca.hccis.fortnite.bo.MatchEntryBO;
import ca.hccis.fortnite.bo.ReportBO;
import ca.hccis.fortnite.entity.ReportInput;
import ca.hccis.fortnite.jpa.entity.MatchEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Controller to administer reports of the project.
 *
 * @author BJM
 * @since 20251009
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    /**
     * Send the user to list of reports view.
     *
     * @param model
     * @param session
     * @return To the appropriate view
     * @author BJM
     * @since 20251009
     */
    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        //BJM 20200602 Issue#1 Set the current date in the session
        logger.info("Running the reports controller base method");
        return "report/list";
    }

    /**
     * Method to send user to the player name report.
     *
     * @param model
     * @return view for list
     * @author BJM
     * @since 2025-10-06
     */
    @RequestMapping("/player/name")
    public String reportPlayerName(Model model) {
        logger.info("Running the reports controller player score method");
        model.addAttribute("reportInput", new ReportInput());
        return "report/reportPlayerName";
    }


    /**
     * Process the report - name
     *
     * @param model
     * @param reportInput Object containing inputs for the report
     * @return view to show report
     * @author BJM
     * @since 2025-10-06
     */
    @RequestMapping("/player/name/submit")
    public String reportPlayerNameSubmit(Model model, @ModelAttribute("reportInput") ReportInput reportInput) {

        System.out.println("Name from input form:" + reportInput.getName());

        //Get all the applicable squash matches from the database
        MatchEntryBO matchEntryBO = new MatchEntryBO();
        ArrayList<MatchEntry> theList = matchEntryBO.processSelectAllByPlayerName(reportInput.getName());
        reportInput.setFortniteMatches(theList);

        //Add a message in case the report does not contain any data
        if (theList != null && theList.isEmpty()) {
            model.addAttribute("message", "No matches found for that name");
            System.out.println("BJM - no data found");
        }

        //Put object in model so it can be used on the view (html)
        model.addAttribute("reportInput", reportInput);

        return "report/reportPlayerName"; //Send user to another view.
    }

    /**
     * Method to send user to the player score report.
     *
     * @param model
     * @return view for list
     * @author BJM
     * @since 2026-10-16
     */
    @RequestMapping("/player/score")
    public String reportPlayerScore(Model model, HttpSession session) {
        logger.info("Running the reports controller player score method");

        //get a list of all the player names
        MatchEntryBO matchEntryBO = new MatchEntryBO();
        ArrayList<String> players = matchEntryBO.getAllPlayerNames();
        session.setAttribute("players", players);

        model.addAttribute("reportInput", new ReportInput());
        return "report/reportPlayerScore";
    }

    /**
     * Process the report - name
     *
     * @param model
     * @param reportInput Object containing inputs for the report
     * @return view to show report
     * @author BJM
     * @since 2026-10-19
     */
    @RequestMapping("/player/score/submit")
    public String reportPlayerScoreSubmit(Model model, @ModelAttribute("reportInput") ReportInput reportInput) {

        System.out.println("Name from input form:" + reportInput.getName());

        ReportBO.processPlayerScoreReport(reportInput);

        //Add a message in case the report does not contain any data
        if (reportInput.getFortniteMatches() != null && reportInput.getFortniteMatches().isEmpty()) {
            model.addAttribute("message", "No matches found for that name");
            System.out.println("BJM - no data found");
        }

        //Put object in model so it can be used on the view (html)
        model.addAttribute("reportInput", reportInput);

        return "report/reportPlayerScore"; //Send user to another view.
    }



}
