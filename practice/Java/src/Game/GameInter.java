package Game;


public interface GameInter { //난이도 설정
    void introduceFighter();
    void chooseFighter(String fightername);
    void makeStage();
    String inCombat();
    void endStage();
    void endGame();//show rank
}



