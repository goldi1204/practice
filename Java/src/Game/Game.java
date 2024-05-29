package Game;

import Card.Card;
import Fighter.Fighter;
import Fighter.Player1;
import Fighter.Player2;
import Fighter.Monster1;
import Stage.Stage;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Game implements GameInter{
    int cI=1;
    int rank, stagenumber=0;
    Set<Fighter> userset;
    Set<Fighter> enemyset;
    Fighter user;
    Map<String,Integer>diff;
    int difficulty;
    String name;
    Stage stage=new Stage();
    public Game(String difficulty,String name) {
        userset=new HashSet<>();
        userset.add(new Player1(100, 10));
        userset.add(new Player2(50, 20));
        enemyset = new HashSet<>();
        //set user and enemy
        this.diff = Map.of("상", 3, "중",2,"하",1);
        this.difficulty = diff.get(difficulty);
        this.name = name;
        rank = 0;
        switch (difficulty) {
            case "상":
                enemyset.add(new Monster1(75, 10));
            case "중":
                enemyset.add(new Monster1(50, 10));
            case "하":
                enemyset.add(new Monster1(25, 10));
        }
        //difficulty로 user가 만들어진 캐릭터와 enemy의 초기값이 다름
        for (Fighter user : userset) {
            System.out.println("Player"+cI+"\nHP: " + user.getResource().get("HP") + ", Damage: " + user.getResource().get("Damage"));
            cI++;
        }
    }

    @Override
    public Fighter chooseFighter(String fighter) {
        Scanner scanner = new Scanner(System.in);
        Fighter selectFighter;
        do {
            if (fighter.equals("Player1")) {

                System.out.println(fighter + "이(가) 생성됩니다.");
                selectFighter = new Player1(100, 10);
                break;
            }
            else if (fighter.equals("Player2")) {
                System.out.println(fighter + "이(가) 생성됩니다.");
                selectFighter = new Player2(50, 20);
                break;
            }
            else {
                System.out.println(fighter + "이란 캐릭터는 존재하지 않습니다. 다시 골라주세요");
                fighter = scanner.next();
            }
        }while(true);
        user=selectFighter;
        return selectFighter;
    }

    @Override
    public void makestage() {
        System.out.println("\nStage "+ ++stagenumber);
        stage.setEnemyAndReward(difficulty,user);
    }

    @Override
    public String inCombat() {
        while(stage.battleResult(user));
        if(user.getResource().get("HP")<=0) {
            System.out.println("당신은 죽었습니다.");
            return "그만";
        }
        else {
            endstage();
            return "다시";
        }
    }

    @Override
    public void endstage() {
        stage.endStage(user);
        rank+=difficulty*1;
    }

    @Override
    public void endgame() {
        System.out.println(name+"\nYour rank is" +rank);
    }
}
