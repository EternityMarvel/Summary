 /**
 * @Description:Form发送方
 * @Date: 2017/2/23 13:06
 * @Author: Mr.m
 */
 public ResponseEntity<Void> sendForm(String comCont,String articleId) throws IOException {
        try {
            //使用表单
            List<BasicNameValuePair> formData = new ArrayList<BasicNameValuePair>();
            formData.add(new BasicNameValuePair("username","root"));
            formData.add(new BasicNameValuePair("password",password));
            post.setEntity(new UrlEncodedFormEntity(formData, "utf-8"));

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
 * @Description:User实体类
 * @Date: 2017/2/23 13:08
 * @Author: Mr.m
 */    
public Class User{
  private String username;
  private String password;
  ...
  
  ...//setter、getter方法
}

/**
 * @Description:Form接受方
 * @Date: 2017/2/23 13:09
 * @Author: Mr.m
 */
 @RequestMapping(method = RequestMethod.POST)
 //直接使用实体类接受
public ResponseEntity<Void> receiveForm(User user, HttpServletRequest request){
    try {
        //使用request接受
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return ResponseEntity.ok(null);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(null);
    }
}
