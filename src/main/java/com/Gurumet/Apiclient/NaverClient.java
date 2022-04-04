package com.Gurumet.Apiclient;


import com.Gurumet.Apiclient.dto.SearchImageReq;
import com.Gurumet.Apiclient.dto.SearchImageRes;
import com.Gurumet.Apiclient.dto.SearchLocalReq;
import com.Gurumet.Apiclient.dto.SearchLocalRes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;


    // TODO: API를 통하여 검색 결과 반환 (GET)
    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq) {
        //TODO: URI 지정

        URI uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        // 기존 방식
//        RequestEntity<Void> req = RequestEntity
//                .get(uri)
//                .header("X-Naver-Client-Id", "You're X-Naver-Client-Id")
//                .header("X-Naver-Client-Secret", "You're X-Naver-Client-Secret")
//                .build();

        // TODO: 새로운 방식 설정
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);


        var responseType = new ParameterizedTypeReference<SearchLocalRes>() {
        };

        var responseEntity = new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, responseType);

        return responseEntity.getBody();
    }
    // TODO: 검색 이미지 결과 출력
    public SearchImageRes searchImage(SearchImageReq searchImageReq){

        URI uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

//
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);


        var responseType = new ParameterizedTypeReference<
                SearchImageRes>() {
        };

        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType);

        return responseEntity.getBody();
    }
}
