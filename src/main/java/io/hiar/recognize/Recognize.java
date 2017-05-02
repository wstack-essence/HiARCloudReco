package io.hiar.recognize;

import io.hiar.config.CloudConfig;
import io.hiar.signature.Signature;
import io.hiar.utils.Utils;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author lerry
 * @Datetime 02/05/2017 16:42
 */
public class Recognize
{

    public Recognize()
    {
    }

    public RecognizeResult recognize(String filePath, String collection_ids, int number) throws IOException
    {
        File file = new File(filePath);
        try {
            return recognize(file,collection_ids, number);
        } catch (IOException e) {
            throw e;
        }
    }

    public RecognizeResult recognize(File file, String collection_ids, int number ) throws IOException
    {
        String filename = file.getName();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(
                (int) file.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw e;
        }
        return recognize(collection_ids, bos.toByteArray(), filename, number);
    }

    public RecognizeResult recognize(String collection_ids, byte[] image,
                                     String filename, int number)
            throws ParseException, IOException, JSONException
    {
        String timeStamp = Long.toString(new Date().getTime());
        String nonce = Utils.randomString(8);

        String signature = Signature.getSignature(CloudConfig.appkey, CloudConfig.secret, timeStamp, nonce);

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(CloudConfig.RecognizeAPI);

        // 识别图片
        MultipartEntity requestEntity = new MultipartEntity();
        ContentBody cb = new ByteArrayBody(image, filename);
        requestEntity.addPart("image", cb);
        // 设置识别参数，拼接成key=value的字符串
        StringBuilder params = new StringBuilder();
        params.append("collectionIds").append("=").append(collection_ids).append("&");
        params.append("number").append("=").append(number).append("&");
        params.append("appKey").append("=").append(CloudConfig.appkey).append("&");
        params.append("timestamp").append("=").append(timeStamp).append("&");
        params.append("nonce").append("=").append(nonce).append("&");

        String data = Utils.base64(params.toString());
        StringBody sBody = StringBody.create(data, HTTP.PLAIN_TEXT_TYPE, Charset.forName(HTTP.UTF_8));
        requestEntity.addPart("data", sBody);
        httpPost.setEntity(requestEntity);
        //header 中设置token
        httpPost.addHeader("HiARAuthorization", signature);

        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String result = EntityUtils.toString(responseEntity);
            System.out.println(result);
            JSONObject resJson = JSONObject.fromObject(result);
            return parseResponse(resJson);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            throw e;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            throw e;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw e;
        } catch (JSONException e) {
            throw e;
        }

    }


    /**
     * 转换识别返回结果的json
     */
    private RecognizeResult parseResponse(JSONObject resJson) {
        RecognizeResult recoResult = new RecognizeResult();
        int retCode = resJson.getInt("retCode");
        if (retCode != 0) {
            recoResult.setRetCode(retCode);
            recoResult.setComment(resJson.getString("comment"));
            return recoResult;
        } else {
            if (resJson.containsKey("items"))
            {
                JSONArray items = resJson.getJSONArray("items");
                List<Target> list = new ArrayList<Target>();
                for (int i = 0; i < items.size(); i++)
                {
                    Target tag = new Target();
                    JSONObject obj = items.getJSONObject(i);
                    tag.setName(obj.getString("name"));
                    tag.setTargetID(obj.getString("targetID"));
                    tag.setImageURL(obj.getString("imageURL"));
                    list.add(tag);
                }
            }
        }
        return recoResult;
    }
}
