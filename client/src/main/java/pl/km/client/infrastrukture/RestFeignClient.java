package pl.km.client.infrastrukture;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${allegro.url.base}", name = "allegroApi")
public interface RestFeignClient {

    @GetMapping("/sale/categories")
    public String getCategories();
}