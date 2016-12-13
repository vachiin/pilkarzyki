package pl.vachiin.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.vachiin.app.Player;

import javax.annotation.PostConstruct;

@Component
@Scope("singleton")
public class PlayerService {

    static final Player nikt = new Player("", "gray");
    private final AppDataModel model;

    @Autowired
    public PlayerService(AppDataModel model) {
        this.model = model;
    }

    @PostConstruct
    void init() {
        model.getAllPlayers().add(new Player("Kałach", "#2ECCFA"));
        model.getAllPlayers().add(new Player("Grześ", "#FA5858"));
        model.getAllPlayers().add(new Player("Daniel", "#81F781"));
        model.getAllPlayers().add(new Player("Duda", "#F5A9F2"));

        model.getCheckedPlayers().addAll(model.getAllPlayers());

        model.getTable().setWhiteAttack(nikt);
        model.getTable().setWhiteDefense(nikt);
        model.getTable().setBlueAttack(nikt);
        model.getTable().setBlueDefense(nikt);
    }

}
