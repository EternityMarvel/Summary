                                                     使用post对Json的传递与接受
/**
 * @Description:Json请求方
 * @Date: 2017/2/23 11:41
 * @Author: Mr.m
 */
//使用HttpPost请求传递Json数据
public ResponseEntity<Void> sendJSON() throws IOException {
    try {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost("--填写目的url--");
        //设置post请求的content-type为json
        post.addHeader(HTTP.CONTENT_TYPE, "application/json");
        String username = "root";
        String password = "root";
        //创建Json格式的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //传递时键一定要用引号括起来！
        stringBuilder.append("{\"username\":").append(username)
                     .append(",\"password\":\"").append(password).append("\"}");
        StringEntity entity = new StringEntity(URLEncoder.encode(stringBuilder.toString(),"UTF-8"));
        
        entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
        entity.setContentType("text/json");
        post.setEntity(entity);
        //发送post请求
        HttpResponse httpResponse = httpClient.execute(post);
        
        int status = httpResponse.getStatusLine().getStatusCode();
        if(status==HttpStatus.SC_OK){
            return ResponseEntity.ok(null);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(null);
}

/**
 * @Description:创建实体类
 * @Date: 2017/2/23 11:49
 * @Author: Mr.m
 */
public Class User{
  private String username;
  private String password;
  
  ...//setter、getter
  
} 

/**
 * @Description:Json接收方
 * @Date: 2017/2/23 11:50
 * @Author: Mr.m
 */
@RequestMapping(method = RequestMethod.POST)
public ResponseEntity<Void> receiveJSON(HttpServletRequest request){
    try {
        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        // 将数据解码
        String data = URLDecoder.decode(sb.toString();, HTTP.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        //读取User数据
        User user = objectMapper.readValue(data,User.class);
        return ResponseEntity.ok(null);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(null);
    }
}

