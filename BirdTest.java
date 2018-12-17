import com.company.Bird;

import static org.junit.Assert.*;

public class BirdTest {
   Bird bird = new Bird();
    @org.junit.Test
    public void getWidth() {
        assertEquals(20,bird.getWidth());
    }
}