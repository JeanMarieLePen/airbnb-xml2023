const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    allowedHosts: [
      'frontmainapp-mozartkaraokeversion.b4a.run',
      'mainapp-nbaj.onrender.com'
    ], 
    client:{
      webSocketURL: 'auto://0.0.0.0:0/ws'
    }
  }
})
