package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private static  Room room = new Room() ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper() ;
        if(req.getParameter("type").equals("init")) {
            if(room.init(req.getParameter("name"))) {
                objectMapper.writeValue(res.getOutputStream(),room.getQuestion());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException {
        ObjectMapper objectMapper = new ObjectMapper() ;
        Answer answer = objectMapper.readValue(request.getInputStream(),Answer.class) ;
        if(room.putAnswer(answer)) {
            System.out.println("check");
            Result result = room.checkResult() ;
            objectMapper.writeValue(response.getOutputStream(),result);
        }
    }

}
