import java.util.Scanner;


interface Game{

    void startPage(); //첫 화면 아무 키나 누르면 게임 실행
    void selectCharacter(); //캐릭터 정하기
    void gamePage(); // 내 정보와 컴퓨터정보 보이게 설정.
    //정보의 종류 : 특정 키를 누르면 인벤토리를 볼 수 있게끔 설정
    //상대 HP와 내 HP
    //내가 갖고 있는 카드 종류 보여야함.
    //카드를 배열에 넣고 번호로 설정.
    void endPage(); //나의 체력이 0이되면 over 점수만 표현
}
interface Character {
    int HP();
    void takeDamage(int amount);
    boolean isAlive();
    String name();
}

interface Card{

}
interface Inventory{

}
interface Item{

}
class GameStart implements Game{
    Scanner scanner = new Scanner(System.in);
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private boolean over;
    private static Character player1;
    private Character player2;
    public GameStart(Character player1, Character player2){
        this.over=false;
        this.player1 = player1;
        this.player2 = player2;
    }
    @Override
    public void startPage(){
        while(true){
            clearScreen();
            System.out.println("1.게임시작\n"+"2.게임설명");
            int key = scanner.nextInt();
            if(key==1) selectCharacter();
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
        while(true){
            clearScreen();

        }
    }
    public void endPage(){
        while(true){
            clearScreen();

        }
    }
}
public class CardGame{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Character player1 = new Character();
        Game game = new GameStart(player1, player2);

    }
}
