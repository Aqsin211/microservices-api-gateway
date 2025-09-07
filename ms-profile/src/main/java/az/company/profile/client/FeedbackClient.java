package az.company.profile.client;

import az.company.profile.client.decoder.CustomErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ms-feedback",
        url = "http://ms-feedback:8082/feedback",
        configuration = {CustomErrorDecoder.class}
)
public interface FeedbackClient {
    @DeleteMapping("/profile/{profileId}")
    ResponseEntity<String> deleteByProfileId(@PathVariable Long profileId);
}
