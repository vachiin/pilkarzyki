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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    final static String NO_NICK = "noNick";

    private final AppDataModel model;

    @Autowired
    public LoginController(AppDataModel aModel) {
        model = aModel;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView readLogin(HttpSession aSession, @ModelAttribute("model") AppDataModel appModel) {
        return new ModelAndView("login", "model", model);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView writeLogin(HttpSession aSession, @ModelAttribute("model") AppDataModel appModel,
                                   @RequestParam String cookie, HttpServletResponse response) {
        if (cookie == null || cookie.trim().length() == 0) {
            return new ModelAndView("login", "model", model);
        }
        Cookie c = null;
        try {
            c = new Cookie("nick", URLEncoder.encode(cookie, "UTF-8"));
        } catch (UnsupportedEncodingException aE) {
            throw new RuntimeException(aE);
        }
        c.setMaxAge(3600 * 24 * 7);
        response.addCookie(c);
        model.setSessionId(cookie);
        return new ModelAndView("redirect:pilkarzyki.jsp", "model", model);
    }
}
