import React, { useState, useEffect } from 'react';
import ProductItem from '../../components/ProductItem';

const Home = () => {
  const [products, setProducts] = useState([]);
  // fetch().then().then().catch();
  useEffect(() => {
    fetch('http://localhost:8081/api/product')
      .then((res) => res.json())
      .then((res) => {
        console.log(1, res);
        setProducts(res);
      }); // 비동기 함수
  }, []);

  return (
    <div>
      {products.map((product) => (
        <ProductItem key={product.id} product={product} />
      ))}
    </div>
  );
};

export default Home;
