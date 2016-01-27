package servlets;

import java.sql.*;
import java.util.ArrayList;

public class Questions {
    private ArrayList<Question> questions ;

    public Questions() {
        questions = new ArrayList<Question>() ;
        questions.add(new Question("Переведите 10010 в десятичную систему","18"));
        questions.add(new Question("Сколько рёбер у тетраедра ?","6"));
        questions.add(new Question("Последняя версия JAVA","8"));
        questions.add(new Question("В каком году родился Алан Тьюринг ?","1912"));
        questions.add(new Question("Сколько нулей у ГУГЛА ?","1000"));
        questions.add(new Question("Сколько символов в самом большом доменном имени в мире ?","63"));
        questions.add(new Question("Какая средняя зарплата в месяц в google (в доларах) ?","3965"));
        questions.add(new Question("Сколько лет Линусу Торвальдсу ?","45"));
    }

    public Question getQuestion(int i) {
        if (i>-1 && i<questions.size()) return questions.get(i) ;
        return null;
    }

    public Question getRandomQuestion() {
        Question q ;
        int i = (int)(Math.random()*19)+1 ;

        Connection connection = null;
        String url = "jdbc:postgresql://127.0.0.1:5432/Conqueror";
        String name = "postgres";
        String password = "qwerty";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM questions where id = ?");
            preparedStatement.setInt(1, i);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                q = new Question(result.getString("Question"), result.getString("Answer"));
                return q ;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null ;
    }

    public Question getRandomQuestionFromList() {
        int i = (int)(Math.random()*questions.size()) ;
        return questions.get(i) ;
    }
}