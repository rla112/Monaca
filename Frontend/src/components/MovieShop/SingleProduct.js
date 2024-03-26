import { Link, useNavigate, useParams } from "react-router-dom";
import styled from "styled-components";
import { getMovieDetailById, getMovieCastById, getBackDropUrl } from "./api";
import { useState, useEffect } from "react";

const Container = styled.div`
  width: 900px;
  display: flex;
  flex-direction: column;
`;
const Header = styled.div`
  margin: 20px;
  color: dodgerblue;
  text-align: center;
  position: relative;
`;
const Back = styled.div`
  position: absolute;
  top: 20px;
  right: 0;
`;
const Img = styled.img`
  width: 100%;
`;
const Content = styled.div`
  font-size: 0.9rem;
  line-height: 30px;
`;
export function SingleProduct() {
  const { id } = useParams();
  const [detail, setDetail] = useState(null);
  const [cast, setCast] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    getMovieDetail().then((data) => setDetail(data));
    getMovieCast().then((data) => setCast(data));
  }, []);

  async function getMovieDetail() {
    const data = await getMovieDetailById(id);
    return data;
  }
  async function getMovieCast() {
    const data = await getMovieCastById(id);
    return data;
  }

  function displayDirector() {
    const director = cast.crew.find((c) => c.job === "Director");
    return director.name;
  }

  function displayCast() {
    let peoples = "";
    if (cast.cast.length > 5) {
      for (let i = 0; i < 5; i++) {
        peoples = peoples + " " + cast.cast[i].name + " /";
      }
    } else {
      for (let i = 0; i < cast.cast.length; i++) {
        peoples = peoples + " " + cast.cast[i].name + " /";
      }
    }
    return peoples;
  }

  return (
    <>
      <Container>
        {!detail || !cast ? (
          <h1>Loading...</h1>
        ) : (
          <>
            <Header>
              <h1>{detail.title}</h1>
              {/*<Back><Link to="/products">BACK</Link></Back>*/}
              <Back>
                <button onClick={() => navigate(-1)}>BACK</button>
              </Back>
            </Header>
            <Img src={getBackDropUrl(detail.backdrop_path)}></Img>
            <Content>
              <p>타이틀 : {detail.title}</p>
              <p>장르 : {detail.genres.map((d) => d.name + " / ")}</p>
              <p>개봉일 : {detail.release_date}</p>
              <p>상영시간 : {detail.runtime}분</p>
              <p>감독 : {displayDirector()}</p>
              <p>배우 : {displayCast()}</p>
              {detail.homepage ? (
                <a href={detail.homepage}>{detail.homepage}</a>
              ) : null}
              <p>{detail.overview}</p>
              <br />
              <Link to="/products">목록으로 돌아가기</Link>
              <br />
              <br />
              <br />
            </Content>
          </>
        )}
      </Container>
    </>
  );
}
