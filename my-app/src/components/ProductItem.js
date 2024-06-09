import React from 'react';
import { Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const ProductItem = (props) => {
  const { id, productName } = props.product; // id, productName, productCompany
  return (
    <Card>
      <Card.Body>
        <Card.Title>{productName}</Card.Title>
        <Link to={'/product/' + id} className="btn btn-primary">
          상세보기
        </Link>
      </Card.Body>
    </Card>
  );
};

export default ProductItem;
