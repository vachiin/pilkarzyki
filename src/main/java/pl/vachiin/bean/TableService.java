package pl.vachiin.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.vachiin.app.HistoryEntry;
import pl.vachiin.app.Player;

import java.security.SecureRandom;
import java.util.*;

import static pl.vachiin.bean.PlayerService.NIKT;

@Component
@Scope("singleton")
public class TableService {

    private final AppDataModel model;

    @Autowired
    public TableService(AppDataModel aModel) {
        model = aModel;
    }

    public void arrangePlayers(List<Player> aCheckedPlayers, String aSessionId) {
        shufflePlayers(aCheckedPlayers);
        genStats();
        saveHistory(aSessionId);
    }

    private void shufflePlayers(List<Player> aCheckedPlayers) {
        List<Player> pGracze = new ArrayList<>(aCheckedPlayers);
        model.setCheckedPlayers(pGracze);
        Collections.shuffle(pGracze, new SecureRandom());
        Iterator<Player> pIterator = pGracze.iterator();
        model.getTable().setWhiteAttack(pIterator.hasNext() ? pIterator.next() : NIKT);
        model.getTable().setBlueAttack(pIterator.hasNext() ? pIterator.next() : NIKT);
        model.getTable().setBlueDefense(pIterator.hasNext() ? pIterator.next() : NIKT);
        model.getTable().setWhiteDefense(pIterator.hasNext() ? pIterator.next() : NIKT);
    }

    private void genStats() {
        model.getGeneration().incGenNumber();
        model.getGeneration().setGenDate(new Date());
    }

    private void saveHistory(String aSessionId) {
        HistoryEntry pLottery = new HistoryEntry(model.getTable(), model.getGeneration(), aSessionId);
        model.getHistoryEntries().addFirst(pLottery);

        model.getStatistics().increment();
        if (!NIKT.equals(model.getTable().getWhiteDefense())) {
            model.getStatistics().addPercentWhiteDefense(model.getTable().getWhiteDefense().getNick());
        }
        if (!NIKT.equals(model.getTable().getWhiteAttack())) {
            model.getStatistics().addPercentWhiteAttack(model.getTable().getWhiteAttack().getNick());
        }
        if (!NIKT.equals(model.getTable().getBlueDefense())) {
            model.getStatistics().addPercentBlueDefense(model.getTable().getBlueDefense().getNick());
        }
        if (!NIKT.equals(model.getTable().getBlueAttack())) {
            model.getStatistics().addPercentBlueAttack(model.getTable().getBlueAttack().getNick());
        }
    }
}
