import java.util.Scanner;


interface Game{
    void startPage(); //첫 화면 아무 키나 누르면 게임 실행 *필수
    void selectCharacter(); //캐릭터 정하기
    void gamePage(); // 내 정보와 컴퓨터정보 보이게 설정. *필수
    //정보의 종류 : 특정 키를 누르면 인벤토리를 볼 수 있게끔 설정
    //상대 HP와 내 HP
    //내가 갖고 있는 카드 종류 보여야함.
    //카드를 배열에 넣고 번호로 설정.
    boolean endPage(); //나의 체력이 0이되면 over 점수만 표현 + gamePage 포함 시켜도 무방
}
interface Character {

    void takeDamage(int amount); //*필수
    boolean isAlive(); // *필수
    String getName(); // *필수
    int getHP(); // *필수
}

interface Card{
        String getName();
        void use();
        String getDescription();
}

interface Inventory{
    void addItem(Item item);
    void removeItem(Item item);
    Item getItem(String name);
    int getItemCount();
}

interface Item{
    String getName();
    void use();
    String getDescription();
}
class GameStart implements Game{
    Scanner scanner = new Scanner(System.in);

    public GameStart() {

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private boolean over;
    private static Character player1;
    private Character player2;
    @Override
    public void startPage(){
        while(true){
            clearScreen();
            System.out.println("1.게임시작\n"+"2.게임설명");
            int key=scanner.nextInt();
            if(key==1){
                clearScreen();
                System.out.println("게임 스타트\n플레이어의 이름을 입력하세요");
                String name=scanner.next();
                player1=new Player(name,100);
                player2=new Player("AI",100);
                break;
            }
            else if(key==2) {
                clearScreen();
                System.out.println("이 게임은 카드 게임입니다.\n");
                int key2 = scanner.nextInt();
                if(key2==1)
                    continue;
            }
        }
    }
    public void selectCharacter(){
        while(true){
            clearScreen();
        }
    }
    public void gamePage(){
        while(endPage()){
            clearScreen();
            System.out.println("플레이어 이름 : "+player1.getName()+"     플레이어 HP : "+player1.getHP());
            System.out.println("카드를 내세요");
            System.out.println(player1.getName());
            System.out.println(player2.getName());
            break;
        }
    }
    public boolean endPage(){
        while(true){
            if(!player1.isAlive()) {
                System.out.println(player1.getName() + "의 체력이 0이 되었습니다.\ngame over");
                return false;
            }
            else
                return true;
        }
    }
}

class Player implements Character {
    private int hp;
    private String name;

    public Player(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    @Override
    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }
    @Override
    public String getName() {
        return name;
    }
    public int getHP(){
        return hp;
    }

}
class AttackCard implements Card{
    private String name;
    private String description;

    public AttackCard(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use() {
        System.out.println(name + " card is used.");
        // 카드 사용 로직
    }

    @Override
    public String getDescription() {
        return description;
    }
}


class PlayerInventory implements Inventory {


    public PlayerInventory() {

    }

    @Override
    public void addItem(Item item) {

    }

    @Override
    public void removeItem(Item item) {

    }

    @Override
    public Item getItem(String name) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class HealthPotion implements Item {
    private String name;
    private String description;

    public HealthPotion(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use() {
        System.out.println(name + " is used.");
        // 아이템 사용 로직
    }

    @Override
    public String getDescription() {
        return description;
    }
}


public class CardGame{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game myGame = new GameStart();
        myGame.startPage();
        myGame.selectCharacter();
        myGame.gamePage();
        myGame.endPage();
    }
}

    }
}
