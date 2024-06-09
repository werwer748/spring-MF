import React, { useState, useEffect } from 'react';
import { Button } from 'react-bootstrap';
import { useNavigate, useParams } from 'react-router-dom';

const Detail = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [product, setProduct] = useState({
    id: '',
    productName: '',
    productCompany: '',
  });

  useEffect(() => {
    fetch('http://localhost:8081/api/product/' + id)
      .then((res) => res.json())
      .then((res) => {
        setProduct(res);
      });
  }, [id]);

  // 삭제함수 추가
  const deleteProduct = () => {
    fetch('http://localhost:8081/api/product/' + id, { method: 'DELETE' })
      .then((res) => res.text())
      .then((res) => {
        if (res === 'ok') {
          navigate('/');
        } else {
          alert('삭제실패');
        }
      });
  };

  // 삭제함수 추가
  const updateProduct = () => {
    navigate('/updateForm/' + id);
  };

  return (
    <div>
      <h1>상품 상세보기</h1>
      <Button variant="warning" onClick={updateProduct}>
        수정
      </Button>
      &nbsp;
      <Button variant="danger" onClick={deleteProduct}>
        삭제
      </Button>
      <hr />
      <h3>{product.productName}</h3>
      <h1>{product.productCompany}</h1>
    </div>
  );
};

export default Detail;
