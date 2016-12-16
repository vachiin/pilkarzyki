package pl.vachiin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.vachiin.app.Player;
import pl.vachiin.bean.AppDataModel;
import pl.vachiin.bean.TableService;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static pl.vachiin.controller.LoginController.NO_NICK;

@Controller
@RequestMapping(value = "/pilkarzyki")
public class ViewController {

    private final AppDataModel model;
    private final TableService tableService;

    @Autowired
    public ViewController(AppDataModel aModel, TableService aTableService) {
        model = aModel;
        tableService = aTableService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView read(HttpSession aSession, @CookieValue(value = "nick", defaultValue = NO_NICK) String loginCookie) {
        if (loginCookie.equals(NO_NICK)) {
            return new ModelAndView("login", "model", model);
        }
        try {
            model.setSessionId(URLDecoder.decode(loginCookie, "UTF-8"));
        } catch (UnsupportedEncodingException aE) {
            throw new RuntimeException(aE);
        }
        return new ModelAndView("pilkarzyki", "model", model);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView refresh(@RequestParam String action, @ModelAttribute("model") AppDataModel appModel,
                                BindingResult result, Model model1, HttpSession aSession,
                                @CookieValue(value = "nick", defaultValue = NO_NICK) String loginCookie) {
        if (loginCookie.equals(NO_NICK)) {
            return new ModelAndView("login", "model", model);
        }
        String pDecodedLogin = null;
        try {
            pDecodedLogin = URLDecoder.decode(loginCookie, "UTF-8");
        } catch (UnsupportedEncodingException aE) {
            throw new RuntimeException(aE);
        }

        if ("Losuj".equals(action)) {
            List<Player> listaZKolorem = new ArrayList<>();
            for (Player pPlayer : appModel.getCheckedPlayers()) {
                for (Player pPlayer1 : model.getAllPlayers()) {
                    if (pPlayer.getNick().equals(pPlayer1.getNick())) {
                        listaZKolorem.add(pPlayer1);
                    }
                }
            }

            if (listaZKolorem.size() >= 4) {
                tableService.arrangePlayers(listaZKolorem, pDecodedLogin);
            }
        }

        model.setSessionId(pDecodedLogin);
        return new ModelAndView("pilkarzyki", "model", model);
    }
}
