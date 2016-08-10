// Component
var SampleBox = React.createClass({
    render: function () {
        return (
            <div>
                React, Ready!!
            </div>
        );
    }
});

// Rendering
ReactDOM.render(
    <SampleBox />,
    document.getElementById('content')
);