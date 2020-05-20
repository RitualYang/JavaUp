package com.test;

import com.frame.dao.UserDao;
import com.frame.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author WTY
 * @Date 2020/5/19 21:21
 */
@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class PageTest {
    @Autowired
    private UserDao userDao;

    /**
     * 根据条件，查询单个对象
     */
    @Test
    public void testSpec() {
        //匿名内部类
        /**
         * 自定义查询条件
         *      1.实现Specification接口（提供泛型：查询的对象类型）
         *      2.实现toPredicate方法（构造查询条件）
         *      3.需要借助方法参数中的两个参数（
         *          root：获取需要查询的对象属性
         *          CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配）
         *       ）
         *  案例：根据用户名称查询，查询用户名为天下的用户
         *          查询条件
         *              1.查询方式
         *                  cb对象
         *              2.比较的属性名称
         *                  root对象
         *
         */
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较的属性
                Path<Object> username = root.get("username");
                //2.构造查询条件  ：    select * from user where username = '天下'
                /**
                 * 第一个参数：需要比较的属性（path对象）
                 * 第二个参数：当前需要比较的取值
                 */
                Predicate predicate = cb.equal(username, "天下");//进行精准的匹配  （比较的属性，比较的属性的取值）
                return predicate;
            }
        };
        User User = userDao.findOne(spec);
        System.out.println(User);
    }

    /**
     * 多条件查询
     *      案例：根据用户名（天下）和用户所属地区查询（天下）
     */
    @Test
    public void testSpec1() {
        /**
         *  root:获取属性
         *      用户名
         *      所属地区
         *  cb：构造查询
         *      1.构造用户名的精准匹配查询
         *      2.构造所属地区的精准匹配查询
         *      3.将以上两个查询联系起来
         */
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> username = root.get("username");//用户名
                Path<Object> address = root.get("address");//所属地区

                //构造查询
                //1.构造用户名的精准匹配查询
                Predicate p1 = cb.equal(username, "天下");//第一个参数，path（属性），第二个参数，属性的取值
                //2..构造所属地区的精准匹配查询
                Predicate p2 = cb.equal(address, "天下");
                //3.将多个查询条件组合到一起：组合（满足条件一并且满足条件二：与关系，满足条件一或满足条件二即可：或关系）
                Predicate and = cb.and(p1, p2);//以与的形式拼接多个查询条件
                // cb.or();//以或的形式拼接多个查询条件
                return and;
            }
        };
        User User = userDao.findOne(spec);
        System.out.println(User);
    }


    /**
     * 案例：完成根据用户名称的模糊匹配，返回用户列表
     *      用户名称以 ’天下‘ 开头
     *
     * equal ：直接的到path对象（属性），然后进行比较即可
     * gt，lt,ge,le,like : 得到path对象，根据path指定比较的参数类型，再去进行比较
     *      指定参数类型：path.as(类型的字节码对象)
     */
    @Test
    public void testSpec3() {
        //构造查询条件
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //查询属性：用户名
                Path<Object> username = root.get("username");
                //查询方式：模糊匹配
                Predicate like = cb.like(username.as(String.class), "天下%");
                return like;
            }
        };
//        List<User> list = userDao.findAll(spec);
//        for (User user : list) {
//            System.out.println(user);
//        }
        //添加排序
        //创建排序对象,需要调用构造方法实例化sort对象
        //第一个参数：排序的顺序（倒序，正序）
        //   Sort.Direction.DESC:倒序
        //   Sort.Direction.ASC ： 升序
        //第二个参数：排序的属性名称
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<User> list = userDao.findAll(spec, sort);
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     * 分页查询
     *      Specification: 查询条件
     *      Pageable：分页参数
     *          分页参数：查询的页码，每页查询的条数
     *          findAll(Specification,Pageable)：带有条件的分页
     *          findAll(Pageable)：没有条件的分页
     *  返回：Page（springDataJpa为我们封装好的pageBean对象，数据列表，共条数）
     */
    @Test
    public void testSpec4() {

        Specification spec = null;
        //PageRequest对象是Pageable接口的实现类
        /**
         * 创建PageRequest的过程中，需要调用他的构造方法传入两个参数
         *      第一个参数：当前查询的页数（从0开始）
         *      第二个参数：每页查询的数量
         */
        Pageable pageable = new PageRequest(0,2);
        //分页查询
        Page<User> page = userDao.findAll(null, pageable);
        System.out.println(page.getContent()); //得到数据集合列表
        System.out.println(page.getTotalElements());//得到总条数
        System.out.println(page.getTotalPages());//得到总页数
    }
}
