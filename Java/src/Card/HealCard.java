package Card;

import java.util.HashMap;
import java.util.List;

public class HealCard extends Card implements CardInter{
    public HealCard() {
        // 불 속성 카드의 능력 초기화
        cardAbility = new HashMap<>();
        cardAbility.put("Heal", List.of(10,0)); // 힐량 값 범위
    }


}