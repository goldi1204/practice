package Fighter;

import java.util.*;

import Card.Card;
import Card.DefenceCard;
import Card.FireCard;
import Card.HealCard;
 public abstract class Fighter implements FighterInter {
    //체력 방어
    //아이템
    //카드 사용
    //카드를 고를 수 있는 개수
    Map<String,Integer> resource;
    Set<Card> deckSet;//파이터가 가질 수 있는 카드의 종류
    List<Card> deckList;//파이터가 가진 카드들
    int deckremain=0;
    int deckoffset=0;
    int hand=1;//한번 턴에 낼 수 있는 카드

    public Fighter(int hp, int damage){
        resource = new HashMap<>();
        resource.put("HP", hp);
        resource.put("Damage", damage);
        deckSet = new HashSet<>();
        deckList = new ArrayList<>();
        for(int i=0; i<3; i++){
            deckSet.add(new DefenceCard());
            deckSet.add(new FireCard());
            deckSet.add(new HealCard());
        }
        deckList.add(new FireCard());
        deckList.add(new HealCard());
        deckList.add(new DefenceCard());
    }
    @Override
    public Card[] chooseCards() {
        Scanner scanner=new Scanner(System.in);
        Card[] cards=new Card[hand];
        deckremain=deckList.size();
        //덱에서 카드 뽑기
        for(int i=deckoffset;deckoffset<i+hand;deckoffset++){
            cards[deckoffset-i]=deckList.get(deckoffset%deckremain);
        }
        deckoffset=deckoffset%deckremain;
        deckremain-=hand;
        if(deckremain<0){
            deckremain=deckList.size()-deckremain;
        }
        //choose card
        System.out.println("Choose Cards");

        return cards;
    }
    @Override
    public Card[] chooseCards(Boolean isEnemy) {
        Random rand = new Random();// Random number of cards to choose
        Card[] chosenCards = new Card[hand];

        for (int i = 0; i < hand; i++) {
            int randomNumber = rand.nextInt(hand);
            chosenCards[i] = deckList.get(randomNumber);
        }

        Card[] cards=new Card[hand];//choose card random for decide enemy action
        return cards;
    }
    @Override
    public Set<Card> getDeckSet() {
        return deckSet;
    }
    @Override
    public Map<String,Integer> getResource(){
        return resource;
    }
    @Override
    public void showFighterInform() {
        Set<String> keySet = resource.keySet();
        for (String key : keySet) {
            System.out.println(key + ": " + resource.get(key));
        }
        //카드를 고르는 도중에 캐릭터의 정보 보여주기
    }

    @Override
    public void setFighterResource(Map<String,Integer> change) {
        for (Map.Entry<String, Integer> changevalue : change.entrySet()) {
            String ablility = changevalue.getKey();
            int value = changevalue.getValue();

            // 현재 캐릭터의 해당 속성 값을 가져옴
            int currentValue = resource.getOrDefault(ablility, 0);

            // 전달된 값에 따라 속성 값을 업데이트
            currentValue -= value;

            // 음수 값을 가질 수 없도록 보정
            if (currentValue < 0) {
                currentValue = 0;
            }

            // 캐릭터의 속성을 업데이트
            resource.put(ablility, currentValue);
        }
        //카드로 인한 캐릭터의 정보가 바뀜(hp-1과 같은)
    }

    @Override
    public void showInventory() {

        for (Card card : deckSet) {
            card.showCard();

        }
    }

    @Override
    public void getreward(Card reward) {
        //카드를 추가
    }
}