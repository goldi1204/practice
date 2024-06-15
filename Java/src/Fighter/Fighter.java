package Fighter;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import Card.Card;
public abstract class Fighter implements FighterInter {
    //체력 방어
    //아이템
    //카드 사용
    //카드를 고를 수 있는 개수
    Map<String, Integer> resource;
    Set<Card> deckSet;//파이터가 가질 수 있는 카드의 종류
    List<Card> deckList;//파이터가 가진 카드들
    int deckremain = 0;
    int deckoffset = 0;
    int hand;//한번 턴에 낼 수 있는 카드

    @Override
    public Card[] chooseCards(Boolean isEnemy) {
        Scanner scanner=new Scanner(System.in);
        if (isEnemy) {
            Card[] cards = new Card[hand];
            Random rand = new Random();
            int randomNum = rand.nextInt(hand);
            System.out.println("몬스터가 카드를 고르는 중...");
            if(randomNum==0) {
                System.out.println("몬스터가 아무 행동도 하지 않았습니다.");
            }
            for(int i=0; i<randomNum; i++) {
                cards[i] = deckList.get(i);
            }

            return cards;
        } else {

            Card[] cards = new Card[hand];
            deckremain = deckList.size();
            //덱에서 카드 뽑기
            for (int i = deckoffset; deckoffset < i + hand; deckoffset++) {
                cards[deckoffset - i] = deckList.get(deckoffset % deckremain);
            }
            deckoffset = deckoffset % deckremain;
            deckremain -= hand;
            if (deckremain < 0) {
                deckremain = deckList.size() - deckremain;
            }
            //카드 정보를 번호를 부여해 보여주기
            System.out.println("카드를 고르시오");
            for (int i=0; i<hand; i++) {
                Card card = cards[i];
                Map<String, List<Integer>> cardInform = card.getCardInform();
                for (Map.Entry<String, List<Integer>> entry : cardInform.entrySet()) {
                    String key = entry.getKey();
                    List<Integer> values = entry.getValue();
                    int firstValue = values.get(0);
                    System.out.println((i+1) + ". Card: " + key + ", Damage: " + firstValue); // 카드 번호와 함께 카드 정보 출력
                }
            }
            Set<Integer> selectedCard = new HashSet<>();
            while (true) {
                String selectCard = scanner.next();


                if (selectCard.equals(";")) break;
                else if (selectCard.equals("enemy"))
                    showInventory();
                else if(selectCard.equals("user"))
                    showInventory();
                else {
                    try {
                        int selectedIndex = Integer.parseInt(selectCard)-1;
                        if ((selectedIndex>=0) && (selectedIndex<hand)) {
                            selectedCard.add(selectedIndex);
                        } else {
                            System.out.println("다시 입력하세요");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("다시 입력하세요");
                    }
                }
            }
            // 선택되지 않은 카드를 제외한 새로운 카드 배열 생성
            List<Card> selectedCards = new ArrayList<>();
            for (int i = 0; i<hand; i++) {
                if (selectedCard.contains(i)) {
                    selectedCards.add(cards[i]);
                }
            }
            Card[] result = new Card[selectedCards.size()];
            for (int i = 0; i<hand; i++) {
                if (selectedCard.contains(i)) {
                    result[i] = cards[i];
                }
            }
            return result;
        }
    }

    public Set<Card> getDeckSet() {
        return deckSet;
    }

    public Map<String, Integer> getResource() {
        return resource;
    }

    @Override
    public void showDescript() {
        //구현 완료
    }

    @Override
    public void setFighterResource(Map<String, Integer> change) {
        resource.put("HP", getResource().get("HP")+change.get("HP"));

    }

    @Override
    public void showInventory() {
    }

    @Override
    public void getreward(Card reward) {
        deckList.add(reward);
        Map<String, List<Integer>> cardInform = reward.getCardInform();
        for (Map.Entry<String, List<Integer>>oneCard : cardInform.entrySet()) {
            String key = oneCard.getKey();
            List<Integer> values = oneCard.getValue();
            int firstvalue = values.get(0);
            System.out.println("몬스터가 갖고 있던 \"" +key+ "\" 카드를 보상받았습니다.\nCard : "+key + ", Damage : "+firstvalue);
        }
        //카드를 추가
    }
}