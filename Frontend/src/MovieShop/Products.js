import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import {
  getMoviesNowPlaying,
  getMoviesPopular,
  getMoviesTopRated,
  getMoviesUpcoming,
  genreList,
  getBackDropUrl,
} from "./api";
import { useContext, useEffect, useState } from "react";
import { MovieContext } from "./MovieShop";

const Container = styled.div`
  width: 900px;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
`;
const Card = styled.div`
  width: 280px;
  border: 2px solid dodgerblue;
  margin: 10px 0;
  font-size: 0.8rem;
  cursor: pointer;
`;
const Img = styled.img`
  width: 100%;
`;
const Text = styled.p`
  margin-left: 10px;
`;
const Btn = styled.button`
  width: 100px;
  height: 40px;
  text-align: center;
  color: #555;
  background-color: lightcoral;
  border: 0px blue solid;
  cursor: pointer;
  &.active {
    color: white;
    background-color: coral;
  }
`;
const categoryList = [
  { name: "Now Playing", func: getMoviesNowPlaying },
  { name: "Popular", func: getMoviesPopular },
  { name: "Top Rated", func: getMoviesTopRated },
  { name: "Upcoming", func: getMoviesUpcoming },
];
export function Products() {
  const { catIndex, setCatIndex } = useContext(MovieContext);
  const [data, setData] = useState(null);
  const navigate = useNavigate();

  async function getMovies(i) {
    const data = await categoryList[i].func();
    console.log("Movie data : ", data);
    return data;
  }

  useEffect(() => {
    getMovies(0).then((data) => setData(data));
  }, []);

  function onClick(id) {
    navigate(`${id}`); // 상대경로, 현재 URL 뒤에 추가됨
  }
  function getGenre(array) {
    let str = "";
    array.forEach((a) => {
      const temp = genreList.find((g) => g.id === +a);
      str = str + " " + temp.name;
    });
    return str;
  }
  function setCategory(i) {
    setCatIndex(i);
    getMovies(i).then((data) => setData(data));
  }
  return (
    <>
      <h1>Movie List</h1>
      {categoryList.map((cat, i) => {
        if (i === +catIndex) {
          return (
            <Btn key={i} onClick={() => setCategory(i)} className="active">
              {cat.name}
            </Btn>
          );
        } else {
          return (
            <Btn key={i} onClick={() => setCategory(i)}>
              {cat.name}
            </Btn>
          );
        }
      })}
      <Container>
        {data &&
          data.results.map((movie, i) => (
            <Card key={movie.id}>
              <div onClick={(e) => onClick(movie.id)}>
                <Img src={getBackDropUrl(movie.backdrop_path)} />
                <Text>타이틀 : {movie.title}</Text>
                <Text>장르 : {getGenre(movie.genre_ids)}</Text>
                <Text>{movie.overview}</Text>
              </div>
            </Card>
          ))}
      </Container>
    </>
  );
}
