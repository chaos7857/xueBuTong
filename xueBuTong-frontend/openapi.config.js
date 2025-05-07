import { generateService } from '@umijs/openapi'

generateService({
  requestLibPath: "import request from '@/request'",
  schemaPath: 'http://124.222.189.155/api/v2/api-docs',
  serversPath: './src',
})
