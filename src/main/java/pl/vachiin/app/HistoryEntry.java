package pl.vachiin.app;

public class HistoryEntry {
    private Table table;
    private Generation generation;
    private String sessionId;

    public HistoryEntry(Table aTable, Generation aGeneration, String aSessionId) {
        table = new Table(aTable);
        generation = new Generation(aGeneration);
        sessionId = aSessionId;
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

    public String getSessionId() {
        return sessionId;
    }
}
