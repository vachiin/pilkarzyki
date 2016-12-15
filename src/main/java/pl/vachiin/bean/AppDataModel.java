package pl.vachiin.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.vachiin.app.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
@Scope("singleton")
public class AppDataModel {

    private List<Player> allPlayers = new ArrayList<>();
    private List<Player> checkedPlayers = new ArrayList<>();
    private Table table = new Table();
    private Generation generation = new Generation();
    private LinkedList<HistoryEntry> historyEntries = new LinkedList<>();
    private String sessionId;
    private Statistics statistics = new Statistics();

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(List<Player> aAllPlayers) {
        allPlayers = aAllPlayers;
    }

    public List<Player> getCheckedPlayers() {
        return checkedPlayers;
    }

    public void setCheckedPlayers(List<Player> aCheckedPlayers) {
        checkedPlayers = aCheckedPlayers;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table aTable) {
        table = aTable;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation aGeneration) {
        generation = aGeneration;
    }

    public LinkedList<HistoryEntry> getHistoryEntries() {
        return historyEntries;
    }

    public void setHistoryEntries(LinkedList<HistoryEntry> aHistoryEntries) {
        historyEntries = aHistoryEntries;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String aSessionId) {
        sessionId = aSessionId;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics aStatistics) {
        statistics = aStatistics;
    }
}
