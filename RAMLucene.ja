public RAMDirectory createDirectory(){
  //创建一个内存目录对象
  RAMDirectoty directory = new RAMDirectory();
  //创建写入配置,Analyser为分词器,此处使用对中文支持较好的IKAnalyser. 在3.5版本的构造器还需要填入版本信息
  IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_35,new IKAnalyzer());
  //创建索引写入对象
  IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
  
  //准备写入的内容 从数据库中获取数据
  List<IndexGoods> indexGoodsList = indexGoodsService.selectAll();
  for(IndexGoods indexGoods:indexGoodsList){
      //创建文档对象,里面维护一个List集合,用于存储实现Fieldable接口的域对象
      Document doc = new Document();
      String id = indexGoods.getId();
      String key = indexGoods.getTitle()+" "+indexGoods.getDescription()+" "+indexGoods.getType()+" "+indexGoods.getTypeDet();
      //id属性不需要进行检索
      doc.add(new Field("id",indexGoods.getId()+"", Field.Store.YES,Field.Index.NOT_ANALYZED));
      //content需要进行检索
      doc.add(new Field("content",key, Field.Store.YES,Field.Index.ANALYZED));
      //将索引写入目录
      indexWriter.addDocument(doc);
   }
   //将写入对象关闭
   indexWriter.close();
   return directory;
}

----------------------------------以上准备工作做完后,就可以从目录中检索了----------------------------------

@RequestMapping(value = "/search",method = RequestMethod.GET)
public ResponseEntity<QueryResult<Goods>> queryGoodsBySearch(String keyword){
    try {
        //由于是Get请求,需要对url参数进行解码、编码
        keyword = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
        //Controller中维护一个静态的Map<String,List<Integer>>作为缓存
        List<Integer> idList = goodsIdMap.get(keyword);
        //如果缓存没有再检索
        if(idList == null){
            //Controller中维护一个静态的目录,若目录不存在再创建
            if(directory==null){
                directory = makeDir();
            }
            // 创建索引读取对象
            IndexReader indexReader = IndexReader.open(directory);
            // 创建IndexSearcher检索索引的对象
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            indexSearcher.setSimilarity(new IKSimilarity());
            // 根据搜索关键字 然后封装成Query查询对象
            Query query = IKQueryParser.parse("content",keyword);
            // 去索引目录中查询，返回的是TopDocs对象，里面存放的就是上面放的Document文档对象
            TopDocs topDocs = indexSearcher.search(query,1000);
            List<Integer> ids = new ArrayList<Integer>();
            if(topDocs.scoreDocs.length>0){
                for(int i = 0;i<topDocs.scoreDocs.length;i++){
                    Document firstHit = indexSearcher.doc(topDocs.scoreDocs[i].doc);
                    //将检索到的content对应的id存入List集合中
                    ids.add(Integer.parseInt(firstHit.get("id")));
                }
            }
            goodsIdMap.put(keyword,ids);
            idList = ids;
        }
        // 检索的结果集个数大于0
        if(idList.size()>0){
            /**
              * 按照检索到的id去数据库中查找并返回结果
              */
            return ResponseEntity.ok(--result--);
        }
        return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(null);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ResponseEntity.status(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR).body(null);
}
