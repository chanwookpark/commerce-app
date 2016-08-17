// Component
var SearchBox = React.createClass({
    getInitialState: function () {
        return {data: []};
    },
    componentDidMount: function () {
        var url = 'http://localhost:8000/search';
        $.ajax({
                url: url,
                dataType: 'json',
                cache: false,
                success: function (data) {
                    this.setState({data: data});
                }.bind(this),
                error: function (xhr, status, err) {
                    console.error(url, status, err.toString());
                    alert("상품 검색이 실패했습니다..다시 해보시겠어요?")
                }
            }
        );
    },
    render: function () {
        return (
            <div className="searchBox">
                <h1>검색!</h1>
                <SearchForm />
                <SearchCriteria data={this.state.data.criteria}/>
                <SearchProductList data={this.state.data.productList}/>
            </div>
        );
    }
});

var SearchForm = React.createClass({
    render: function () {
        return (
            <form className="searchForm" onsubmit="">
                <select>
                    <option value="C1">전체</option>
                    <option value="C2">전자/TV</option>
                </select>
                <input name="searchKeyword" type="text"/>
                <input type="submit"/>
            </form>
        );
    }
});

var SearchCriteria = React.createClass({
    render: function () {
        return (
            <div className="searchCriteria">
                검색조건!
            </div>
        );
    }
});

var SearchProductList = React.createClass({
    render: function () {
        var productList = this.props.data.map(function (product) {
            return (
                <ProductTypeA displayName={product.displayName}/>
            );
        });

        return (
            <div className="searchProductList">
                {productList}
            </div>
        );
    }
});

// Rendering
ReactDOM.render(
    <SearchBox />,
    document.getElementById('search')
);