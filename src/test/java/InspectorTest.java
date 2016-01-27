import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import servlets.Inspector;
import servlets.Player;

public class InspectorTest extends Assert {

    private Inspector inspector;

    @Before
    public void init() {
        Player p1 = new Player();
        Player p2 = new Player();
        p1.setName("one");
        p2.setName("two");
        inspector = new Inspector();
        inspector.setP1(p1);
        inspector.setP2(p2);
    }

    @Test
    public void Test() {
        for (int i = 0; i < 8; i++) {
            inspector.watch("one");
        }
        for (int i = 0; i < 12; i++) {
            inspector.watch("two");
        }
        assertTrue(inspector.getP1Counter() == 8);
        assertTrue(inspector.getP2Counter() == 12);
        inspector.clean();
        assertFalse(inspector.getP2Counter() == 0);
        inspector.clean();
        assertTrue(inspector.getP2Counter() == 0);
    }
}
