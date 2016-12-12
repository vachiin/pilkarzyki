package pl.vachiin.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.vachiin.app.Lottery;
import pl.vachiin.app.Player;
import pl.vachiin.app.Statistics;
import pl.vachiin.app.Table;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
@Scope("singleton")
public class AppDataModel {

    private List<Player> allPlayers = new ArrayList<>();
    private List<Player> checkedPlayers = new ArrayList<>();
    private Table table = new Table();
    private Statistics statistics = new Statistics();
    private LinkedList<Lottery> lotteryList = new LinkedList<>();

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

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics aStatistics) {
        statistics = aStatistics;
    }

    public LinkedList<Lottery> getLotteryList() {
        return lotteryList;
    }

    public void setLotteryList(LinkedList<Lottery> aLotteryList) {
        lotteryList = aLotteryList;
    }
}