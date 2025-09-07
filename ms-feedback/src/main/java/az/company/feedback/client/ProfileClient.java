package az.company.feedback.client;

import az.company.feedback.client.decoder.CustomErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ms-profile",
        url = "http://ms-profile:8081/profiles",
        configuration = {CustomErrorDecoder.class}
)
public interface ProfileClient {
    @GetMapping("/validate/{id}")
    Boolean validateProfile(@PathVariable Long id);
}
