package mandel.museum;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RijksService
{
    @GET("/api/en/collection")
    Single<Art> artP(
            @Query("key") String key,
            @Query("p") int page
    );

    @GET("/api/en/collection")
    Single<Art> artQ(
            @Query("key") String key,
            @Query("p") int page,
            @Query("q") String query
    );

    @GET("/api/en/collection")
    Single<Art> artInM(
            @Query("key") String key,
            @Query("p") int page,
            @Query("involvedMaker") String maker
    );
}
