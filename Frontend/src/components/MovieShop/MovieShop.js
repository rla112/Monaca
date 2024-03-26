import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Navbar } from "./Navbar";
import { Home } from "./Home";
import { Products } from "./Products";
import { SingleProduct } from "./SingleProduct";
import { Dashboard } from "./Dashboard";
import { Login } from "./Login";
import { Cart } from "./Cart";
import { Error } from "./Error";
import { ProductsWrapper } from "./ProductsWrapper";
import { ProtectedRoute } from "./ProtectedRoute";
import { createContext, useState } from "react";
import styled from "styled-components";
import { Search } from "./Search";

const Container = styled.div`
  width: 100vw;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const MovieContext = createContext();

export function MovieShop() {
  const [catIndex, setCatIndex] = useState(0);
  return (
    <>
      <MovieContext.Provider value={{ catIndex, setCatIndex }}>
        <BrowserRouter>
          <Container>
            <div>
              <Routes>
                <Route path="/" element={<Navbar />}>
                  <Route index element={<Home />}></Route>
                  <Route path="home" element={<Home />}></Route>
                  <Route path="products" element={<ProductsWrapper />}>
                    <Route index element={<Products />}></Route>
                    <Route path=":id" element={<SingleProduct />}></Route>
                  </Route>
                  <Route
                    path="dashboard"
                    element={
                      <ProtectedRoute>
                        <Dashboard />
                      </ProtectedRoute>
                    }
                  ></Route>
                  <Route path="login" element={<Login />}></Route>
                  <Route path="cart" element={<Cart />}></Route>
                  <Route path="search" element={<Search />}></Route>
                  <Route path="*" element={<Error />}></Route>
                </Route>
              </Routes>
            </div>
          </Container>
        </BrowserRouter>
      </MovieContext.Provider>
    </>
  );
}
