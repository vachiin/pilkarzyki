package pl.vachiin.app;

public class Lottery {
    private Table table;
    private Statistics statistics;

    public Lottery(Table aTable, Statistics aStatistics) {
        table = new Table(aTable);
        statistics = new Statistics(aStatistics);
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
}
