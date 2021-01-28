public class Player{

    String name;

    public void player(String name){
        this.name = name;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Player's name is: " + name ;
    }
}