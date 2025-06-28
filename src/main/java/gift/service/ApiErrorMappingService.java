package gift.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ApiErrorMappingService {

    public String mapApiErrorToUserMessage(HttpStatus status) {
        if (status == HttpStatus.BAD_REQUEST) {
            return "입력 데이터가 유효하지 않습니다. 이름을 채우고, 가격은 양수, URL 형식을 확인하세요.";
        } else if (status == HttpStatus.CONFLICT) {
            return "이미 존재하는 ID입니다. 다른 ID를 사용해 주세요.";
        } else if (status == HttpStatus.NOT_FOUND) {
            return "요청한 리소스를 찾을 수 없습니다.";
        } else {
            return "API 서버에서 오류가 발생했습니다. (상태 코드: " + status.value() + ")";
        }
    }

    public String getErrorMessageForResourceAccess() {
        return "API 서버에 연결할 수 없습니다. 서버가 실행 중인지 확인하세요.";
    }

    public String getErrorMessageForNotFoundProduct() {
        return "수정할 상품을 찾을 수 없습니다.";
    }

    public String getGenericErrorMessage() {
        return "알 수 없는 오류가 발생했습니다. 다시 시도해주세요.";
    }

    public String getErrorMessageForHttpClientError(HttpClientErrorException e) {
        return mapApiErrorToUserMessage((HttpStatus) e.getStatusCode());
    }
}
