package app.controller;

import app.model.RankStep;
import app.service.PersonalRanking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    @RequestMapping(value = "/")
    public String getMainPage() {
        return "index";
    }

    @RequestMapping(value = "/second/form")
    public String viewSecondMethodForm()  {
        return "index";
    }

    @RequestMapping(value = "/second/calc")
    public String doSecondMethod(Model model) {
        log.debug("ENTER");
        List<Float> initial = new ArrayList<>(Arrays.asList(10f, 5f, 3f, 4f, 2f, 1f, 6f, 7f, 8f, 9f));
        List<RankStep> steps = PersonalRanking.RankBySecondMethod(initial);
        model.addAttribute("initial", initial);
        model.addAttribute("steps", steps);
        log.debug("EXIT");
        return "second_method_result";
    }
}
