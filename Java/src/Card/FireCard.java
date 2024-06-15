package Card;

import java.util.HashMap;
import java.util.List;

public class FireCard extends Card implements CardInter{
    public FireCard() {
        // 불 속성 카드의 능력 초기화
        cardAbility = new HashMap<>();
        cardAbility.put("Fire", List.of(20,1)); // 데미지 값 범위
    }

}

