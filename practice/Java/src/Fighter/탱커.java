package Fighter;

import java.util.*;

import Card.Card;
import Card.FireCard;
import Card.HealCard;
public class 탱커 extends Fighter implements FighterInter{
    public 탱커() {

        resource = new HashMap<>();
        resource.put("HP", 100); // 체력
        resource.put("Defense", 50); // 방어력
        deckSet = new HashSet<>();
        deckSet.add(new HealCard());
        deckSet.add(new FireCard());
        deckList = new ArrayList<>();
        hand = 1; // 한 번에 낼 수 있는 카드 수 (예시)
        deckList.add(new FireCard());
        deckList.add(new HealCard());

        // 몬스터가 사용할 카드를 초기화할 수 있습니다.
        // 예를 들어:
        // deckList.add(new SomeCard());

    }
    @Override
    public void showDescript() {
        Set<String> keySet = resource.keySet();
        System.out.println("탱커");
        for (String key : keySet) {
            System.out.println(key + " : " + resource.get(key));
        }
        System.out.println("한번에 받는 카드의 수 : "+hand);
    }
    @Override
    public void showInventory() {
        //캐릭터가 가진 카드를 보여주기
        System.out.println("user의 Inventory :");
        for (Card card : deckList) {
            Map<String, List<Integer>> cardInform = card.getCardInform();
            for (Map.Entry<String, List<Integer>>oneCard : cardInform.entrySet()) {
                String key = oneCard.getKey();
                List<Integer> values = oneCard.getValue();
                int firstvalue = values.get(0);
                System.out.println("Card : "+key + ", Damage : "+firstvalue);
            }
        }
    }

}
