package lt.viko.eif.pyritefarmers.taxesapi.APIs;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lt.viko.eif.pyritefarmers.taxesapi.models.Options;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SkyScanner {
    public static JSONObject getBrowseQuotesResult(Options options) throws IOException, ParseException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0" +
                        "/" + options.getMarket() +
                        "/" + options.getCurrency() +
                        "/en-US" +
                        "/" + options.getOriginPlace() +
                        "/" + options.getDestinationPlace() +
                        "/anytime?inboundpartialdate=anytime")
                .get()
                .addHeader("x-rapidapi-key", "c0dd1b869fmsh6b647aa42d660f4p1c85b7jsn5d757cf79a06")
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JSONParser parse = new JSONParser();
        JSONObject jsonObject = (JSONObject)parse.parse(responseString);
        System.out.println(responseString);

        return jsonObject;
    }
}
