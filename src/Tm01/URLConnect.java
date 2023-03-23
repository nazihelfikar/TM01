package Tm01;

import org.json.JSONArray;
import org.json.JSONObject;
import responModel.ResponModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class URLConnect {
    public static void main(String[] args) throws IOException {
        Connect koneksi = new Connect();
        URL myAddress = koneksi.buildURL("https://harber.mimoapps.xyz/api/getaccess.php");
        String response = koneksi.getResponseFromHttpUrl(myAddress);
        System.out.println(response);

        assert response != null;
        JSONArray responseJSON = new JSONArray(response);
        ArrayList<ResponModel> responModels = new ArrayList<>();
        for (int i = 0; i < responseJSON.length(); i++) {
            ResponModel resmodel = new ResponModel();
            JSONObject myJSONObject = responseJSON.getJSONObject(i);
            resmodel.setMessage(myJSONObject.getString("message"));
            resmodel.setStatus(myJSONObject.getString("status"));
            resmodel.setComment(myJSONObject.getString("comment"));
            responModels.add(resmodel);
}           System.out.println("respon are:");
            for (int index=0; index <responModels.size();index++){
            System.out.println("MASSAGE:" + responModels.get(index).getMessage());
            System.out.println("STATUS:" + responModels.get(index).getStatus());
            System.out.println("COMMENT:" + responModels.get(index).getComment());


        }
    }
}