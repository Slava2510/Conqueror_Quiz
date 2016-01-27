package servlets;

public class Inspector {
    private Player p1 ;
    private Player p2 ;
    private int p1Counter ;
    private int p2Counter ;
    private boolean isP2Do ;
    private boolean isP1Do ;
    private int counter =0;

    public Inspector() {
        p1Counter =0 ;
        p2Counter = 0 ;
        isP1Do=false ;
        isP1Do=false ;
    }
    public boolean isP1(String name) {
        return  p1.getName().equals(name)?true : false ;
    }
    public boolean isP2(String name) {
        return  p2.getName().equals(name)?true : false ;
    }
    public boolean isP1First(){
        return p1Counter==1 ? true : false ;
    }
    public boolean isP2First(){
        return p2Counter==1 ? true : false ;
    }
    public void watch(String name) {
        if(isP1(name))p1Counter++ ;
        if(isP2(name))p2Counter++ ;
    }
    public void clean() {
        counter++ ;
        if(counter==2) {
            System.out.println("CLEAN");
            p1Counter = 0;
            p2Counter = 0;
            isP1Do = false;
            isP2Do = false;
            counter=0 ;
        }
    }

    public boolean isP2Do() {
        return isP2Do;
    }

    public boolean isP1Do() {
        return isP1Do;
    }

    public void setP2Do(boolean isP2Do) {
        this.isP2Do = isP2Do;
    }

    public void setP1Do(boolean isP1Do) {
        this.isP1Do = isP1Do;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public int getP1Counter() {
        return p1Counter;
    }

    public int getP2Counter() {
        return p2Counter;
    }
}
