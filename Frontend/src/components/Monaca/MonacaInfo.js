import styled from "styled-components";
import CTextLogo from "./image/CTextLogo.jpg";

const Container = styled.div`
  width: 100vw;
  height: 30vh;
  background-color: #303740;
  margin-top: 10vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Logo = styled.div`
  background-image: url(${CTextLogo});
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  width: 15%;
  height: 20%;
`;

const InfoWrap = styled.div`
  width: 65%;
  height: 80%;
  align-items: center;
  justify-content: center;
  display: flex;
`;

const Sec1 = styled.div`
  height: 100%;
  width: 60%;
  display: flex;
  flex-direction: column;
  justify-content: center;

  & h2 {
    color: #a2a3a5;
    font-size: smaller;
  }
`;

const FirstIf = styled.div`
  display: flex;
  align-items: center;
  height: 30%;
  width: 100%;
`;

const SecIf = styled.div`
  line-height: 1.3rem;
`;

const Sec2 = styled.div`
  height: 100%;
  width: 40%;
  display: flex;
  margin-left: auto;
  align-items: center;
  justify-content: center;
`;

const Icon = styled.div`
  height: 100%;
  align-items: center;
  justify-content: center;
  display: flex;
  & i {
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.5rem;
    margin: 1rem;
    background-color: gainsboro;
    border-radius: 100%;
    width: 40px;
    height: 40px;
  }
`;
export function MonacaInfo() {
  return (
    <>
      <Container>
        <InfoWrap>
          <Sec1>
            <FirstIf>
              <Logo></Logo>
              <h2> | 개인정보처리방침 | 이용약관 </h2>
            </FirstIf>
            <SecIf>
              <h2>
                &#40;주&#41;모나카 | 설립자: 김선욱, 김예은, 염혜정 |
                사업자번호:305-90-45602 사업자 정보 확인
              </h2>
              <h2>통신판매업: 2022-서울마포-3633 | 이메일: dw@gmail.com</h2>
              <h2>
                전화번호: 042-222-2402 | 주소: 대전광역시 중구 중앙로121번길 20
              </h2>
            </SecIf>
          </Sec1>
          <Sec2>
            <Icon>
              <i className="ti ti-brand-instagram" title="MONACA_123"></i>
              <i className="ti ti-brand-youtube-filled" title="MONACA"></i>
              <i className="ti ti-brand-facebook-filled" title="MONACA123"></i>
              <i className="ti ti-brand-twitter-filled" title="MoNaCa123"></i>
              <i className="ti ti-letter-n" title="MONACA"></i>
              <i className="ti ti-letter-b" title="모나카밴드"></i>
            </Icon>
          </Sec2>
        </InfoWrap>
      </Container>
    </>
  );
}
