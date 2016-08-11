// Component
var SearchBox = React.createClass({
    render: function () {
        return (
            <div>
                Search, Box!!
            </div>
        );
    }
});

// Rendering
ReactDOM.render(
    <SearchBox />,
    document.getElementById('search')
);