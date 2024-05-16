package mandel.museum;

import com.andrewoid.ApiKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RijksServiceFactoryTest
{
    @Test
    public void artP()
    {
        // given
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        //when
        Art artP = service.artP(
                apiKey.get(),
                27
        ).blockingGet();

        //then
        assertNotNull(artP.artObjects[0]);
        assertNotNull(artP.artObjects[1]);
        assertNotNull(artP.artObjects[2]);
        assertNotNull(artP.artObjects[3]);
    }

    @Test
    public void artQ()
    {
        // given
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        //when
        Art artQ = service.artQ(
                apiKey.get(),
                31,
                "De Nachtwacht"
        ).blockingGet();

        //then
        assertNotNull(artQ.artObjects[0]);
        assertNotNull(artQ.artObjects[1]);
        assertNotNull(artQ.artObjects[2]);
        assertNotNull(artQ.artObjects[3]);
    }

    @Test
    public void artInM()
    {
        // given
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        //when
        Art artInM = service.artInM(
                apiKey.get(),
                104,
                "Rembrandt van Rijn"
        ).blockingGet();

        //then
        assertNotNull(artInM.artObjects[0]);
        assertNotNull(artInM.artObjects[1]);
        assertNotNull(artInM.artObjects[2]);
        assertNotNull(artInM.artObjects[3]);
    }
}