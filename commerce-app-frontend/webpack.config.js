module.exports = {
    entry: __dirname + '/src/webpack_entry.js',
    output: {
        path: __dirname + '/public/script',
        filename: 'components.js'
    },
    /*devServer는 사용하지 않고 테스트*/
    /*
    devServer: {
        inline: true,
        port: 4000,
        contentBase: __dirname + '/public/'
    },
    */
    module: {
        loaders: [
            {
                test: /\.js$/,
                loader: 'babel',
                exclude: /node_modules/,
                query: {
                    cacheDirectory: true,
                    presets: ['es2015', 'react']
                }
            }
        ]
    }
};