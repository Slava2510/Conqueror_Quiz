package servlets;

public class Room {
    private Player p1;
    private Player p2;
    private Questions questions;
    private Question question;
    private Result result;
    private Inspector inspector ;

    private int counter = 0;
    private int resultCounter = 0;
    private int prepeareCounter = 0;

    public Room() {
        p1 = new Player();
        p2 = new Player();
        p1.setName("");
        p2.setName("");
        questions = new Questions();
        question = questions.getRandomQuestion();
        result = new Result();
        inspector=new Inspector();
        inspector.setP1(p1);
        inspector.setP2(p2);
    }

    public Result checkResult() {
        if (resultCounter == 0) {
            result.setName1(p1.name);
            result.setName2(p2.name);
            result.setAnswer1(p1.getAnswer().getAnswer());
            result.setAnswer2(p2.getAnswer().getAnswer());
            result.setTime1(p1.getAnswer().getTime());
            result.setTime2(p2.getAnswer().getTime());
            result.setTrueAnswer(question.getRightAnswer());
            int sub1 = Math.abs(result.getAnswer1() - result.getTrueAnswer());
            int sub2 = Math.abs(result.getAnswer2() - result.getTrueAnswer());
            if (sub1 == sub2) {
                if (result.getTime1() > result.getTime2()) {
                    result.setWinner(p2.getName());
                } else {
                    result.setWinner(p1.getName());
                }
            } else if (sub1 > sub2) {
                result.setWinner(p2.getName());
            } else {
                result.setWinner(p1.getName());
            }

           question = questions.getRandomQuestion();   ////////Тут роблю новий вопрос
            if (result.getWinner().equals(p1.getName())) {
                p1.setScore(p1.getScore() + 100);
            } else p2.setScore(p2.getScore() + 100);


            result.setScore1(p1.getScore());
            result.setScore2(p2.getScore());
        }
        resultCounter++;
        if(resultCounter==2) {
            resultCounter=0 ;
            System.out.println("nullers");
            p1.setAnswer(null);
            p2.setAnswer(null);
        }
        return result;
    }

   public boolean putAnswer(Answer answer) {
        inspector.watch(answer.getName());
        if (answer.getName().equals(p1.getName()) && (p1.getAnswer() == null)) {
            System.out.println("set");
            p1.setAnswer(answer);
            inspector.setP1Do(true);
            return false;
        } else if ((answer.getName().equals(p2.getName())) && (p2.getAnswer() == null)) {
            p2.setAnswer(answer);
            System.out.println("P2 SET");
            inspector.setP2Do(true);
            return false;
        }
        if(inspector.isP1Do()&&inspector.isP2Do()){
            inspector.clean();
            return true;
        } else return false ;
    }

    public boolean init(String name) {
        if (counter == 0) {
            p1.setName(name);
            p1.setId(1);
            counter++;
        } else if ((!p1.getName().equals(name)) && (p2.getName().equals(""))) {
            p2.setName(name);
            p2.setId(2);
            counter++;
        }
        if (counter >= 2) return true;
        else return false;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }
}
