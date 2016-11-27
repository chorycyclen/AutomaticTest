package com.cook.selenium.download;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class FileDownloader {

    private RequestType httpRequestMethod = RequestType.GET;
    private URI fileURI;
    List<NameValuePair> urlParameters;

    public void setHttpRequestMethod(RequestType httpRequestMethod) {
        this.httpRequestMethod = httpRequestMethod;
    }

    public void setFileURI(URI fileURI) {
        this.fileURI = fileURI;
    }

    public void setUrlParameters(List<NameValuePair> urlParameters) {
        this.urlParameters = urlParameters;
    }

    private HttpResponse makeHttpConnection() throws IOException,
            NullPointerException {
        if (fileURI == null) {
            throw new NullPointerException("No file URI specified!");
        }
        HttpClient client = HttpClientBuilder.create().build();

        HttpRequestBase requestMethod = httpRequestMethod.getRequestMethod();
        requestMethod.setURI(this.fileURI);

        BasicHttpContext localContext = new BasicHttpContext();

        if (null != urlParameters && (
                httpRequestMethod.equals(RequestType.PATCH) ||
                        httpRequestMethod.equals(RequestType.POST) ||
                        httpRequestMethod.equals(RequestType.PUT)
        )) {
            ((HttpEntityEnclosingRequestBase) requestMethod)
                    .setEntity(new UrlEncodedFormEntity(urlParameters));
        }
        return client.execute(requestMethod, localContext);
    }

    public int getLinkHttpStatus() throws Exception {
        HttpResponse downloadableFile = makeHttpConnection();
        int httpStatusCode;
        try {
            httpStatusCode = downloadableFile.getStatusLine().getStatusCode();
        } finally {
            if (null != downloadableFile.getEntity()) {
                downloadableFile.getEntity().getContent().close();
            }
        }
        return httpStatusCode;
    }
}
