module.exports = {
  configureWebpack: {
    devServer: {
      port: 80,
      proxy: {
        '/api': { // '/api'是代理标识，用于告诉node，url前面是/api的就是使用代理的
          target: "http://localhost:9000", //目标地址，一般是指后台服务器地址
          changeOrigin: true, //表示是否改变原域名
          ws: true,//表示WebSocket协议
          pathRewrite: { // pathRewrite 的作用是把实际Request Url中的'/api'用""代替
            '^/api': ""
          }
        }
      }
    }
  },
  publicPath: './', // 打包后静态资源请求根目录
  pages: {
    index: {
      entry: 'src/main.js',
      template: 'public/index.html',
      filename: 'index.html',
      chunks: ['chunk-vendors', 'chunk-common', 'index']
    },
    login: {
      entry: 'src/login/main.js',
      template: 'public/login.html',
      filename: 'login.html',
      chunks: ['chunk-vendors', 'chunk-common', 'login']
    }
  },
  // productionSourceMap: false, // 取消源代码映射（防止源代码暴露在浏览器控制台的source中的top/webpack://目录下）
}