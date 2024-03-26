import axios from "axios";

const IMG_PATH = "https://image.tmdb.org/t/p/original";
const options = {
  method: "GET",
  headers: {
    accept: "application/json",
    Authorization:
      "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0NDFmN2JmMDgwOWMxZGFlNTViYzgyMTkzNDcwMTQwMiIsInN1YiI6IjY0Njk2MzUwYTUwNDZlMDBlNWI2NjBkMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.mSjmEwZcJ3-dRS1DX6Y_l3bh29btb6B0BHCirvAeumU",
  },
};

export function getMoviesNowPlaying() {
  // return fetch('https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1', options)
  // .then(response => response.json());
  return axios
    .get(
      "https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1",
      {
        headers: {
          accept: "application/json",
          Authorization:
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0NDFmN2JmMDgwOWMxZGFlNTViYzgyMTkzNDcwMTQwMiIsInN1YiI6IjY0Njk2MzUwYTUwNDZlMDBlNWI2NjBkMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.mSjmEwZcJ3-dRS1DX6Y_l3bh29btb6B0BHCirvAeumU",
        },
      }
    )
    .then((response) => response.data);
}

export function getMoviesPopular() {
  return fetch(
    "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1",
    options
  ).then((response) => response.json());
}

export function getMoviesTopRated() {
  return fetch(
    "https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1",
    options
  ).then((response) => response.json());
}

export function getMoviesUpcoming() {
  return fetch(
    "https://api.themoviedb.org/3/movie/upcoming?language=en-US&page=1",
    options
  ).then((response) => response.json());
}

export const genreList = [
  { name: "Action", id: 28 },
  { name: "Adventure", id: 12 },
  { name: "Animation", id: 16 },
  { name: "Comedy", id: 35 },
  { name: "Crime", id: 80 },
  { name: "Documentary", id: 99 },
  { name: "Drama", id: 18 },
  { name: "Family", id: 10751 },
  { name: "Fantasy", id: 14 },
  { name: "History", id: 36 },
  { name: "Horror", id: 27 },
  { name: "Music", id: 10402 },
  { name: "Mystery", id: 9648 },
  { name: "Romance", id: 10749 },
  { name: "Science Fiction", id: 878 },
  { name: "TV Movie", id: 10770 },
  { name: "Thriller", id: 53 },
  { name: "War", id: 10752 },
  { name: "Western", id: 37 },
];

export function getBackDropUrl(url) {
  return IMG_PATH + url;
}

export function getMovieDetailById(id) {
  return fetch(
    `https://api.themoviedb.org/3/movie/${id}?language=en-US`,
    options
  ).then((response) => response.json());
}

export function getMovieCastById(id) {
  return fetch(
    `https://api.themoviedb.org/3/movie/${id}/credits?language=en-US`,
    options
  ).then((response) => response.json());
}

export function searchKeywordMovies(keyword) {
  if (!keyword) {
    return;
  }
  return fetch(
    `https://api.themoviedb.org/3/search/movie?query=${keyword}`,
    options
  ).then((response) => response.json());
}
