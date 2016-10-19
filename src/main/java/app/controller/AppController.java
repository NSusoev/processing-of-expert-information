package app.controller;

import app.model.ranking.personal.PersonalRankingForm;
import app.model.ranking.personal.RankStep;
import app.service.PersonalRanking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
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
    public String viewSecondMethodForm(@Valid PersonalRankingForm personalRankingForm,
                                       BindingResult bindingResult)  {
        return "second_method_form";
    }

    @RequestMapping(value = "/second/calc", method = RequestMethod.POST)
    public String doSecondMethod(@Valid PersonalRankingForm personalRankingForm,
                                 BindingResult bindingResult,
                                 Model model) {
        log.debug("ENTER");
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Введите данные для ранжирования");
            return "second_method_form";
        }

        List<Float> sequence = new ArrayList<>();
        try {
            for (String val : Arrays.asList(personalRankingForm.getSequence().split(" "))) {
                sequence.add(Float.parseFloat(val));
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Необходимо ввести только числовые значения");
            model.addAttribute("sequence", personalRankingForm.getSequence());
            return "second_method_form";
        }

        List<RankStep> steps = PersonalRanking.RankBySecondMethod(sequence);
        model.addAttribute("initial", sequence);
        model.addAttribute("steps", steps);
        log.debug("EXIT");
        return "second_method_result";
    }
}
