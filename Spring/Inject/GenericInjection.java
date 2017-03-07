/**
 * @Description: (Spring4新特性：泛型注入) 
 * @Create: 2017/2/26 9:40
 * @Author: Mr.m
 */
 
 //定义实体类
 public Class User implements Serializable {
  private String username;
  private String password;
  
  ...//setter、getter方法
 }
 public Class Car implements Serializable {
  private String type;
  private String price;
  
  ...//setter、getter方法
 }
 
 -------------------------------------------------------------------
 
 //定义与数据库交互的公共接口
 //Map condition可以用来封装查询条件,例如类型,分页信息等等
public interface CommonMapper<T extends Serializable> {
    //插入数据
    int insert(T entity);

    //删除数据
    int delete(Map<String, Object> condition);

    //更新单条数据
    int updateOne(T entity);

    //查询个数
    int selectCount(Map<String, Object> condition);

    //查询单个
    T selectOne(Map<String,Object> condition);

    //分页页查询
    List<T> selectSome(Map<String, Object> condition);

    //选择所有
    List<T> selectAll();
}

//定义具体ORM
public UserMapper extends CommonMapper<User>{
  //如果有其它交互操作,可以额外定义
}
public CarMapper extends CommonMapper<Car>{
  //如果有其它交互操作,可以额外定义
}

 -------------------------------------------------------------------

//定义公共Service层
public abstract class CommonService<T extends Serializable>{
    @Autowired
    private CommonMapper<T> commonMapper;

    //插入数据
    public int insert(T entity){
       return commonMapper.insert(entity);
    }

    //删除数据
    public int delete(Map<String, Object> condition){
        return commonMapper.delete(condition);
    }

    //更新单条数据
    public int updateOne(T entity){return commonMapper.updateOne(entity);};

    //查询个数
    public int selectCount(Map<String, Object> condition){
        return commonMapper.selectCount(condition);
    }

    //查询单个
    public T selectOne(Map<String,Object> condition){
        return commonMapper.selectOne(condition);
    }

    //分页页查询
    public List<T> selectSome(Map<String, Object> condition){
        return commonMapper.selectSome(condition);
    }
}

//定义具体Service
@Service
public class UserService extends CommonService<User>{
  /**如果有其他额外的操作,可以注入具体的Mapper.
   * @Autowired
   * private UserMapper userMapper;
   */
} 
public class CarService extends CommonService<Car>{
  /**如果有其他额外的操作,可以注入具体的Mapper.
   * @Autowired
   * private CarMapper carMapper;
   */
} 

 -------------------------------------------------------------------

//具体的Controller
@Controller
public Class UserController{
  @Autowired
  private UserService userService;
}
@Controller
public Class CarController{
  @Autowired
  private CarService carService;
}
