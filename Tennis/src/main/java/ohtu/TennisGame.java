package ohtu;

public class TennisGame {

    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            playerOneScore += 1;
        } else {
            playerTwoScore += 1;
        }
    }

    private boolean scoreIsEven() {
        if (playerOneScore == playerTwoScore) {
            return true;
        }
        return false;
    }

    private String printScoreIfEven() {
        switch (playerOneScore) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";
        }
    }

    private boolean checkIfAdvantageOrWin() {
        if (playerOneScore >= 4 || playerTwoScore >= 4) {
            return true;
        }
        return false;
    }

    private String printScoreIfAdvantageOrWin() {
        int scoreDifference = playerOneScore - playerTwoScore;
        if (scoreDifference == 1) {
            return "Advantage player1";
        } else if (scoreDifference == -1) {
            return "Advantage player2";
        } else if (scoreDifference >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    public String printScore() {
        
        
        int tempScore = 0;
        String score = "";
        score += playerIndexScore(playerOneScore);
        score += "-";
        score += playerIndexScore(playerTwoScore);
        return score;
                
    }
    
    private String playerIndexScore(int m_score11) {
        switch (m_score11) {
                    case 0:
                        return "Love";
                    case 1:
                        return "Fifteen";
                    case 2:
                        return "Thirty";
                    case 3:
                        return "Forty";
                }
        return "";
    }

    public String getScore() {
        if (scoreIsEven()) {
            return printScoreIfEven();
        } else if (checkIfAdvantageOrWin()) {
            return printScoreIfAdvantageOrWin();
        }
        return printScore();

    }
}
