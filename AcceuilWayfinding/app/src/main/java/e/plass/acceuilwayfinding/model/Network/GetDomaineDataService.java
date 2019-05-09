package e.plass.acceuilwayfinding.model.Network;

import e.plass.acceuilwayfinding.model.DomaineList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDomaineDataService {
    @GET("SelectAll_BLL.php")
    Call<DomaineList> geDomaineData();
}
