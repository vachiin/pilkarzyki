package pl.vachiin.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.vachiin.app.Lottery;
import pl.vachiin.app.Player;

import java.security.SecureRandom;
import java.util.*;

@Component
@Scope("singleton")
public class TableService {

    private final AppDataModel model;

    @Autowired
    public TableService(AppDataModel aModel) {
        model = aModel;
    }

    public void arrangePlayers(List<Player> aCheckedPlayers, String aSessionId) {
        List<Player> pGracze = new ArrayList<>(aCheckedPlayers);
        model.setCheckedPlayers(pGracze);
        Collections.shuffle(pGracze, new SecureRandom());
        Iterator<Player> pIterator = pGracze.iterator();
        model.getTable().setWhiteAttack(pIterator.hasNext() ? pIterator.next() : PlayerService.nikt);
        model.getTable().setBlueAttack(pIterator.hasNext() ? pIterator.next() : PlayerService.nikt);
        model.getTable().setBlueDefense(pIterator.hasNext() ? pIterator.next() : PlayerService.nikt);
        model.getTable().setWhiteDefense(pIterator.hasNext() ? pIterator.next() : PlayerService.nikt);

        genSave();
        saveLottry(aSessionId);
    }

    private void genSave() {
        model.getStatistics().incGenNumber();
        model.getStatistics().setGenDate(new Date());
    }

    private void saveLottry(String aSessionId) {
        Lottery pLottery = new Lottery(model.getTable(), model.getStatistics(), aSessionId);
        model.getLotteryList().addFirst(pLottery);
    }
}
