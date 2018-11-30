package friendgoods.vidic.com.generalframework.util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/21.
 */
public class JsonUtil {

    public static String getString(String field, String json) {

        try {
            JSONObject obj = new JSONObject(json);
            String imag = obj.getString(field);
            return imag;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return field;
    }
}
