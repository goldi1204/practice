package Card;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;

public abstract class Card implements CardInter {
    //카드 뽑기
    protected Map<String,List<Integer>> cardAbility;//상대방이나 자신에게도 효과
    public Card() {
        cardAbility = new HashMap<>();
    }
    /*

    public void showCard() {
        Set<String> keySet = cardAbility.keySet();
        for (String key : keySet) {
            System.out.println(key + ": " + cardAbility.get(key));
        }
    }
    */

    @Override
    public Map<String,List<Integer>> getCardInform() {
        return cardAbility;
    }

}