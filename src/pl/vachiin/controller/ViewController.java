package pl.vachiin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.vachiin.app.Player;
import pl.vachiin.bean.AppDataModel;
import pl.vachiin.bean.TableService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {

    private final AppDataModel model;
    private final TableService tableService;

    @Autowired
    public ViewController(AppDataModel aModel, TableService aTableService) {
        model = aModel;
        tableService = aTableService;
    }

    @RequestMapping(value = "/pilkarzyki", method = RequestMethod.GET)
    public ModelAndView read() {

        return new ModelAndView("pilkarzyki", "model", model);
    }

    @RequestMapping(value = "/pilkarzyki", method = RequestMethod.POST)
    public ModelAndView refresh(@RequestParam String action, @ModelAttribute("model") AppDataModel appModel, BindingResult result, Model model1) {

        if ("Losuj".equals(action)) {
            List<Player> listaZKolorem = new ArrayList<>();
            for (Player pPlayer : appModel.getCheckedPlayers()) {
                for (Player pPlayer1 : model.getAllPlayers()) {
                    if (pPlayer.getNick().equals(pPlayer1.getNick())) {
                        listaZKolorem.add(pPlayer1);
                    }
                }
            }

            tableService.arrangePlayers(listaZKolorem);
        }

        return new ModelAndView("pilkarzyki", "model", model);
    }
}
