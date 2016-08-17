// Component
var SearchBox = React.createClass({
    getInitialState: function () {
        return {data: {searchProduct: []}};
    },
    componentDidMount: function () {
        var url = 'http://localhost:8000/api/search?keyword=마블';
        var keyword = '마블'; //TODO 파라미터 처리

        $.ajax({
                url: url,
                dataType: 'json',
                cache: false,
                // data: {keyword: keyword},
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
                <SearchProductList data={this.state.data.searchProduct}/>
            </div>
        );
    }
});

var SearchForm = React.createClass({
    render: function () {
        return (
            <form className="searchForm" onSubmit="">
                <select>
                    <option value="C1">전체</option>
                    <option value="C2">전자/TV</option>
                </select>
                <input name="searchKeyword" type="text"/>
                <input type="submit" value="검색"/>
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
        var searchProduct = this.props.data.map(function (product) {
            return (
                <ProductTypeA displayName={product.displayName}/>
            );
        });
        return (
            <div className="searchProductList">
                {searchProduct}
            </div>
        );
    }
});

//TODO product.js 파일로 분리 
var ProductTypeA = React.createClass({
    render: function () {
        return (
            <div class="product-type-a">
                <p>상품명: {this.props.displayName}</p>
            </div>
        );
    }
});

// Rendering
ReactDOM.render(
    <SearchBox />,
    document.getElementById('search')
);