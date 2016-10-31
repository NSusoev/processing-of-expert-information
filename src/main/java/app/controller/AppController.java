package app.controller;

import app.model.ranking.group.BordRankingForm;
import app.model.ranking.group.BordRankingResult;
import app.model.ranking.group.Relation;
import app.model.ranking.personal.InteractivePersonalRankingForm;
import app.model.ranking.personal.PersonalRankingForm;
import app.model.ranking.personal.RankStep;
import app.service.RankingMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

        List<RankStep> steps = RankingMethods.RankBySecondMethod(sequence);
        model.addAttribute("initial", sequence);
        model.addAttribute("steps", steps);
        log.debug("EXIT");
        return "second_method_result";
    }

    @RequestMapping(value = "/second/interactive/form")
    public String viewInteractiveSecondMethodForm(@Valid InteractivePersonalRankingForm interactivePersonalRankingForm,
                                                  BindingResult bindingResult,
                                                  Model model)  {
        model.addAttribute("interactive", 1);
        return "second_method_form";
    }

    @RequestMapping(value = "/second/interactive/calc", method = RequestMethod.POST)
    public String doInteractiveSecondMethod(@Valid InteractivePersonalRankingForm interactivePersonalRankingForm,
                                 BindingResult bindingResult,
                                 Model model) {
        log.debug("ENTER");
        model.addAttribute("interactive", 1);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Введите корректные данные для ранжирования");
            return "second_method_form";
        }

        //List<RankStep> steps = RankingMethods.RankBySecondMethod();
        log.debug("EXIT");
        return "second_method_result";
    }

    @RequestMapping(value = "/bord/form")
    public String viewBordMethodForm(@Valid BordRankingForm bordRankingForm,
                                     BindingResult bindingResult,
                                     HttpServletRequest request,
                                     Model model) {
        log.debug("ENTER");
        if (Objects.equals(request.getMethod(), "POST")) {
            log.debug("experts = {}", bordRankingForm.getExpertsCount());
            log.debug("objects = {}", bordRankingForm.getObjectsCount());
            model.addAttribute("experts_count", bordRankingForm.getExpertsCount());
            model.addAttribute("objects_count", bordRankingForm.getObjectsCount());

            if (bindingResult.hasErrors()) {
                model.addAttribute("error", "Проверьте введенные вами данные");
                return "bord_method_form";
            } else {
                model.addAttribute("bord_result", 1);
                model.addAttribute("relations", Relation.values());
                return "bord_method_form";
            }
        }
        log.debug("EXIT");
        return "bord_method_form";
    }

    @RequestMapping(value = "/bord/calc", method = RequestMethod.POST)
    public String viewBordInputDataForm(@Valid BordRankingForm bordRankingForm,
                                        BindingResult bindingResult,
                                        Model model) {
        log.debug("ENTER");
        log.debug("form data = {}", bordRankingForm);
        for (int i = 0; i < bordRankingForm.getPersonalRankings().size(); i++) {
            bordRankingForm.getPersonalRankings().set(i, bordRankingForm.getPersonalRankings().get(i).replaceAll(",", ""));
        }
        log.debug("personal rankings after preprocessor = {}", bordRankingForm.getPersonalRankings());
        BordRankingResult rankingResult = RankingMethods.RankByBordMethod(bordRankingForm.getPersonalRankings());
        model.addAttribute("individual_rankings", bordRankingForm.getPersonalRankings());
        model.addAttribute("ranking_result",rankingResult);
        model.addAttribute("experts_count", bordRankingForm.getExpertsCount());
        model.addAttribute("objects_count", bordRankingForm.getObjectsCount());
        log.debug("EXIT");
        return "bord_method_result";

    }


}
