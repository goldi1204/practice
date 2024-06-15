package Card;

import java.util.HashMap;
import java.util.List;

public class Fire2Card extends Card implements CardInter{
    public Fire2Card() {
        // 불 속성 카드의 능력 초기화
        cardAbility = new HashMap<>();
        cardAbility.put("Fire2", List.of(15,1)); // 공격 20
    }


}