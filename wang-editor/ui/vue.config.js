module.exports = {
    devServer: {
        host: '127.0.0.1',
        port: 8084,
        open: true,
        proxy: {
            '/api': {
                target: "http://localhost:8888/",
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ""
                }
            }
        }
    }
}
