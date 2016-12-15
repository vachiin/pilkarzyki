package pl.vachiin.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.vachiin.app.HistoryEntry;
import pl.vachiin.app.Player;
import pl.vachiin.app.Table;

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
        boolean goodMixed = false;

        while (!goodMixed) {
            Collections.shuffle(pGracze, new SecureRandom());
            Iterator<Player> pIterator = pGracze.iterator();
            model.getTable().setWhiteAttack(pIterator.hasNext() ? pIterator.next() : NIKT);
            model.getTable().setBlueAttack(pIterator.hasNext() ? pIterator.next() : NIKT);
            model.getTable().setBlueDefense(pIterator.hasNext() ? pIterator.next() : NIKT);
            model.getTable().setWhiteDefense(pIterator.hasNext() ? pIterator.next() : NIKT);

            if (model.getHistoryEntries().isEmpty()) {
                goodMixed = true;
            } else {
                Table pLast = model.getHistoryEntries().getFirst().getTable();
                int howManyTheSame = 0;
                howManyTheSame += pLast.getWhiteDefense().getNick().equals(model.getTable().getWhiteDefense().getNick()) ? 1 : 0;
                howManyTheSame += pLast.getWhiteAttack().getNick().equals(model.getTable().getWhiteAttack().getNick()) ? 1 : 0;
                howManyTheSame += pLast.getBlueDefense().getNick().equals(model.getTable().getBlueDefense().getNick()) ? 1 : 0;
                howManyTheSame += pLast.getBlueAttack().getNick().equals(model.getTable().getBlueAttack().getNick()) ? 1 : 0;

                goodMixed = (howManyTheSame <= 0);
            }
        }
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
