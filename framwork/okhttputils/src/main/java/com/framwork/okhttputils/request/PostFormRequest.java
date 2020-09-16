package com.framwork.okhttputils.request;

import com.framwork.okhttputils.OkHttpUtils;
import com.framwork.okhttputils.builder.PostFormBuilder;
import com.framwork.okhttputils.callback.Callback;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PostFormRequest extends OkHttpRequest {
    private List<PostFormBuilder.FileInput> files;

    public PostFormRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, List<PostFormBuilder.FileInput> files) {
        super(url, tag, params, headers);
        this.files = files;
    }

    @Override
    protected RequestBody buildRequestBody() {
        if (files == null || files.isEmpty()) {
            //            FormEncodingBuilder builder = new FormEncodingBuilder();
            //            addParams(builder);
            //            return builder.build();
            FormBody.Builder builder = new FormBody.Builder();
            if (params == null || params.isEmpty()) {
                builder.add("1", "1");
            } else {
                for (String key : params.keySet()) {
                    builder.add(key, params.get(key));
                }
            }
            return builder.build();
        } else {
            //            MultipartBuilder builder = new MultipartBuilder()
            //                    .type(MultipartBuilder.FORM);
            //            addParams(builder);
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            if (params != null && !params.isEmpty()) {
                for (String key : params.keySet()) {
                    builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""), RequestBody.create(null, params.get(key)));
                }
            }
            for (int i = 0; i < files.size(); i++) {
                PostFormBuilder.FileInput fileInput = files.get(i);
                RequestBody fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileInput.filename)), fileInput.file);
                builder.addFormDataPart(fileInput.key, fileInput.filename, fileBody);
            }
            //            return builder.build();
            return builder.build();
        }
    }

    @Override
    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
        if (callback == null)
            return requestBody;
        CountingRequestBody countingRequestBody = new CountingRequestBody(requestBody, new CountingRequestBody.Listener() {
            @Override
            public void onRequestProgress(final long bytesWritten, final long contentLength) {

                OkHttpUtils.getInstance().getDelivery().post(new Runnable() {
                    @Override
                    public void run() {
                        callback.inProgress(bytesWritten, contentLength);
                    }
                });

            }
        });
        return countingRequestBody;
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.post(requestBody).build();
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    //    private void addParams(MultipartBuilder builder) {
    //        if(params != null && !params.isEmpty()) {
    //            for(String key : params.keySet()) {
    //                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""), RequestBody.create(null, params.get(key)));
    //            }
    //        }
    //    }

    //    private void addParams(FormEncodingBuilder builder) {
    //        if(params == null || params.isEmpty()) {
    //            builder.add("1", "1");
    //            return;
    //        }
    //
    //        for(String key : params.keySet()) {
    //            builder.add(key, params.get(key));
    //        }
    //    }
}
