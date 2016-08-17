/** webpack을 적용하지 않은 버전 */
var SearchBox = React.createClass({
    getInitialState: function () {
        return {data: {searchProduct: []}};
    },
    searchProductByServer: function (keyword) {
        var url = 'http://localhost:8000/api/search';
        $.ajax({
                url: url,
                dataType: 'json',
                cache: false,
                data: {keyword: keyword},
                success: function (data) {
                    console.log("검색어: " + keyword);
                    this.setState({data: data});
                }.bind(this),
                error: function (xhr, status, err) {
                    console.error(url, status, err.toString());
                    alert("상품 검색이 실패했습니다..다시 해보시겠어요?")
                }
            }
        );
    },
    componentDidMount: function () {
        var keyword = Commerce.util.getParameterByName('keyword');
        this.searchProductByServer(keyword);
    },
    render: function () {
        var keyword = Commerce.util.getParameterByName('keyword');
        return (
            <div className="searchBox">
                <h1>검색!</h1>
                <SearchForm searchProductByServer={this.searchProductByServer} keyword={keyword}/>
                <SearchCriteria data={this.state.data.criteria}/>
                <SearchProductList data={this.state.data.searchProduct}/>
            </div>
        );
    }
});

var SearchForm = React.createClass({
    getInitialState: function () {
        return {keyword: ''};
    },
    handleSubmit: function (e) {
        e.preventDefault();
        var keyword = this.state.keyword.trim();
        this.props.searchProductByServer(keyword);
    },
    handleChange: function (event) {
        this.setState({keyword: event.target.value});
    },
    render: function () {
        // this.state.keyword = this.props.keyword; // 갈아치기??
        return (
            <form className="searchForm" onSubmit={this.handleSubmit}>
                <select>
                    <option value="C1">전체</option>
                    <option value="C2">전자/TV</option>
                </select>
                <input type="text"
                       value={this.state.keyword}
                       onChange={this.handleChange}
                       placeholder="검색어를 입력하세요!"
                />
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
                <ProductTypeA key={product.productId} displayName={product.displayName}/>
            );
        });
        return (
            <div className="searchProductList">
                {searchProduct}
            </div>
        );
    }
});

//TODO webpack이나 browerify 등을 적용해 컴포넌트 파일을 분리해 관리하도록 개선
var ProductTypeA = React.createClass({
    render: function () {
        return (
            <div className="product-type-a">
                <p>상품명: {this.props.displayName}</p>
            </div>
        );
    }
});

// Rendering
ReactDOM.render(
    <SearchBox />,
    document.getElementById('content')
);