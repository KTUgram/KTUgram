module.exports = [
  {
    changeOrigin: false,
    context: ['/**', '!/app/', '!/sockjs-node/**', '!/assets/**'],
    logLevel: 'debug',
    target: 'http://localhost:8080/',
  },
]

//        target: 'http://10.10.64.21:8000/',


//    target: 'http://ltmps-test.elsis.lt:8000/',
