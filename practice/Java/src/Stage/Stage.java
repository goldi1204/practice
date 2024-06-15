package Stage;
import java.util.*;
import java.util.Scanner;

import Fighter.Fighter;
import Fighter.Monster1;
import Fighter.Monster2;
import Card.Card;
import Game.Game;


public class Stage implements StageInter{
    Map<String,Integer> userChange;
    Map<String,Integer> enemyChange;
    Fighter enemy;
    private Card reward;
    Set<Card> rewardCards;
    int stagenum=0;

    @Override
    public void setEnemyAndReward(Fighter user, Set<Fighter> enemySet ) {
        System.out.println("\n" + ++stagenum + "번째 스테이지");
        Set<Fighter> enemySetCopy = new HashSet<>(enemySet);
        Iterator<Fighter> iterator = enemySetCopy.iterator();
        enemy = iterator.next();
        List<Card> cards = new ArrayList<>(user.getDeckSet());
        if (!cards.isEmpty()) {
            rewardCards=enemy.getDeckSet();
            reward = rewardCards.iterator().next();
            System.out.println("카드를 보급받았습니다.");
            // 보상용 카드 선택
        } else {
            System.out.println("보상용 카드가 없습니다.");//보상용 카드 선택
        }
    }

    @Override
    public boolean battleResult(Fighter user) {
        userChange = new HashMap<>();
        enemyChange = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("적의 체력: " + enemy.getResource().get("HP"));
        System.out.println("나의 체력: " + user.getResource().get("HP"));
        //입력을 받고 상대방 카드를 보여주거나 내카드를 보여주거나 카드를 골랐다면 다음 흐름으로
        Card[] userCard = user.chooseCards(false);
        Card[] enemyCard = enemy.chooseCards(true);
        //카드의 작업에 따라 userChage와 enemyChange를 업데이
        userChange.put("HP", 0);
        enemyChange.put("HP", 0);
        for (Card card : userCard) {
            Collection<List<Integer>> values = card.getCardInform().values();
            for (List<Integer> list : values) {
                if (list.get(1) == 1) {
                    enemyChange.put("HP", enemyChange.get("HP") - list.get(0));
                    System.out.println((-1)*enemyChange.get("HP") + "의 데미지를 몬스터에게 입혔습니다");
                } else if (list.get(1) == 0) {
                    userChange.put("HP", userChange.get("HP") + list.get(0));
                    System.out.println(userChange.get("HP") + " 만큼 회복합니다.");
                }

            }
        }
        try{for(Card card: enemyCard){
            Collection<List<Integer>> values = card.getCardInform().values();
            for(List<Integer> list : values) {
                if (list.get(1) == 1) {
                    userChange.put("HP", userChange.get("HP") - list.get(0));
                    System.out.println("몬스터가 플레이어를 공격합니다");
                } else if (list.get(1) == 0) {
                    enemyChange.put("HP", enemyChange.get("HP") + list.get(0));
                    System.out.println("몬스터가 HP를 회복합니다.");
                }
            }
        }}
        catch(NullPointerException e){}

        user.setFighterResource(userChange);
        enemy.setFighterResource(enemyChange);
        int userHp=user.getResource().get("HP");
        int enemyHp=enemy.getResource().get("HP");
        return userHp<=0||enemyHp<=0;
    }

    @Override
    public void endStage(Fighter user) {
        user.getreward(reward);
    }



}
