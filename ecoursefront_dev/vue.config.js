const path = require('path')

const resolve = dir => {
  return path.join(__dirname, dir)
}

module.exports = {
  chainWebpack: config => {
    config.resolve.alias
      .set('@', resolve('src'))
  },
  // devServer:{
  //   proxy:{
  //     '/api':{
  //       target:'http://localhost:8081',
  //       pathRewrite:{'^api':''},
  //       "secure":false
  //     }
  //   }
  // }
}
