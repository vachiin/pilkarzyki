package pl.vachiin.app;

public class Player {

    private String nick;
    private String color;

    /**
     * fakowy konstruktor dla convertera z GUI
     */
    public Player(String nick) {
        this.nick = nick;
    }

    public Player(String nick, String color) {
        this.nick = nick;
        this.color = color;
    }

    public Player(Player aPlayer) {
        if (aPlayer != null) {
            this.nick = aPlayer.nick;
            this.color = aPlayer.color;
        }
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String aColor) {
        color = aColor;
    }

    @Override
    public String toString() {
        return nick;
    }
}
