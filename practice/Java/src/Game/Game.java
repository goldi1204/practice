package Game;

import Fighter.Fighter;
import Fighter.검사;
import Fighter.탱커;
import Fighter.Monster1;
import Fighter.Monster2;
import Stage.Stage;

import java.util.*;

public class Game implements GameInter{
    int rank;
    Set<Fighter> userset;
    Set<Fighter> enemysetCopy;
    Set<Fighter> enemyset;
    Fighter user;
    Map<String,Integer>diff;
    int difficulty;
    String name;
    Stage stage=new Stage();
    int firstHP;
    public Game(String difficulty,String name) {
        enemyset = new HashSet<>();
        userset = new HashSet<>();
        diff = new HashMap<>();
        diff.put("상", 3);
        diff.put("중", 2);
        diff.put("하", 1);
        userset.add(new 검사());
        userset.add(new 탱커());
        enemyset.add(new Monster1());
        enemyset.add(new Monster2());
        switch (difficulty) {
            case "상":
                for (Fighter fighter : userset) {
                    Map<String, Integer> resource = fighter.getResource();
                    if (resource != null) {
                        resource.put("HP", resource.get("HP") / 2);
                    }
                }
                for (Fighter enemy : enemyset) {
                    Map<String, Integer> resource = enemy.getResource();
                    if (resource != null) {
                        resource.put("HP", resource.get("HP") * 2);
                        firstHP=resource.get("HP");
                    }
                }
                break;
            case "중":
                break;
            case "하":
                for (Fighter fighter : userset) {
                    Map<String, Integer> resource = fighter.getResource();
                    if (resource != null) {
                        resource.put("HP", resource.get("HP") *2);
                    }
                }
                for (Fighter enemy : enemyset) {
                    Map<String, Integer> resource = enemy.getResource();
                    if (resource != null) {
                        resource.put("HP", resource.get("HP")/2);
                        firstHP=resource.get("HP");
                    }
                }
                break;
        }
        //difficulty로 user가 만들어진 캐릭터와 enemy의 초기값이 다름
        //set user and enemy
        //set diff
        this.difficulty=diff.get(difficulty);
        this.name = name;
        rank = 0;
    }

    @Override
    public void introduceFighter() {
        for (Fighter fighter : userset) {
            System.out.print("캐릭터 : ");
            fighter.showDescript();
        }
    }

    @Override
    public void chooseFighter(String fighterName) {
        Scanner scanner = new Scanner(System.in);
        for (Fighter fighter : userset) {
            if (fighter.getClass().getSimpleName().equals(fighterName)) {
                user = fighter;
                System.out.println(fighterName + "가 선택되었습니다.");
                return;
            }
        }
        System.out.println("해당 캐릭터는 존재하지 않습니다. 다시 골라주세요");
        String fighter2 = scanner.next();
        chooseFighter(fighter2);
    }

    @Override
    public void makeStage() {
        for (Fighter enemy : enemyset){
            Map<String, Integer> resource = enemy.getResource();
            if(resource.get("HP")<0){
                resource.put("HP",firstHP);
            }
        }
        enemysetCopy = new HashSet<>(enemyset);
        stage.setEnemyAndReward(user, enemysetCopy);
        System.out.println("몬스터 출현");

    }

    @Override
    public String inCombat() {
        if(stage.battleResult(user)){
            if (user.getResource().get("HP") <= 0) {
                System.out.println("당신은 죽었습니다.");
                return "그만";
            } else {
                endStage();
                return "진행";
            }
        }
        else
            return inCombat();
    }

    @Override
    public void endStage() {

        stage.endStage(user);
        rank+=difficulty*1;
        System.out.println("현재 점수는 : "+rank);
        //현재 점수 출력
    }

    @Override
    public void endGame() {
        System.out.print(name+"\nYour rank is" +rank);
    }
    public static GameInter createInstance(String difficulty, String name) {
        Game gameInstance = new Game(difficulty, name);

        return new GameInter() {
            @Override
            public void introduceFighter() {
                gameInstance.introduceFighter();
            }

            @Override
            public void chooseFighter(String fighterName) {
                gameInstance.chooseFighter(fighterName);
            }

            @Override
            public void makeStage() {
                gameInstance.makeStage();
            }

            @Override
            public String inCombat() {
                return gameInstance.inCombat();
            }

            @Override
            public void endStage() {
                gameInstance.endStage();
            }

            @Override
            public void endGame() {
                gameInstance.endGame();
            }
        };
    }
}
