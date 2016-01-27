import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import servlets.Answer;
import servlets.Room;

public class RoomTest extends Assert {

    private Room room;
    private Answer answerTest;
    private Answer answerTest2;

    @Before
    public void init() {
        room = new Room();
        answerTest = new Answer();
        answerTest.setAnswer(10);
        answerTest.setName("one");
        answerTest.setTime(2000);

        answerTest2 = new Answer();
        answerTest2.setAnswer(200);
        answerTest2.setName("two");
        answerTest2.setTime(5000);
    }

    @Test
    public void test() {
        assertFalse(room.init("one"));
        assertTrue(room.init("two"));
        assertTrue(room.init("one"));
        assertFalse(room.putAnswer(answerTest));
        assertFalse(room.putAnswer(answerTest2));
        assertTrue(room.putAnswer(answerTest));

        assertTrue(room.checkResult().getTime1() == 2000);
    }


}
