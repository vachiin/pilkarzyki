package pl.vachiin.app;

public class HistoryEntry {
    private Table table;
    private Statistics statistics;
    private String sessionId;

    public HistoryEntry(Table aTable, Statistics aStatistics, String aSessionId) {
        table = new Table(aTable);
        statistics = new Statistics(aStatistics);
        sessionId = aSessionId;
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

    public String getSessionId() {
        return sessionId;
    }
}
