public class Player {
    Integer playerId;
    Integer position;

    public Player(Integer playerId, Integer position) {
        this.playerId = playerId;
        this.position = position;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
