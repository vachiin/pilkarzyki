package pl.vachiin.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.vachiin.app.HistoryEntry;
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
        shufflePlayers(aCheckedPlayers);
        genStats();
        saveHistory(aSessionId);
    }

    private void shufflePlayers(List<Player> aCheckedPlayers) {
        List<Player> pGracze = new ArrayList<>(aCheckedPlayers);
        model.setCheckedPlayers(pGracze);
        Collections.shuffle(pGracze, new SecureRandom());
        Iterator<Player> pIterator = pGracze.iterator();
        model.getTable().setWhiteAttack(pIterator.hasNext() ? pIterator.next() : PlayerService.nikt);
        model.getTable().setBlueAttack(pIterator.hasNext() ? pIterator.next() : PlayerService.nikt);
        model.getTable().setBlueDefense(pIterator.hasNext() ? pIterator.next() : PlayerService.nikt);
        model.getTable().setWhiteDefense(pIterator.hasNext() ? pIterator.next() : PlayerService.nikt);
    }

    private void genStats() {
        model.getStatistics().incGenNumber();
        model.getStatistics().setGenDate(new Date());
    }

    private void saveHistory(String aSessionId) {
        HistoryEntry pLottery = new HistoryEntry(model.getTable(), model.getStatistics(), aSessionId);
        model.getHistoryEntries().addFirst(pLottery);
    }
}
