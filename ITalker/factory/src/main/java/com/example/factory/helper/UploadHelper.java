package com.example.factory.helper;

import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.example.factory.Factory;

/**
 * Created by douliu on 2017/6/8.
 */

public class UploadHelper {

    private static final String ENDPOINT = "http://oss-cn-shanghai.aliyuncs.com";
    private static final String ACCESSKEY_ID = "LTAIMJuvY6VC4UY1";
    private static final String ACCESSKEY_SECRET = "hQek4RqQEsCm9i8Py8y1hGeT2yv0Jh";
    private static final String BUCKET_NAME = "talker";

    public static OSSClient getOssClient() {
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(ACCESSKEY_ID, ACCESSKEY_SECRET);
        return new OSSClient(Factory.app(), ENDPOINT, credentialProvider);
    }


    public static String upload(String objectKey,String path) {
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(BUCKET_NAME, objectKey, path);
        try {
            PutObjectResult putResult = getOssClient().putObject(put);

            String url = putResult.getServerCallbackReturnBody();

            Log.d("PutObject", "UploadSuccess");
            Log.d("ETag", putResult.getETag());
            Log.d("RequestId", putResult.getRequestId());

            return url;
        } catch (Exception e) {
            // 本地异常如网络异常等
            e.printStackTrace();
            return null;
        }
    }


    public static String getObjKey() {
        return null;
    }


    public static String uploadImageObj(String objectKey,String path) {
        return null;
    }


}
