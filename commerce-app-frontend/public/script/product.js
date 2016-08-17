var ProductTypeA = React.createClass({
    render: function () {
        return (
            <div class="product-type-a">
                <p>상품명: {this.props.displayName}</p>
            </div>
        );
    }
});