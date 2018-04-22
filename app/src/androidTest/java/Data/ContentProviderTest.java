package Data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kaarel on 22.04.18.
 */
public class ContentProviderTest {
    ContentProvider provider;

    /*
    * Testing an Intent service too complicated.
    * Since service relies on ContentProvider, will test that instead.
    * */
    @Before
    public void setup() {
        provider = new ContentProvider();
        provider.loadMockData();

    }

    @Test
    public void getMovieById() throws Exception {
        //Check id of returned movie.
        for (int i = 0; i < 10; i++) {
            assertEquals(provider.getMovieById(i).getMovieId(), i);
            //Check Category inclusion.
            assertNotEquals(provider.getMovieById(i).getCategory(),null);
        }
        //Check non existing ID
        assertEquals(provider.getMovieById(121),null);

        //Check some categories
        assertEquals(provider.getMovieById(0).getCategory().getCategoryId(),0);
        assertEquals(provider.getMovieById(6).getCategory().getCategoryId(),6);

    }

    @Test
    public void getMovieList() throws Exception {
        //Check list length
        assertEquals(provider.getMovieList().size(),10);

    }

}