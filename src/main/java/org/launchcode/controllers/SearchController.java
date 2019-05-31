package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String results(Model model,
                          @RequestParam String searchType,
                          @RequestParam String searchTerm) {
        if (searchType.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            boolean isJobsEmpty = jobs.isEmpty();
            model.addAttribute("isJobsEmpty", isJobsEmpty);
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("defTerm", searchTerm);
            model.addAttribute("resAmt", jobs.size() + " results found");
        } else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            boolean isJobsEmpty = jobs.isEmpty();
            model.addAttribute("isJobsEmpty", isJobsEmpty);
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("defterm", searchTerm);
            model.addAttribute("resAmt", jobs.size() + " results found");
        }
        return "search";
    }
}
