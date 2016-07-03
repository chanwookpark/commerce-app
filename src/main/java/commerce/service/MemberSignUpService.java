package commerce.service;

import commerce.dto.MemberSignUpResponse;
import commerce.entity.Member;
import commerce.repository.MemberJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chanwook
 */
@Service
public class MemberSignUpService {

    private final Logger logger = LoggerFactory.getLogger(MemberSignUpService.class);

    @Autowired
    MemberJpaRepository mr;

    @Transactional
    public MemberSignUpResponse signUp(Member newMember) {
        final MemberSignUpResponse response = new MemberSignUpResponse(false, newMember);

        try {
            boolean validated = validateSignUpRequest(newMember, response);

            if (validated) {
                newMember.setMemberStatus(Member.MemberStatus.H); //가입승인대기
                mr.save(newMember);

                response.setResult(true);
            }
        } catch (Exception e) {
            logger.error("회원 가입 중 에러 발생!", e);
            response.handleError("시스템 에러가 발생했습니다. 다시 가입을 시도하거나 관리자에게 문의해주세요!", e.getMessage());
        } finally {

        }

        return response;
    }

    private boolean validateSignUpRequest(Member newMember, MemberSignUpResponse response) {
        if (Member.MemberType.P.equals(newMember.getMemberType()) && newMember.getAffiliated() != null) {
            response.setUserMessage("개인 회원의 경우 소속 회사를 선택하시면 안 됩니다!");
            return false;
        }

        if (Member.MemberType.E.equals(newMember.getMemberType()) && newMember.getAffiliated() == null) {
            response.setUserMessage("기업 회원의 경우 반드시 소속 회사를 선택해야 합니다!");
            return false;
        }
        return true;
    }
}
