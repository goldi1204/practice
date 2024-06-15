package Card;

import java.util.HashMap;
import java.util.List;

public class DefenceCard extends Card implements CardInter{
    public DefenceCard() {
        // 불 속성 카드의 능력 초기화
        cardAbility = new HashMap<>();
        cardAbility.put("Defence", List.of(20)); // 방어 20
    }


}