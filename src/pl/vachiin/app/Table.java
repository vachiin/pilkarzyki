package pl.vachiin.app;


public class Table {

    private Player whiteDefense;
    private Player whiteAttack;
    private Player blueDefense;
    private Player blueAttack;

    public Table() {
    }

    public Table(Table aTable) {
        whiteDefense = new Player(aTable.whiteDefense);
        whiteAttack = new Player(aTable.whiteAttack);
        blueDefense = new Player(aTable.blueDefense);
        blueAttack = new Player(aTable.blueAttack);
    }

    public Player getWhiteDefense() {
        return whiteDefense;
    }

    public void setWhiteDefense(Player aWhiteDefense) {
        whiteDefense = aWhiteDefense;
    }

    public Player getWhiteAttack() {
        return whiteAttack;
    }

    public void setWhiteAttack(Player aWhiteAttack) {
        whiteAttack = aWhiteAttack;
    }

    public Player getBlueDefense() {
        return blueDefense;
    }

    public void setBlueDefense(Player aBlueDefense) {
        blueDefense = aBlueDefense;
    }

    public Player getBlueAttack() {
        return blueAttack;
    }

    public void setBlueAttack(Player aBlueAttack) {
        blueAttack = aBlueAttack;
    }

}
