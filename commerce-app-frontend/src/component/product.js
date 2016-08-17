import React from 'react';

class ProductTypeA extends React.Component {
    render(){
        return (
            <div className="product-type-a">
                <p>상품명: {this.props.displayName}</p>
            </div>
        );
    }
}
export default ProductTypeA;