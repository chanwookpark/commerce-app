var ProductTypeA = React.createClass({
    render: function () {
        return (
            <div className="product-type-a">
                <p>상품명: {this.props.displayName}</p>
            </div>
        );
    }
});