package commerce.dto;

import commerce.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chanwook
 */
@AllArgsConstructor
@Getter
@Setter
public class MemberSignUpResponse {

    private boolean result;

    private Member member;

    private String userMessage;
    private String systemMessage;

    public MemberSignUpResponse(boolean result, Member member) {
        this.result = result;
        this.member = member;
    }

    public void handleError(String userMessage, String systemMessage) {
        this.setResult(false);
        this.setUserMessage(userMessage);
        this.setSystemMessage(systemMessage);
    }
}
