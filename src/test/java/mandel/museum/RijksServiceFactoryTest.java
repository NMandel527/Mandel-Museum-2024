package mandel.museum;

import com.andrewoid.ApiKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RijksServiceFactoryTest
{
    @Test
    public void pageNumber()
    {
        // given
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        //when
        Art pageNumber = service.pageNumber(
                apiKey.get(),
                27
        ).blockingGet();

        //then
        assertNotNull(pageNumber.artObjects[0].title);
        assertNotNull(pageNumber.artObjects[0].longTitle);
        assertNotNull(pageNumber.artObjects[0].principalOrFirstMaker);
        assertNotNull(pageNumber.artObjects[0].webImage);
    }

    @Test
    public void queryAndPage()
    {
        // given
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        //when
        Art queryAndPage = service.queryAndPage(
                apiKey.get(),
                31,
                "De Nachtwacht"
        ).blockingGet();

        //then
        assertNotNull(queryAndPage.artObjects[0].title);
        assertNotNull(queryAndPage.artObjects[0].longTitle);
        assertNotNull(queryAndPage.artObjects[0].principalOrFirstMaker);
        assertNotNull(queryAndPage.artObjects[0].webImage);
    }

    @Test
    public void artistAndPage()
    {
        // given
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        //when
        Art artistAndPage = service.artistAndPage(
                apiKey.get(),
                104,
                "Rembrandt van Rijn"
        ).blockingGet();

        //then
        assertNotNull(artistAndPage.artObjects[0].title);
        assertNotNull(artistAndPage.artObjects[0].longTitle);
        assertNotNull(artistAndPage.artObjects[0].principalOrFirstMaker);
        assertNotNull(artistAndPage.artObjects[0].webImage);
    }
}