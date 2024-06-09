import React, { useState, useEffect } from 'react';
import { Button, Form } from 'react-bootstrap';
import { useNavigate, useParams } from 'react-router-dom';

const UpdateForm = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [product, setProduct] = useState({
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

  const changeValue = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const submitProduct = (e) => {
    e.preventDefault();
    fetch('http://localhost:8081/api/product/' + id, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify(product),
    }).then((res) => {
      if (res.status === 200) {
        navigate('/');
      } else {
        alert('상품 수정에 실패하였습니다.');
      }
    });
  };

  return (
    <Form onSubmit={submitProduct}>
      <Form.Group className="mb-3">
        <Form.Label>productName</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter productName"
          name="productName"
          value={product.productName}
          onChange={changeValue}
        />
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>productCompany</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter productCompany"
          name="productCompany"
          value={product.productCompany}
          onChange={changeValue}
        />
      </Form.Group>
      <Button type="submit" className="btn btn-primary">
        수정
      </Button>
    </Form>
  );
};

export default UpdateForm;
