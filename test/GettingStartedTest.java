import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GettingStartedTest {

    @Test
    public void add() {
        assertThat(GettingStarted.add(2, 3), is(5));
    }
}