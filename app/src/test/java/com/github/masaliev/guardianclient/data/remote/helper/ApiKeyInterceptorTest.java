package com.github.masaliev.guardianclient.data.remote.helper;

import org.junit.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApiKeyInterceptorTest {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void intercept() throws IOException{
        //Given
        ApiKeyInterceptor interceptor = new ApiKeyInterceptor("some-key");

        Request request = mock(Request.class);
        Request newRequest = mock(Request.class);
        HttpUrl url = mock(HttpUrl.class);
        HttpUrl.Builder urlBuilder = mock(HttpUrl.Builder.class);
        Interceptor.Chain chain = mock(Interceptor.Chain.class);
        Request.Builder requestBuilder = mock(Request.Builder.class);


        when(urlBuilder.build())
                .thenReturn(url);
        when(urlBuilder.addQueryParameter(anyString(), anyString()))
                .thenReturn(urlBuilder);
        when(url.newBuilder())
                .thenReturn(urlBuilder);

        when(requestBuilder.url(any(HttpUrl.class)))
                .thenReturn(requestBuilder);
        when(requestBuilder.build())
                .thenReturn(newRequest);

        when(request.url())
                .thenReturn(url);
        when(request.newBuilder())
                .thenReturn(requestBuilder);
        when(chain.request())
                .thenReturn(request);

        //When
        interceptor.intercept(chain);

        //Then
        verify(chain, times(2)).request();
        verify(request).url();
        verify(url).newBuilder();
        verify(urlBuilder).addQueryParameter("api-key", "some-key");
        verify(urlBuilder).build();

        verify(request).newBuilder();
        verify(requestBuilder).url(url);
        verify(requestBuilder).build();
        verify(chain).proceed(newRequest);

    }
}